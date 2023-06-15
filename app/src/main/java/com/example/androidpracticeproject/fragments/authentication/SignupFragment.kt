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
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var btnLogin: Button
    private lateinit var tvAppTitleLogo: TextView
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin = binding.loginButton
        navController = findNavController()
        btnLogin.setOnClickListener {
            navController.popBackStack()
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
            intArrayOf(
                resources.getColor(R.color.redColor, null),
                resources.getColor(R.color.purpleColor, null)
            ),
            floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP
        )
        tvAppTitleLogo.paint.shader = textShader
    }

}