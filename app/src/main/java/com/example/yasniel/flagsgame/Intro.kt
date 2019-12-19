package com.example.yasniel.flagsgame

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_intro.*
import java.util.*

class Intro : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val logo_apk: Typeface = Typeface.createFromAsset( assets, "font/pricedown.ttf" )
        nombreApk.setTypeface(logo_apk)

        val compannia_apk: Typeface = Typeface.createFromAsset( assets, "font/akbar.ttf" )
        nombreCompannia.setTypeface(compannia_apk)


        val mover_derecha: Animation = AnimationUtils.loadAnimation(this, R.anim.mover_derecha)
        val mover_derecha2: Animation = AnimationUtils.loadAnimation(this, R.anim.mover_derecha)
        val mover_izquierda: Animation = AnimationUtils.loadAnimation(this, R.anim.mover_izquierda)

        mover_izquierda?.setStartOffset(300)
        mover_derecha2?.setStartOffset(600)


        nombreApk.startAnimation(mover_derecha)
        nombreCompannia.startAnimation(mover_izquierda)
        logo.startAnimation(mover_derecha2)


        val task = object : TimerTask() {
            override fun run() {
                // Start the next activity
                val mainIntent = Intent().setClass(
                        this@Intro, MainActivity::class.java)
                startActivity(mainIntent)
                overridePendingTransition(R.anim.animate_slide_left_enter, R.anim.animate_slide_left_exit)
                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish()

            }
        }
        // Simulate a long loading process on application startup.
        val timer = Timer()
        timer.schedule(task, 5000)



        introl.setOnClickListener() {
            val mainIntent = Intent().setClass(
                    this@Intro, MainActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(R.anim.animate_slide_left_enter, R.anim.animate_slide_left_exit)
            // Close the activity so the user won't able to go back this
            // activity pressing Back button
            task.cancel()
            this.finish()
        }

    }

}