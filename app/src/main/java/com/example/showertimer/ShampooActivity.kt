package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.showertimer.databinding.ActivityShampooBinding
import com.example.showertimer.databinding.ActivityToothbrushBinding

class ShampooActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShampooBinding
    private var IsShampoo = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityShampooBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count = binding.tvShampooTime.text.toString().toIntOrNull() ?: 0

        // "+" 버튼 클릭 리스너
        binding.ivShampooPlusBtn.setOnClickListener {
            count++
            binding.tvShampooTime.text = count.toString()
        }

        // "-" 버튼 클릭 리스너
        binding.ivShampooMinusBtn.setOnClickListener {
            count--
            if (count < 0) {
                Toast.makeText(this, "0 미만은 입력할 수 없어요", Toast.LENGTH_SHORT).show()
                count = 0
            }
            binding.tvShampooTime.text = count.toString()
        }

        binding.ivInitialShampooCheckOff.setOnClickListener {
            binding.ivInitialShampooCheckOff.visibility = View.GONE
            binding.ivInitialShampooCheckOn.visibility = View.VISIBLE
            IsShampoo = false
        }

        binding.ivInitialShampooCheckOn.setOnClickListener {
            binding.ivInitialShampooCheckOff.visibility = View.VISIBLE
            binding.ivInitialShampooCheckOn.visibility = View.GONE
            IsShampoo = true
        }


        binding.ibShampooNext.setOnClickListener {
            val intent = Intent(this, ShavingActivity::class.java)
            startActivity(intent)
        }
    }
}
