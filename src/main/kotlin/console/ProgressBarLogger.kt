package console

import webPaser.FetchWeb

class ProgressBarLogger(private val fetchWeb: FetchWeb, private val progressBarLength: Int) {

    private fun printProgressBar(progress: Int) {
        val progressChars = (progress * progressBarLength / fetchWeb.totalPageCount)
        val progressBarString = "[" + "=".repeat(progressChars) + ">"+" ".repeat(progressBarLength - progressChars) + "]"
        print("\rProgress: ${progress * 100/fetchWeb.totalPageCount }% $progressBarString")
    }

    fun updateProgress(thread: Thread) {
        var progress = 0
        while (thread.isAlive && progress <= fetchWeb.totalPageCount) {
            print("Logging: $progress% completed\r")
            printProgressBar(progress)
            progress = fetchWeb.curPage
        }
    }
}
