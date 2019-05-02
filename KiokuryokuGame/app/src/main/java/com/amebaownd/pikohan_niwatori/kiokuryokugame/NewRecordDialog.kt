package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.Button
import android.widget.TextView

class NewRecordDialog:DialogFragment(){
    var record :Int=0
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertView: View = activity!!.layoutInflater.inflate(R.layout.dialog_new_record,null)
        val okButton = alertView.findViewById<Button>(R.id.new_record_ok)
        alertView.findViewById<TextView>(R.id.new_record).text=getString(R.string.new_record,record)
        okButton.setOnClickListener{ dialog.dismiss() }
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setView(alertView)
        return dialogBuilder.create()
    }
}