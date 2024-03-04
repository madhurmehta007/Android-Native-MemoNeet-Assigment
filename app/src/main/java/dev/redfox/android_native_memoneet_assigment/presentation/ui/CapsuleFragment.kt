package dev.redfox.android_native_memoneet_assigment.presentation.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dev.redfox.android_native_memoneet_assigment.R
import dev.redfox.android_native_memoneet_assigment.databinding.FragmentCapsuleBinding
import dev.redfox.android_native_memoneet_assigment.presentation.adapter.CapsulePageAdapter
import dev.redfox.android_native_memoneet_assigment.presentation.viewmodel.CapsuleViewModel
import kotlinx.coroutines.Runnable

class CapsuleFragment : Fragment() {

    private lateinit var binding: FragmentCapsuleBinding
    private lateinit var adapter: CapsulePageAdapter
    private var remainingTimeInSeconds: Int = 600
    private var timeHandler = Handler(Looper.getMainLooper())
    private lateinit var timerRunnable: Runnable

    private val capsuleViewModel: CapsuleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCapsuleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = CapsulePageAdapter(childFragmentManager, lifecycle)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Video"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Note"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Quiz"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Result"))

        binding.viewPager.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    if (tab.position == 2) {
                        binding.appbar.background = ColorDrawable(
                            ContextCompat.getColor(
                                context!!,
                                R.color.white
                            )
                        )
                        activity?.window?.statusBarColor =
                            ContextCompat.getColor(requireActivity(), R.color.white)
                        binding.tabLayout.visibility = View.GONE
                    } else {
                        binding.appbar.background =
                            ColorDrawable(ContextCompat.getColor(context!!, R.color.white))
                        activity?.window?.statusBarColor =
                            ContextCompat.getColor(requireActivity(), R.color.white)
                        binding.tabLayout.visibility = View.VISIBLE
                    }
                    binding.viewPager.currentItem = tab.position
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        startTimer()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

            findNavController().navigateUp()

            true
        }

    }

    private fun startTimer() {
        timerRunnable = object : Runnable {
            override fun run() {
                if (remainingTimeInSeconds > 0) {
                    remainingTimeInSeconds--
                    binding.timerText.text =
                        capsuleViewModel.convertSecondsToFormattedTime(remainingTimeInSeconds) + " min"
                    timeHandler.postDelayed(this, 1000) // Delay 1 second
                } else {
                    Toast.makeText(context, "Time Up!", Toast.LENGTH_SHORT).show()
                    binding.timerText.text = "00:00"
                    timeHandler.removeCallbacks(timerRunnable)
                    findNavController().navigateUp()
                }
            }
        }
        timeHandler.postDelayed(timerRunnable, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        timeHandler.removeCallbacks(timerRunnable)
    }
}