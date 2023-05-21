package Console;

import java.util.Scanner;

public class Console {
    private String command;
    private String path;
    private String key;

    public String getCommand() {
        return command;
    }

    public String getPath() {
        return path;
    }

    public String getKey() {
        return key;
    }

    public void workWithConsole() {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a command (ENCRYPT, DECRYPT): ");
            command = scanner.nextLine();

            System.out.print("Enter a absolute path: ");
            path = scanner.nextLine();

            System.out.print("Enter a key: ");
            key = scanner.nextLine();
        }
    }


}
