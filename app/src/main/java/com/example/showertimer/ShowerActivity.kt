package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.showertimer.databinding.ActivityShowerBinding
import com.example.showertimer.databinding.ActivityToothbrushBinding

class ShowerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowerBinding
    private var IsToothbrush = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityShowerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count = binding.tvShowerTime.text.toString().toIntOrNull() ?: 0

        // "+" 버튼 클릭 리스너
        binding.ivShowerPlusBtn.setOnClickListener {
            count++
            binding.tvShowerTime.text = count.toString()
        }

        // "-" 버튼 클릭 리스너
        binding.ivShowerMinusBtn.setOnClickListener {
            count--
            if (count < 0) {
                Toast.makeText(this, "0 미만은 입력할 수 없어요", Toast.LENGTH_SHORT).show()
                count = 0
            }
            binding.tvShowerTime.text = count.toString()
        }

        binding.ibShowerNext.setOnClickListener {
            val intent = Intent(this, ShampooActivity::class.java)
            intent.putExtra("샤워 시간", count)
            startActivity(intent)
        }

        binding.ibShowerBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.none, R.anim.none)
        }

    }
}
