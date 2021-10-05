package com.example.cursortest

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cursortest.databinding.ActivityMainBinding

/**
 * 用cursor搜尋手機內所有圖片
 * 用RecyclerView跟GridLayoutManager顯示圖片清單
 *
 * #實做點圖放大
 * #權限要求
 */
class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissionRegister.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
    }

    private val requestPermissionRegister =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            // Handle Permission granted/rejected
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value
                if (isGranted) {
                    // Permission is granted
                    adapter = ImageAdapter(viewModel.imageList)
                    binding.recyclerViewMain.adapter = adapter
                    binding.recyclerViewMain.layoutManager = GridLayoutManager(this, 4)
                } else {
                    // Permission is denied
                    Toast.makeText(this, "please give permission", Toast.LENGTH_SHORT).show()
                }
            }
        }

}