package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var board : Array<Array<Button>>
    var numbers = Array<String>(2){""}
    var Operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(btn1,btn2,btn3,btn4),
            arrayOf(btn5,btn6,btn7,btn8),
            arrayOf(btn9,btn10,btn11,btn12),
            arrayOf(btn13,pow,btn15,btn16)
            )

        for(i in board)
            for(button in i)
                button.setOnClickListener(this)

        Displaytext.text = ""

        clear.setOnClickListener(){
            numbers[0] = ""
            numbers[1] = ""
            Operator = null
            Displaytext.text = ""
        }
        dot.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btn1->AddNumberOrDecimelNumber("7")
            R.id.btn2->AddNumberOrDecimelNumber("8")
            R.id.btn3->AddNumberOrDecimelNumber("9")
            R.id.btn5->AddNumberOrDecimelNumber("4")
            R.id.btn6->AddNumberOrDecimelNumber("5")
            R.id.btn7->AddNumberOrDecimelNumber("6")
            R.id.btn9->AddNumberOrDecimelNumber("1")
            R.id.btn10->AddNumberOrDecimelNumber("2")
            R.id.btn11->AddNumberOrDecimelNumber("3")
            R.id.btn13->AddNumberOrDecimelNumber("0")
            R.id.btn4->AddOperator("*")
            R.id.btn8->AddOperator("/")
            R.id.btn12->AddOperator("+")
            R.id.btn16->AddOperator("-")
            R.id.pow->AddOperator("^")
            R.id.dot->AddNumberOrDecimelNumber(".")
            R.id.btn15->calculate()
        }
    }

    private fun AddOperator(value: String) {
        Operator = value
        UpdateTextView(value)
    }

    private fun calculate(value: String? = null) {
        var result:Double? = null
        var first:Double? = if(numbers[0] == "") null else numbers[0].toDouble()
        var second:Double? = if(numbers[1] == "") null else numbers[1].toDouble()

        if ((numbers[0] == "") || (numbers[1] == "")){
            Toast.makeText(this,"enter the variables first",Toast.LENGTH_SHORT).show()
        }
        else{
            when(Operator){
                "+"->{ result = second!! + first!!}
                "-"->{result = second!! - first!! }
                "*"->{ result = first!! * second!!}
                "/"->{ result = second!! / first!!}
                "^"->{result = 1.0
                    while(numbers[0].toDouble()>0.0){
                        result = result!! * numbers[1].toDouble()
                        numbers[0] = (numbers[0].toDouble() - 1.0).toString()
                    }
                }
            }
            numbers[1] = result.toString()
            numbers[0] = ""
            Displaytext.text = result.toString()
            result = 0.0
        }
    }

    private fun AddNumberOrDecimelNumber(value: String) {
        var index  = if (Operator == null) 1 else 0
        if (value =="." && numbers[index].contains(".")){
            return
        }
        numbers[index] = numbers[index] + value

        UpdateTextView(numbers[index])
    }

    private fun UpdateTextView(value: String) {
        Displaytext.text =  value
    }

}
