package com.example.myapp // (Asegúrate de que coincida con tu paquete si lo cambiaste)

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Busca el botón principal de tu pantalla
        val btnIniciarRoblox = findViewById<Button>(R.id.btnIniciarRoblox)

        btnIniciarRoblox.setOnClickListener {
            verificarYAbrirRoblox()
        }
    }

    private fun verificarYAbrirRoblox() {
        val packageName = "com.roblox.client" // Paquete oficial de Roblox

        val pm = packageManager
        val isInstalled = try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }

        if (isInstalled) {
            // Si Roblox está instalado, lo abre con la cuenta del usuario
            val intent = pm.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                startActivity(intent)
                Toast.makeText(this, "Abriendo LX Ejecutor...", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Si NO está instalado, lo manda a la Google Play Store para que lo descargue
            Toast.makeText(this, "Roblox no está instalado. Redirigiendo...", Toast.LENGTH_LONG).show()
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
            } catch (e: Exception) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
            }
        }
    }
}
