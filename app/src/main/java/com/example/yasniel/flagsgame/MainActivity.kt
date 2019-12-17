package com.example.yasniel.flagsgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val subir1: Animation = AnimationUtils.loadAnimation(this, R.anim.animate_slide_down_enter)
        val subir2: Animation = AnimationUtils.loadAnimation(this, R.anim.animate_windmill_enter)
        val subir3: Animation = AnimationUtils.loadAnimation(this, R.anim.animate_spin_enter)
        val subir4: Animation = AnimationUtils.loadAnimation(this, R.anim.animate_diagonal_right_enter)

        subir1?.setStartOffset(200)
        subir2?.setStartOffset(400)
        subir3?.setStartOffset(600)
        subir4?.setStartOffset(800)

        correcto.startAnimation(subir1)
        corregidos.startAnimation(subir2)
        errores.startAnimation(subir3)
        falta.startAnimation(subir4)

        val anim: Animation = AnimationUtils.loadAnimation(this, R.anim.aumentar_tamanno)
        anim?.setStartOffset(1000)
        anim?.setRepeatCount(Animation.INFINITE)
        comenzar.startAnimation(anim)

        comenzar.setOnClickListener {
            val mainIntent = Intent().setClass(
                    this@MainActivity, PantallaDeJuego::class.java)
            startActivity(mainIntent)
            overridePendingTransition(R.anim.animate_slide_left_enter,R.anim.animate_slide_left_exit)
            this.finish()
        }

        calificacion.setOnClickListener {
            val mainIntent = Intent().setClass(
                    this@MainActivity, Rating::class.java)
            startActivity(mainIntent)
            overridePendingTransition(R.anim.animate_slide_left_enter,R.anim.animate_slide_left_exit)
            this.finish()
        }




    }
}
