package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.showertimer.databinding.ActivityClensingBinding
import com.example.showertimer.databinding.ActivityShavingBinding
import com.example.showertimer.databinding.ActivityShowerBinding
import com.example.showertimer.databinding.ActivityToothbrushBinding

class ClensingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClensingBinding
    private var IsClensing = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityClensingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count = binding.tvClensingTime.text.toString().toIntOrNull() ?: 0

        // "+" 버튼 클릭 리스너
        binding.ivClensingPlusBtn.setOnClickListener {
            count++
            binding.tvClensingTime.text = count.toString()
        }

        // "-" 버튼 클릭 리스너
        binding.ivClensingMinusBtn.setOnClickListener {
            count--
            if (count < 0) {
                Toast.makeText(this, "0 미만은 입력할 수 없어요", Toast.LENGTH_SHORT).show()
                count = 0
            }
            binding.tvClensingTime.text = count.toString()
        }

        binding.ibClensingNext.setOnClickListener {
            val intent = Intent(this, InitialInfoActivity::class.java)
            startActivity(intent)
        }

        binding.ivInitialClensingCheckOff.setOnClickListener {
            binding.ivInitialClensingCheckOff.visibility = View.GONE
            binding.ivInitialClensingCheckOn.visibility = View.VISIBLE
            IsClensing = false
        }

        binding.ivInitialClensingCheckOn.setOnClickListener {
            binding.ivInitialClensingCheckOff.visibility = View.VISIBLE
            binding.ivInitialClensingCheckOn.visibility = View.GONE
            IsClensing = true
        }

    }
}
