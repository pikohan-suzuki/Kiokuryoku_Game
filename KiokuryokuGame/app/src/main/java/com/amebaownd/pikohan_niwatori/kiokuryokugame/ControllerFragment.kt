package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class ControllerFragment: Fragment() {
    interface OnButtonClickListener {
        fun onButtonClicked(view: View)
    }

    private lateinit var blueImage :ImageView
    private lateinit var redImage :ImageView
    private lateinit var greenImage :ImageView
    private lateinit var orangeImage :ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_controller, container, false)
        blueImage=view.findViewById(R.id.cat_image)
        blueImage.setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        redImage=view.findViewById(R.id.bird_image)
        redImage.setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        greenImage=view.findViewById(R.id.dog_image)
        greenImage.setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        orangeImage=view.findViewById(R.id.sheep_image)
        orangeImage.setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        return view
    }
}

fun newControllerFragment(): ControllerFragment {
    return ControllerFragment()
}