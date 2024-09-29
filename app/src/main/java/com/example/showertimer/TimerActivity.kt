package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.showertimer.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {
    lateinit var binding: ActivityTimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        // 3초 타이머 시작
        object : CountDownTimer(3000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                // 매 초마다 실행되는 코드
                var time = (millisUntilFinished / 1000).toInt()
                binding.tvSc.text = time.toString()

            }

            override fun onFinish() {
                // 타이머가 끝났을 때 실행되는 코드
            }
        }.start()
    }

}