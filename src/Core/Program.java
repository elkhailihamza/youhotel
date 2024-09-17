package Core;

import java.util.Scanner;

public class Program {
    private static Scanner scannerInstance;

    private Program() {
    }

    public static Scanner getScannerInstance() {
        if (scannerInstance == null)
            scannerInstance = new Scanner(System.in);
        return scannerInstance;
    }

    public static void clearInputBuffer(Scanner scanner) {
        if (scanner.hasNextLine())
            scanner.nextLine();
    }

    public static boolean isLong(Object obj) {
        try {
            Long.valueOf((String) obj);
        } catch (Exception ex) {
            return (false);
        }
        return (true);
    }

    public static void giveWarning(String warning) {
        System.out.println("***WARNING*** : "+warning);
    }

    public static boolean exit() {
        System.out.println("Bye");
        return false;
    }
}