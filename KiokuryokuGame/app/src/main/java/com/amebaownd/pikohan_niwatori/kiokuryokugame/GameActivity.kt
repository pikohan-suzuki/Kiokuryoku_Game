package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_controller.*
import java.lang.Thread.sleep
import java.util.*

class GameActivity : AppCompatActivity(), ControllerFragment.OnButtonClickListener {

    lateinit var sound: Sound
    private var bestRecord = 0
    var currentRecord = 0
    private var questionFragment: QuestionFragment? = null
    private var controllerFragment: ControllerFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        bestRecord = intent.getIntExtra("record", 0)

        if (supportFragmentManager.findFragmentByTag("QuestionFragment") == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.question_frameLayout, newQuestionFragment(bestRecord), "QuestionFragment")
                .commit()
        }
        if (supportFragmentManager.findFragmentByTag("ControllerFragment") == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.controller_frameLayout, newControllerFragment(), "ControllerFragment")
                .commit()
        }



        findViewById<Button>(R.id.test_button).setOnClickListener {
            val dialog = MakeTestDialog(this)
            dialog.showDialog(201, supportFragmentManager)
        }
    }

    override fun onResume() {
        super.onResume()
        if (questionFragment == null) {
            questionFragment = supportFragmentManager.findFragmentByTag("QuestionFragment") as QuestionFragment
            controllerFragment = supportFragmentManager.findFragmentByTag("ControllerFragment") as ControllerFragment
            sound = Sound(
                this,
                listOf<ImageView>(
                    findViewById(R.id.blue_button),
                    findViewById(R.id.red_button),
                    findViewById(R.id.green_button),
                    findViewById(R.id.orange_button)
                )
            )
            sound.load()
            val dialog = MakeTestDialog(this)
            dialog.showDialog(201, supportFragmentManager)
        }
    }

    private fun question(count: Int) {
        val questionList = mutableListOf<Int>()
        val rand = Random(Date().time)
        for (i in 0 until count) {
            questionList.add(i, rand.nextInt(4))
        }
        questionFragment!!.proposingQuestion(questionList, sound)
    }

    override fun onStop() {
        super.onStop()
        sound.unload()
    }

    override fun onDestroy() {
        super.onDestroy()
        sound.unload()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 201 && resultCode == Activity.RESULT_OK) {
            question(currentRecord + 1)
        } else if (requestCode == 202 && resultCode == Activity.RESULT_OK) {
            question(currentRecord + 1)
        } else if (requestCode == 204 && resultCode == Activity.RESULT_OK) {
            TODO("start game")
        } else if (requestCode == 204 && resultCode == Activity.RESULT_CANCELED) {
            finish()
        }
    }

    override fun onButtonClicked(view: View) {
        var response = 0
        when (view.id) {
            R.id.red_button -> {
                response = questionFragment!!.answer(0, sound,currentRecord)
            }
            R.id.blue_button -> {
                response = questionFragment!!.answer(1, sound,currentRecord)
            }
            R.id.green_button -> {
                response = questionFragment!!.answer(2, sound,currentRecord)
            }
            R.id.orange_button -> {
                response = questionFragment!!.answer(3, sound,currentRecord)
            }
        }

        if (response == 1) {
            currentRecord++
            showLevel(currentRecord)
            question(currentRecord + 1)
            setUnclickable()
        } else if (response == -1) {
            endGame(currentRecord, bestRecord)
        }

    }

    private fun showLevel(level: Int) {
//        val countPopupDialog = MakeTestDialog(this,level)
//        countPopupDialog.showDialog(202,supportFragmentManager)
//        questionFragment!!.addLevel(currentRecord)
    }

    private fun newRecord(record: Int) {
        writeFile(this, "bestRecord", record.toString())
    }

    private fun endGame(record: Int, bestRecord: Int) {
        if (record > bestRecord)
            newRecord(record)
        val dialog = MakeTestDialog(this)
        dialog.showDialog(204, supportFragmentManager)
    }

    private fun setPopup(id: Int) {

    }

    private fun setUnclickable(){
        findViewById<ImageView>(R.id.blue_button).isClickable=false
        findViewById<ImageView>(R.id.red_button).isClickable=false
        findViewById<ImageView>(R.id.green_button).isClickable=false
        findViewById<ImageView>(R.id.orange_button).isClickable=false
    }
}