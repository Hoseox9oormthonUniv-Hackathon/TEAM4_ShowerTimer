package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.showertimer.databinding.ActivityCleansingBinding

class CleansingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCleansingBinding
    private var isCleansing = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityCleansingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count = binding.tvCleanserTime.text.toString().toIntOrNull() ?: 0
        val showerTime = intent.getIntExtra("샤워 시간", 15)
        val shampooTime = intent.getIntExtra("샴푸 시간", 5)
        val isShampoo = intent.getBooleanExtra("샴푸 여부", true)

        // "+" 버튼 클릭 리스너
        binding.ivCleanserPlusBtn.setOnClickListener {
            count++
            binding.tvCleanserTime.text = count.toString()
        }

        // "-" 버튼 클릭 리스너
        binding.ivCleanserMinusBtn.setOnClickListener {
            count--
            if (count < 0) {
                Toast.makeText(this, "0 미만은 입력할 수 없어요", Toast.LENGTH_SHORT).show()
                count = 0
            }
            binding.tvCleanserTime.text = count.toString()
        }

        binding.ivInitialCleanserCheckOff.setOnClickListener {
            binding.ivInitialCleanserCheckOff.visibility = View.GONE
            binding.ivInitialCleanserCheckOn.visibility = View.VISIBLE
            isCleansing = false
        }

        binding.ivInitialCleanserCheckOn.setOnClickListener {
            binding.ivInitialCleanserCheckOff.visibility = View.VISIBLE
            binding.ivInitialCleanserCheckOn.visibility = View.GONE
            isCleansing = true
        }

        binding.ibCleanserNext.setOnClickListener {
            val intent = Intent(this, ShavingActivity::class.java)
            intent.putExtra("세안 시간", count)
            intent.putExtra("샤워 시간", showerTime)
            intent.putExtra("샴푸 시간", shampooTime)
            intent.putExtra("샴푸 여부", isShampoo)
            intent.putExtra("세안 여부", isCleansing)
            startActivity(intent)
            overridePendingTransition(R.anim.none, R.anim.none)
        }

        binding.ibCleansingBack.setOnClickListener {
            val intent = Intent(this, ShampooActivity::class.java)
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
