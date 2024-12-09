package com.example.misaludev3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.misaludev3.databinding.ActivityPostLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class PostLogin : AppCompatActivity() {

    private lateinit var binding: ActivityPostLoginBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPostLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Encontrar el botón "menu" en el diseño y configurar un listener para cambiar de pantalla
        val cambiarPantalla: Button = findViewById(R.id.btn_menu)
        cambiarPantalla.setOnClickListener {
            // Crear un Intent para abrir la actividad "Menu"
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        auth = Firebase.auth

        binding.btnLogout.setOnClickListener {

            MaterialAlertDialogBuilder(this)
                .setTitle("Cerrar Sesion")
                .setMessage("¿Estas Seguro de cerrar sesion?")
                .setNeutralButton("Cancelar") { dialog, which ->
                    // Respond to neutral button press
                }
                .setPositiveButton("Aceptar") { dialog, which ->
                    signOut() //cerrar sesion
                }
                .show()
        }
    }
        // cerrar la sesion
    private fun signOut() {
        Firebase.auth.signOut()
        Toast.makeText(this, "sesion cerrada", Toast.LENGTH_SHORT).show()
        finish()
    }
}