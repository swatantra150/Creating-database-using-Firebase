package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import android.widget.Toast
import com.example.database.databinding.ActivityMainBinding
import com.example.database.databinding.ActivitySignInBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding: ActivitySignInBinding
    companion object{
        const val KEY1="com.example.database.SignIn.name"
        const val KEY2="com.example.database.SignIn.mail"
        const val KEY3="com.example.database.SignIn.uniqueId"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val uniqueId = binding.UniqueId.text.toString()
            if (uniqueId.isNotEmpty()) {
                readData(uniqueId)
            } else {
                Toast.makeText(this, "Enter the unique id ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(uniqueId: String) {
        databaseReference=FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
          if(it.exists()){
              val email=it.child("email")
              val name=it.child("name")
              val userid=it.child("uniqueId")
              val intentWelcome=Intent(this,WelcomeActivity::class.java)
              intentWelcome.putExtra(KEY2,email.toString())
              intentWelcome.putExtra(KEY1,name.toString())
              intentWelcome.putExtra(KEY3,userid.toString())
              startActivity(intentWelcome)
          }else{
              Toast.makeText(this,"Enter the valid Id",Toast.LENGTH_SHORT).show()
          }
        }.addOnFailureListener(){
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }

    }
}