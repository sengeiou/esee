package com.nong.nongo2o.module.main.fragment.personal;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.nong.nongo2o.databinding.FragmentPersonalBinding;
import com.nong.nongo2o.module.main.viewModel.personal.PersonalVM;
import com.trello.rxlifecycle2.components.RxFragment;

import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by Administrator on 2017-6-22.
 */

public class PersonalFragment extends RxFragment {

    private FragmentPersonalBinding binding;
    private PersonalVM vm;

    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (vm == null) {
            vm = new PersonalVM(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPersonalBinding.inflate(inflater, container, false);
        initView();
        binding.setViewModel(vm);
        return binding.getRoot();
    }

    private void initView() {
        binding.tvName.getPaint().setFakeBoldText(true);
        binding.tvBalance.getPaint().setFakeBoldText(true);
    }

    public void startWaveAnim() {
        View view = binding.ll;
        View startView = binding.btnSwitch;

        int startX = (startView.getLeft() + startView.getRight()) / 2;
        int startY = (startView.getTop() + startView.getBottom()) / 2;

        Animator animator = ViewAnimationUtils.createCircularReveal(view, startX, startY, 0, 10000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1500);
        animator.start();
    }
}