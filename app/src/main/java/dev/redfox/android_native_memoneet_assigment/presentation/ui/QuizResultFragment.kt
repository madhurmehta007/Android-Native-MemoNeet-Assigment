package dev.redfox.android_native_memoneet_assigment.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dev.redfox.android_native_memoneet_assigment.R
import dev.redfox.android_native_memoneet_assigment.databinding.FragmentQuizResultBinding
import dev.redfox.android_native_memoneet_assigment.presentation.viewmodel.CapsuleViewModel
import dev.redfox.android_native_memoneet_assigment.utils.Constants
import dev.redfox.android_native_memoneet_assigment.utils.Constants.Companion.WRONG_ANSWER

class QuizResultFragment : Fragment() {
    private lateinit var binding: FragmentQuizResultBinding
    private val capsuleViewModel: CapsuleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizResultBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        capsuleViewModel.userAnswers.observe(viewLifecycleOwner, Observer {
            val map = capsuleViewModel.calculateResults()
            var accuracy = ""
             accuracy ="${
                map.get(Constants.CORRECT_ANSWER)?.toFloat()
                    ?.div(map.get(Constants.TOTAL_ANSWERED)!!.toFloat())?.times(100)
            }"
                binding.totalNumber.text = "${capsuleViewModel.quizQuestions.size}"
            binding.answeredNumber.text = "${map.get(Constants.TOTAL_ANSWERED)}"
            binding.unansweredNumber.text = "${map.get(Constants.UNANSWERED_COUNT)}"
            binding.correctNumber.text = "${map.get(Constants.CORRECT_ANSWER)}"
            binding.wrongNumber.text = "${map.get(WRONG_ANSWER)}"
            binding.tvScoreValue.text = "${map.get(Constants.CORRECT_ANSWERS_COUNT)} points"
            if(accuracy != "NaN"){
                binding.tvAccuracyValue.text = "${accuracy} %"
            }else{
                binding.tvAccuracyValue.text = "0.0 %"
            }

        })

        binding.btnEndCapsule.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}