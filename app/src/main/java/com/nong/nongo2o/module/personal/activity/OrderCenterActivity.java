package com.nong.nongo2o.module.personal.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nong.nongo2o.R;
import com.nong.nongo2o.base.RxBaseToolbarActivity;
import com.nong.nongo2o.databinding.ActivityOrderCenterBinding;
import com.nong.nongo2o.module.personal.fragment.OrderListTotalFragment;

/**
 * Created by Administrator on 2017-6-29.
 */

public class OrderCenterActivity extends RxBaseToolbarActivity {

    private ActivityOrderCenterBinding binding;

    public static Intent newIntent(Context context) {
        return new Intent(context, OrderCenterActivity.class);
    }

    @Override
    protected ViewDataBinding getBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_center);
        return binding;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        replaceFragment(R.id.fl, OrderListTotalFragment.newInstance(), OrderListTotalFragment.TAG);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.anim_right_out);
    }

    public void setToolbarTitle(String title) {
        setTitle("");
        binding.tvToolbarTitle.setText(title);
    }
}