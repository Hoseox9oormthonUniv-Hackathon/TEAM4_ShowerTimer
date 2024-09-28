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
        val toothTime = intent.getIntExtra("세안 시간", 2)
        val showerTime = intent.getIntExtra("샤워 시간", 15)
        val shampooTime = intent.getIntExtra("샴푸 시간", 5)
        val shavingTime = intent.getIntExtra("면도 시간", 3)

        // "+" 버튼 클릭 리스너
        binding.ivToothbrushPlusBtn.setOnClickListener {
            count++
            binding.tvToothbrushTime.text = count.toString()
        }

        // "-" 버튼 클릭 리스너
        binding.ivToothbrushMinusBtn.setOnClickListener {
            count--
            if (count < 0) {
                Toast.makeText(this, "0 미만은 입력할 수 없어요", Toast.LENGTH_SHORT).show()
                count = 0
            }
            binding.tvToothbrushTime.text = count.toString()
        }

        binding.ivInitialToothbrushCheckOff.setOnClickListener {
            binding.ivInitialToothbrushCheckOff.visibility = View.GONE
            binding.ivInitialToothbrushCheckOn.visibility = View.VISIBLE
            IsToothbrush = false
        }

        binding.ivInitialToothbrushCheckOn.setOnClickListener {
            binding.ivInitialToothbrushCheckOff.visibility = View.VISIBLE
            binding.ivInitialToothbrushCheckOn.visibility = View.GONE
            IsToothbrush = true
        }

        binding.ibToothbrushNext.setOnClickListener {
            val intent = Intent(this, InitialInfoActivity::class.java)
            intent.putExtra("양치 시간", count)
            intent.putExtra("세안 시간", toothTime)
            intent.putExtra("샤워 시간", showerTime)
            intent.putExtra("샴푸 시간", shampooTime)
            intent.putExtra("면도 시간", shavingTime)
            startActivity(intent)
        }
    }
}
