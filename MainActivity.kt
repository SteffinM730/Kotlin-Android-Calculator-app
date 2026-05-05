package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private lateinit var tvExpression: TextView

    private val engine = CalculatorEngine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay    = findViewById(R.id.tvDisplay)
        tvExpression = findViewById(R.id.tvExpression)

        updateDisplay()
    }

    // Called by every number / operator / action button via android:onClick in XML
    fun onButtonClick(view: View) {
        val btn = view as Button
        when (val tag = btn.tag.toString()) {
            "0","1","2","3","4","5","6","7","8","9","." -> engine.appendDigit(tag)
            "+", "-", "*", "/"                          -> engine.setOperator(tag)
            "="                                         -> engine.evaluate()
            "C"                                         -> engine.clear()
            "backspace"                                 -> engine.backspace()
            "+/-"                                       -> engine.negate()
            "%"                                         -> engine.percent()
        }
        updateDisplay()
    }

    private fun updateDisplay() {
        tvExpression.text = engine.expression
        tvDisplay.text    = engine.display
    }
}
