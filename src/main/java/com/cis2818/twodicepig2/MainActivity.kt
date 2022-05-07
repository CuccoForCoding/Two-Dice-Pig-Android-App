package com.cis2818.twodicepig2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cis2818.twodicepig2.databinding.ActivityMainBinding
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var turnTotal: Int = 0
    var currentPlayer = "P1"
    var player1Total = 0
    var player2Total = 0


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHold.setOnClickListener {
            switchPlayer()
        }
    }

    fun onRollDIce(view: View) {
        binding.btnHold.isEnabled = true
        var dice1Value = (1..6).random()
        var dice2Value = (1..6).random()

        displayDiceOne(dice1Value)
        displayDiceTwo(dice2Value)


        if (dice1Value == 1 || dice2Value == 1) {
            switchPlayer()
        }

        else if (dice1Value == dice2Value && dice1Value == 1) {
                turnTotal = 0
            if (currentPlayer == "P1") {
                player1Total = 0
            }else{
                player2Total = 0
            }
            switchPlayer()
        }

        else if (dice1Value == dice2Value) {
            turnTotal += dice1Value + dice2Value
            binding.btnHold.isEnabled = false
            binding.turnTotal.text = "$turnTotal"
        }else{
            turnTotal += dice1Value + dice2Value
            binding.turnTotal.text = "$turnTotal"
        }
    }

    private fun displayDiceOne(value: Int) {
        when (value) {
            1 -> binding.imageDice1.setImageResource(R.drawable.d1)
            2 -> binding.imageDice1.setImageResource(R.drawable.d2)
            3 -> binding.imageDice1.setImageResource(R.drawable.d3)
            4 -> binding.imageDice1.setImageResource(R.drawable.d4)
            5 -> binding.imageDice1.setImageResource(R.drawable.d5)
            6 -> binding.imageDice1.setImageResource(R.drawable.d6)
        }
    }

    private fun displayDiceTwo(value: Int) {
        when (value) {
            1 -> binding.imageDice2.setImageResource(R.drawable.d1)
            2 -> binding.imageDice2.setImageResource(R.drawable.d2)
            3 -> binding.imageDice2.setImageResource(R.drawable.d3)
            4 -> binding.imageDice2.setImageResource(R.drawable.d4)
            5 -> binding.imageDice2.setImageResource(R.drawable.d5)
            6 -> binding.imageDice2.setImageResource(R.drawable.d6)
        }
    }

    private fun switchPlayer () {
        if (currentPlayer == "P1") {
            currentPlayer = "P2"
            player1Total += turnTotal
            turnTotal = 0
            binding.player1Total.text = "$player1Total"
            binding.turnTotal.text = "$turnTotal"
            binding.currentPlayer.text = "$currentPlayer"
        } else {
            currentPlayer = "P1"
            binding.currentPlayer.text = "$currentPlayer"
            player2Total += turnTotal
            turnTotal = 0
            binding.player2Total.text = "$player2Total"
            binding.turnTotal.text = "$turnTotal"
        }
        if (player1Total >= 50 ||  player2Total >= 50) {
            if (player1Total >= 50) {
                Toast.makeText(
                    this, "Player 1 Wins!",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(
                    this, "Player 2 Wins!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.btnHold.isEnabled = false
            binding.btnRoll.isEnabled = false
        } else {
            binding.btnHold.isEnabled = true
            binding.btnRoll.isEnabled = true
        }
    }
}
