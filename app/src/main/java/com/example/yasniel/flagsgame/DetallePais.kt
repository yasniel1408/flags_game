package com.example.yasniel.flagsgame

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.transition.Transition
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.example.yasniel.flagsgame.utils.TransitionUtils
import kotlinx.android.synthetic.main.activity_detalle_pais.*

class DetallePais : AppCompatActivity() {

    var nestedScrollView: NestedScrollView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pais)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val posicion = intent?.getStringExtra("pos")


            val transition = window.sharedElementEnterTransition
            transition.addListener(object : Transition.TransitionListener {
                override fun onTransitionStart(transition: Transition) {
                    nestedScrollView?.setAlpha(0f)
                }

                override fun onTransitionEnd(transition: Transition) {
                    nestedScrollViewDP?.translationY = TransitionUtils.dpToPixels(this@DetallePais, 72).toFloat()
                    nestedScrollViewDP?.animate()?.alpha(1f)?.translationY(0f)?.interpolator = DecelerateInterpolator()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        transition.removeListener(this)
                    }
                }

                override fun onTransitionCancel(transition: Transition) {

                }

                override fun onTransitionPause(transition: Transition) {

                }

                override fun onTransitionResume(transition: Transition) {

                }
            })
        } else {
            if (savedInstanceState == null) {
            }
        }
    }

    override fun onActivityReenter(resultCode: Int, data: Intent) {
        super.onActivityReenter(resultCode, data)
        supportPostponeEnterTransition()
    }

    override fun onBackPressed() {
        nestedScrollViewDP?.animate()?.alpha(0f)?.setInterpolator(AccelerateInterpolator())?.translationY(TransitionUtils.dpToPixels(this@DetallePais, 72).toFloat())?.start()
        supportFinishAfterTransition()
    }
}
