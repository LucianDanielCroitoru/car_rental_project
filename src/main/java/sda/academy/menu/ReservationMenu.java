package sda.academy.menu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.academy.entities.*;
import sda.academy.repositories.*;
import sda.academy.util.HibernateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ReservationMenu {
    private static boolean reservationMenuFlag = true;

    public static void reservationMenu(Scanner scanner) {
        reservationMenuFlag = true;
        while (reservationMenuFlag) {
            showMenuReservation();
            int choice = Integer.parseInt(scanner.nextLine());
            executeCommandReservation(choice, scanner);
        }
    }

    private static void showMenuReservation() {
        System.out.println("1. Register reservation");
        System.out.println("2. Edit reservation");
        System.out.println("3. View reservation");
        System.out.println("4. Delete reservation");
        System.out.println("5. Exit");
        System.out.println("Enter your choice: ");
    }

    private static void executeCommandReservation(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                registerReservation(scanner);
                break;

            case 2:
                editReservation(scanner);
                break;

            case 3:
                viewReservation(scanner);
                break;

            case 4:
                deleteReservation(scanner);
                break;

            case 5:
                exitMenuReservation(scanner);
                break;
        }
    }

    private static void registerReservation(Scanner scanner) {
        System.out.println("Setting current date for your reservation...");
        LocalDateTime reservationDate = LocalDateTime.now();
        System.out.println("Enter car's ID you want to reserve: ");
        int car_id = Integer.parseInt(scanner.nextLine());
        CarRepository carRepository = new CarRepository();
        Car car = carRepository.findById(car_id);
        System.out.println("Enter customer's ID for this reservation: ");
        int customer_id = Integer.parseInt(scanner.nextLine());
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = customerRepository.findById(customer_id);
        System.out.println("Pick-up Station ID: ");
        int pickUpStation_id = Integer.parseInt(scanner.nextLine());
        StationRepository stationRepository = new StationRepository();
        Station pickUpStation = stationRepository.findById(pickUpStation_id);
        System.out.println("Drop-off Station ID: ");
        int dropOffStation_id = Integer.parseInt(scanner.nextLine());
        Station dropOffStation = stationRepository.findById(dropOffStation_id);
        System.out.println("Pick-up Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        LocalDate formatterStartDate = LocalDate.parse(startDate);
        System.out.println("Drop-off Date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        LocalDate formatterEndDate = LocalDate.parse(endDate);
        Reservation reservation = new Reservation();
        reservation.setReservationDate(reservationDate);
        reservation.setCar(car);
        reservation.setCustomer(customer);
        reservation.setPickUpStation(pickUpStation);
        reservation.setDropOffStation(dropOffStation);
        reservation.setStartDate(formatterStartDate);
        reservation.setEndDate(formatterEndDate);
        ReservationRepository reservationRepository = new ReservationRepository();
        Reservation reservationCheck = reservationRepository.allreadyBooked(formatterStartDate, formatterEndDate);
        MaintenanceRecordRepository maintenanceRecordRepository = new MaintenanceRecordRepository();
        MaintenanceRecord maintenanceRecord = maintenanceRecordRepository.validate(formatterStartDate, formatterEndDate);
        if (maintenanceRecord == null && reservationCheck == null) {
            reservationRepository.save(reservation);
        } else {
            String statement = "Reservation could not be saved. Please choose another car.";
            if (maintenanceRecord != null) {
                System.out.println(statement + " Reason: car unavailable.");
            }
            if (reservationCheck != null) {
                System.out.println(statement + " Reason: already booked.");
            }
        }
    }

    private static void editReservation(Scanner scanner) {
        System.out.println("Updating current date for your reservation...");
        LocalDateTime reservationDate = LocalDateTime.now();
        System.out.println("Enter reservation's ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter car's ID you want to reserve: ");
        int car_id = Integer.parseInt(scanner.nextLine());
        CarRepository carRepository = new CarRepository();
        Car car = carRepository.findById(car_id);
        System.out.println("Enter customer's ID for this reservation: ");
        int customer_id = Integer.parseInt(scanner.nextLine());
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = customerRepository.findById(customer_id);
        System.out.println("Edit Pick-up Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        LocalDate formatterStartDate = LocalDate.parse(startDate);
        System.out.println("Edit Drop-off Date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        LocalDate formatterEndDate = LocalDate.parse(endDate);
        Reservation reservation = new Reservation();
        reservation.setReservationDate(reservationDate);
        reservation.setCar(car);
        reservation.setCustomer(customer);
        reservation.setStartDate(formatterStartDate);
        reservation.setEndDate(formatterEndDate);
        ReservationRepository reservationRepository = new ReservationRepository();
        Reservation reservationCheck = reservationRepository.allreadyBooked(formatterStartDate, formatterEndDate);
        MaintenanceRecordRepository maintenanceRecordRepository = new MaintenanceRecordRepository();
        MaintenanceRecord maintenanceRecord = maintenanceRecordRepository.validate(formatterStartDate, formatterEndDate);
        if (maintenanceRecord == null && reservationCheck == null) {
            reservationRepository.update(reservation);
        } else {
            String statement = "";
            if (maintenanceRecord != null) {
                statement = " Reason: car unavailable.";
            }
            if (reservationCheck != null) {
                statement = " Reason: already booked.";
            }
            System.out.println("Reservation could not be saved. Please choose another car." + statement);
        }
    }

    private static void viewReservation(Scanner scanner) {
        ReservationRepository reservationRepository = new ReservationRepository();
        List<Reservation> reservations = reservationRepository.findAll();
        reservations.forEach(reservationFromList -> System.out.println("\n ID: " + reservationFromList.getId() + "\n Pick-up Date: " + reservationFromList.getStartDate() + "\n Drop-off Date: " + reservationFromList.getEndDate()));
    }

    private static void deleteReservation(Scanner scanner) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ReservationRepository reservationRepository = new ReservationRepository();
        System.out.println("Introduce the reservation's ID you want to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Reservation reservation = reservationRepository.findById(id);
        if (reservation != null) {
            session.delete(reservation);
        }
        session.getTransaction().commit();
        session.close();

    }

    private static void exitMenuReservation(Scanner scanner) {
        System.out.println("Exiting");
        reservationMenuFlag = false;
    }
}
