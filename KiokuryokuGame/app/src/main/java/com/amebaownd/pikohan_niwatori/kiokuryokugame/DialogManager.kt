package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.Button
import java.lang.Thread.sleep

class DialogManager(context: Context,private val value:Int=0 ){
    fun showDialog(requestCode: Int, manager: FragmentManager) {
        when (requestCode) {
            201 -> {
                val dialog: InformationDialog = InformationDialog()
                dialog.setTargetFragment(null, requestCode)
                dialog.show(manager, "informationDialog")
            }
            202 -> {
                val dialog: CountPopupDialog = CountPopupDialog()
                dialog.count=value
                dialog.setTargetFragment(null, requestCode)
                dialog.show(manager, "countPopupDialog")
//                sleep(1000)
//                dialog.dismiss()
            }
            203 -> {
                val dialog: NewRecordDialog = NewRecordDialog()
                dialog.record=value
                dialog.setTargetFragment(null, requestCode)
                dialog.show(manager, "newRecordDialog")
            }
            204 -> {
                val dialog: ContinueMenuDialog = ContinueMenuDialog()
                dialog.record =value
                dialog.setTargetFragment(null, requestCode)
                dialog.show(manager, "continueMenuDialog")
            }
        }
    }
}
