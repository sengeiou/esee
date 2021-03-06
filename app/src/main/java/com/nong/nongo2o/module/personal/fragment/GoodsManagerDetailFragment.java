package com.nong.nongo2o.module.personal.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nong.nongo2o.R;
import com.nong.nongo2o.databinding.FragmentGoodsManagerDetailBinding;
import com.nong.nongo2o.entity.domain.City;
import com.nong.nongo2o.entity.domain.Goods;
import com.nong.nongo2o.module.common.CancelDialogListener;
import com.nong.nongo2o.module.common.ConfirmDialogListener;
import com.nong.nongo2o.module.personal.activity.GoodsManagerActivity;
import com.nong.nongo2o.module.personal.viewModel.GoodsManagerDetailVM;
import com.nong.nongo2o.widget.recyclerView.LinearItemDecoration;
import com.trello.rxlifecycle2.components.RxFragment;

/**
 * Created by Administrator on 2017-6-27.
 */

public class GoodsManagerDetailFragment extends RxFragment {

    public static final String TAG = "GoodsManagerDetailFragment";
    public static final int GET_AREA = 100;

    private GoodsManagerDetailVM vm;
    private FragmentGoodsManagerDetailBinding binding;
    private Goods goods;
    public static GoodsManagerDetailFragment newInstance(Goods goods) {
        Bundle args = new Bundle();
        args.putSerializable("goods", goods);
        GoodsManagerDetailFragment fragment = new GoodsManagerDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().getSerializable("goods") != null){
            goods = (Goods) getArguments().getSerializable("goods");
        }

        if (vm == null) {
            vm = new GoodsManagerDetailVM(this,goods);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGoodsManagerDetailBinding.inflate(inflater, container, false);
        initView();
        binding.setViewModel(vm);
        return binding.getRoot();
    }

    private void initView() {
        ((GoodsManagerActivity) getActivity()).setToolbarTitle("编辑商品");

        binding.rvBanner.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        binding.rvStandard.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvStandard.addItemDecoration(new LinearItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.shape_vertical_divider));
        binding.rvStandard.setNestedScrollingEnabled(false);

        binding.rvDesc.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvDesc.addItemDecoration(new LinearItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.shape_vertical_divider));
        binding.rvDesc.setNestedScrollingEnabled(false);
    }

    /**
     * 操作确认框
     */
    public void showConfirmDialog(String content, ConfirmDialogListener confirm, CancelDialogListener cancel) {
        new AlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setMessage(content)
                .setCancelable(false)
                .setNegativeButton("取消", (dialog, which) -> {
                    dialog.dismiss();
                    if (cancel != null) cancel.onCancel();
                })
                .setPositiveButton("确定", (dialog, which) -> {
                    dialog.dismiss();
                    if (confirm != null) confirm.onConfirm();
                })
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GET_AREA:
                if (resultCode == -1 && vm != null) {
                    vm.setCities((City) data.getSerializableExtra("cityP"), (City) data.getSerializableExtra("cityC"),
                            (City) data.getSerializableExtra("cityD"));
                }
                break;
        }
    }
}
