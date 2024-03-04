package dev.redfox.android_native_memoneet_assigment.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.redfox.android_native_memoneet_assigment.domain.model.QuizQuestion
import dev.redfox.android_native_memoneet_assigment.utils.Constants.Companion.CORRECT_ANSWER
import dev.redfox.android_native_memoneet_assigment.utils.Constants.Companion.CORRECT_ANSWERS_COUNT
import dev.redfox.android_native_memoneet_assigment.utils.Constants.Companion.TOTAL_ANSWERED
import dev.redfox.android_native_memoneet_assigment.utils.Constants.Companion.UNANSWERED_COUNT
import dev.redfox.android_native_memoneet_assigment.utils.Constants.Companion.WRONG_ANSWER
import dev.redfox.android_native_memoneet_assigment.utils.QuestionLoader

class CapsuleViewModel:ViewModel() {
    var currentPosition: Int = 0

    var quizQuestions = listOf<QuizQuestion>()
    val userAnswers: MutableLiveData<List<Int>> = MutableLiveData()

    init {
        quizQuestions = QuestionLoader.loadBloodQuestions()
        userAnswers.value = MutableList(quizQuestions.size) { -1 }
    }

    fun convertSecondsToFormattedTime(seconds: Int): String {
        val minutes = (seconds % 3600) / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }

    fun calculateResults(): Map<String, Int> {
        val answers = userAnswers.value!!
        var totalAnswered = 0
        var correctAnswersCount = 0

        for (i in 0 until quizQuestions.size) {
            if (answers[i] != -1) {
                totalAnswered++
                if (quizQuestions[i].answer == answers[i]) {
                    correctAnswersCount++
                }
            }
        }

        val unansweredCount = quizQuestions.size - totalAnswered
        val incorrectAnswersCount = totalAnswered - correctAnswersCount
        val scorePercentage = correctAnswersCount * 100 / quizQuestions.size

        val resultsMap: HashMap<String, Int> = HashMap()
        resultsMap.put(TOTAL_ANSWERED, totalAnswered)
        resultsMap.put(UNANSWERED_COUNT, unansweredCount)
        resultsMap.put(CORRECT_ANSWER, correctAnswersCount)
        resultsMap.put(WRONG_ANSWER, incorrectAnswersCount)
        resultsMap.put(CORRECT_ANSWERS_COUNT, scorePercentage)

        return resultsMap
    }

}