package console

import webPaser.FetchWeb

class ProgressBar(private val fetchWeb: FetchWeb, private val progressBarLength: Int) {

    private fun printProgressBar(progress: Int) {
        val progressChars = (progress * progressBarLength / fetchWeb.totalPageCount)
        val progressBarString =
            "[" + "=".repeat(progressChars) + ">" + " ".repeat(progressBarLength - progressChars) + "]"
        print("\rProgress: ${progress * 100 / fetchWeb.totalPageCount}% $progressBarString Fetching...")
    }

    fun updateProgress(progress: Int) {
        printProgressBar(progress)
    }
}
