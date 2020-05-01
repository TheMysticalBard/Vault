package themysticalbard.sei.vault;

public class Administrator extends Employee {
    private Administrator(String pass) {
        super(pass);
    }

    private static Administrator admin;
    public static Administrator getAdmin(){
        return admin == null ? (admin = new Administrator("123456")) : admin;
    }
}