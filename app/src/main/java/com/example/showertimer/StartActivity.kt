package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        var toothTime = intent.getIntExtra("양치 시간", 3)
        var showerTime = intent.getIntExtra("샤워 시간", 15)
        var shampooTime = intent.getIntExtra("샴푸 시간", 5)
        var shavingTime = intent.getIntExtra("면도 시간", 3)
        var cleansingTime = intent.getIntExtra("세안 시간", 2)

        val startBtn = findViewById<ImageButton>(R.id.startBtn)
        startBtn.setOnClickListener {

            intent.putExtra("세안 시간", cleansingTime)
            intent.putExtra("양치 시간", toothTime)
            intent.putExtra("샤워 시간", showerTime)
            intent.putExtra("샴푸 시간", shampooTime)
            intent.putExtra("면도 시간", shavingTime)

            val intent = Intent(this, Widget::class.java)
            startActivity(intent)
        }
//        val btn = findViewById<ImageButton>(R.id.ib_start)
    }

    private var backPressedTime: Long = 0

    override fun onBackPressed() {
        if(System.currentTimeMillis() - backPressedTime >= 2000) {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            moveTaskToBack(true); // 태스크를 백그라운드로 이동
            finishAndRemoveTask(); // 액티비티 종료 + 태스크 리스트에서 지우기
            android.os.Process.killProcess(android.os.Process.myPid()); // 앱 프로세스 종료
        }
    }
}