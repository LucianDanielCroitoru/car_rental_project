package sda.academy.menu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.academy.entities.Car;
import sda.academy.entities.Customer;
import sda.academy.entities.MaintenanceRecord;
import sda.academy.repositories.CarRepository;
import sda.academy.repositories.CustomerRepository;
import sda.academy.repositories.MaintenanceRecordRepository;
import sda.academy.util.HibernateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MaintenanceMenu {
    private static boolean maintenanceMenuFlag = true;

    public static void maintenanceMenu(Scanner scanner) {
        maintenanceMenuFlag = true;
        while (maintenanceMenuFlag) {
            showMenuMaintenance();
            int choice = Integer.parseInt(scanner.nextLine());
            executeCommandMaintenance(choice, scanner);
        }
    }

    private static void showMenuMaintenance() {
        System.out.println("1. Register maintenance record");
        System.out.println("2. Edit maintenance record");
        System.out.println("3. View maintenance record");
        System.out.println("4. Exit");
        System.out.println("Enter your choice: ");
    }

    private static void executeCommandMaintenance(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                registerMaintenance(scanner);
                break;

            case 2:
                editMaintenance(scanner);
                break;

            case 3:
                viewMaintenance(scanner);
                break;

            case 4:
                exitMenuMaintenance(scanner);
                break;
        }
    }

    private static void registerMaintenance(Scanner scanner) {
        System.out.println("Enter maintenance date (YYYY-MM-DD): ");
        String maintenanceDate = scanner.nextLine();
        LocalDate formatterMaintenanceDate = LocalDate.parse(maintenanceDate);
        System.out.println("Enter car's ID for maintenance: ");
        int car_id = Integer.parseInt(scanner.nextLine());
        CarRepository carRepository = new CarRepository();
        Car car = carRepository.findById(car_id);
        System.out.println("Enter details of maintenance: ");
        String details = scanner.nextLine();
        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        maintenanceRecord.setMaintenanceDate(formatterMaintenanceDate);
        maintenanceRecord.setCar(car);
        maintenanceRecord.setDetails(details);
        MaintenanceRecordRepository maintenanceRecordRepository = new MaintenanceRecordRepository();
        maintenanceRecordRepository.save(maintenanceRecord);
    }

    private static void editMaintenance(Scanner scanner) {
        System.out.println("Enter maintenance record's ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter maintenance date (YYYY-MM-DD): ");
        String maintenanceDate = scanner.nextLine();
        LocalDate formatterMaintenanceDate = LocalDate.parse(maintenanceDate);
        System.out.println("Enter car's ID you want to reserve: ");
        int car_id = Integer.parseInt(scanner.nextLine());
        CarRepository carRepository = new CarRepository();
        Car car = carRepository.findById(car_id);
        System.out.println("Edit details of maintenance: ");
        String details = scanner.nextLine();
        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        maintenanceRecord.setMaintenanceDate(formatterMaintenanceDate);
        maintenanceRecord.setCar(car);
        maintenanceRecord.setDetails(details);
        MaintenanceRecordRepository maintenanceRecordRepository = new MaintenanceRecordRepository();
        maintenanceRecordRepository.update(maintenanceRecord);
    }

    private static void viewMaintenance(Scanner scanner) {
        MaintenanceRecordRepository maintenanceRecordRepository = new MaintenanceRecordRepository();
        List<MaintenanceRecord> maintenanceRecords = maintenanceRecordRepository.findAll();
        maintenanceRecords.forEach(maintenanceRecordFromList -> System.out.println("\n ID: " + maintenanceRecordFromList.getId() + "\n Maintenance Date: " + maintenanceRecordFromList.getMaintenanceDate() + "\n Details: " + maintenanceRecordFromList.getDetails()));
    }

    private static void exitMenuMaintenance(Scanner scanner) {
        System.out.println("Exiting");
        maintenanceMenuFlag = false;
    }
}