package com.singh.androidcb

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.w3c.dom.Text
import java.security.cert.TrustAnchor

lateinit var board:Array<Array<Button>>
var player =true
var Turn_count=0

var boardStatus=Array(3){IntArray(3)}
class MainActivity : AppCompatActivity() , View.OnClickListener {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn1:Button=findViewById(R.id.btn1)
        var btn2:Button=findViewById(R.id.btn2)
        var btn3:Button=findViewById(R.id.btn3)
        var btn4:Button=findViewById(R.id.btn4)
        var btn5:Button=findViewById(R.id.btn5)
        var btn6:Button=findViewById(R.id.btn6)
        var btn7:Button=findViewById(R.id.btn7)
        var btn8:Button=findViewById(R.id.btn8)
        var btn9:Button=findViewById(R.id.btn9)
        var resetbtn:Button=findViewById(R.id.rbtn)
        var textVIew:TextView=findViewById(R.id.textview)




    board= arrayOf(

        arrayOf(btn1,btn2,btn3),
        arrayOf(btn4,btn5,btn6),
        arrayOf(btn7,btn8,btn9)
    )
    for(i in board){
    for(button in i){
        button.setOnClickListener(this)
    }
    }

    // function for Reset Button
    intilizeBoardStatus()

    resetbtn.setOnClickListener{
        player=true
        Turn_count=0
        intilizeBoardStatus()

        }
}

    private fun intilizeBoardStatus() {
        for(i in 0..2){
            for (j in 0..2){
                boardStatus[i][j]=-1



            }
        }

        for(i in board){
            for (button in  i){

                button.isEnabled=true
                button.text=""
            }
        }
    }


    override fun onClick(view: View) {
    when(view.id){

        R.id.btn1->{
            updateValue(row=0,col=0, player= player)
            Toast.makeText(this, "Clicked Button1", Toast.LENGTH_SHORT).show()


        }
        R.id.btn2->{
            updateValue(row=0,col=1, player= player)
        }
        R.id.btn3->{
            updateValue(row=0,col=2, player= player)
        }
        R.id.btn4->{
            updateValue(row=1,col=0, player= player)
        }
        R.id.btn5->{
            updateValue(row=1,col=1, player= player)
        }
        R.id.btn6->{
            updateValue(row=1,col=2, player= player)
        }
        R.id.btn7->{
            updateValue(row=2,col=0, player= player)
        }
        R.id.btn8->{
            updateValue(row=2,col=1, player= player)
        }
        R.id.btn9->{

            updateValue(row=2,col=2, player= player)
        }



}
        Turn_count++
        player=!player


        if(player){
            updateDisplay("player X Turn")
        }else(
            updateDisplay("player 0 Turn")

        )
            if (Turn_count==9){
                updateDisplay("Game Draw")

            }
        checkWinner()

    }

    private fun checkWinner() {
        //horizental rows
        for (i in 0..2){
            if(boardStatus[i][0]== boardStatus[i][1] && boardStatus[i][0]== boardStatus[i][2]){
                if (boardStatus[i][0]==1){
                    updateDisplay("Player x is won the match ")
                    break
                }

                else if (boardStatus[i][0]==0){
                    updateDisplay("Player 0 is won the match ")
                    break
                }
            }

        }
        //vertical coumum

        for (i in 0..2){
            if(boardStatus[0][i]== boardStatus[1][i] && boardStatus[0][i]== boardStatus[2][i]){
                if (boardStatus[0][i]==1){
                    updateDisplay("Player x is won the match ")
                    break
                }

                else if (boardStatus[0][i]==0){
                    updateDisplay("Player 0 is won the match ")
                    break
                }
            }

        }

        //First Dia
        if(boardStatus[0][0]== boardStatus[1][1] && boardStatus[0][0]== boardStatus[2][2]){
            if (boardStatus[0][0]==1){
                updateDisplay("Player x is won the match ")

            }

            else if (boardStatus[0][0]==0){
                updateDisplay("Player 0 is won the match ")

            }
        }
        //second Dia
        if(boardStatus[0][2]== boardStatus[1][1] && boardStatus[0][2]== boardStatus[2][0]){
            if (boardStatus[0][2]==1){
                updateDisplay("Player x is won the match ")

            }

            else if (boardStatus[0][2]==0){
                updateDisplay("Player 0 is won the match ")

            }
        }
    }

    private fun updateDisplay(text: String) {
        var textVIew:TextView=findViewById(R.id.textview)

        textVIew.text=text

        if (text.contains("won")){
         btnDis()

        }
    }
private fun btnDis(){
    for(i in board){
        for(button in i){
            button.isEnabled=false

        }
    }

}
    private fun updateValue(row: Int, col: Int, player: Boolean) {
    val text:String=if (player) "x" else "0"
    val value:Int=if (player) 1 else 0
        board[row][col].apply {

            isEnabled=false
            setText(text)
        }
        boardStatus[row][col]=value
    }
}