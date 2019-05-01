package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var startButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val record =readFile(this,"bestRecord")?.toInt() ?: 0
        val recordTextView = findViewById<TextView>(R.id.record_text)
        recordTextView.text=getString(R.string.record,record)

        startButton = findViewById<Button>(R.id.start)
        startButton.setOnClickListener{
            it.isClickable=false
            val intent= Intent(this,GameActivity::class.java)
            intent.putExtra("record",record)
            startActivityForResult(intent,101)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==101 && resultCode== Activity.RESULT_OK){
            val record = intent.getIntExtra("record",0)
            if(record!=0)
                findViewById<TextView>(R.id.best_record).text=getString(R.string.best_record,record)
            startButton.isClickable=true
        }
    }
}
