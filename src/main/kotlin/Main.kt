
import console.ProgressBarLogger
import entity.MusicEntity
import fileIO.CsvWriter
import webPaser.FetchWeb

fun main(args: Array<String>) {
    val fetchWeb = FetchWeb()

    val musicPageResult = mutableListOf<MusicEntity>()


    val runnable = Runnable {
        for (i in 1..fetchWeb.totalPageCount) {
            musicPageResult.addAll(fetchWeb.getMusicPage(i))
        }
    }

    val musicFetchThread = Thread(runnable)

    musicFetchThread.start()

    val progressBarLogger = ProgressBarLogger(fetchWeb, 50)
    progressBarLogger.updateProgress(musicFetchThread)
    musicFetchThread.join()

    val csvWriter = CsvWriter("ustaIndex.csv")
    csvWriter.write(musicPageResult)
    println("Finished")
}