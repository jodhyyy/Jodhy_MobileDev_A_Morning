package com.example.jodhymobiledevmorning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jodhymobiledevmorning.Data.Data
import com.example.jodhymobiledevmorning.Data.DataDatabaseHelper
import com.example.jodhymobiledevmorning.databinding.ActivityUpdateDataBinding

class UpdateData : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateDataBinding
    private lateinit var db: DataDatabaseHelper
    private var dataId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DataDatabaseHelper(this)

        dataId = intent.getIntExtra("data_id", -1)
        if (dataId == -1){
            finish()
            return
        }
        val data = db.getDataByID(dataId)
        binding.updateTitleEditText.setText(data.title)
        binding.updateContentEditText.setText(data.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updateData = Data(dataId, newTitle, newContent)
            db.updateData(updateData)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}