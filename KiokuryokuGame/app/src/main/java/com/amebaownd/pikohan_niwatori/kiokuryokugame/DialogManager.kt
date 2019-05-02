package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.support.v4.app.FragmentManager

class DialogManager(private val value:Int=0 ){
    fun showDialog(requestCode: Int, manager: FragmentManager) {
        when (requestCode) {
            201 -> {
                val dialog = InformationDialog()
                dialog.setTargetFragment(null, requestCode)
                dialog.show(manager, "informationDialog")
            }
            203 -> {
                val dialog = NewRecordDialog()
                dialog.record=value
                dialog.setTargetFragment(null, requestCode)
                dialog.show(manager, "newRecordDialog")
            }
            204 -> {
                val dialog = ContinueMenuDialog()
                dialog.record =value
                dialog.setTargetFragment(null, requestCode)
                dialog.show(manager, "continueMenuDialog")
            }
        }
    }
}
