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

    }
}
