package themysticalbard.sei.vault;

public class Employee {
    private String password;
    Employee(String pass) {
        password = pass;
    }
    public boolean verifyPassword(String testPassword) {
        return password.equals(testPassword);
    }

    public void changePassword(String pass) {
        password = pass;
    }
}
