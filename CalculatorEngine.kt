package com.example.calculator

class CalculatorEngine {

    var display: String = "0"
        private set

    var expression: String = ""
        private set

    private var operand1: Double? = null
    private var operator: String? = null
    private var newInput: Boolean = true   // true = next digit starts fresh

    fun appendDigit(digit: String) {
        if (newInput) {
            display  = if (digit == ".") "0." else digit
            newInput = false
        } else {
            if (digit == "." && display.contains(".")) return
            display = if (display == "0" && digit != ".") digit else display + digit
        }
    }

    fun setOperator(op: String) {
        // Chain: evaluate pending op first, then store new one
        if (operand1 != null && !newInput) evaluate(chainMode = true)
        operand1   = display.toDoubleOrNull() ?: 0.0
        operator   = op
        expression = "${format(operand1!!)} $op"
        newInput   = true
    }

    fun evaluate(chainMode: Boolean = false) {
        val op2 = display.toDoubleOrNull() ?: return
        val op1 = operand1 ?: return
        val op  = operator ?: return

        val result = when (op) {
            "+"  -> op1 + op2
            "-"  -> op1 - op2
            "*"  -> op1 * op2
            "/"  -> if (op2 == 0.0) { display = "Error"; expression = ""; reset(); return } else op1 / op2
            else -> op2
        }

        if (!chainMode) expression = "${format(op1)} $op ${format(op2)} ="
        display  = format(result)
        operand1 = if (chainMode) result else null
        if (!chainMode) operator = null
        newInput = true
    }

    fun clear() {
        display    = "0"
        expression = ""
        reset()
    }

    fun backspace() {
        if (newInput) return
        display = if (display.length <= 1) "0" else display.dropLast(1)
    }

    fun negate() {
        val v = display.toDoubleOrNull() ?: return
        display = format(-v)
    }

    fun percent() {
        val v = display.toDoubleOrNull() ?: return
        display = format(v / 100.0)
    }

    // ── helpers ──────────────────────────────────────────────────────────────

    private fun reset() {
        operand1 = null
        operator = null
        newInput = true
    }

    /** Strips trailing .0 for whole numbers, caps at 10 significant digits */
    private fun format(v: Double): String {
        if (v.isInfinite() || v.isNaN()) return "Error"
        return if (v == kotlin.math.floor(v) && !v.isInfinite())
            v.toLong().toString()
        else
            "%.10g".format(v).trimEnd('0').trimEnd('.')
    }
}
