package com.example.cursortest

import android.app.Application
import android.database.Cursor
import android.database.MergeCursor
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import java.lang.Exception
import java.util.*

class MainViewModel(app :Application): AndroidViewModel(app) {
    var imageList: MutableList<String> = mutableListOf()
    init {
        val contentResolver = app.contentResolver
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor = contentResolver.query(uri, null, null, null, MediaStore.MediaColumns.DATE_ADDED + " DESC")
        when {
            cursor == null -> {
                // query failed, handle error.
            }
            !cursor.moveToFirst() -> {
                // no media on the device
            }
            else -> {
//                val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
                val pathColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
//                val titleColumn: Int = cursor.getColumnIndex(MediaStore.Images.Media.TITLE)
//                val idColumn: Int = cursor.getColumnIndex(MediaStore.Images.Media._ID)
                do {
                    imageList.add(cursor.getString(pathColumn))
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
    }
}