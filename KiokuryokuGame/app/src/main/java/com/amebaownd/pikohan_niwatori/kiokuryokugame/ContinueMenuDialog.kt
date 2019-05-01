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
import android.widget.TextView

class ContinueMenuDialog:DialogFragment() {
    var record =0
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertView: View = activity!!.layoutInflater.inflate(R.layout.dialog_continue_menu, null)
        alertView.findViewById<TextView>(R.id.result_record).text=record.toString()
        val oneMoreButton = alertView.findViewById<Button>(R.id.one_more)
        val backToTileButton = alertView.findViewById<Button>(R.id.back_to_title)
        oneMoreButton.setOnClickListener {
            val result = Intent()
            val pendingIntent = activity!!.createPendingResult(targetRequestCode, result, PendingIntent.FLAG_ONE_SHOT)
            try {
                pendingIntent.send(Activity.RESULT_OK)
            } catch (ex: PendingIntent.CanceledException) {
                ex.printStackTrace()
            }
            dialog.dismiss()
        }
        backToTileButton.setOnClickListener{
            val result = Intent()
            val pendingIntent = activity!!.createPendingResult(targetRequestCode, result, PendingIntent.FLAG_ONE_SHOT)
            try {
                pendingIntent.send(Activity.RESULT_CANCELED)
            } catch (ex: PendingIntent.CanceledException) {
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