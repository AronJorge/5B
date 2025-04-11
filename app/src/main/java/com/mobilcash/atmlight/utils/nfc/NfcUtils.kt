package com.mobilcash.atmlight.utils.nfc

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.provider.Settings

object NfcUtils {
    fun isNfcSupported(context: Context): Boolean {
        return NfcAdapter.getDefaultAdapter(context) != null
    }

    fun isNfcEnabled(context: Context): Boolean {
        return NfcAdapter.getDefaultAdapter(context)?.isEnabled == true
    }

    fun openNfcSettings(activity: Activity) {
        activity.startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
    }
}
