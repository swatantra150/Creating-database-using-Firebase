package com.example.database

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.database.databinding.ActivityWelcomeBinding

//Jb uniqueId confirm ho jaaye toh hm ek nye page pe aa jaayenge
class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val name=intent.getStringExtra(SignIn.KEY1)
        val mail=intent.getStringExtra(SignIn.KEY2)
        val uniqueId=intent.getStringExtra(SignIn.KEY3)
        binding= ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val welcomeText=binding.textView
        val mailText=binding.button2
        val idText=binding.button3
        welcomeText.text="Welcome $name"
        mailText.text="mail $mail"
        idText.text="Id $uniqueId"
    }
}