package com.scriptech.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var Player = true
    var TurnCount = 0


    var boardStatus = Array(3){ IntArray(3) }

    lateinit var board: Array<Array<Button>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9: Button = findViewById<Button>(R.id.btn9)
        val btnReset = findViewById<Button>(R.id.btnReset)

        var txtPlayer =findViewById<TextView>(R.id.txtPlayer)




        board = arrayOf(
            arrayOf(btn1, btn2, btn3),
            arrayOf(btn4, btn5, btn6),
            arrayOf(btn7, btn8, btn9)
        )

        for (i in board) {
            for (btn1 in i) {
                btn1.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        btnReset.setOnClickListener {
            Player = true
            TurnCount = 0
            initializeBoardStatus()
            txtPlayer.text="____"
        }
    }

    private fun initializeBoardStatus() {
        for (i in 0..2){
            for (j in 0..2){
                boardStatus[i][j] ==-1
            }
        }

        for (i in board){
            for (btn1 in i){
                btn1.isEnabled=true
                btn1.text=""
            }
        }
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.btn1 -> {
                updateValue(row = 0, col = 0, player = Player)
            }
            R.id.btn2 -> {
                updateValue(row = 0, col = 1, player = Player)
            }
            R.id.btn3 -> {
                updateValue(row = 0, col = 2, player = Player)
            }
            R.id.btn4 -> {
                updateValue(row = 1, col = 0, player = Player)
            }
            R.id.btn5 -> {
                updateValue(row = 1, col = 1, player = Player)
            }
            R.id.btn6 -> {
                updateValue(row = 1, col = 2, player = Player)
            }
            R.id.btn7 -> {
                updateValue(row = 2, col = 0, player = Player)
            }
            R.id.btn8 -> {
                updateValue(row = 2, col = 1, player = Player)
            }
            R.id.btn9 -> {
                updateValue(row = 2, col = 2, player = Player)
            }

        }
        TurnCount++
        Player = !Player

        if (Player) {
            updateDisplay("Player X Turn")
        } else {
            updateDisplay("Player O Turn")
        }

        if (TurnCount == 9){
            updateDisplay("Game is Draw")
        }

        checkWinner()
    }

    private fun checkWinner() {
    // Horizontal Rows
        for (i in 0..2){
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if (boardStatus[i][0]==1){
                    updateDisplay("Player X Winner")
                    break
                }else if (boardStatus[i][0]==0){
                    updateDisplay("Player O winner")
                    break
                }
            }
        }

        // Vertical Column
        for (i in 0..2){
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if (boardStatus[0][i]==1){
                    updateDisplay("Player X Winner")
                    break
                }else if (boardStatus[0][i]==0){
                    updateDisplay("Player O winner")
                    break
                }
            }
        }

        // First Diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if (boardStatus[0][0]==1){
                updateDisplay("Player X Winner")

            }else if (boardStatus[0][0]==0){
                updateDisplay("Player O winner")

            }
        }

        // Second Diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
            if (boardStatus[0][2]==1){
                updateDisplay("Player X Winner")

            }else if (boardStatus[0][2]==0){
                updateDisplay("Player O winner")

            }
        }
    }


    private fun updateDisplay(text: String) {
    val txtPlayer = findViewById<TextView>(R.id.txtPlayer)
    txtPlayer.text = text
        if (text.contains("Winner")){
            disableButton()
        }
    }

    private fun disableButton(){
        for (i in board) {
            for (btn1 in i) {
                btn1.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
    val text:String = if (player) "X" else "O"
        val value = if (player) 1 else 0


        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] =value
    }
}