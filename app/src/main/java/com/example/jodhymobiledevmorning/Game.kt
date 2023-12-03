package com.example.jodhymobiledevmorning

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Game : AppCompatActivity() {
    private lateinit var startButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var pointsTextView: TextView
    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var sumTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var playAgainButton: Button
    private lateinit var gameRelativeLayout: RelativeLayout

    private val answers = ArrayList<Int>()
    private var score = 0
    private var locationOfCorrectAnswer = 0
    private var numberOfQuestions = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        startButton = findViewById(R.id.startButton)
        sumTextView = findViewById(R.id.sumTextView)
        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        resultTextView = findViewById(R.id.resultTextView)
        pointsTextView = findViewById(R.id.pointsTextView)
        timerTextView = findViewById(R.id.timerTextView)
        playAgainButton = findViewById(R.id.playAgainButton)
        gameRelativeLayout = findViewById(R.id.gameRelativeLayout)

        startButton.setOnClickListener { start(it) }
        playAgainButton.setOnClickListener { playAgain(it) }

        generateQuestion()
    }

    fun start(view: View) {
        startButton.visibility = View.INVISIBLE
        gameRelativeLayout.visibility = RelativeLayout.VISIBLE
        playAgain(findViewById(R.id.playAgainButton))
    }

    fun playAgain(view: View) {
        score = 0
        numberOfQuestions = 0

        timerTextView.text = "30s"
        pointsTextView.text = "0/0"
        resultTextView.text = ""
        playAgainButton.visibility = View.INVISIBLE
        generateQuestion()

        object : CountDownTimer(30100, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = "${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                playAgainButton.visibility = View.VISIBLE
                timerTextView.text = "0s"
                resultTextView.text = "Your score: $score/$numberOfQuestions"
            }
        }.start()
    }

    fun chooseAnswer(view: View) {
        if (view.tag.toString() == locationOfCorrectAnswer.toString()) {
            score++
            resultTextView.text = "Correct!"
        } else {
            resultTextView.text = "Incorrect!"
        }

        numberOfQuestions++
        pointsTextView.text = "$score/$numberOfQuestions"
        generateQuestion()
    }

    private fun generateQuestion() {
        val rand = Random()
        val a = rand.nextInt(21)
        val b = rand.nextInt(21)

        sumTextView.text = "$a + $b"

        locationOfCorrectAnswer = rand.nextInt(4)
        answers.clear()
        var incorrectAnswer: Int

        for (i in 0 until 4) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b)
            } else {
                incorrectAnswer = rand.nextInt(41)
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41)
                }
                answers.add(incorrectAnswer)
            }
        }

        button0.text = answers[0].toString()
        button1.text = answers[1].toString()
        button2.text = answers[2].toString()
        button3.text = answers[3].toString()
    }
}