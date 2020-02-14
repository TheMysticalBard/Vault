package themysticalbard.sei.vault;

public class Employee {
    private String password;
    Employee(String pass) {
        password = pass;
    }
    //Method that returns whether or not the password is valid
    public boolean verifyPassword(String testPassword) {
        return password.equals(testPassword);
    }

    //Method to update the employee's password
    public void changePassword(String pass) {
        password = pass;
    }
}
