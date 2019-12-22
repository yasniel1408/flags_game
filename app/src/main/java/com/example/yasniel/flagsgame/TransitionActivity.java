package com.example.yasniel.flagsgame;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yasniel.flagsgame.utils.TransitionUtils;

public class TransitionActivity extends AppCompatActivity {

    NestedScrollView nestedScrollView;
    RelativeLayout relativeLayout;
    TextView numeroTV, textoOpcion;
    ImageView imageView;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutPaCambiar);
        numeroTV = (TextView) findViewById(R.id.textView);
        textoOpcion = (TextView) findViewById(R.id.textoOpcion);
        imageView = (ImageView) findViewById(R.id.iconoDetalle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = getIntent();
            relativeLayout.setTransitionName(intent.getStringExtra("transition"));
            String color = intent.getStringExtra("color");
            numeroTV.setText(intent.getStringExtra("numero"));
            if(color.equals("1")) {
                relativeLayout.setBackgroundResource(R.color.aceptados);
                imageView.setImageResource(R.drawable.pdlg_icon_successgrande);
                textoOpcion.setText(R.string.acertados);
            }else if(color.equals("2")) {
                relativeLayout.setBackgroundResource(R.color.corregidos);
                imageView.setImageResource(R.drawable.ic_done_all_white_24dp);
                textoOpcion.setText(R.string.corregidos);
            }else if(color.equals("3")) {
                relativeLayout.setBackgroundResource(R.color.errores);
                imageView.setImageResource(R.drawable.ic_warning_white_48dp);
                textoOpcion.setText(R.string.errores);
            }else if(color.equals("4")) {
                relativeLayout.setBackgroundResource(R.color.faltan);
                imageView.setImageResource(R.drawable.ic_control_point_white_48dp);
                textoOpcion.setText(R.string.faltan);
            }

            Transition transition = getWindow().getSharedElementEnterTransition();
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    nestedScrollView.setAlpha(0f);
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    nestedScrollView.setTranslationY(
                            TransitionUtils.dpToPixels(TransitionActivity.this, 72));
                    nestedScrollView.animate().alpha(1f).translationY(0)
                            .setInterpolator(new DecelerateInterpolator());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        transition.removeListener(this);
                    }
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        } else {
            if (savedInstanceState == null) {
            }
        }
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        supportPostponeEnterTransition();
    }

    @Override
    public void onBackPressed() {
        nestedScrollView.animate().alpha(0)
                .setInterpolator(new AccelerateInterpolator())
                .translationY(TransitionUtils.dpToPixels(TransitionActivity.this, 72))
                .start();
        supportFinishAfterTransition();
    }
}
