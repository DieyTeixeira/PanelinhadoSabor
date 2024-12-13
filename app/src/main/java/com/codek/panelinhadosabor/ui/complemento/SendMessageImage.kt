package com.codek.panelinhadosabor.ui.complemento

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

/*
fun captureScreen(view: View): Bitmap {
    val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}

@SuppressLint("QueryPermissionsNeeded", "IntentReset")
fun sendWhatsAppMessageWithImage(context: Context, phoneNumber: String, message: String, image: Bitmap) {
    try {
        // Salvar a imagem como um arquivo local
        val file = File(context.getExternalFilesDir(null), "pedido_${System.currentTimeMillis()}.png")
        val outputStream = FileOutputStream(file)
        image.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()

        // Obter URI do arquivo usando FileProvider
        val imageUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        // Formatar o número para o padrão internacional
        val formattedPhoneNumber = phoneNumber.replace("[^\\d+]".toRegex(), "")

        // Verificar se o WhatsApp está instalado
        val packageManager = context.packageManager
        if (packageManager.getLaunchIntentForPackage("com.whatsapp") != null) {
            // Abrir o chat com o número específico
            val chatIntent = Intent(Intent.ACTION_VIEW).apply {
                Intent.setData = Uri.parse("https://api.whatsapp.com/send?phone=$formattedPhoneNumber&text=${Uri.encode(message)}")
            }
            context.startActivity(chatIntent)

            // Criar o Intent para enviar imagem (após o usuário entrar no chat)
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                Intent.setType = "image/png"
                putExtra(Intent.EXTRA_STREAM, imageUri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                Intent.setPackage = "com.whatsapp"
            }
            context.startActivity(shareIntent)
        } else {
            Toast.makeText(context, "WhatsApp não está instalado.", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Erro ao enviar mensagem: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}
*/

/*
Chamada para colocar no botão de finalizar compra

val activity = context as? Activity
activity?.window?.decorView?.rootView?.let { rootView ->
    val bitmap = captureScreen(rootView)

    bitmap.let { bitmap ->
        sendWhatsAppMessageWithImage(
            context = context,
            phoneNumber = "5551992189353",
            message = "Olá, gostaria de fazer um pedido!",
            image = bitmap
        )
    }
}
 */