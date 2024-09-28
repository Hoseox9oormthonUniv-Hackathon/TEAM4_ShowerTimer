package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.showertimer.databinding.ActivityShavingBinding

class ShavingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShavingBinding
    private var IsShaving = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityShavingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count = binding.tvShavingTime.text.toString().toIntOrNull() ?: 0
        val toothTime = intent.getIntExtra("양치 시간", 3)
        val showerTime = intent.getIntExtra("샤워 시간", 15)
        val shampooTime = intent.getIntExtra("샴푸 시간", 5)

        // "+" 버튼 클릭 리스너
        binding.ivShavingPlusBtn.setOnClickListener {
            count++
            binding.tvShavingTime.text = count.toString()
        }

        // "-" 버튼 클릭 리스너
        binding.ivShavingMinusBtn.setOnClickListener {
            count--
            if (count < 0) {
                Toast.makeText(this, "0 미만은 입력할 수 없어요", Toast.LENGTH_SHORT).show()
                count = 0
            }
            binding.tvShavingTime.text = count.toString()
        }

        binding.ivInitialShavingCheckOff.setOnClickListener {
            binding.ivInitialShavingCheckOff.visibility = View.GONE
            binding.ivInitialShavingCheckOn.visibility = View.VISIBLE
            IsShaving = false
        }

        binding.ivInitialShavingCheckOn.setOnClickListener {
            binding.ivInitialShavingCheckOff.visibility = View.VISIBLE
            binding.ivInitialShavingCheckOn.visibility = View.GONE
            IsShaving = true
        }

        binding.ibShavingNext.setOnClickListener {
            val intent = Intent(this, CleansingActivity::class.java)
            intent.putExtra("면도 시간", count)
            intent.putExtra("양치 시간", toothTime)
            intent.putExtra("샤워 시간", showerTime)
            intent.putExtra("샴푸 시간", shampooTime)
            startActivity(intent)
        }

    }
}
