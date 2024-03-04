package dev.redfox.android_native_memoneet_assigment.utils

import dev.redfox.android_native_memoneet_assigment.domain.model.QuizQuestion

class QuestionLoader {

        companion object {

            fun loadBloodQuestions(): List<QuizQuestion> {
                val bloodQuestions = mutableListOf<QuizQuestion>()

                val question1 = QuizQuestion(
                    1,
                    "What is the main function of red blood cells?",
                    0,
                    listOf(
                        "Transporting oxygen",
                        "Producing antibodies",
                        "Filtering toxins",
                        "Storing nutrients"
                    )
                )

                val question2 = QuizQuestion(
                    2,
                    "Which blood type is considered the universal donor?",
                    1,
                    listOf(
                        "O-negative",
                        "A-positive",
                        "B-negative",
                        "AB-positive"
                    )
                )

                val question3 = QuizQuestion(
                    3,
                    "What is the medical term for the process of blood clotting?",
                    2,
                    listOf(
                        "Coagulation",
                        "Oxidation",
                        "Denaturation",
                        "Filtration"
                    )
                )

                val question4 = QuizQuestion(
                    4,
                    "Which organ produces most of the proteins involved in blood clotting?",
                    0,
                    listOf(
                        "Liver",
                        "Heart",
                        "Lungs",
                        "Pancreas"
                    )
                )

                val question5 = QuizQuestion(
                    5,
                    "What is the average lifespan of a red blood cell in the human body?",
                    1,
                    listOf(
                        "120 days",
                        "30 days",
                        "1 year",
                        "5 days"
                    )
                )

                val question6 = QuizQuestion(
                    6,
                    "What is the role of platelets in the blood?",
                    2,
                    listOf(
                        "Blood clotting",
                        "Oxygen transport",
                        "Immune response",
                        "Nutrient absorption"
                    )
                )

                val question7 = QuizQuestion(
                    7,
                    "Which vitamin is essential for the synthesis of blood clotting factors?",
                    0,
                    listOf(
                        "Vitamin K",
                        "Vitamin C",
                        "Vitamin D",
                        "Vitamin B12"
                    )
                )

                val question8 = QuizQuestion(
                    8,
                    "In which part of the body does hematopoiesis (blood cell formation) primarily occur?",
                    1,
                    listOf(
                        "Bone marrow",
                        "Liver",
                        "Spleen",
                        "Kidneys"
                    )
                )

                val question9 = QuizQuestion(
                    9,
                    "What is the liquid component of blood that suspends cells and platelets?",
                    2,
                    listOf(
                        "Plasma",
                        "Serum",
                        "Lymph",
                        "Mucus"
                    )
                )

                val question10 = QuizQuestion(
                    10,
                    "Which hormone stimulates the production of red blood cells?",
                    0,
                    listOf(
                        "Erythropoietin",
                        "Insulin",
                        "Thyroxine",
                        "Cortisol"
                    )
                )

                bloodQuestions.add(question1)
                bloodQuestions.add(question2)
                bloodQuestions.add(question3)
                bloodQuestions.add(question4)
                bloodQuestions.add(question5)
                bloodQuestions.add(question6)
                bloodQuestions.add(question7)
                bloodQuestions.add(question8)
                bloodQuestions.add(question9)
                bloodQuestions.add(question10)

                return bloodQuestions
            }
        }
    }

