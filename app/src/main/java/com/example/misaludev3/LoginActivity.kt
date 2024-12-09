import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.misaludev3.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogout = findViewById<com.google.android.material.button.MaterialButton>(R.id.btnLogout)

        btnLogout.setOnClickListener {
            // Mostrar cuadro de diálogo de confirmación
            MaterialAlertDialogBuilder(this)
                .setTitle("Cerrar Sesión")
                .setMessage("¿Estás seguro de que deseas cerrar sesión?")
                .setNeutralButton("Cancelar") { dialog, _ ->
                    dialog.dismiss() // Simplemente cierra el diálogo
                }
                .setPositiveButton("Aceptar") { _, _ ->
                    cerrarSesion() // Ejecuta la función para cerrar sesión
                }
                .show()
        }
    }

    private fun cerrarSesion() {

        Toast.makeText(this, "Sesión cerrada exitosamente", Toast.LENGTH_SHORT).show()
    }
}

