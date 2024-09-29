package com.example.showertimer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.showertimer.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

//    private var toothTime = intent.getIntExtra("최종 양치 시간", 3)
//    private var showerTime = intent.getIntExtra("최종 샤워 시간", 15)
//    private var shampooTime = intent.getIntExtra("최종 샴푸 시간", 5)
//    private var shavingTime = intent.getIntExtra("최종 면도 시간", 3)
//    private var cleansingTime = intent.getIntExtra("최종 세안 시간", 2)

//    private var isCleansing = intent.getBooleanExtra("세안 여부", true)
//    private var isShampoo = intent.getBooleanExtra("샴푸 여부", true)
//    private var isShaving = intent.getBooleanExtra("면도 여부", true)
    lateinit var binding: ActivityStartBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)



        var toothTime = intent.getIntExtra("최종 양치 시간", 3)
        var showerTime = intent.getIntExtra("최종 샤워 시간", 15)
        var shampooTime = intent.getIntExtra("최종 샴푸 시간", 5)
        var shavingTime = intent.getIntExtra("최종 면도 시간", 3)
        var cleansingTime = intent.getIntExtra("최종 세안 시간", 2)


        val btnMenu3 = findViewById<ImageButton>(R.id.btn_menu3)
        btnMenu3.setOnClickListener {
            val intent = Intent(this, ShowerActivity::class.java)
            startActivity(intent)
        }

        val startBtn = findViewById<ImageButton>(R.id.btn_menu2)
        startBtn.setOnClickListener {

            intent.putExtra("세안 시간", cleansingTime)
            intent.putExtra("양치 시간", toothTime)
            intent.putExtra("샤워 시간", showerTime)
            intent.putExtra("샴푸 시간", shampooTime)
            intent.putExtra("면도 시간", shavingTime)

            val intent = Intent(this, Widget::class.java)
            startActivity(intent)
        }

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