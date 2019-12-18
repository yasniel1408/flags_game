package com.example.yasniel.flagsgame

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_rating.*
import kotlinx.android.synthetic.main.dialogadicionar.*

class MainActivity : AppCompatActivity() {

    var inflater: LayoutInflater? = null
    var dialog: Dialog? = null
    var layout: View? = null
    var nombre: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ajustes: SharedPreferences? = getSharedPreferences("PREFERENCIAS", 0)

        nombre = ajustes?.getString("nombre_user","").toString()

        inflater = this.layoutInflater
        layout = inflater?.inflate(R.layout.dialogadicionar, null)

        dialog = Dialog(this)
        dialog!!.setContentView(layout)
        dialog!!.setTitle(R.string.datos)
        val a = dialog!!.getWindow()!!.getAttributes()
        a.dimAmount = 0f
        dialog!!.getWindow()!!.setAttributes(a)
        dialog!!.setCancelable(true)
        dialog!!.getWindow()!!.getAttributes().windowAnimations = R.style.myDialog
        dialog!!.getWindow()!!.setLayout(Toolbar.LayoutParams.FILL_PARENT, Toolbar.LayoutParams.WRAP_CONTENT)



        val badicionar = layout!!.findViewById(R.id.adicionarYa) as android.support.v7.widget.AppCompatButton
        val nombApell = layout!!.findViewById(R.id.nombreConApellidosDialog) as EditText

        badicionar.setOnClickListener {
            dialog!!.cancel()
            val ajustes: SharedPreferences? = getSharedPreferences("PREFERENCIAS", 0)
            val editor: SharedPreferences.Editor = ajustes!!.edit()
            editor.putString("nombre_user", nombApell.text.toString())
            editor.commit()

            nombreApellidos.text = nombApell.text.toString()

            if(nombApell.text.toString().equals("")){
                nombreApellidos.setText(R.string.nombreA)
            }
        }
        if(nombre.equals("")){
            dialog!!.show()
        }else{
            nombreApellidos.text = nombre
        }
        editarUser.setOnClickListener {
            dialog!!.show()
            nombApell.setText(ajustes?.getString("nombre_user","").toString())
        }

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
        anim?.setFillAfter(true)
        anim?.setDuration(600)
        anim?.setRepeatMode(Animation.REVERSE)
        anim?.setRepeatCount(Animation.INFINITE)
        anim?.setStartOffset(100)
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



        val cargarFoto = layout!!.findViewById(R.id.fotoSelect) as ImageView

        cargarFoto.setOnClickListener {
//            val intent = Intent(Intent.ACTION_CHOOSER)
//            intent.setType("image/*")
        }





    }
}
