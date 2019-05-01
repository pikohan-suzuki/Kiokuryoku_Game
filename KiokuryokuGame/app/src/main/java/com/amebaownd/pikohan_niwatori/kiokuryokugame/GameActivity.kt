package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import java.util.*

class GameActivity : AppCompatActivity(), ControllerFragment.OnButtonClickListener {

    lateinit var sound: Sound
    private var bestRecord = 0
    private var currentRecord = 0
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
    }

    override fun onResume() {
        super.onResume()
        setUnclickable()
        if (questionFragment == null) {
            questionFragment = supportFragmentManager.findFragmentByTag("QuestionFragment") as QuestionFragment
            controllerFragment = supportFragmentManager.findFragmentByTag("ControllerFragment") as ControllerFragment
            sound = Sound(
                this,
                listOf<ImageView>(
                    findViewById(R.id.cat_image),
                    findViewById(R.id.dog_image),
                    findViewById(R.id.bird_image),
                    findViewById(R.id.sheep_image)
                )
            )
            sound.load()
            val dialog = DialogManager(this)
            dialog.showDialog(201, supportFragmentManager)
        }else if(currentRecord!=0){
            sound.load()
            question(currentRecord+1)
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 201 && resultCode == Activity.RESULT_OK) {
            question(currentRecord + 1)
        } else if (requestCode == 202 && resultCode == Activity.RESULT_OK) {
            question(currentRecord + 1)
        } else if (requestCode == 204 && resultCode == Activity.RESULT_OK) {
            continueGame()
        } else if (requestCode == 204 && resultCode == Activity.RESULT_CANCELED) {
            val intent = Intent()
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    override fun onButtonClicked(view: View) {
        var response = 0
        when (view.id) {
            R.id.dog_image -> {
                response = questionFragment!!.answer(0, sound,currentRecord)
            }
            R.id.cat_image -> {
                response = questionFragment!!.answer(1, sound,currentRecord)
            }
            R.id.bird_image -> {
                response = questionFragment!!.answer(2, sound,currentRecord)
            }
            R.id.sheep_image -> {
                response = questionFragment!!.answer(3, sound,currentRecord)
            }
        }

        if (response == 1) {
            currentRecord++
            showLevel(currentRecord)
            setUnclickable()
            question(currentRecord + 1)

        } else if (response == -1) {
            setUnclickable()
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
        questionFragment?.setBestRecord(record)
        val dialog=DialogManager(this,currentRecord)
        dialog.showDialog(203,supportFragmentManager)
    }

    private fun endGame(record: Int, bestRecord: Int) {
        val dialog = DialogManager(this,currentRecord)
        dialog.showDialog(204, supportFragmentManager)
        if (record > bestRecord)
            newRecord(record)
    }
    private fun continueGame(){
        currentRecord=0
        questionFragment!!.setCurrentRecord(-1)
        question(currentRecord+1)

    }
    private fun setPopup(id: Int) {

    }

    private fun setUnclickable(){
        findViewById<ImageView>(R.id.cat_image).isClickable=false
        findViewById<ImageView>(R.id.dog_image).isClickable=false
        findViewById<ImageView>(R.id.bird_image).isClickable=false
        findViewById<ImageView>(R.id.sheep_image).isClickable=false
    }

    override fun onBackPressed() {
        val intent = Intent()
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt("currentRecord",currentRecord)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        currentRecord= savedInstanceState?.getInt("currentRecord") ?: 0
    }
}