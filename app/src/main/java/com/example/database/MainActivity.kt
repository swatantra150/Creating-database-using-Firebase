package com.example.database
import android.content.Intent
import com.example.database.R
import com.example.database.Users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.database.databinding.ActivityMainBinding

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var database:DatabaseReference
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLogin.setOnClickListener {
            val name=binding.name.text.toString()
            val mail=binding.Email.text.toString()
            val password=binding.editTextPassword.text.toString()
            val uniqueId=binding.UniqueId.text.toString()
            val user= Users(name,mail,password,uniqueId)
database=FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener { 
                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
            }.addOnCanceledListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
      binding.textView8.setOnClickListener {
          val openSignInActivity=Intent(this,SignIn::class.java)
          startActivity((openSignInActivity))
      }
    }
}