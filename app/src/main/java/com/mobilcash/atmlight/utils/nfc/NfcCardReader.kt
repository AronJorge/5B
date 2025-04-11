package com.mobilcash.atmlight.utils.nfc

import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.util.Log
import android.util.Log.e
import androidx.core.graphics.TypefaceCompatUtil.closeQuietly
import java.io.IOException

object NfcCardReader {

    data class CreditCardInfo(
        val rawPpseResponse: String,
        val rawAidResponse: String,
        val rawCardData: String,
        val cardId: String
    )

    fun read(tag: Tag): CreditCardInfo? {
        return try {
            IsoDep.get(tag)?.use { isoDep ->
                isoDep.timeout = 2000
                if (!isoDep.isConnected) {
                    isoDep.connect()
                }

                if (!isoDep.isConnected) {
                    throw IOException("No se pudo conectar con la tarjeta")
                }

                val selectPPSE = byteArrayOf(
                    0x00, 0xA4.toByte(), 0x04, 0x00, 0x0E,
                    0x32, 0x50, 0x41, 0x59, 0x2E,
                    0x53, 0x59, 0x53, 0x2E, 0x44,
                    0x44, 0x46, 0x30, 0x31
                )

                val ppseResponse =
                    isoDep.transceive(selectPPSE) ?: throw IOException("Respuesta PPSE nula")

                val selectAID = byteArrayOf(
                    0x00, 0xA4.toByte(), 0x04, 0x00, 0x07,
                    0xA0.toByte(), 0x00, 0x00, 0x00, 0x04,
                    0x10, 0x10
                )
                val aidResponse =
                    isoDep.transceive(selectAID) ?: throw IOException("Respuesta AID nula")

                val gpoCommand = byteArrayOf(
                    0x80.toByte(), 0xA8.toByte(), 0x00, 0x00,
                    0x02, 0x83.toByte(), 0x00, 0x00
                )
                val cardData =
                    isoDep.transceive(gpoCommand) ?: throw IOException("Respuesta GPO nula")

                CreditCardInfo(
                    rawPpseResponse = ppseResponse.toHexString(),
                    rawAidResponse = aidResponse.toHexString(),
                    rawCardData = cardData.toHexString(),
                    cardId = tag.id.toHexString()
                )
            }
        } catch (e: IOException) {
            Log.e("NFC", "Timeout o error de comunicación", e)
            null
        } catch (e: Exception) {
            Log.e("NFC", "Error general", e)
            null
        }
    }

    private fun ByteArray.toHexString(): String = joinToString("") { "%02X".format(it) }

    private fun Tag.idToHex(): String = id.joinToString("") { "%02X".format(it) }

    private fun IsoDep.closeQuietly() {
        try {
            if (isConnected) close()
        } catch (e: Exception) {
            Log.w("NfcCardReader", "Error al cerrar conexión", e)
        }
    }
}