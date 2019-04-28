package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView


class ControllerFragment() : Fragment() {
    interface OnButtonClickListener {
        fun onButtonClicked(view: View)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_controller, container, false)
        view.findViewById<ImageView>(R.id.blue_button).setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        view.findViewById<ImageView>(R.id.green_button).setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        view.findViewById<ImageView>(R.id.red_button).setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        view.findViewById<ImageView>(R.id.orange_button).setOnClickListener{ val listener = context as? OnButtonClickListener
            listener?.onButtonClicked(it) }
        return view
    }
}

fun newControllerFragment(): ControllerFragment {
    val fragment = ControllerFragment()
    return fragment
}