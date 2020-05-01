package themysticalbard.sei.vault;

import java.util.HashMap;
import java.util.Map;

public class Vault {
    private Employee user;
    private Map<String, Employee> accounts = new HashMap<>(2);
    private boolean locked;
    private boolean open;
    //Constructor initializing the default state of the vault
    Vault() {
        user = null;
        locked = true;
        open = false;
        accounts.put("employee", EmployeeFactory.createEmployee("12345"));
        accounts.put("admin", Administrator.getAdmin());
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

    //Method to change the state of the lock
    public void toggleLock() {
        locked = !locked;
    }

    public boolean isUnlocked() {
        return !locked;
    }

    //Method to change the state of the door
    public void toggleOpen() {
        open = !open;
    }

    public boolean isOpen() {
        return open;
    }

    public void setUser(String username) {
        //If username is in the map, get the user
        if(accounts.containsKey(username))
            user = accounts.get(username);
        //If the username is empty, don't set the user
        if(username == null)
            user = null;
    }

    public Employee currentUser() {
        return user;
    }

    public void changePassword(String user, String pass) {
        //Check if the username is already in the accounts map
        if(accounts.containsKey(user)) {
            //Update the password in the map
            accounts.get(user).changePassword(pass);
        }
        else{
            //Make sure the username is not empty
            if (user!=null)
                //Create a new key in the map.
                accounts.put(user, new Employee(pass));
        }
    }
}