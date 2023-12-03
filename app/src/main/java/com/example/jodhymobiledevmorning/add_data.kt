package com.example.jodhymobiledevmorning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jodhymobiledevmorning.Data.Data
import com.example.jodhymobiledevmorning.Data.DataDatabaseHelper
import com.example.jodhymobiledevmorning.databinding.ActivityAddDataBinding

class add_data : AppCompatActivity() {
    private lateinit var binding: ActivityAddDataBinding
    private lateinit var db: DataDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DataDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val data = Data(0, title, content)
            db.insertData(data)
            finish()
            Toast.makeText(this, "Data Telah disimpan", Toast.LENGTH_SHORT).show()
        }
    }
}