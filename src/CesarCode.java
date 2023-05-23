public class CesarCode {
    private final static String ALPHABET_PART_ONE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
                                            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!’? +-*/\\@#$%^&(){}[];'|`~=_©«»—0123456789";

    private final static String ALPHABET_PART_TWO = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!’? +-*/\\@#$%^&(){}[];'|`~=_©«»—0123456789";

    private final static String ALPHABET = ALPHABET_PART_ONE + ALPHABET_PART_TWO;

    public int alphabetLength(){
        return ALPHABET.length()/2;
    }

    public String encrypt(String message, int key) {
        //byte[] messageInBytes = inputMessage2.getBytes();//создает массив в котором с 0 до 25 идут буквы алфавита
        char[] charArray = message.toCharArray();//создает массив из char длиной 5(0-25)
        StringBuilder newString = new StringBuilder();
        for (char eachChar : charArray) {
            int originalNumber = ALPHABET.indexOf(eachChar);
            char encryptedChar = 0;
            int newNumber = 0;
            if (originalNumber >= 0) {
                if (key >= 0) {
                    newNumber = (originalNumber + key) % (ALPHABET.length() / 2);
                } else {
                    int newKey = key%(ALPHABET.length()/2);
                    newNumber =  (originalNumber + (ALPHABET.length()/2) + newKey)%ALPHABET.length() ;//взяли индекс закод-го символа, сдвинули его во вторую часть, прибавили ключ
                }
                encryptedChar = ALPHABET.charAt(newNumber);
            }
            newString.append(encryptedChar);
        }
        return newString.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, -1 * key);
    }


}




