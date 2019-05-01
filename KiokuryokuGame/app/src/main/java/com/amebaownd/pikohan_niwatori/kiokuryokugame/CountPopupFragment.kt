package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CountPopupFragment(): Fragment() {
    lateinit var levelTextView:TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_count_popup,container,false)
        return view
    }

    fun setLevel(level:Int){
        levelTextView.text=getString(R.string.level_popup,level)
    }
}
fun  newCountPopupFragment(level:Int):CountPopupFragment{
    val fragment = CountPopupFragment()
    val bundle = Bundle()
    bundle.putInt("level", level)
    fragment.arguments = bundle
    return fragment
}
//
//class SetCountPopup():Async