package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.content.Context
import android.media.AudioAttributes
import android.media.Image
import android.media.SoundPool
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_question.*
import java.lang.Thread.sleep

class QuestionFragment() : Fragment() {

    lateinit var redImage: ImageView
    lateinit var blueImage: ImageView
    lateinit var greenImage: ImageView
    lateinit var orangeImage: ImageView
    lateinit var currentRecordTextView:TextView
    lateinit var questionMutableList: MutableList<Int>
    var count = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
         currentRecordTextView= view.findViewById<TextView>(R.id.current_record)
        val bestRecordTextView = view.findViewById<TextView>(R.id.best_record)
        currentRecordTextView.text = getString(R.string.current_record, 0)
        val bestRecord = arguments?.getInt("bestRecord") ?: 0
        bestRecordTextView.text = getString(R.string.best_record, bestRecord)
        redImage = view.findViewById(R.id.red_image)
        blueImage = view.findViewById(R.id.blue_image)
        greenImage = view.findViewById(R.id.green_image)
        orangeImage = view.findViewById(R.id.orange_image)
        return view
    }

    fun answer(answer: Int,sound: Sound,record:Int): Int {
        if (answer == questionMutableList[count]) {
            Toast.makeText(context, "Correct", Toast.LENGTH_SHORT).show()
            count++
            if (count == questionMutableList.size) {
                setCurrentRecord(record)
                sound.playLast(answer)
                return 1
            }
            sound.play(answer)
            return 0
        }
        incorrect()
        sound.play(answer)
        return -1
    }

    fun addLevel(level: Int) {
        current_record.text = getString(R.string.level_popup, level)
    }

    private fun setCurrentRecord(record:Int){
        currentRecordTextView.text=getString(R.string.current_record,record+1)
    }

    private fun incorrect() {

    }

    fun proposingQuestion(question: MutableList<Int>, sound: Sound) {
        count = 0
        questionMutableList = question
        sound.question(question)
    }

}

fun newQuestionFragment(bestRecord: Int): QuestionFragment {
    val fragment = QuestionFragment()
    val bundle = Bundle()
    bundle.putInt("bestRecord", bestRecord)
    fragment.arguments = bundle
    return fragment
}
