package hello.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileReaderClass {

    String fileName;

    public FileReaderClass(String fileName) {
        this.fileName = fileName;
    }

    public String useScanner() throws FileNotFoundException {

        StringBuilder result = new StringBuilder();

        File file = new File(this.fileName);
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            result.append(reader.nextLine());
            result.append(System.lineSeparator());
        }

        System.out.print(result);

        return result.toString();
    }

    public String useFileReader() throws IOException {

        StringBuilder builder = new StringBuilder();
        FileReader reader = new FileReader(this.fileName);

        int c;
        while ((c = reader.read()) != -1) {
            builder.append((char)c);
        }

        System.out.print(builder);

        return builder.toString();

    }

}
