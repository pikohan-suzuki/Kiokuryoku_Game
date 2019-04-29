package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import java.lang.Thread.sleep
import java.util.*


class GameActivity : AppCompatActivity(), ControllerFragment.OnButtonClickListener,InformationFragment.OnButtonClickListener {

    lateinit var sound: Sound
    private var bestRecord=0
    var answerable = false
    var currentRecord=0
    private lateinit var questionFragment: QuestionFragment
    private lateinit var informationFragment: InformationFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        bestRecord = intent.getIntExtra("record", 0)

//        if (supportFragmentManager.findFragmentByTag("QuestionFragment") == null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.question_frameLayout, newQuestionFragment(bestRecord), "QuestionFragment")
//                .commit()
//        }
//        if (supportFragmentManager.findFragmentByTag("ControllerFragment") == null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.controller_frameLayout, newControllerFragment(),"ControllerFragment")
//                .commit()
//        }

        sound = Sound(this)

        findViewById<Button>(R.id.test_button).setOnClickListener{
            val aaa = TestDialog()
            aaa.title="asdfasdfasdf"
            aaa.show(supportFragmentManager,"dialog")
        }
    }
    override fun onResume() {
        super.onResume()
        sound.load()
//        questionFragment = supportFragmentManager.findFragmentByTag("QuestionFragment") as QuestionFragment
//        question(1)

        var dialog: TestDialog = TestDialog()
        dialog.title="aaaa"
        dialog.show(supportFragmentManager,"dialog")
    }

    private fun question(count:Int){
        val questionList= mutableListOf<Int>()
        val rand = Random(Date().time)
        for (i in 0 until count) {
            questionList.add(i,rand.nextInt(4))
        }
        answerable=questionFragment.proposingQuestion(questionList,sound)
    }
    override fun onStop() {
        super.onStop()
        sound.unload()
    }

    override fun onButtonClicked(view: View) {
        var response=0
        if(answerable) {
            when (view.id) {
                R.id.red_button -> {
                    sound.play("dog")
                    response=questionFragment.answer(0)
                }
                R.id.blue_button -> {
                    sound.play("bird")
                    response=questionFragment.answer(1)
                }
                R.id.green_button -> {
                    sound.play("sheep")
                    response=questionFragment.answer(2)
                }
                R.id.orange_button -> {
                    sound.play("cat")
                    response=questionFragment.answer(3)
                }
            }
            if(response==1){
                currentRecord++
                showLevel(currentRecord)
                question(currentRecord+1)
            }else if(response==-1){
                endGame(currentRecord,bestRecord)
            }
        }
    }
    override fun onButtonClicked() {
        findViewById<LinearLayout>(R.id.popup_linearLayout).visibility=View.GONE
        supportFragmentManager.beginTransaction().remove(informationFragment).commit()
        sleep(1000)
        showLevel(1)
        question(1)
    }

    private fun showLevel(level:Int){
        val countPopupFragment= newCountPopupFragment(level)
        supportFragmentManager.beginTransaction().add(R.id.popup_linearLayout,countPopupFragment,"CountPopupFragment").commit()
//        sleep(1000)
        supportFragmentManager.beginTransaction().remove(countPopupFragment).commit()
    }

    private fun newRecord(record:Int) {
        writeFile(this,"bestRecord",record.toString())
    }

    private fun endGame(record: Int, bestRecord: Int) {
        if (record > bestRecord)
            newRecord(record)
    }

    private fun setPopup(id:Int) {

    }
}