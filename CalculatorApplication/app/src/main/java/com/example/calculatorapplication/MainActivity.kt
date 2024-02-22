package com.example.calculatorapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.calculatorapplication.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
    fun onBtnClicked(view:View){
        when(view.id){
            R.id.btn_0 -> binding.tvCalculation.text = addToCalculationText("0")
            R.id.btn_1 -> binding.tvCalculation.text =addToCalculationText("1")
            R.id.btn_2 -> binding.tvCalculation.text = addToCalculationText("2")
            R.id.btn_3 -> binding.tvCalculation.text = addToCalculationText("3")
            R.id.btn_4 -> binding.tvCalculation.text = addToCalculationText("4")
            R.id.btn_5 -> binding.tvCalculation.text = addToCalculationText("5")
            R.id.btn_6 -> binding.tvCalculation.text = addToCalculationText("6")
            R.id.btn_7 -> binding.tvCalculation.text = addToCalculationText("7")
            R.id.btn_8 -> binding.tvCalculation.text = addToCalculationText("8")
            R.id.btn_9 -> binding.tvCalculation.text = addToCalculationText("9")
            R.id.btn_dot -> binding.tvCalculation.text = addToCalculationText(".")
            R.id.btn_openbracket -> binding.tvCalculation.text = addToCalculationText("(")
            R.id.btn_closebracket -> binding.tvCalculation.text = addToCalculationText(")")
            
            R.id.btn_plus -> binding.tvCalculation.text = addToCalculationText(" + ")
            R.id.btn_minus -> binding.tvCalculation.text = addToCalculationText(" - ")
            R.id.btn_multiply -> binding.tvCalculation.text = addToCalculationText(" x ")
            R.id.btn_divide -> binding.tvCalculation.text = addToCalculationText(" ÷ ")

            R.id.btn_equal -> {
                showResult()
                binding.tvEqual.visibility = View.VISIBLE
            }

            R.id.btn_clear -> {
                binding.tvAns.text = ""
                binding.tvCalculation.text = ""
                binding.tvEqual.visibility = View.GONE
            }
        }
    }

    private fun showResult() {
        try {
            var expression = getInputExpression()
            var result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                binding.tvAns.text = ""
                binding.tvAns.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                // Show Result
                binding.tvAns.text = DecimalFormat("0.######").format(result).toString()
                binding.tvAns.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        }catch (e:Exception){
            binding.tvAns.text = ""
            binding.tvAns.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }

    private fun getInputExpression(): String {
        var expression = binding.tvCalculation.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun addToCalculationText(buttonValue: String): String {
        return binding.tvCalculation.text.toString() + buttonValue
    }
}