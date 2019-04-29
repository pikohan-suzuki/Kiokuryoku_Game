package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class InformationFragment() :Fragment(){
    interface OnButtonClickListener {
        fun onButtonClicked()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =inflater.inflate(R.layout.fragment_information,container,false)
        val okButton = view.findViewById<Button>(R.id.information_ok).setOnClickListener{val listener = context as? OnButtonClickListener
                listener?.onButtonClicked() }
        return view
    }
}