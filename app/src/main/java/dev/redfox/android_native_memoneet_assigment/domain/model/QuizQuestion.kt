package dev.redfox.android_native_memoneet_assigment.domain.model

data class QuizQuestion(
    val id: Int,
    val question: String,
    val answer: Int,
    val options: List<String>
)
