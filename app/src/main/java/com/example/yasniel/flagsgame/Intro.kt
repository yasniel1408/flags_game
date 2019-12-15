package com.example.yasniel.flagsgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_intro.*
import ss.com.bannerslider.Slider
import java.util.*

class Intro : AppCompatActivity() {

    var OK = false
    var subir1: Animation? = null
    var subir2: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        Slider.init(PicassoImageLoadingService(this))
//        setupViews()

        subir1 = AnimationUtils.loadAnimation(this@Intro, R.anim.subir)
        subir2 = AnimationUtils.loadAnimation(this@Intro, R.anim.subir)

        subir1?.setStartOffset(400)
        subir2?.setStartOffset(600)

        nombreApk.startAnimation(subir1)
        nombreCompannia.startAnimation(subir2)


        val task = object : TimerTask() {
            override fun run() {
                // Start the next activity
                val mainIntent = Intent().setClass(
                        this@Intro, MainActivity::class.java)
                startActivity(mainIntent)
                overridePendingTransition(R.anim.design_bottom_sheet_slide_in,R.anim.design_bottom_sheet_slide_out)
                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish()

            }
        }
        // Simulate a long loading process on application startup.
        val timer = Timer()
        timer.schedule(task, 3000)



        cerrar.setOnClickListener (){
            val mainIntent = Intent().setClass(
                    this@Intro, MainActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(R.anim.design_bottom_sheet_slide_in,R.anim.design_bottom_sheet_slide_out)
            // Close the activity so the user won't able to go back this
            // activity pressing Back button
            task.cancel()
            this.finish()
        }

    }

//    private fun setupViews() {
//        banner_slider1.setSelectedSlideIndicator(ContextCompat.getDrawable(this, R.drawable.selected_slide_indicator))
//        banner_slider1.setUnSelectedSlideIndicator(ContextCompat.getDrawable(this, R.drawable.unselected_slide_indicator))
//        setupSettingsUi()
//
//        //delay for testing empty view functionality
//        banner_slider1.postDelayed(Runnable {
//            banner_slider1.setAdapter(MainSliderAdapter())
//            banner_slider1.setSelectedSlide(0)
//        }, 1500)
//
//    }

//    private fun setupSettingsUi() {
//        val intervalSeekBar = 3000
//        val indicatorSizeSeekBar = 1
//        val loopSlidesSwitch = true
//        val mustAnimateIndicators = true
//        banner_slider1.showIndicators()
//    }

}
