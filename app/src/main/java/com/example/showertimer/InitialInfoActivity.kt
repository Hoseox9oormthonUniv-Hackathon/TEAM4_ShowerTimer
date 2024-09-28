package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.showertimer.databinding.ActivityInitialInfoBinding
import com.example.showertimer.databinding.ActivityShavingBinding
import com.example.showertimer.databinding.ActivityShowerBinding
import com.example.showertimer.databinding.ActivityToothbrushBinding

class InitialInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitialInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityInitialInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var toothTime = intent.getIntExtra("양치 시간", 3)
        var showerTime = intent.getIntExtra("샤워 시간", 15)
        var shampooTime = intent.getIntExtra("샴푸 시간", 5)
        var shavingTime = intent.getIntExtra("면도 시간", 3)
        var clensingTime = intent.getIntExtra("세안 시간", 2)

        Toast.makeText(this, "양치 시간: $toothTime, 샤워 시간: $showerTime", Toast.LENGTH_SHORT).show()
        binding.tvToothTimeInfo.text = toothTime.toString() + "분"
        binding.tvShowerTimeInfo.text = showerTime.toString() + "분"


    }
}
