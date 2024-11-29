package com.example.calculadoram2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Spinner
import android.widget.EditText
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val spinnerTerrainType = findViewById<Spinner>(R.id.spinner_terrain_type)
        val etWidth = findViewById<EditText>(R.id.et_width)
        val etHeight = findViewById<EditText>(R.id.et_height)
        val spinnerUnits = findViewById<Spinner>(R.id.spinner_units)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate)
        val tvResult = findViewById<TextView>(R.id.tv_result)

        btnCalculate.setOnClickListener {
            // Obtener las claves asociadas a las selecciones
            val terrainTypeKeys = resources.getStringArray(R.array.terrain_type_array_keys)
            val unitsKeys = resources.getStringArray(R.array.units_array_keys)

            val terrainTypeIndex = spinnerTerrainType.selectedItemPosition
            val unitIndex = spinnerUnits.selectedItemPosition

            val terrainTypeKey = terrainTypeKeys[terrainTypeIndex]
            val unitKey = unitsKeys[unitIndex]

            val width = etWidth.text.toString().toDoubleOrNull()
            val height = etHeight.text.toString().toDoubleOrNull()

            if (width != null && height != null) {
                var area = 0.0

                when (terrainTypeKey) {
                    "regular" -> area = width * height
                    "irregular" -> area = (width * height) / 2
                }

                if (unitKey == "feet") {
                    area *= 10.764
                }

                val resultText = getString(R.string.result_label) + "$area ${spinnerUnits.selectedItem}² (${spinnerTerrainType.selectedItem})"
                tvResult.text = resultText
            } else {
                tvResult.text = getString(R.string.error_message)
            }
        }
    }
}

