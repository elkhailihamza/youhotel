//package Views;
//
//import Core.Program;
//import Models.Reservations;
//
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class Hotel {

//    public Status status;
//    private Category category;
//    private Equipements equipements;
//    private int id;
//    private String title;
//    private double price;



//    public static int getHotelRooms(HashMap<String, Hotel> hotelRooms) {
//        Scanner scanner = Program.getScannerInstance();
//        String userConfirmation;
//        boolean emptyRoomsDone = false;
//
//        System.out.println();
//        System.out.println(" Empty: ");
//        for (Hotel h:hotelRooms.values()) {
//            if (h.status == Status.Empty) {
//                emptyRoomsDone = true;
//                System.out.println(h.id + ". " + h.title + " - " + h.price + " " + h.category + " " + h.equipements + "dh . " + h.status);
//            }
//        }
//
//        System.out.println(" Reserved: ");
//        if (emptyRoomsDone) {
//            for (Hotel h : hotelRooms.values()) {
//                if (h.status == Status.Reserved)
//                    System.out.println(h.id + ". " + h.title + " - " + h.price + "dh . " + h.status);
//            }
//        }
//        System.out.println();
//
//        do {
//            System.out.println(" Would you like to reserve a spot (y,n)");
//            userConfirmation = scanner.nextLine().trim();
//        } while (!userConfirmation.equalsIgnoreCase("Y") && !userConfirmation.equalsIgnoreCase("N"));
//
//        if (userConfirmation.equalsIgnoreCase("Y"))
//            return Reservations.create(hotelRooms);
//        return 0;
//    }

//    public static boolean checkHotelId(int id, HashMap<String, Hotel> hotelRooms) {
//        for (Hotel n : hotelRooms.values())
//            if (n.id == id)
//                return true;
//        return false;
//    }

//    public static boolean checkIfAlreadyReservedRoom(Hotel room) {
//        return room.status == Status.Reserved;
//    }
// }
