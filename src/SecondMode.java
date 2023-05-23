import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class SecondMode {
    private final Scanner scanner = new Scanner(System.in);
    private final CesarCode cesarCode = new CesarCode();

    public void secondMode() throws IOException {
        System.out.println("where is your encrypted file?");
        String encryptedFileAddress = scanner.nextLine();

        System.out.println("Enter key");
        int key = Integer.parseInt(scanner.nextLine());

        String decryptedFileAddress = "/Users/user/IdeaProjects/CryptoAnalyzer/src/resources/decryptedFile.txt";

        try (BufferedReader reader = Files.newBufferedReader(Path.of(encryptedFileAddress));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(decryptedFileAddress));
        ) {
            while (reader.ready()) {
                String string = reader.readLine();
                String decryptedString = cesarCode.decrypt(string, key);
                writer.write(decryptedString+ System.lineSeparator());
            }
        }
        System.out.println("your text is decrypted");
    }
}
