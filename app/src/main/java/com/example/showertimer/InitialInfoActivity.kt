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


        val showerTime = intent.getIntExtra("샤워 시간", 15)
        var shampooTime = intent.getIntExtra("샴푸 시간", 5)
        var cleansingTime = intent.getIntExtra("세안 시간", 2)
        var shavingTime = intent.getIntExtra("면도 시간", 3)
        var toothTime = intent.getIntExtra("양치 시간", 3)

        val isCleansing = intent.getBooleanExtra("세안 여부", true)
        val isShampoo = intent.getBooleanExtra("샴푸 여부", true)
        val isShaving = intent.getBooleanExtra("면도 여부", true)
        val isBrushing = intent.getBooleanExtra("양치 여부", true)

        binding.tvBrushTime.text = toothTime.toString() + "분"
        binding.tvShowerTime.text = showerTime.toString() + "분"
        binding.tvShampooTime.text = shampooTime.toString() + "분"
        binding.tvShavingTime.text = shavingTime.toString() + "분"
        binding.tvCleanserTime.text = cleansingTime.toString() + "분"

        if (!isCleansing) {
            binding.cleanserLayout.visibility = View.GONE
            cleansingTime = 0
        }
        if (!isShampoo) {
            binding.shampooLayout.visibility = View.GONE
            shampooTime = 0
        }
        if (!isShaving) {
            binding.shavingLayout.visibility = View.GONE
            shavingTime = 0
        }
        if (!isBrushing) {
            binding.brushLayout.visibility = View.GONE
            toothTime = 0
        }

        binding.ibInfoNext.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            intent.putExtra("최종 샤워 시간", showerTime)
            intent.putExtra("최종 샴푸 시간", shampooTime)
            intent.putExtra("최종 세안 시간", cleansingTime)
            intent.putExtra("최종 면도 시간", shavingTime)
            intent.putExtra("최종 양치 시간", toothTime)
            startActivity(intent)
        }

        binding.ibInfoBack.setOnClickListener {
            val intent = Intent(this, ToothbrushActivity::class.java)
            startActivity(intent);
            overridePendingTransition(R.anim.none, R.anim.none)
        }

    }
}
