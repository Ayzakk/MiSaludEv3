package com.example.misaludev3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.misaludev3.databinding.ActivityMenuBinding
import com.example.misaludev3.vistass.AcercaFragment
import com.example.misaludev3.vistass.InicioFragment
import com.example.misaludev3.vistass.PerfilFragment

class Menu : AppCompatActivity() {

    // Inicialización de ViewBinding
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Configurar ViewBinding
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajustar márgenes del diseño para barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cargar el fragment y su opcion
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InicioFragment()) // InicioFragment como predeterminado
                .commit()
        }
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, InicioFragment()) // Cambiar al InicioFragment
                        .commit()
                    true
                }
                R.id.item_2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AcercaFragment()) // Cambiar al AcercaFragment
                        .commit()
                    true
                }
                R.id.item_3 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, PerfilFragment()) // Cambiar al PerfilFragment
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}