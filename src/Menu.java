public class Menu {
    public static int mainMenu() {
        System.out.println("Hotel Reservations");
        System.out.println("1. Show Hotel Rooms");
        System.out.println("2. Reserve a hotel room");
        System.out.println("3. Edit past reservations");
        System.out.println("4. Cancel a reservation");
        System.out.println("5. View Model.Reservation History");
        System.out.println("6. Quit");

        int userInput;
        if (Program.getScannerInstance().hasNextInt()){
            userInput = Program.getScannerInstance().nextInt();
            Program.clearInputBuffer(Program.getScannerInstance());
            return userInput;
        }
        return 0;
    }
}