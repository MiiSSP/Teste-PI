package com.junior.testepi

import android.app.Activity
import android.app.AlertDialog

class LoadingDiaolog(val mActivity: Activity) {
    private lateinit var dialog: AlertDialog
    fun startLoading(){
        val inflater = mActivity.layoutInflater
        val dialogView =inflater.inflate(R.layout.dialog_view, null)
        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog = builder.show()

    }
    fun dismiss(){
        dialog.dismiss()
    }

}