package themysticalbard.sei.vault;

import java.util.HashMap;
import java.util.Map;

public class Vault {
    private Map<String, Employee> accounts = new HashMap<>(2);
    private boolean locked;
    private boolean open;
    Vault() {
        locked = true;
        open = false;
        accounts.put("employee", new Employee("12345"));
        accounts.put("admin", new Administrator("123456"));
    }

    //Checks to see if the user exists in the accounts HashMap, and then checks to see if the employee associated with
    // that user has the same password as inputted.
    //The boolean returns true if the username and password match and are in the HashMap, otherwise false.
    public boolean verified(String user, String pass) {
        if(accounts.containsKey(user)) {
            return accounts.get(user).verifyPassword(pass);
        }
        else
            return false;
    }

    public void toggleLock() {
        locked = !locked;
    }

    public boolean isUnlocked() {
        return !locked;
    }

    public void toggleOpen() {
        open = !open;
    }

    public boolean isOpen() {
        return open;
    }
}