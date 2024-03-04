package dev.redfox.android_native_memoneet_assigment.presentation.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import dev.redfox.android_native_memoneet_assigment.R
import dev.redfox.android_native_memoneet_assigment.databinding.FragmentVideoBinding
import dev.redfox.android_native_memoneet_assigment.presentation.viewmodel.CapsuleViewModel

class VideoFragment : Fragment() {

    private lateinit var binding: FragmentVideoBinding
    private val capsuleViewModel: CapsuleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoPath = "android.resource://" + "dev.redfox.android_native_memoneet_assigment" + "/" + R.raw.blood_video
        binding.videoView.setVideoURI(Uri.parse(videoPath))

        val mediaController = android.widget.MediaController(context)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)

        binding.videoView.requestFocus()
        mediaController.setMediaPlayer(binding.videoView)
        binding.videoView.start()

        binding.bottomCard.setOnClickListener {
            val viewPager: ViewPager2? = activity?.findViewById(R.id.view_pager)
            viewPager?.currentItem = 1

    }
    }

    override fun onPause() {
        super.onPause()
        binding.videoView.pause()
        capsuleViewModel.currentPosition = binding.videoView.currentPosition

    }

    override fun onResume() {
        super.onResume()
        binding.videoView.seekTo(capsuleViewModel.currentPosition)
        binding.videoView.start()
    }
}