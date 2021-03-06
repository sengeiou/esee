package com.nong.nongo2o.module.dynamic.viewModel;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.Toast;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMClient;
import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.nong.nongo2o.BR;
import com.nong.nongo2o.R;
import com.nong.nongo2o.entities.response.DynamicContent;
import com.nong.nongo2o.entities.response.User;
import com.nong.nongo2o.entity.bean.SimpleUser;
import com.nong.nongo2o.entity.bean.UserInfo;
import com.nong.nongo2o.entity.domain.City;
import com.nong.nongo2o.entity.domain.ImgTextContent;
import com.nong.nongo2o.entity.domain.Moment;
import com.nong.nongo2o.entity.domain.MomentComment;
import com.nong.nongo2o.entity.domain.MomentFavorite;
import com.nong.nongo2o.entity.request.CreateMomentCommentRequest;
import com.nong.nongo2o.entity.request.IdRequest;
import com.nong.nongo2o.module.common.viewModel.ItemCommentListVM;
import com.nong.nongo2o.module.common.viewModel.ItemImageTextVM;
import com.nong.nongo2o.module.dynamic.fragment.DynamicDetailFragment;
import com.nong.nongo2o.module.merchant.activity.MerchantGoodsActivity;
import com.nong.nongo2o.module.message.activity.ChatActivity;
import com.nong.nongo2o.module.personal.activity.PersonalHomeActivity;
import com.nong.nongo2o.network.RetrofitHelper;
import com.nong.nongo2o.network.auxiliary.ApiResponseFunc;
import com.nong.nongo2o.uils.AddressUtils;
import com.nong.nongo2o.uils.BeanUtils;
import com.nong.nongo2o.uils.FocusUtils;
import com.nong.nongo2o.uils.MyTimeUtils;
import com.nong.nongo2o.uils.imUtils.IMUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017-6-30.
 */

public class DynamicDetailVM implements ViewModel {

    private DynamicDetailFragment fragment;
    private Moment dynamic;
    private int pageSize = 999;
    private int total;

    @DrawableRes
    public final int headPlaceHolder = R.mipmap.head_default_60;
    public final ObservableList<DefaultSliderView> sliderList = new ObservableArrayList<>();
    //  作者信息
    public final ObservableField<String> headUri = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> summary = new ObservableField<>();
    @DrawableRes
    public final int isFocus = R.mipmap.icon_focus_p;
    @DrawableRes
    public final int unFocus = R.mipmap.icon_focus;
    //  正文
    public final ObservableField<String> title = new ObservableField<>();
    //  商品链接
    @DrawableRes
    public final int goodsImgPlaceHolder = R.mipmap.picture_default;
    public final ObservableField<String> goodsImgUri = new ObservableField<>();
    public final ObservableField<String> goodsName = new ObservableField<>();
    public final ObservableField<BigDecimal> goodsPrice = new ObservableField<>();
    public final ObservableField<Integer> saleNum = new ObservableField<>();
    public final ObservableField<Integer> stockNum = new ObservableField<>();
    @DrawableRes
    public final int cartPlaceHolder = R.mipmap.add_shoppingcar;
    //  地点、时间
    public final ObservableField<String> city = new ObservableField<>();
    public final ObservableField<String> time = new ObservableField<>();
    @DrawableRes
    public final int btnCommentPlaceHolder = R.mipmap.button_menu;
    @DrawableRes
    public final int isLikePlaceHolder = R.mipmap.icon_dianzan;
    @DrawableRes
    public final int unLikePlaceHolder = R.mipmap.icon_dianzan_grey;
     // 图文列表
    public final ObservableList<ItemImageTextVM> itemImageTextVMs = new ObservableArrayList<>();
    public final ItemBinding<ItemImageTextVM> itemImageTextBinding = ItemBinding.of(BR.viewModel, R.layout.item_image_text);
    //  评论列表
    public final ObservableList<ItemCommentListVM> itemCommentListVMs = new ObservableArrayList<>();
    public final ItemBinding<ItemCommentListVM> itemCommentBinding = ItemBinding.of(BR.viewModel, R.layout.item_comment_list);
    //  评论
    public final ObservableField<String> likeName = new ObservableField<>();
    public final ObservableField<String> editComment = new ObservableField<>();
    public final ObservableField<String> hintComment = new ObservableField<>();

    private MomentComment targetComment = null;

    public DynamicDetailVM(DynamicDetailFragment fragment, Moment dynamic) {
        this.fragment = fragment;
        this.dynamic = dynamic;

        initData();
    }

    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableBoolean isSelf = new ObservableBoolean(false);
        public final ObservableBoolean isFocus = new ObservableBoolean(false);
        public final ObservableBoolean hasGoodsLink = new ObservableBoolean(false);
        public final ObservableBoolean isLike = new ObservableBoolean(false);
        public final ObservableBoolean showLikeList = new ObservableBoolean(false);
        public final ObservableBoolean isRefreshing = new ObservableBoolean(false);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        addSliderView();
        headUri.set(dynamic.getUser().getAvatar());
        name.set(dynamic.getUser().getUserNick());
        summary.set(dynamic.getUser().getProfile());
        viewStyle.isSelf.set(dynamic.getUserCode().equals(UserInfo.getInstance().getUserCode()));
        viewStyle.isFocus.set(FocusUtils.checkIsFocus(dynamic.getUser().getUserCode()));

        title.set(dynamic.getTitle());
        if (!TextUtils.isEmpty(dynamic.getContent())) {
            List<ImgTextContent> contentList = new Gson().fromJson(dynamic.getContent(), new TypeToken<List<ImgTextContent>>() {
            }.getType());
            if (contentList != null && !contentList.isEmpty()) {
                for (ImgTextContent content : contentList) {
                    itemImageTextVMs.add(new ItemImageTextVM(content));
                }
            }
        }

        viewStyle.hasGoodsLink.set(dynamic.getGoods() != null);
        if (dynamic.getGoods() != null) {
            List<String> goodCovers = new Gson().fromJson(dynamic.getGoods().getCovers(), new TypeToken<List<String>>() {
            }.getType());
            goodsImgUri.set((goodCovers != null && goodCovers.size() > 0) ? goodCovers.get(0) : "");
            goodsName.set(dynamic.getGoods().getTitle());
            goodsPrice.set(dynamic.getGoods().getPrice());
            saleNum.set(dynamic.getGoods().getTotalSale());
            if (dynamic.getGoods().getGoodsSpecs() != null && dynamic.getGoods().getGoodsSpecs().size() > 0) {
                stockNum.set(dynamic.getGoods().getGoodsSpecs().get(0).getQuantity());
            }
        }

        if (!TextUtils.isEmpty(dynamic.getProvinceCode()) && !TextUtils.isEmpty(dynamic.getCityCode()) && !TextUtils.isEmpty(dynamic.getAreaCode())) {
            List<City> cityList = AddressUtils.getCities(new String[]{dynamic.getProvinceCode(), dynamic.getCityCode(), dynamic.getAreaCode()});
            city.set(AddressUtils.getCityName(cityList));
        }

        time.set(MyTimeUtils.formatTime(dynamic.getCreateTime()));
//        viewStyle.isLike.set(dynamic.getIsFavorite() == 1);

        //  获取点赞列表
        getLikeList(1);
        //  获取评论
        getCommentList(1);

        hintComment.set("评论：");
    }

    /**
     * 添加轮播图
     */
    private void addSliderView() {
        if (dynamic.getHeaderImg() != null) {
            List<String> headerImgList = new Gson().fromJson(dynamic.getHeaderImg(), new TypeToken<List<String>>() {
            }.getType());
            if (headerImgList != null && !headerImgList.isEmpty()) {
                for (String anUriArray : headerImgList) {
                    DefaultSliderView view = new DefaultSliderView(fragment.getActivity());
                    view.image(anUriArray)
                            .setScaleType(BaseSliderView.ScaleType.CenterCrop);
                    sliderList.add(view);
                }
            }
        }
    }

    /**
     * 获取评论列表
     */
    private void getCommentList(int page) {
        viewStyle.isRefreshing.set(true);

        RetrofitHelper.getDynamicAPI()
                .getDynamicCommentList(dynamic.getMomentCode(), page, pageSize)
                .subscribeOn(Schedulers.io())
                .map(new ApiResponseFunc<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resp -> {
                    total = resp.getTotal();
                    if (resp.getRows() != null && !resp.getRows().isEmpty()) {
                        for (MomentComment comment : resp.getRows()) {
                            if (comment.getUser() != null) {
                                ItemCommentListVM item = new ItemCommentListVM(targetComment -> {
                                    this.targetComment = targetComment;
                                    fragment.editCommentRequestFocus();
                                    hintComment.set("回复" + targetComment.getUser().getUserNick() + "：");
                                }, comment);
                                itemCommentListVMs.add(item);
                            }
                        }
                    }
                }, throwable -> {
                    Toast.makeText(fragment.getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    viewStyle.isRefreshing.set(false);
                }, () -> viewStyle.isRefreshing.set(false));
    }

    /**
     * 获取点赞列表
     */
    private void getLikeList(int page) {
        RetrofitHelper.getDynamicAPI()
                .getMomentFavoriteList(dynamic.getMomentCode(), page, 999)
                .subscribeOn(Schedulers.io())
                .map(new ApiResponseFunc<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resp -> {
                    if (resp.getRows() != null && resp.getRows().size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        Set<String> likeList = new HashSet<String>();
                        for (MomentFavorite favor : resp.getRows()) {
                            if (favor.getUser() != null) {
                                sb.append(favor.getUser().getUserNick());
                                sb.append(",");
                                likeList.add(favor.getUserCode());
                            }
                        }
                        likeName.set(sb.substring(0, sb.length() - 1));
                        viewStyle.showLikeList.set(true);
                        viewStyle.isLike.set(likeList.contains(UserInfo.getInstance().getUserCode()));
                    }
                }, throwable -> {
                    Toast.makeText(fragment.getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * 刷新
     */
    public final ReplyCommand onRefreshCommand = new ReplyCommand(this::refreshData);

    private void refreshData() {
        itemCommentListVMs.clear();
        getCommentList(1);
    }

    /**
     * 下拉加载更多
     */
    public final ReplyCommand<Integer> onLoadMoreCommand = new ReplyCommand<>(integer -> loadMoreData());

    private void loadMoreData() {
        if (itemCommentListVMs.size() < total) {
            getCommentList(itemCommentListVMs.size() / pageSize + 1);
        }
    }

    /**
     * 查看作者主页
     */
    public final ReplyCommand personalHomeClick = new ReplyCommand(() -> {
        fragment.getActivity().startActivity(PersonalHomeActivity.newIntent(fragment.getActivity(), dynamic.getUser()));
        fragment.getActivity().overridePendingTransition(R.anim.anim_right_in, 0);
    });

    /**
     * 联系商家
     */
    public final ReplyCommand contactClick = new ReplyCommand(() -> {
        IMUtils.checkIMLogin(isSuccess -> {
            if (isSuccess) {
                String userName = dynamic.getUser().getId();
                if (userName.equals(EMClient.getInstance().getCurrentUser())) {
                    Toast.makeText(fragment.getActivity(), "您不能自言自语了啦^.^", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(fragment.getActivity(), ChatActivity.class);
                intent.putExtra("userId", userName);
                fragment.getActivity().startActivity(intent);
                fragment.getActivity().overridePendingTransition(R.anim.anim_right_in, 0);
            } else {
                Toast.makeText(fragment.getActivity(), "聊天可能有点问题，请稍候再试", Toast.LENGTH_SHORT).show();
            }
        });
    });

    /**
     * 关注按钮
     */
    public final ReplyCommand focusClick = new ReplyCommand(() -> {
        FocusUtils.changeFocus(fragment.getActivity(), viewStyle.isFocus.get(), dynamic.getUser().getUserCode(), viewStyle.isFocus::set);
    });

    /**
     * 查看商品详情
     */
    public final ReplyCommand goodsDetailClick = new ReplyCommand(() -> {
        fragment.getActivity().startActivity(MerchantGoodsActivity.newIntent(fragment.getActivity(), dynamic.getGoods()));
        fragment.getActivity().overridePendingTransition(R.anim.anim_right_in, 0);
    });

    /**
     * 点赞按钮
     */
    public final ReplyCommand likeClick = new ReplyCommand(() -> {
        if (!viewStyle.isLike.get()) {
            //  点赞
            RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"),
                    new Gson().toJson(new IdRequest(dynamic.getMomentCode())));

            RetrofitHelper.getDynamicAPI()
                    .likeDynamic(requestBody)
                    .subscribeOn(Schedulers.io())
                    .map(new ApiResponseFunc<>())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
//                        dynamic.setIsFavorite(1);
                        viewStyle.isLike.set(true);
                        viewStyle.showLikeList.set(true);

                        StringBuilder sb = new StringBuilder(TextUtils.isEmpty(likeName.get()) ? "" : likeName.get());
                        sb.append(sb.length() > 0 ? "," + UserInfo.getInstance().getUserNick() : UserInfo.getInstance().getUserNick());
                        likeName.set(sb.toString());
                    }, throwable -> Toast.makeText(fragment.getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show());
        }
    });

    /**
     * 发送评论
     */
    public final ReplyCommand sendCommentClick = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            if (TextUtils.isEmpty(editComment.get())) {
                Toast.makeText(fragment.getActivity(), "评论内容不能回空", Toast.LENGTH_SHORT).show();
                return;
            }

            viewStyle.isRefreshing.set(true);
            CreateMomentCommentRequest request = new CreateMomentCommentRequest();
            request.setMomentCode(dynamic.getMomentCode());
            request.setContent(editComment.get());
            if (targetComment != null) request.setToUserCode(targetComment.getUserCode());

            MomentComment newComment = new MomentComment();
            newComment.setContent(editComment.get());
            newComment.setMomentCode(dynamic.getMomentCode());
            newComment.setUserCode(UserInfo.getInstance().getUserCode());
            newComment.setUser((SimpleUser) BeanUtils.Copy(new SimpleUser(), UserInfo.getInstance(), true));
            if (targetComment != null) {
                newComment.setToUserCode(targetComment.getUserCode());
                newComment.setToUser(targetComment.getUser());
            }

            RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"),
                    new Gson().toJson(request));
            RetrofitHelper.getDynamicAPI()
                    .postDynamicComment(requestBody)
                    .subscribeOn(Schedulers.io())
                    .map(new ApiResponseFunc<>())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        ItemCommentListVM item = new ItemCommentListVM(comment -> {
                            targetComment = comment;
                            fragment.editCommentRequestFocus();
                            hintComment.set("回复" + targetComment.getUser().getUserNick() + "：");
                        }, newComment);
                        itemCommentListVMs.add(item);

                        finishEdit();
                    }, throwable -> {
                        Toast.makeText(fragment.getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        viewStyle.isRefreshing.set(false);
                    }, () -> viewStyle.isRefreshing.set(false));
        }
    });

    /**
     * 隐藏软键盘，清空输入框，取消焦点
     */
    private void finishEdit() {
        fragment.clearFocus();
        hintComment.set("评论：");
        editComment.set("");
    }

    /**
     * 选择点赞或评论的弹框
     */
    public class CommentPopupVM implements ViewModel {

        /**
         * 点赞
         */
        public final ReplyCommand likeClick = new ReplyCommand(() -> {

        });

        /**
         * 点评论
         */
        public final ReplyCommand commentClick = new ReplyCommand(() -> {
//            showCommentEditPopup(-1);
        });
    }

    /**
     * 弹出评论编辑框
     */
    private void showCommentEditPopup(int position) {
//        fragment.dismissCommentPopup();
//        fragment.showCommentEditPopup(position);
    }

    /**
     * 评论编辑框
     */
    public class CommentEditPopupVM implements ViewModel {

        public final ObservableField<String> editComment = new ObservableField<>();

        public final ReplyCommand sendCommentClick = new ReplyCommand(() -> {
            Toast.makeText(fragment.getActivity(), "你输入了" + editComment.get(), Toast.LENGTH_SHORT).show();
        });
    }
}
