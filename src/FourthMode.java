import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;

public class FourthMode {
    private Scanner scanner = new Scanner(System.in);

    private HashMap<Character, Integer> mapEncrypted = new HashMap<>();//создание Мапы куда будем класть пары символ/как часто встречается в ЗАШИФР файле
    private HashMap<Character, Integer> mapSample = new HashMap<>();//создание Мапы куда будем класть пары символ/как часто встречается в файле-образце
    private HashMap<Character, Character> decryptedMap = new HashMap<>();//создание Мапы куда будем класть пары символ зашифрованный-символ из образца, сопоставленные
    //по частоте с которой они встречаются

    //A - 134,
    //$ - 135,

    //A = $, E = %,
    //$ = A, % = E


    public void fourthMode() throws IOException {
        System.out.println("Write the path to the encrypted file");
        String encryptedPath = scanner.nextLine();
        System.out.println("Write the path to the statistics file");
        String samplePath = scanner.nextLine();
        String decryptedPath = "/Users/user/IdeaProjects/CryptoAnalyzer/src/" + "resources/fourthmode";
        HashMap<Character, Integer> filledEncryptedMap = fillMapValues(mapEncrypted, encryptedPath);//
        HashMap<Character, Integer> filledSampleMap = fillMapValues(mapSample, samplePath);

        ArrayList<Map.Entry<Character, Integer>> listEncrypted = mapToList(filledEncryptedMap);
        ArrayList<Map.Entry<Character, Integer>> listSample = mapToList(filledSampleMap);
        /*
        если количество пар в ARRAYLIST зашифр меньше или равно в листе сделанном из образца то формируем decrypted map - там лежат пары - зашифр символ - его
        предполагаемая оригинальная пара


         */
        if (listEncrypted.size() <= listSample.size()) {
            for (int i = 0; i < listEncrypted.size(); i++) {
                decryptedMap.put(listEncrypted.get(i).getKey(), listSample.get(i).getKey());
            }
            try (BufferedReader reader = Files.newBufferedReader(Path.of(encryptedPath));
                 BufferedWriter writer = Files.newBufferedWriter(Path.of(decryptedPath))
            ) {
                while (reader.ready()) {
                    String line = reader.readLine();//line = "dfadfadfs343easdfasd"
                    char[] chars = line.toCharArray();
                    StringBuilder builder = new StringBuilder();
                    for (char aChar : chars) {   //{3,c,%,6,4,} # = decryptedMap key
                        Character decryptedChar = decryptedMap.get(aChar);//для каждого зашифр символа получаем его пару из расшифр мапы пару для него из
                        // файла-образца
                        builder.append(decryptedChar);//формируем builder с каждым расшифр сомволом
                    }
                    writer.write(builder + System.lineSeparator());//когда цикл по стоке закончился, записываем внутрь файла
                }
                System.out.println("everything decrypted correctly");

            }
        } else {
            System.out.println("File size is not sufficient for statistics analysis");
        }


    }

/*
FillMapValues принимает два параметра - мапу(мы ее создали выше пустую, и строку к файлу
reader читает по строкам из файла (в одном случае будет зашифр, в другом расшифр) и если символа нет, добавляет символ и доаписывает единицу, если есть, то
пытается добавить ключ но так как он есть, он не добавляется, значение увелич на +1.
возвращает готовую мапу из
 */


    private HashMap<Character, Integer> fillMapValues(HashMap<Character, Integer> map, String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string);
            }
            for (char aChar : builder.toString().toCharArray()) {
                if (!map.containsKey(aChar)) {
                    map.put(aChar, 1);
                } else {
                    map.put(aChar, map.get(aChar) + 1);
                }
            }
        }
        return map;
    }
/*
в этом методе принимаем мапу из символ, частота встречания,
создаем set entries в который складываем объекты с двумя полями созданный с помощью метода entrySet который получает пары из Мапы
создаем ArrayList куда складываем этот сет в конструктор (переделываем сет в ArrayList)
создаем правило сортировки с помощью функц интерфейса
передаем в метод .sort в виде параметра переменную функц интерфейса, у нее вызывается метод reversed чтобы сортировать в обрат порядке
возвращается сортированный ArrayList list где идет по убыванию объекты состоящие из двух полей - символ, его частота
 */
    private ArrayList<Map.Entry<Character, Integer>> mapToList(HashMap<Character, Integer> map) {
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>(entries);
        /*Comparator<Map.Entry<Character, Integer>> comparator = new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        };*/
        Comparator <Map.Entry<Character, Integer>> comparator = Comparator.comparingInt(Map.Entry::getValue);
        list.sort(comparator.reversed());
        return list;

    }
}





