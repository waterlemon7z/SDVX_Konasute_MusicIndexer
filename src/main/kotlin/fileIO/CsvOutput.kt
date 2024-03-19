package fileIO

import entity.MusicEntity
import java.io.BufferedWriter
import java.io.FileWriter

class CsvWriter(private val filePath: String) {
    fun write(data: List<MusicEntity>) {
        try {
            val bufferedWriter = BufferedWriter(FileWriter(filePath))
            bufferedWriter.write("Genre,Name,Author,Lvl.nov,Lvl.adv,Lvl.exh,Lvl.inf/grv/hvn/vid/xcd/mxm")
            bufferedWriter.newLine()
            bufferedWriter.use { writer ->
                for (music in data) {
                    val line = StringBuffer()
                    line.append("\"${music.genre}\"")
                    line.append(',')
                    line.append("\"${music.name}\"")
                    line.append(',')
                    line.append("\"${music.author}\"")
                    line.append(",")
                    for (key in music.level.keys) {
                        line.append(music.level[key])
                        line.append(',')
                    }
                    line.delete(line.length - 1, line.length)
                    writer.write(line.toString())
                    writer.newLine()
                }
                println("\nCSV saved => $filePath")
            }
        } catch (e: Exception) {
            println("Error occurred : ${e.message}")
        }
    }
}