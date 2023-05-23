import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ThirdMode {
    private final Scanner scanner = new Scanner(System.in);
    private final CesarCode cesarCode = new CesarCode();

    public void thirdMode() throws IOException {
        System.out.println("Where is your encrypted file?");
        String path = scanner.nextLine();

        String selfDecryptedFileAddress = "/Users/user/IdeaProjects/CryptoAnalyzer/src/resources/selfDecryptedFile.txt";
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(selfDecryptedFileAddress))
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            List<String> stringList = new ArrayList<>();

            while (reader.ready()) {
                String string = reader.readLine();
                stringBuilder.append(string);
                stringList.add(string);
            }
            for (int i = 0; i < cesarCode.alphabetLength(); i++) {
                String testString = cesarCode.decrypt(stringBuilder.toString(), i);
                if (isCheckedText(testString)) {
                    //текст валидный, ключ верный. выполняем дешифровку Цезаря этим верным ключом и записываем его в файл
                    for (String listString : stringList) {
                        String encrypt = cesarCode.decrypt(listString,i);
                        writer.write(encrypt+ System.lineSeparator());
                    }
                    System.out.println("the file is decrypted. The key is = " + i);
                    break;
                }
            }
        }//проверить изменилось ли CheckedText!!! в процессе проверки ориг строка меняется, поэтому вместе нее проверяем ее копию String text
    }

    private boolean isCheckedText(String textToCheck) {
        boolean flag = false;
        if (textToCheck.length() > 50){
            int indexStart = new Random().nextInt(textToCheck.length()/2);
            int indexFinish = indexStart + (int) Math.sqrt(textToCheck.length());

            textToCheck = textToCheck.substring(indexStart, indexFinish);
        }

        String[] dividedWords = textToCheck.split(" ");//делим строку по пробелу и передаем в массив разделенных слов
        for (String dividedWord : dividedWords) {
            if (dividedWord.length() > 24) {//если слова между пробелами больше 24 символов то ключ не тот и метод закрывается
                return false;
            }
        }
        if (textToCheck.contains(". ") || textToCheck.contains("! ") || textToCheck.contains(", ") || textToCheck.contains("? ")) {
            flag = true;
        }


        while (flag) {
            System.out.println(textToCheck);
            System.out.println("Do you understand this? Y/N ");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                flag = false;
            } else {
                System.out.println("press only Y or N");
            }
        }



        return flag;
    }
}







