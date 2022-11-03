package com.rhmsoft.pulsa.white

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rhmsoft.pulsa.R
import kotlinx.android.synthetic.main.activity_final.*

class Final : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)
        btn_again.setOnClickListener{
            startActivity(Intent(this, Game::class.java))
        }
    }
}