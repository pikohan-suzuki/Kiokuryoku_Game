package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.lang.Thread.sleep

class QuestionFragment() : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var redImage: ImageView
    lateinit var blueImage: ImageView
    lateinit var greenImage: ImageView
    lateinit var orangeImage: ImageView
    lateinit var questionMutableList: MutableList<Int>
    var count=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        val currentRecordTextView = view.findViewById<TextView>(R.id.current_record)
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

    fun answer(answer: Int):Int {
        if(answer==questionMutableList[count]){
            Toast.makeText(context,"Correct",Toast.LENGTH_SHORT).show()
            count++
            if(count==questionMutableList.size) {
                correct()
                return 1
            }
            return 0
        }
        incorrect()
        return -1
    }

    private fun correct(){

    }
    private fun incorrect(){

    }
    fun proposingQuestion(question: MutableList<Int>, sound: Sound): Boolean {
        count=0
        questionMutableList=question
        if(questionMutableList.size==1)
            sleep(5000)
        else
            sleep(1500)
        for (i in 0 until question.size) {
            when (question[i]) {
                0 -> {
                    sound.play("dog")
                    redImage.isPressed = true
                }
                1 -> {
                    sound.play("bird")
                    blueImage.isPressed = true
                }
                2 -> {
                    sound.play("sheep")
                    greenImage.isPressed = true
                }
                3 -> {
                    sound.play("cat")
                    orangeImage.isPressed = true
                }
            }
            sleep(1500)
        }
        return true
    }
}

fun newQuestionFragment(bestRecord: Int): QuestionFragment {
    val fragment = QuestionFragment()
    val bundle = Bundle()
    bundle.putInt("bestRecord", bestRecord)
    fragment.arguments = bundle
    return fragment
}
