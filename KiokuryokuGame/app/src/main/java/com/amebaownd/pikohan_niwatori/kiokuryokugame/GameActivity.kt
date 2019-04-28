package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity(), ControllerFragment.OnButtonClickListener {
    lateinit var sound: Sound
    var continueFlg = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val bestRecord = intent.getIntExtra("record", 0)

        if (supportFragmentManager.findFragmentByTag("QuestionFragment") == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.question_frameLayout, newQuestionFragment(bestRecord), "QuestionFragment")
                .commit()
        }
        if (supportFragmentManager.findFragmentByTag("ControllerFragment") == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.controller_frameLayout, newControllerFragment(),"ControllerFragment")
                .commit()
        }

        sound = Sound(this)




    }
    override fun onResume() {
        super.onResume()
        sound.load()
        val questionFragment = supportFragmentManager.findFragmentByTag("QuestionFragment") as QuestionFragment
        var currentRecord = 0
        while (continueFlg) {
            val questionList: MutableList<Int> = mutableListOf(currentRecord + 1)
            val rand = Random(Date().time)
            for (i in 0..currentRecord) {
                questionList[i] = rand.nextInt(4)
            }
            questionFragment.proposingQuestion(questionList,sound)
            continueFlg = answer()
            if (continueFlg)
                currentRecord++
//            else {
//                endGame(currentRecord, )
//            }
        }
    }

    override fun onStop() {
        super.onStop()
        sound.unload()
    }

    override fun onButtonClicked(view: View) {
        when (view.id) {
            R.id.red_button ->
                sound.play("dog")
            R.id.blue_button ->
                sound.play("bird")
            R.id.green_button ->
                sound.play("sheep")
            R.id.orange_button ->
                sound.play("cat")
        }
    }



    private fun newRecord(record:Int) {
        writeFile(this,"bestRecord",record.toString())
    }

    private fun question(questionList: MutableList<Int>) {

    }

    private fun answer(): Boolean {

        return false
    }

//    private fun endGame(record: Int, bestRecord: Int) {
//        if (record > bestRecord)
//            newRecord(record)
//    }
}