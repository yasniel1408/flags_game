package com.example.yasniel.flagsgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class PantallaDeJuego : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_de_juego)
    }

    override fun onBackPressed() {
        val mainIntent = Intent().setClass(
                this@PantallaDeJuego, MainActivity::class.java)
        startActivity(mainIntent)
        overridePendingTransition(R.anim.animate_slide_in_left,R.anim.animate_slide_out_right)
        this.finish()
    }
}
