package com.example.score

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var logres: Int = 0
    var diceval: Int =0
    var isRolled: Boolean = false

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", logres)
        Log.i("lap", "saveInstanceState $logres")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val diceroll = findViewById<Button>(R.id.btrandom)
        val diceplace = findViewById<TextView>(R.id.placeholder)
        val scoreb = findViewById<TextView>(R.id.scoreb)
        val score = findViewById<Button>(R.id.add)
        val steal = findViewById<Button>(R.id.min)
        val reset = findViewById<Button>(R.id.reset)
        var snd = MediaPlayer.create(this, R.raw.click_effect)
        if(savedInstanceState != null){
            logres = savedInstanceState.getInt("count")
            scoreb.text = logres.toString()
            when(scoreb.text.toString().toInt()){
                in 0..19 -> scoreb.setTextColor(Color.RED)
                in 20..20 -> scoreb.setTextColor(Color.BLUE)
                else -> scoreb.setTextColor(Color.BLACK)
            }
        }else{
            scoreb.text = 0.toString()
            scoreb.setTextColor(Color.BLACK)
        }
        diceroll.setOnClickListener{
            if(!isRolled){
                diceval = Random.nextInt(1,7)
                diceplace.text = diceval.toString()
                isRolled = true
            }

        }
        score.setOnClickListener {
            Log.i("lap", "SCORE!!!")
            isRolled = false
            scoreb.text = (scoreb.text.toString().toInt() + diceval).toString()

            when(scoreb.text.toString().toInt()){
                in 0..19 -> scoreb.setTextColor(Color.BLACK)
                in 20..20 -> scoreb.setTextColor(Color.GREEN)
                20 -> snd.start()
                else -> scoreb.setTextColor(Color.RED)
            }
            logres = scoreb.text.toString().toInt()
        }
        steal.setOnClickListener {
            Log.i("lap", "STEAL!!!")
            isRolled = false
            if (scoreb.text.toString().toInt() > 0){
                scoreb.text = (scoreb.text.toString().toInt() - diceval).toString()
            }
            when(scoreb.text.toString().toInt()){
                in 0..19 -> scoreb.setTextColor(Color.BLACK)
                in 20..20 -> scoreb.setTextColor(Color.GREEN)
                20 -> snd.start()
                else -> scoreb.setTextColor(Color.RED)
            }
            logres = scoreb.text.toString().toInt()
        }

        reset.setOnClickListener {
            Log.i("lap", "RESET!!!")
            if (logres ==20){
                isRolled = false
            }
            scoreb.text = 0.toString()
            logres = scoreb.text.toString().toInt()
            scoreb.setTextColor(Color.BLACK)
        }
    }
}