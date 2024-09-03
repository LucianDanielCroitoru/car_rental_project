package sda.academy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.academy.entities.Car;
import sda.academy.entities.Customer;
import sda.academy.repositories.CarRepository;
import sda.academy.repositories.CustomerRepository;
import sda.academy.util.HibernateUtil;


import java.util.List;
import java.util.Scanner;

public class Main {
    private static boolean carMenuFlag = true;
    private static boolean customerMenuFlag = true;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            executeCommandMainMenu(choice, scanner);
        }
}

    private static void showMainMenu() {
        System.out.println("1. Car Menu");
        System.out.println("2. Customer Menu");
        System.out.println("3. Exit");
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
                exitMainMenu();
                break;
        }
    }

    private static void carMenu(Scanner scanner) {
        carMenuFlag = true;
        while (carMenuFlag) {
            showMenuCar();
            int choice = Integer.parseInt(scanner.nextLine());
            executeCommandCar(choice, scanner);
        }
    }

    private static void customerMenu(Scanner scanner) {
        customerMenuFlag = true;
        while (customerMenuFlag) {
            showMenuCustomer();
            int choice = Integer.parseInt(scanner.nextLine());
            executeCommandCustomer(choice, scanner);
        }
    }

    private static void exitMainMenu() {
        System.exit(0);
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
        Car car = new Car();
        car.setModel(model);
        car.setLicensePlate(licensePlate);
        car.setPricePerDay(pricePerDay);
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
        CarRepository carRepository = new CarRepository();
        Car car = carRepository.findById(id);
        car.setModel(model);
        car.setLicensePlate(licensePlate);
        car.setPricePerDay(pricePerDay);
        carRepository.update(car);
    }

    private static void viewCar(Scanner scanner) {
        CarRepository carRepository = new CarRepository();
        List<Car> cars = carRepository.findAll();
        cars.forEach(carFromList -> System.out.println("\n ID: " + carFromList.getId() + "\n Model: " + carFromList.getModel() + "\n Plate Number: " + carFromList.getLicensePlate() + "\n Price per Day: " + carFromList.getPricePerDay()));
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

    private static void showMenuCustomer() {
        System.out.println("1. Register customer");
        System.out.println("2. Edit customer");
        System.out.println("3. View customer");
        System.out.println("4. Delete customer");
        System.out.println("5. Exit Menu Customer");
        System.out.println("Enter your choice: ");
    }

    private static void executeCommandCustomer(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                registerCustomer(scanner);
                break;

            case 2:
                editCustomer(scanner);
                break;

            case 3:
                viewCustomer(scanner);
                break;

            case 4:
                deleteCustomer(scanner);
                break;

            case 5:
                exitMenuCustomer(scanner);
                break;
        }
    }

    private static void registerCustomer(Scanner scanner) {
        System.out.println("Enter customer first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter customer last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter driver license number: ");
        int driverLicenseNumber = Integer.parseInt(scanner.nextLine());
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setDriverLicenseNumber(driverLicenseNumber);
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.save(customer);
    }

    private static void editCustomer(Scanner scanner) {
        System.out.println("Enter customer first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter customer last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter driver license number: ");
        int driverLicenseNumber = Integer.parseInt(scanner.nextLine());
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setDriverLicenseNumber(driverLicenseNumber);
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.update(customer);
    }

    private static void viewCustomer(Scanner scanner) {
        CustomerRepository customerRepository = new CustomerRepository();
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(customerFromList -> System.out.println("\n ID: " + customerFromList.getId() + "\n First name: " + customerFromList.getFirstName() + "\n Last name: " + customerFromList.getLastName() + "\n Driver license number: " + customerFromList.getDriverLicenseNumber()));
    }

    private static void deleteCustomer(Scanner scanner) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CustomerRepository customerRepository = new CustomerRepository();
        System.out.println("Introduce the customer ID you want to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Customer findedCustomer = customerRepository.findById(id);

        customerRepository.delete(findedCustomer);

        session.getTransaction().commit();
        session.close();
    }

    private static void exitMenuCustomer(Scanner scanner) {
        System.out.println("Exiting");
        customerMenuFlag = false;
    }
}