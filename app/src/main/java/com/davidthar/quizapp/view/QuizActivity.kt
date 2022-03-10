package com.davidthar.quizapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davidthar.quizapp.R
import com.davidthar.quizapp.databinding.ActivityQuizBinding
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.davidthar.quizapp.viewmodel.QuizViewModel
import kotlin.random.Random


class QuizActivity : AppCompatActivity() {

    private lateinit var binding : ActivityQuizBinding
    private val quizViewModel : QuizViewModel by viewModels()
    private var questionNumber = 0
    private var correctAnswer = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val randomSet = createRandomArray()

        quizViewModel.quizModel.observe(this, Observer {
            //Load Image
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(this).load(it.url).apply(requestOptions).into(binding.ivImage)

            binding.tvQuestion.text = it.question
            binding.btnAnswer1.text = it.answer1
            binding.btnAnswer2.text = it.answer2
            binding.btnAnswer3.text = it.answer3
            correctAnswer = it.correctAnswer
        })

        //Set first question
        quizViewModel.setQuestion(randomSet)
        questionNumber++


        binding.placeholderTimer.setOnClickListener {
            quizViewModel.setQuestion(randomSet)
        }

        binding.btnBack.setOnClickListener {
            this.onBackPressed()
        }
    }


    private fun createRandomArray(): HashSet<Int>{
        val size = 10
        val s = HashSet<Int>(size)
        while (s.size < size) {
            s += Random.nextInt(0,30)
        }
        return  s
    }
}