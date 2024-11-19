package com.example.fragmentexample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_Fragment1: Button = findViewById(R.id.btn_fragment1)
        val btn_Fragment2: Button = findViewById(R.id.btn_fragment2)

        val fragment1 = Fragment1()
        val fragment2 = Fragment2()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment, fragment1)
            commit()
        }

        btn_Fragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, fragment1)
                addToBackStack(null)
                commit()
            }
        }

        btn_Fragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, fragment2)
                addToBackStack(null)
                commit()
            }
        }
    }
}