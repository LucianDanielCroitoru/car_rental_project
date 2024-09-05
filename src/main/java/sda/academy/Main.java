package sda.academy;

import java.util.Scanner;

import static sda.academy.menu.CarMenu.carMenu;
import static sda.academy.menu.CustomerMenu.customerMenu;
import static sda.academy.menu.MaintenanceMenu.maintenanceMenu;
import static sda.academy.menu.ReservationMenu.reservationMenu;
import static sda.academy.menu.StationMenu.stationMenu;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            executeCommandMainMenu(choice, scanner);
        }
}

    private static void showMainMenu() {
        System.out.println("1. Cars Menu");
        System.out.println("2. Customers Menu");
        System.out.println("3. Reservations Menu");
        System.out.println("4. Maintenance Record Menu");
        System.out.println("5. Station Menu");
        System.out.println("6. Exit");
        System.out.println("Enter your choice: ");
}

    private static void executeCommandMainMenu(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                carMenu(scanner);
                break;

            case 2:
                customerMenu(scanner);
                break;

            case 3:
                reservationMenu(scanner);
                break;

            case 4:
                maintenanceMenu(scanner);
                break;

            case 5:
                stationMenu(scanner);
                break;

            case 6:
                exitMainMenu();
                break;
        }
    }
    private static void exitMainMenu() {
        System.exit(0);
    }
}