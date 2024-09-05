package sda.academy.menu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.academy.entities.Station;
import sda.academy.repositories.StationRepository;
import sda.academy.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class StationMenu {
    private static boolean stationMenuFlag = true;

    public static void stationMenu(Scanner scanner) {
        stationMenuFlag = true;
        while (stationMenuFlag) {
            showMenuStation();
            int choice = Integer.parseInt(scanner.nextLine());
            executeCommandStation(choice, scanner);
        }
    }

    private static void showMenuStation() {
        System.out.println("1. Register stations");
        System.out.println("2. Edit stations");
        System.out.println("3. View stations");
        System.out.println("4. Delete stations");
        System.out.println("5. Exit");
        System.out.println("Enter your choice: ");
    }

    private static void executeCommandStation(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                registerStation(scanner);
                break;

            case 2:
                editStation(scanner);
                break;

            case 3:
                viewStation(scanner);
                break;

            case 4:
                deleteStation(scanner);
                break;

            case 5:
                exitMenuStation(scanner);
                break;
        }
    }

    private static void registerStation(Scanner scanner) {
        System.out.println("Enter station name: ");
        String locationName = scanner.nextLine();
        System.out.println("Enter station address: ");
        String address = scanner.nextLine();
        Station station = new Station();
        station.setLocationName(locationName);
        station.setAddress(address);
        StationRepository stationRepository = new StationRepository();
        stationRepository.save(station);
    }

    private static void editStation(Scanner scanner) {
        System.out.println("Enter station's FirstName: ");
        String locationName = scanner.nextLine();
        System.out.println("Enter station's LastName: ");
        String address = scanner.nextLine();
        System.out.println("Enter driver's license number: ");
        int driverLicenseNumber = Integer.parseInt(scanner.nextLine());
        Station station = new Station();
        station.setLocationName(locationName);
        station.setAddress(address);
        StationRepository stationRepository = new StationRepository();
        stationRepository.update(station);
    }

    private static void viewStation(Scanner scanner) {
        StationRepository stationRepository = new StationRepository();
        List<Station> stations = stationRepository.findAll();
        stations.forEach(stationFromList -> System.out.println("\n ID: " + stationFromList.getId() + "\n Station Name: " + stationFromList.getLocationName() + "\n Address: " + stationFromList.getAddress()));
    }

    private static void deleteStation(Scanner scanner) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        StationRepository stationRepository = new StationRepository();
        System.out.println("Introduce the station's ID you want to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Station findedStation = stationRepository.findById(id);

        stationRepository.delete(findedStation);

        session.getTransaction().commit();
        session.close();
    }

    private static void exitMenuStation(Scanner scanner) {
        System.out.println("Exiting");
        stationMenuFlag = false;
    }
}
