package com.example.androidpracticeproject.fragments.authentication

import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.androidpracticeproject.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    private lateinit var btnLogin: Button
    private lateinit var tvAppTitleLogo: TextView

    private val redColor = 0xFFFF3D00
    private val purpleColor = 0xFF705FAA

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin = binding.loginButton
        btnLogin.setOnClickListener {
            view.findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
        }
        tvAppTitleLogo = binding.appNameLogo
        colorAppTitleLogo()
    }

    private fun colorAppTitleLogo() {
        val textShader: Shader = LinearGradient(
            0f,
            0f,
            0f,
            tvAppTitleLogo.paint.textSize,
            intArrayOf(redColor.toInt(), purpleColor.toInt()),
            floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP
        )
        tvAppTitleLogo.paint.shader = textShader
    }

}