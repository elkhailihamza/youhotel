package Controllers;

import Core.Program;
import Core.Repository;
import Models.Users;
import Services.AuthService;

import java.util.Scanner;

public class AuthController {
    private final AuthService AuthService;
    private final Scanner scanner;
    public AuthController(Repository repository) {
        this.AuthService = repository.getAuthService();
        this.scanner = Program.getScannerInstance();
    }

    public boolean showMenu() {
        boolean result = false;

        while(true) {
            System.out.println("1. Register new user");
            System.out.println("2. Login");
            System.out.println("-");
            int choice = this.scanner.nextInt();

            Program.clearInputBuffer(this.scanner);

            switch (choice) {
                case 1:
                    System.out.println("REGISTER NEW USER");
                    System.out.println("Enter a username: ");
                    String reg_user_name = this.scanner.nextLine();
                    System.out.println("Enter a password: ");
                    String reg_user_pass = this.scanner.nextLine();

                    result = this.AuthService.register(reg_user_name, reg_user_pass);
                    break;
                case 2:
                    System.out.println("LOGIN");
                    System.out.println("Enter username: ");
                    String log_user_name = this.scanner.nextLine();
                    System.out.println("Enter password: ");
                    String log_user_pass = this.scanner.nextLine();

                    result = this.AuthService.login(log_user_name, log_user_pass);
                    break;
                default:
            }
            if (result)
                return true;
        }
    }
}
