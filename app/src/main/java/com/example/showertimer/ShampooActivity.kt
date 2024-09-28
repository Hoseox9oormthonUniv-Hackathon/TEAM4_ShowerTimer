package com.example.showertimer

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.showertimer.databinding.ActivityShampooBinding
import com.example.showertimer.databinding.ActivityToothbrushBinding

class ShampooActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShampooBinding
    private var isShampoo = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityShampooBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count = binding.tvShampooTime.text.toString().toIntOrNull() ?: 0

        val showerTime = intent.getIntExtra("샤워 시간", 15)

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
            isShampoo = false
        }

        binding.ivInitialShampooCheckOn.setOnClickListener {
            binding.ivInitialShampooCheckOff.visibility = View.VISIBLE
            binding.ivInitialShampooCheckOn.visibility = View.GONE
            isShampoo = true
        }


        binding.ibShampooNext.setOnClickListener {
            val intent = Intent(this, CleansingActivity::class.java)
            intent.putExtra("샤워 시간", showerTime)
            intent.putExtra("샴푸 시간", count)
            intent.putExtra("샴푸 여부", isShampoo)
            startActivity(intent)
            overridePendingTransition(R.anim.none, R.anim.none)
        }

        binding.ibShampooBack.setOnClickListener {
            val intent = Intent(this, ShowerActivity::class.java)
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
