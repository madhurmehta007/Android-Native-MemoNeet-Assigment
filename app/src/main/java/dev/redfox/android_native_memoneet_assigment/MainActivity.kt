package dev.redfox.android_native_memoneet_assigment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dev.redfox.android_native_memoneet_assigment.databinding.ActivityMainBinding
import dev.redfox.android_native_memoneet_assigment.presentation.viewmodel.CapsuleViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var capsuleViewModel: CapsuleViewModel
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        capsuleViewModel = ViewModelProvider(this)[CapsuleViewModel::class.java]
        navController = findNavController(R.id.navHostFragment)
    }
}