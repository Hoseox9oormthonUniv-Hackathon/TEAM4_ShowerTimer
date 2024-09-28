package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.showertimer.databinding.ActivityShavingBinding

class ShavingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShavingBinding
    private var isShaving = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityShavingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count = binding.tvShavingTime.text.toString().toIntOrNull() ?: 0
        val showerTime = intent.getIntExtra("샤워 시간", 15)
        val shampooTime = intent.getIntExtra("샴푸 시간", 5)
        val cleansingTime = intent.getIntExtra("세안 시간", 2)
        val isCleansing = intent.getBooleanExtra("세안 여부", true)
        val isShampoo = intent.getBooleanExtra("샴푸 여부", true)

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
            isShaving = false
        }

        binding.ivInitialShavingCheckOn.setOnClickListener {
            binding.ivInitialShavingCheckOff.visibility = View.VISIBLE
            binding.ivInitialShavingCheckOn.visibility = View.GONE
            isShaving = true
        }

        binding.ibShavingNext.setOnClickListener {
            val intent = Intent(this, ToothbrushActivity::class.java)

            intent.putExtra("세안 시간", count)
            intent.putExtra("샤워 시간", showerTime)
            intent.putExtra("샴푸 시간", shampooTime)
            intent.putExtra("샴푸 여부", isShampoo)
            intent.putExtra("세안 여부", isCleansing)
            intent.putExtra("면도 여부", isShaving)
            startActivity(intent)
            overridePendingTransition(R.anim.none, R.anim.none)
        }

        binding.ibShavingBack.setOnClickListener {
            val intent = Intent(this, CleansingActivity::class.java)
            startActivity(intent);
            overridePendingTransition(R.anim.none, R.anim.none)
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
            overridePendingTransition(R.anim.none, R.anim.none)
            return true // 이벤트를 여기서 처리했음을 반환
        }
        return super.onKeyDown(keyCode, event)
    }
}
