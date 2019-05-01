package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class ControllerFragment() : Fragment() {
    interface OnButtonClickListener {
        fun onButtonClicked(view: View)
    }

    lateinit var blue_Button :ImageView
    lateinit var red_Button :ImageView
    lateinit var green_Button :ImageView
    lateinit var orange_Button :ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_controller, container, false)
        blue_Button=view.findViewById<ImageView>(R.id.cat_image)
        blue_Button.setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        red_Button=view.findViewById<ImageView>(R.id.bird_image)
        red_Button.setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        green_Button=view.findViewById<ImageView>(R.id.dog_image)
        green_Button.setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        orange_Button=view.findViewById<ImageView>(R.id.sheep_image)
        orange_Button.setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        return view
    }

    fun setUnclickaable(){
        blue_Button.isClickable=false
        red_Button.isClickable=false
        green_Button.isClickable=false
        orange_Button.isClickable=false
    }
    fun setClickable(){
        blue_Button.isClickable=true
        red_Button.isClickable=true
        green_Button.isClickable=true
        orange_Button.isClickable=true
    }
}

fun newControllerFragment(): ControllerFragment {
    val fragment = ControllerFragment()
    return fragment
}