package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val record =intent.getIntExtra("record",0)

    }

    private fun newRecord(){

    }
}