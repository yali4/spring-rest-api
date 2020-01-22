package hello.IO

import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.util.*

class FileReaderClass(var fileName: String) {
    @Throws(FileNotFoundException::class)
    fun useScanner(): String {
        val result = StringBuilder()
        val file = File(fileName)
        val reader = Scanner(file)
        while (reader.hasNextLine()) {
            result.append(reader.nextLine())
            result.append(System.lineSeparator())
        }
        print(result)
        return result.toString()
    }

    @Throws(IOException::class)
    fun useFileReader(): String {
        val builder = StringBuilder()
        val reader = FileReader(fileName)
        var c: Int
        while (reader.read().also { c = it } != -1) {
            builder.append(c.toChar())
        }
        print(builder)
        return builder.toString()
    }

}