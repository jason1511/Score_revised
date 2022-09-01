package com.example.score

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var logres: Int = 0
    private var x=0

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", logres)
        Log.i("lap", "saveInstanceState $logres")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scoreb = findViewById<TextView>(R.id.scoreb)
        val score = findViewById<Button>(R.id.add)
        val steal = findViewById<Button>(R.id.min)
        val reset = findViewById<Button>(R.id.reset)
        var snd = MediaPlayer.create(this, R.raw.click_effect)
        if(savedInstanceState != null){
            logres = savedInstanceState.getInt("count")
            scoreb.text = logres.toString()
            when(scoreb.text.toString().toInt()){
                in 5..9 -> scoreb.setTextColor(Color.RED)
                in 10..15 -> scoreb.setTextColor(Color.BLUE)
                else -> scoreb.setTextColor(Color.BLACK)
            }
        }else{
            scoreb.text = 0.toString()
            scoreb.setTextColor(Color.BLACK)
        }
        score.setOnClickListener {
            Log.i("lap", "SCORE!!!")
            if (scoreb.text.toString().toInt() < 15){
                scoreb.text = (scoreb.text.toString().toInt() + 1).toString()
            }
            when(scoreb.text.toString().toInt()){
                in 5..9 -> scoreb.setTextColor(Color.RED)
                in 10..15 -> scoreb.setTextColor(Color.BLUE)
                15 -> snd.start()
                else -> scoreb.setTextColor(Color.BLACK)
            }
            logres = scoreb.text.toString().toInt()
        }
        steal.setOnClickListener {
            Log.i("lap", "STEAL!!!")
            if (scoreb.text.toString().toInt() > 0){
                scoreb.text = (scoreb.text.toString().toInt() - 1).toString()
            }
            when(scoreb.text.toString().toInt()){
                in 5..9 -> scoreb.setTextColor(Color.RED)
                in 10..15 -> scoreb.setTextColor(Color.BLUE)
                15 -> snd.start()
                else -> scoreb.setTextColor(Color.BLACK)
            }
            logres = scoreb.text.toString().toInt()
        }
        reset.setOnClickListener {
            Log.i("lap", "RESET!!!")
            scoreb.text = 0.toString()
            logres = scoreb.text.toString().toInt()
            scoreb.setTextColor(Color.BLACK)
        }
    }
}