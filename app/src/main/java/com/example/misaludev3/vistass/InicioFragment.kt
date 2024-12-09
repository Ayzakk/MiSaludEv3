package com.example.misaludev3.vistass

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import LoginActivity
import com.example.misaludev3.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class InicioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)

        // Obtener referencia al botón
        val btnLogout = view.findViewById<MaterialButton>(R.id.btn_cerrar_sesion)

        // Configurar el clic del botón para mostrar el diálogo de confirmación
        btnLogout.setOnClickListener {
            mostrarDialogoCerrarSesion()
        }

        return view
    }

    private fun mostrarDialogoCerrarSesion() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Cerrar Sesión")
            .setMessage("¿Estás seguro de cerrar sesión?")
            .setNeutralButton("Cancelar") { dialog, _ ->
                dialog.dismiss() // Cierra el diálogo
            }
            .setPositiveButton("Aceptar") { _, _ ->
                cerrarSesion()
            }
            .show()
    }

    private fun cerrarSesion() {
        val sharedPreferences = requireActivity().getSharedPreferences("Sesion", 0)
        sharedPreferences.edit().clear().apply()

        // Redirigir al usuario a la pantalla de inicio de sesión
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
