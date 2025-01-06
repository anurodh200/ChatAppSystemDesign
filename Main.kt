object Logger {
    private val logHistory = mutableListOf<String>()

    fun log(message: String) {
        logHistory.add("${System.currentTimeMillis()}: $message")
        println(message)
    }

    fun getLogHistory(): List<String> = logHistory.toList()
}

fun main() {
    Logger.log("Application started")
    Logger.log("Processing data")
    println(Logger.getLogHistory())
}