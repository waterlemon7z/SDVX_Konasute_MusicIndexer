package com.waterlemon7z

import com.waterlemon7z.console.Console
import com.waterlemon7z.console.Menu
import console.ProgressBar
import entity.MusicEntity
import fileIO.CsvOutput
import webPaser.FetchWeb

fun main() {

    Console.cls()
    val sel = Menu.mainMenu()

    val fetchWeb = FetchWeb(sel)
    val progressBar = ProgressBar(fetchWeb, 10)
    val musicPageResult = mutableListOf<MusicEntity>()
    for (i in 1..fetchWeb.totalPageCount) {
        musicPageResult.addAll(fetchWeb.getMusicPage(i))
        progressBar.updateProgress(i)
    }
    val serviceName = when(sel){
        1 -> "KonasuteJP"
        else -> "KonasuteKR"
    }
    val csvWriter = CsvOutput("${serviceName}Index.csv")
    csvWriter.write(musicPageResult)
    println("Finished.")
}