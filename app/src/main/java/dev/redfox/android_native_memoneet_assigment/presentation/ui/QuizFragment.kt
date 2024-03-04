package dev.redfox.android_native_memoneet_assigment.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import dev.redfox.android_native_memoneet_assigment.R
import dev.redfox.android_native_memoneet_assigment.databinding.BottomSheetBinding
import dev.redfox.android_native_memoneet_assigment.databinding.FragmentQuizBinding
import dev.redfox.android_native_memoneet_assigment.presentation.adapter.QuizRecyclerViewAdapter
import dev.redfox.android_native_memoneet_assigment.presentation.viewmodel.CapsuleViewModel

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var adapter: QuizRecyclerViewAdapter
    private val capsuleViewModel: CapsuleViewModel by activityViewModels()
    private var currentQuestion = 0
    private lateinit var dialogBinding: BottomSheetBinding
    private lateinit var bottomSheet: BottomSheetDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerSetup()
        initializeDialog()
        setQuestion(currentQuestion)


        val questionList = capsuleViewModel.quizQuestions

        binding.nextButton.setOnClickListener {
            if (currentQuestion == questionList.size - 1) {
                val viewPager: ViewPager2? = activity?.findViewById(R.id.view_pager)
                viewPager?.currentItem = 3

            } else {
                currentQuestion++
                setQuestion(currentQuestion)
                if (currentQuestion > 0) {
                    binding.previousButton.isEnabled = true
                }

            }

        }
        binding.previousButton.isEnabled = false
        binding.previousButton.setOnClickListener {


            if (currentQuestion <= 0) {
                it.isEnabled = false
            } else {
                currentQuestion--
                it.isEnabled = true
                setQuestion(currentQuestion)
            }
        }

        binding.checkAnsButton.setOnClickListener {
            checkAnswer()
        }

    }

    private fun checkAnswer() {

        val selectedOption = capsuleViewModel.userAnswers.value!![currentQuestion]
        val question = capsuleViewModel.quizQuestions[currentQuestion]

        if (selectedOption == -1) {
            Toast.makeText(context, "Please select an option", Toast.LENGTH_SHORT).show()
        } else if (selectedOption == question.answer) {
            dialogBinding.tvAnswerType.text = "Great!!"
            bottomSheet.show()

        } else {
            dialogBinding.tvAnswerType.text = "Oops!!"
            dialogBinding.tvAnswerText.text = "Correct Answer: ${question.options[question.answer]}"
            bottomSheet.show()
        }


    }

    private fun initializeDialog() {
        dialogBinding = BottomSheetBinding.inflate(layoutInflater)
        bottomSheet = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(dialogBinding.root)
        bottomSheet.setCancelable(true)

    }

    private fun setQuestion(questionId: Int) {
        val questionList = capsuleViewModel.quizQuestions
        binding.tvQuestion.text = "Q${(questionId + 1)}.${questionList[questionId].question}"
        binding.progressText.text = "Question ${(questionId + 1)}/${questionList.size}"
        binding.progressBar.progress = ((questionId + 1) / questionList.size) * 100
        setOptions(questionId)
    }

    private fun recyclerSetup() {
        binding.rvOptions.layoutManager = LinearLayoutManager(requireContext())
        adapter = QuizRecyclerViewAdapter { selectedOption: Int -> itemSelected(selectedOption) }
        binding.rvOptions.adapter = adapter

    }

    private fun setOptions(questionId: Int) {
        val selectedPosition = capsuleViewModel.userAnswers.value!![questionId]
        adapter.setList(capsuleViewModel.quizQuestions[questionId].options)
        if (selectedPosition != -1) {
            adapter.setSelection(selectedPosition)
        } else {
            adapter.setSelection(RecyclerView.NO_POSITION)
        }

        adapter.notifyDataSetChanged()
    }

    private fun itemSelected(selectedOption: Int) {
        val newAnsList = capsuleViewModel.userAnswers.value!!.toMutableList()
        newAnsList[currentQuestion] = selectedOption
        capsuleViewModel.userAnswers.value = newAnsList

    }
}