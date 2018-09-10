package stacksqueues

/**
 * Checks the correct sequence of brackets closing
 */
object BracketsChecker {
    fun check(sequence: String): String {
        val stack = CharStack(sequence.length / 3)

        for (i in 0 until sequence.length) {
            when (sequence[i]) {
                '[' -> stack.push('[')
                '(' -> stack.push('(')
                '{' -> stack.push('{')

                ']' -> if (stack.isEmpty() || stack.pop() != '[') return printError(i)
                ')' -> if (stack.isEmpty() || stack.pop() != '(') return printError(i)
                '}' -> if (stack.isEmpty() || stack.pop() != '{') return printError(i)
            }
        }

        return "The sequence is correct"
    }

    private fun printError(charNumber: Int) =
            "Error of brackets closing - symbol number ${charNumber + 1}"
}