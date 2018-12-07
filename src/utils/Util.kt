package utils

/**
 * Counts time of running code block within its start and stop functions
 */
class Timer {

    private var startTime: Long = 0L

    /**
     * starts timer
     *
     * @return start system time nanoseconds as a String
     */
    fun start(): String {
        startTime = System.nanoTime()
        return startTime.toString()
    }

    /**
     * stops timer and show time
     *
     * @return time from start in milliseconds as a String
     */
    fun stopAndShowTime(): String {
        if (startTime == 0L) return "You have to call start() before stopAndShowTime()"

        val deltaMillis: Float = ((System.nanoTime() - startTime) / 1_000).toFloat() / 1000.0f
        startTime = 0L

        return String.format("It's taken %.3f milliseconds", deltaMillis)
    }
}