package sda.academy.menu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.academy.entities.Car;
import sda.academy.entities.Station;
import sda.academy.repositories.CarRepository;
import sda.academy.repositories.StationRepository;
import sda.academy.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class CarMenu {
    private static boolean carMenuFlag = true;

    public static void carMenu(Scanner scanner) {
        carMenuFlag = true;
        while (carMenuFlag) {
            showMenuCar();
            int choice = Integer.parseInt(scanner.nextLine());
            executeCommandCar(choice, scanner);
        }
    }

    private static void showMenuCar() {
        System.out.println("1. Register cars");
        System.out.println("2. Edit cars");
        System.out.println("3. View cars");
        System.out.println("4. Delete cars");
        System.out.println("5. Exit");
        System.out.println("Enter your choice: ");
    }

    private static void executeCommandCar(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                registerCar(scanner);
                break;

            case 2:
                editCar(scanner);
                break;

            case 3:
                viewCar(scanner);
                break;

            case 4:
                deleteCar(scanner);
                break;

            case 5:
                exitMenuCar(scanner);
                break;
        }
    }

    private static void registerCar(Scanner scanner) {
        System.out.println("Enter model's car: ");
        String model = scanner.nextLine();
        System.out.println("Enter license's plate car: ");
        String licensePlate = scanner.nextLine();
        System.out.println("Enter price per day for above model: ");
        int pricePerDay = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the station's ID: ");
        int station_id = Integer.parseInt(scanner.nextLine());
        StationRepository stationRepository = new StationRepository();
        Station station = stationRepository.findById(station_id);
        Car car = new Car();
        car.setModel(model);
        car.setLicensePlate(licensePlate);
        car.setPricePerDay(pricePerDay);
        car.setStation(station);
        CarRepository carRepository = new CarRepository();
        carRepository.save(car);
    }

    private static void editCar(Scanner scanner) {
        System.out.println("Enter car's ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Edit model's car: ");
        String model = scanner.nextLine();
        System.out.println("Edit license's plate car: ");
        String licensePlate = scanner.nextLine();
        System.out.println("Edit price per day for above model: ");
        int pricePerDay = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the station's ID: ");
        int station_id = Integer.parseInt(scanner.nextLine());
        StationRepository stationRepository = new StationRepository();
        Station station = stationRepository.findById(station_id);
        CarRepository carRepository = new CarRepository();
        Car car = carRepository.findById(id);
        car.setModel(model);
        car.setLicensePlate(licensePlate);
        car.setPricePerDay(pricePerDay);
        car.setStation(station);
        carRepository.update(car);
    }

    private static void viewCar(Scanner scanner) {
        CarRepository carRepository = new CarRepository();
        List<Car> cars = carRepository.findAll();
        cars.forEach(carFromList -> System.out.println("\n ID: " + carFromList.getId() + "\n Model: " + carFromList.getModel() + "\n Plate Number: " + carFromList.getLicensePlate() + "\n Price per Day: " + carFromList.getPricePerDay() + "\n Station: " + carFromList.getStation()));
    }

    private static void deleteCar(Scanner scanner) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CarRepository carRepository = new CarRepository();
        System.out.println("Introduce the car's ID you want to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Car car = carRepository.findById(id);
        if (car != null) {
            session.delete(car);
        }
        session.getTransaction().commit();
        session.close();

    }

    private static void exitMenuCar(Scanner scanner) {
        System.out.println("Exiting");
        carMenuFlag = false;
    }
}
