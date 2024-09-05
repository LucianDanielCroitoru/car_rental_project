package sda.academy.menu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.academy.entities.Customer;
import sda.academy.repositories.CustomerRepository;
import sda.academy.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    private static boolean customerMenuFlag = true;

    public static void customerMenu(Scanner scanner) {
        customerMenuFlag = true;
        while (customerMenuFlag) {
            showMenuCustomer();
            int choice = Integer.parseInt(scanner.nextLine());
            executeCommandCustomer(choice, scanner);
        }
    }

    private static void showMenuCustomer() {
        System.out.println("1. Register customers");
        System.out.println("2. Edit customers");
        System.out.println("3. View customers");
        System.out.println("4. Delete customers");
        System.out.println("5. Exit");
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
        System.out.println("Enter customer's FirstName: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter customer's LastName: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter driver's license number: ");
        int driverLicenseNumber = Integer.parseInt(scanner.nextLine());
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setDriverLicenseNumber(driverLicenseNumber);
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.save(customer);
    }

    private static void editCustomer(Scanner scanner) {
        System.out.println("Enter customer's FirstName: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter customer's LastName: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter driver's license number: ");
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
        customers.forEach(customerFromList -> System.out.println("\n ID: " + customerFromList.getId() + "\n First Name: " + customerFromList.getFirstName() + "\n Last Name: " + customerFromList.getLastName() + "\n Driver license number: " + customerFromList.getDriverLicenseNumber()));
    }

    private static void deleteCustomer(Scanner scanner) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CustomerRepository customerRepository = new CustomerRepository();
        System.out.println("Introduce the customer's ID you want to delete: ");
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
