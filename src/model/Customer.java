package model;

import java.util.regex.Pattern;

public class Customer {

    private final String firstName;
    private final String lastName;
    public final  String email;

    public Customer(String email, String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;

        if (!checkEmail(email)) {
            throw new IllegalArgumentException("email must be a valid email address");
        } else {
            this.email = email;
        }
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }


    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+).com$");
        if (!pattern.matcher(email).matches()) {
            return false;
        } else
            return true;
    }

    @Override
    public String toString() {
        return " firstName: " + firstName + ", LastName: " + lastName + ", Email: " + email;
    }
}