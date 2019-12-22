package com.example.yasniel.flagsgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.yasniel.flagsgame.adapters.AdapterRecyclerView
import com.example.yasniel.flagsgame.configuracion.Configuracion

class GaleriaBanderas : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var adapterrv: RecyclerView.Adapter<*>? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria_banderas)

        recyclerView = findViewById(R.id.recyclerview) as RecyclerView
        layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.setLayoutManager(layoutManager)

        adapterrv = AdapterRecyclerView(this, Configuracion.getList(this))
        recyclerView!!.setAdapter(adapterrv)
    }


    override fun onBackPressed() {
        val mainIntent = Intent().setClass(
                this@GaleriaBanderas, MainActivity::class.java)
        startActivity(mainIntent)
        overridePendingTransition(R.anim.animate_slide_in_left,R.anim.animate_slide_out_right)
        this.finish()
    }
}
