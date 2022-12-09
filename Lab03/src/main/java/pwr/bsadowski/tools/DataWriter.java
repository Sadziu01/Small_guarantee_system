package pwr.bsadowski.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataWriter {

    public void writeFile(String name, String dataToWrite){
        Charset charset = Charset.forName("US-ASCII");
        Path file = Paths.get(name);

        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writer.write(dataToWrite, 0, dataToWrite.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
