package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {
    private static CustomerService INSTANCE;

    public static CustomerService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomerService();
        }

        return INSTANCE;
    }

    private CustomerService() {
    }

    public static List<Customer> customers = new ArrayList<Customer>();

    public void addCustomer(String email, String firstName, String lastName) {

        if (checkDuplicateCustomer(email)) {
            Customer customer = new Customer(email, firstName, lastName);
            customers.add(customer);
            System.out.println("Added customer: " + customer);
        } else {
            System.out.println("Duplicate customer, please enter another email");
        }
    }

    public Customer getCustomer(String customerEmail) {
        for (Customer customer : customers) {
            if (customerEmail.equals(customer.getEmail())) {
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return customers;
    }

    public boolean checkCustomerEmail(String email) {
        if (!Customer.checkEmail(email)) {
            return false;
        } else return true;
    }

    public boolean checkDuplicateCustomer(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {                //check if duplicate
                return false;
            }
        }
        return true;
    }


}
