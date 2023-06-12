package com.example.androidpracticeproject.navigationComponentsPractice.authentication

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpracticeproject.R


class Authentication : AppCompatActivity() {

    // variables
    lateinit var tvZanab: TextView
    private val redColor = 0xFFFF3D00
    private val purpleColor = 0xFF705FAA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        tvZanab = findViewById(R.id.app_name_logo)
        val textShader: Shader = LinearGradient(
            0f,
            0f,
            0f,
            tvZanab.paint.textSize,
            intArrayOf(redColor.toInt(), purpleColor.toInt()),
            floatArrayOf(0f, 1f),
            TileMode.CLAMP
        )
        tvZanab.paint.shader = textShader
    }
}