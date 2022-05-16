package com.pamento.customfancontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pamento.customfancontroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.switcherOnOff.onCheckListener = object : SwitchView.OnCheckListener {
//            override fun onSwitchToggled(check: Boolean, switched: SwitchView) {
//                Log.i("SWITCHER", "onSwitchToggled: MainActivity::$check")
//                binding.switcherOnOff.setSwitchChecked()
//            }
//        }
        binding.switcherOnOff.setOnClickListener {
            Log.i("SWITCHER", "onCreate: MA__it.view::$it")
            (it as SwitchView).setSwitchChecked()
        }
    }



}