package com.t3h.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    private ImageView ivAnimation;

    private boolean isMoveDown = false;
    private boolean isMakingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isMoveDown = true;
        initViews();

        makeAnimationViewRoot();
    }

    private void initViews() {
        ivAnimation =
                (ImageView) findViewById(R.id.iv_animation);
        findViewById(R.id.btn_stranslate).setOnClickListener(this);
        findViewById(R.id.btn_alpha).setOnClickListener(this);
        findViewById(R.id.btn_scale).setOnClickListener(this);
        findViewById(R.id.btn_rotate).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_more).setOnClickListener(this);
        findViewById(R.id.btn_animator).setOnClickListener(this);
    }

    private void makeAnimationViewRoot() {
        Animation animation =
                AnimationUtils.loadAnimation(this, R.anim.to_right_act);
        View viewRoot = findViewById(R.id.view_root);
        viewRoot.startAnimation(animation);


    }

    @Override
    public void onClick(View v) {
        if (isMakingAnimation) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_stranslate:
                makeAnimationTranslate();
                break;
            case R.id.btn_alpha:
                makeAnimation(R.anim.demo_alpha_down);
                break;
            case R.id.btn_scale:
                makeAnimation(R.anim.demo_scale_in);
                break;
            case R.id.btn_rotate:
                makeAnimation(R.anim.demo_rotate);
                break;
            case R.id.btn_clear:
                //xoa tat ca cac animation trong view
                ivAnimation.clearAnimation();
                break;
            case R.id.btn_more:
                makeAnimation(R.anim.demo_set_tran_ro_down);
                break;
            case R.id.btn_animator:
                Animator animatorX = ObjectAnimator.ofFloat(ivAnimation, "x", 0,
                        ivAnimation.getWidth()*2, 0, 500);
                animatorX.setDuration(1000);
                Animator animatorY = ObjectAnimator.ofFloat(ivAnimation, "y", 0,
                        ivAnimation.getWidth()*2, 0, 500);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(animatorX, animatorY);

                set.start();
                break;
            default:
                break;
        }

    }


    private void makeAnimation( int animationID ) {
        Animation animation =
                AnimationUtils.loadAnimation(this, animationID);
        ivAnimation.startAnimation(animation);
    }
    private void makeAnimationTranslate() {
        int anim;
        if (isMoveDown) {
            anim = R.anim.demo_traslate;
        } else {
            anim = R.anim.demo_translate_to_up;
        }
        // cach anh xa animation tu xml vao trong java code
        TranslateAnimation animation = (TranslateAnimation) AnimationUtils.loadAnimation(this,
                anim);

        //dang ky cac su kien thuc hienh animation
        animation.setAnimationListener(this);
        //dua animation do vao trong view va cho animation do chay
        ivAnimation.startAnimation(animation);
        isMoveDown = !isMoveDown;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        isMakingAnimation = true;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        isMakingAnimation = false;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
