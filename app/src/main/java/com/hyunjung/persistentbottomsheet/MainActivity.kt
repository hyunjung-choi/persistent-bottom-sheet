package com.hyunjung.persistentbottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.hyunjung.persistentbottomsheet.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var behavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initEvent()
    }

    private fun initEvent() {
        persistentBottomSheetEvent()

        binding.btnExpanded.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.btnCollapsed.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun persistentBottomSheetEvent() {
        behavior = BottomSheetBehavior.from(binding.persistentBottomSheet)
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> Log.d(TAG, "onStateChanged: COLLAPSED")
                    BottomSheetBehavior.STATE_DRAGGING -> Log.d(TAG, "onStateChanged: DRAGGING")
                    BottomSheetBehavior.STATE_EXPANDED -> Log.d(TAG, "onStateChanged: EXPANDED")
                    BottomSheetBehavior.STATE_HIDDEN -> Log.d(TAG, "onStateChanged: HIDDEN")
                    BottomSheetBehavior.STATE_SETTLING -> Log.d(TAG, "onStateChanged: SETTING")
                }
            }

            // 슬라이드 되는 도중 계속 호출
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Toast.makeText(this@MainActivity, "onSlide", Toast.LENGTH_SHORT).show()
            }
        })

    }
}