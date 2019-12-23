package com.example.yasniel.flagsgame

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pantalla_de_juego.*

class PantallaDeJuego : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_de_juego)


        val ajustes: SharedPreferences? = getSharedPreferences("PREFERENCIAS", 0)

        var urlImagen: String = ajustes?.getString("image_user","").toString()
        var nombre: String = ajustes?.getString("nombre_user","").toString()

        if(nombre.equals("")){
            nombrePersonaJuego.setText(R.string.nombreA)
        }else{
            nombrePersonaJuego.text = nombre
        }

        if(urlImagen.equals("")){
            imagenPersonaJuego.setImageResource(R.drawable.persona)
        }else{
            //imagenPersonaJuego.setImageURI(Uri.parse(urlImagen)) | linea bugg
        }

    }

    override fun onBackPressed() {
        val mainIntent = Intent().setClass(
                this@PantallaDeJuego, MainActivity::class.java)
        startActivity(mainIntent)
        overridePendingTransition(R.anim.animate_slide_in_left,R.anim.animate_slide_out_right)
        this.finish()
    }
}
