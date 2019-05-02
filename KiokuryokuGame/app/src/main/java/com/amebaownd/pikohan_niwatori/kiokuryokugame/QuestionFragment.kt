package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.lang.Math.cos
import java.lang.Math.sin
import kotlin.math.PI

class QuestionFragment : Fragment() {

    private lateinit var houseImage:ImageView
    private lateinit var currentRecordTextView:TextView
    private lateinit var bestRecordTextView: TextView
    private lateinit var questionMutableList: MutableList<Int>
    private lateinit var questionFrameLayout: FrameLayout
    private var count = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
         currentRecordTextView= view.findViewById(R.id.current_record)
        bestRecordTextView = view.findViewById(R.id.best_record)
        questionFrameLayout=view.findViewById(R.id.question_front_framelayout)
        currentRecordTextView.text = getString(R.string.current_record, 0)
        val bestRecord = arguments?.getInt("bestRecord") ?: 0
        bestRecordTextView.text = getString(R.string.best_record, bestRecord)
        houseImage=view.findViewById(R.id.house_image)
        return view
    }

    fun answer(answer: Int,sound: Sound,record:Int): Int {
        if (answer == questionMutableList[count]) {
            count++
            addAnimationView(answer,questionMutableList.size,count)
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

    fun setCurrentRecord(record:Int){
        currentRecordTextView.text=getString(R.string.current_record,record+1)
    }
    fun setBestRecord(record:Int){
        bestRecordTextView.text=getString(R.string.best_record,record)
    }

    private fun incorrect() {

    }

    fun proposingQuestion(question: MutableList<Int>, sound: Sound) {
        count = 0
        questionMutableList = question
        sound.question(question,houseImage,questionFrameLayout)
    }

    private fun addAnimationView(id:Int,quantity :Int,num:Int){
        val view = ImageView(context)
        val layoutParams = FrameLayout.LayoutParams(200,200)
        view.layoutParams = layoutParams
        view.x=((questionFrameLayout.width-200)/2).toFloat()
        view.y=(questionFrameLayout.height-200).toFloat()
        when (id) {
            0 ->view.setImageResource(R.drawable.dog_alpha)
            1 -> view.setImageResource(R.drawable.cat_alpha)
            2 -> view.setImageResource(R.drawable.bird_alpha)
            3 -> view.setImageResource(R.drawable.sheep_alpha)
        }
        questionFrameLayout.addView(view)
        val animationX = PropertyValuesHolder.ofFloat("translationX",((questionFrameLayout.width-200)/2).toFloat(),((questionFrameLayout.width-200)/2).toFloat()+(questionFrameLayout.width*cos(PI*(num*180/(quantity+1))/180)*2/5).toFloat())
        val animationY=PropertyValuesHolder.ofFloat("translationY",(questionFrameLayout.height-200).toFloat(),((questionFrameLayout.height-200).toFloat()+-questionFrameLayout.width*sin(PI*(num*180/(quantity+1))/180)*2/5).toFloat())
        val objectAnimator: ObjectAnimator =
            ObjectAnimator.ofPropertyValuesHolder(view, animationX, animationY)
        objectAnimator.duration = 125
        objectAnimator.start()
    }

}

fun newQuestionFragment(bestRecord: Int): QuestionFragment {
    val fragment = QuestionFragment()
    val bundle = Bundle()
    bundle.putInt("bestRecord", bestRecord)
    fragment.arguments = bundle
    return fragment
}


