package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        var toothTime = intent.getIntExtra("양치 시간", 3)
        var showerTime = intent.getIntExtra("샤워 시간", 15)
        var shampooTime = intent.getIntExtra("샴푸 시간", 5)
        var shavingTime = intent.getIntExtra("면도 시간", 3)
        var cleansingTime = intent.getIntExtra("세안 시간", 2)

        val finishBtn = findViewById<ImageButton>(R.id.ib_button_finish)
        finishBtn.setOnClickListener {
            intent.putExtra("세안 시간", cleansingTime)
            intent.putExtra("양치 시간", toothTime)
            intent.putExtra("샤워 시간", showerTime)
            intent.putExtra("샴푸 시간", shampooTime)
            intent.putExtra("면도 시간", shavingTime)

            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.none, R.anim.none)
        }
    }

}