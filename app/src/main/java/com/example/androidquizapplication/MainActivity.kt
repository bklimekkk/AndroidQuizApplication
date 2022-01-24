/**
 * Contains fragment container view which holds fragments in the application.
 * @author Bartosz Klimek
 * @version 1
 */
package com.example.androidquizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidquizapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //Making sure that back button in the application doesn't do anything
    override fun onBackPressed() {}
}