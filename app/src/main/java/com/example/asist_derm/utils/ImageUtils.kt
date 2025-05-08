package com.example.asist_derm.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun captureAndSaveImage(
    context: Context,
    imageCapture: ImageCapture?,
    onImageSaved: (Uri) -> Unit,
    onError: (String) -> Unit
) {
    if (imageCapture == null) {
        onError("La cámara aún no está lista.")
        return
    }
    val outputDirectory = getOutputDirectory(context)
    val photoFile = File(outputDirectory, "photo_${System.currentTimeMillis()}.jpg")

    val outputOptions = ImageCapture.OutputFileOptions
        .Builder(photoFile)
        .build()

    imageCapture?.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFile)
                Log.d("captureAndSaveImage", "Imagen capturada correctamente en $savedUri")
                onImageSaved(savedUri)
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("captureAndSaveImage", "Error al capturar imagen", exception)
                onError("Error al capturar la foto: ${exception.message}")
            }

        })
}

private fun getOutputDirectory(context: Context): File {
    val mediaDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.let {
        File(it, "AsistDerm").apply { mkdirs() }
    }
    return mediaDir ?: context.filesDir
}

