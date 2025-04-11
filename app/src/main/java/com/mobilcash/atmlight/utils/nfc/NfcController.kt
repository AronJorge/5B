package com.mobilcash.atmlight.utils.nfc

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import androidx.activity.ComponentActivity
import java.io.IOException

class NfcController(
    private val activity: ComponentActivity,
    private val onTagRead: (String) -> Unit
) {
    private val adapter = NfcAdapter.getDefaultAdapter(activity)

    private val pendingIntent: PendingIntent by lazy {
        val intent = Intent(activity, activity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_MUTABLE)
    }

    fun enable() {
        adapter?.enableForegroundDispatch(
            activity, pendingIntent, null, arrayOf(arrayOf(IsoDep::class.java.name))
        )
    }

    fun disable() {
        adapter?.disableForegroundDispatch(activity)
    }

    fun handleIntent(intent: Intent?) {
        if (intent?.action == NfcAdapter.ACTION_TECH_DISCOVERED) {
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG) ?: return
            IsoDep.get(tag)?.let { isoDep ->
                try {
                    isoDep.connect()
                    val id = tag.id.joinToString("") { "%02X".format(it) }
                    onTagRead("Card ID: $id")
                } catch (e: IOException) {
                    onTagRead("Error: ${e.localizedMessage}")
                }
            }
        }
    }
}
