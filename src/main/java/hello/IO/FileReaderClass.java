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

    public String useScanner() {

        String content = null;

        try {

            StringBuilder result = new StringBuilder();

            File file = new File(this.fileName);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                result.append(reader.nextLine());
                result.append(System.lineSeparator());
            }

            System.out.print(result);
            content = result.toString();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return content;
    }

    public String useFileReader() {

        String content = null;

        try {

            StringBuilder builder = new StringBuilder();
            FileReader reader = new FileReader(this.fileName);

            int c;
            while ((c = reader.read()) != -1) {
                builder.append((char)c);
            }

            System.out.print(builder);
            content = builder.toString();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return content;

    }

}
