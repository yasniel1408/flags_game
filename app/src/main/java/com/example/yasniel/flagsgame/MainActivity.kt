package com.example.yasniel.flagsgame

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.animation.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    var inflater: LayoutInflater? = null
    var dialog: Dialog? = null
    var layout: View? = null
    var nombre: String? = null
    var direccionImagen: String? = null
    var appfile: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val font: Typeface = Typeface.createFromAsset( assets, "font/pricedown.ttf" )

        nombreApellidos.setTypeface(font)
        calificacionText.setTypeface(font)
        comenzar.setTypeface(font)
        flag_top.setTypeface(font)
        flag_top_caption.setTypeface(font)

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

        if(nombre.equals("")){
            dialog!!.show()
        }else{
            nombreApellidos.text = nombre
        }


        val imagePrew = layout!!.findViewById(R.id.previewFoto) as ImageView
        direccionImagen = ajustes?.getString("image_user","").toString()

        if(direccionImagen.equals("")){
            fotoPersona.setImageResource(R.drawable.persona)
            imagePrew.setImageResource(R.drawable.persona)
        }else{
            fotoPersona.setImageURI(Uri.parse(direccionImagen))
          //  imagePrew.setImageURI(Uri.parse(direccionImagen)) | linea bug
        }


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
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.setType("image/")
            startActivityForResult(Intent.createChooser(intent,"Seleccione la Aplicacion"),10)
        }

        correcto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val intent = Intent(this, TransitionActivity::class.java)
                intent.putExtra("transition", "transition1")
                intent.putExtra("color", "1")
                intent.putExtra("numero", numeroAceptados.text)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, correcto, "transition1")
                ActivityCompat.startActivity(this, intent, options.toBundle())

            }else{
                val mainIntent = Intent().setClass(
                        this@MainActivity, TransitionActivity::class.java)
                startActivity(mainIntent)
            }
        }
        corregidos.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val intent = Intent(this, TransitionActivity::class.java)
                intent.putExtra("transition", "transition1")
                intent.putExtra("color", "2")
                intent.putExtra("numero", numeroCorregidos.text)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, corregidos, "transition1")
                ActivityCompat.startActivity(this, intent, options.toBundle())

            }else{
                val mainIntent = Intent().setClass(
                        this@MainActivity, TransitionActivity::class.java)
                startActivity(mainIntent)
            }
        }
        errores.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val intent = Intent(this, TransitionActivity::class.java)
                intent.putExtra("transition", "transition1")
                intent.putExtra("color", "3")
                intent.putExtra("numero", numeroErrores.text)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, errores, "transition1")
                ActivityCompat.startActivity(this, intent, options.toBundle())

            }else{
                val mainIntent = Intent().setClass(
                        this@MainActivity, TransitionActivity::class.java)
                startActivity(mainIntent)
            }
        }
        falta.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val intent = Intent(this, TransitionActivity::class.java)
                intent.putExtra("transition", "transition1")
                intent.putExtra("color", "4")
                intent.putExtra("numero", numeroFaltan.text)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, falta, "transition1")
                ActivityCompat.startActivity(this, intent, options.toBundle())

            }else{
                val mainIntent = Intent().setClass(
                        this@MainActivity, TransitionActivity::class.java)
                startActivity(mainIntent)
            }
        }

        ayuda.setOnClickListener {
            val mainIntent = Intent().setClass(
                    this@MainActivity, GaleriaBanderas::class.java)
            startActivity(mainIntent)
            overridePendingTransition(R.anim.animate_slide_left_enter,R.anim.animate_slide_left_exit)
            this.finish()
        }


        share.setOnClickListener {
            val mainIntent = Intent(Intent.ACTION_MAIN, null)
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
            val apps = packageManager.queryIntentActivities(mainIntent, 0)
            for (i in apps.indices) {
                if (apps[i].activityInfo.packageName == this@MainActivity.getPackageName()) {
                    appfile = File(apps[i].activityInfo.applicationInfo.publicSourceDir)
                }
            }

            val intentShareFile = Intent(Intent.ACTION_SEND)
            var t: TextView? = null
            t?.setText(R.string.compartir2)
            var t2: TextView? = null
            t2?.setText(R.string.compartir)
            var t3: TextView? = null
            t3?.setText(R.string.compartiendo)

            if (appfile!!.exists()) {
                intentShareFile.type = "application/apk"
                intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + appfile))
                intentShareFile.putExtra(Intent.EXTRA_SUBJECT, t3?.text)
                intentShareFile.putExtra(Intent.EXTRA_TEXT, t2?.text)
                startActivityForResult(Intent.createChooser(intentShareFile, t?.text), 777)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imagePrew = layout!!.findViewById(R.id.previewFoto) as ImageView

        if(resultCode == Activity.RESULT_OK){
            var path: Uri  = data?.data!!
            imagePrew.setImageURI(path)
            fotoPersona.setImageURI(path)

            val ajustes: SharedPreferences? = getSharedPreferences("PREFERENCIAS", 0)
            val editor: SharedPreferences.Editor = ajustes!!.edit()
            editor.putString("image_user", path.toString())
            editor.commit()
        }
    }

}
