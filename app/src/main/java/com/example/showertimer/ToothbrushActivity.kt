package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.showertimer.databinding.ActivityToothbrushBinding

class ToothbrushActivity : AppCompatActivity() {

    private lateinit var binding: ActivityToothbrushBinding
    private var IsToothbrush = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityToothbrushBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count = binding.tvToothbrushTime.text.toString().toIntOrNull() ?: 0

        // "+" 버튼 클릭 리스너
        binding.ivToothPlusBtn.setOnClickListener {
            count++
            binding.tvToothbrushTime.text = count.toString()
        }

        // "-" 버튼 클릭 리스너
        binding.ivToothMinusBtn.setOnClickListener {
            count--
            if (count < 0) {
                Toast.makeText(this, "0 미만은 입력할 수 없어요", Toast.LENGTH_SHORT).show()
                count = 0
            }
            binding.tvToothbrushTime.text = count.toString()
        }

        binding.ivInitialToothCheckOff.setOnClickListener {
            binding.ivInitialToothCheckOff.visibility = View.GONE
            binding.ivInitialToothCheckOn.visibility = View.VISIBLE
            IsToothbrush = false
        }

        binding.ivInitialToothCheckOn.setOnClickListener {
            binding.ivInitialToothCheckOff.visibility = View.VISIBLE
            binding.ivInitialToothCheckOn.visibility = View.GONE
            IsToothbrush = true
        }

        binding.ibToothNext.setOnClickListener {
            val intent = Intent(this, ShowerActivity::class.java)
            startActivity(intent)
        }
    }
}
