package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.lang.Thread.sleep

class CountPopupDialog() : DialogFragment() {
    //    lateinit var countTextView:TextView
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.fragment_count_popup,container,false)
//        val level =arguments?.getInt("level") ?: 1
//        countTextView=view.findViewById<TextView>(R.id.count_popup)
//        countTextView.text=getString(R.string.level_popup,level)
//        return view
//    }
//    fun setLevel(level:Int){
//        countTextView.text=getString(R.string.level_popup,level)
//    }
//}
//fun newCountPopupFragment(level: Int): CountPopupFragment {
//    val fragment = CountPopupFragment()
//    val bundle = Bundle()
//    bundle.putInt("level", level)
//    fragment.arguments = bundle
//    return fragment
    lateinit var ctx: Context
    var count: Int = 0
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertView: View = activity!!.layoutInflater.inflate(R.layout.fragment_count_popup, null)
        val countTextView = alertView.findViewById<TextView>(R.id.count_popup)
        countTextView.text=getString(R.string.level_popup,count)
        val result= Intent()
        val pendingIntent =activity!!.createPendingResult(targetRequestCode,result, PendingIntent.FLAG_ONE_SHOT)
        try{
            pendingIntent.send(Activity.RESULT_OK)
        }catch (ex: PendingIntent.CanceledException){
            ex.printStackTrace()
        }
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setView(alertView)
        return dialogBuilder.create()

    }
}

