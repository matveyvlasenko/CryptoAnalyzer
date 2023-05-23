import javax.annotation.processing.Filer;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FirstMode {
    private final Scanner scanner = new Scanner(System.in);
    private final CesarCode cesarCode = new CesarCode();

    public void firstMode() throws IOException {
        System.out.println("Where is your file");
        String address = scanner.nextLine();

        System.out.println("Enter key");
        int key = Integer.parseInt(scanner.nextLine());

        String encryptedFileAddress = "/Users/user/IdeaProjects/CryptoAnalyzer/src/resources/encryptedFile.txt";

        try (BufferedReader reader = Files.newBufferedReader(Path.of(address));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(encryptedFileAddress)))

        { while (reader.ready()) {//пока в reader есть что читать

                String string = reader.readLine();//записывать в строку то что reader прочитал построчно
                String encryptedString = cesarCode.encrypt(string, key);//в закодированную строку передавать то что возвр метод encrypt
                writer.write(encryptedString+System.lineSeparator());//записываем в файл через Files строку плюс универс сепаратор

            }


        }
        System.out.println("your text is encrypted");
    }
}
