package com.example.yasniel.flagsgame

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import kotlinx.android.synthetic.main.activity_rating.*

class Rating : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        val ajustes: SharedPreferences? = getSharedPreferences("PREFERENCIAS", 0)

        rating_bar.rating = ajustes?.getFloat("rating", 0F)!!
        review_box.setText(ajustes?.getString("text_rating",""))
        smiley_view.defaultRating = rating_bar.rating.toInt() //Esto esta haciendo lo q le da la gana!!!!!!

        rating_bar.setOnRatingBarChangeListener { ratingBar: RatingBar, fl: Float, b: Boolean ->
            Log.d("Rating", fl.toString())
            smiley_view.setSmiley(fl)
        }


        send.setOnClickListener {
            val editor: SharedPreferences.Editor = ajustes.edit()
            editor.putFloat("rating", rating_bar.rating)
            editor.commit()

            editor.putString("text_rating", review_box.text.toString())
            editor.commit()

            val mainIntent = Intent().setClass(
                    this@Rating, MainActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(R.anim.animate_slide_in_left,R.anim.animate_slide_out_right)
            this.finish()
        }

    }

    override fun onBackPressed() {
        val mainIntent = Intent().setClass(
                this@Rating, MainActivity::class.java)
        startActivity(mainIntent)
        overridePendingTransition(R.anim.animate_slide_in_left,R.anim.animate_slide_out_right)
        this.finish()
    }
}
