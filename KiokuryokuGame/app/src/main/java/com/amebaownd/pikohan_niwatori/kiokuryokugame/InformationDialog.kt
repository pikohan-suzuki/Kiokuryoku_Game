package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.Button

class InformationDialog : DialogFragment(){
    lateinit var ctx : Context
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertView:View = activity!!.layoutInflater.inflate(R.layout.fragment_information,null)
        val okButton = alertView.findViewById<Button>(R.id.information_ok)
        okButton.setOnClickListener{
            val result= Intent()
            val pendingIntent =activity!!.createPendingResult(targetRequestCode,result, PendingIntent.FLAG_ONE_SHOT)
            try{
                pendingIntent.send(Activity.RESULT_OK)
            }catch (ex: PendingIntent.CanceledException){
                ex.printStackTrace()
            }
            dialog.dismiss()
        }
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setView(alertView)
        this.isCancelable=false
        return dialogBuilder.create()
    }
}