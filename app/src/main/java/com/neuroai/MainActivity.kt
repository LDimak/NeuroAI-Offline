package com.neuroai

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_RECORD_AUDIO = 100
    }

    private lateinit var buttonListen: Button
    private lateinit var textLog: TextView
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализируем элементы интерфейса
        buttonListen = findViewById(R.id.button_listen)
        textLog     = findViewById(R.id.text_log)
        scrollView  = findViewById(R.id.scroll_view)

        // Проверяем разрешение на запись аудио
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                REQUEST_RECORD_AUDIO
            )
        }

        // Обработчик нажатия кнопки «Говорить»
        buttonListen.setOnClickListener {
            appendLog("Нейро: слушаю тебя...")
            // TODO: здесь будем вызывать Whisper.cpp → LLM → XTTS
        }
    }

    // Вспомогательный метод для добавления строки в лог диалога
    private fun appendLog(message: String) {
        textLog.append("$message\n\n")
        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
    }
}
