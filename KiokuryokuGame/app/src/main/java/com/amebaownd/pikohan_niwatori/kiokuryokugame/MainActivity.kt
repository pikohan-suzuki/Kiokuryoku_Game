package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.Format

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val record =readFile(this,"record")?.toInt() ?: 0
        val recordTextView = findViewById<TextView>(R.id.record_text)
        recordTextView.text=getString(R.string.record,record)

        val startButton = findViewById<Button>(R.id.start)
        startButton.setOnClickListener{
            val intent= Intent(this,GameActivity::class.java)
            intent.putExtra("record",record)
            startActivityForResult(intent,101)
        }
    }
}
