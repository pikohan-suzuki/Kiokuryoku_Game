package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment

class TestDialog(): DialogFragment() {

    lateinit var title:String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setTitle(title)
        dialogBuilder.setIcon(R.drawable.bird_normal)
        return dialogBuilder.create()
    }
}