package themysticalbard.sei.vault;

public class EmployeeFactory {
    public static Employee createEmployee(String code){
        return new Employee(code);
    }
}
