package com.waterlemon7z.console

import java.util.*

object Console {
    fun cls()
    {
//       val processBuilder = when(System.getProperty("os.name"))
//        {
//            "Mac OS X" -> ProcessBuilder("clear")
//           else -> {ProcessBuilder("cls")}
//       }
//        processBuilder.start()
        val os = System.getProperty("os.name").lowercase(Locale.ROOT)
        val process: Process = if (os.contains("win")) {
            ProcessBuilder("cmd", "/c", "cls").inheritIO().start()
        } else {
            ProcessBuilder("clear").inheritIO().start()
        }
        process.waitFor()
    }
}