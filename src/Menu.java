import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    What do you want to do today?
                    Mode 1 - enter 1
                    Mode 2 - enter 2
                    Mode 3 - enter 3
                    Mode 4 - enter 4
                    exit program - enter exit""");
            String answer = scanner.nextLine();
            switch (answer) {
                case ("1"): {
                    new FirstMode().firstMode();
                    break;
                }
                case ("2"): {
                    new SecondMode().secondMode();
                    break;
                }
                case ("3"): {
                    new ThirdMode().thirdMode();
                    break;
                }
                case ("4"): {
                    new FourthMode().fourthMode();
                    break;
                }
                default:{
                    System.out.println("choose one of 4 options");
                }
            }
            if (answer.equals("exit")){
                break;
            }
        }
    }
}
