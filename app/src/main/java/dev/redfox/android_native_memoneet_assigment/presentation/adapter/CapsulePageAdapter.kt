package dev.redfox.android_native_memoneet_assigment.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.redfox.android_native_memoneet_assigment.presentation.ui.NotesFragment
import dev.redfox.android_native_memoneet_assigment.presentation.ui.QuizFragment
import dev.redfox.android_native_memoneet_assigment.presentation.ui.QuizResultFragment
import dev.redfox.android_native_memoneet_assigment.presentation.ui.VideoFragment

class CapsulePageAdapter(fragmentManager: FragmentManager,
                         lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VideoFragment()
            1 -> NotesFragment()
            2 -> QuizFragment()
            3 -> QuizResultFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }

    }
}