package com.example.misaludev3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.misaludev3.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    // Configurar ViewBinding
    private lateinit var binding: ActivityMainBinding

    // Configurar Firebase Authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializar El ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajustar los márgenes para barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar Firebase Authentication
        auth = Firebase.auth

        // Configuracion del botón de login
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            // Validaciones de entrada
            if (email.isEmpty()) {
                binding.etEmail.error = "Por favor ingrese un correo"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.etPassword.error = "Por favor ingrese la contraseña"
                return@setOnClickListener
            }

            // Iniciar sesión
            signIn(email, password)
        }

        // Registro de usuario
        binding.tvRegistrar.setOnClickListener {
            try {
                val intent = Intent(this, RegistrarActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                // Mostrar un mensaje en caso de error
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para el  iniciar sesión
    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión exitoso
                    Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()
                    // Ir a la actividad de PostLogin
                    try {
                        val intent = Intent(this, PostLogin::class.java)
                        startActivity(intent)
                    } catch (e: Exception) {
                        // Mostrar mensaje en caso de error
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Inicio de sesión fallido, mostrar mensaje al usuario
                    Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
