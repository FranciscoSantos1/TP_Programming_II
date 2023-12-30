import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeBLL {
    public static void createEmployee(Employee employee, Clinic clinic){
        employee.setClinic(clinic);
        Repository.getRepository().getEmployees().put(clinic.getNIF(), employee);


        Map<Clinic, List<Employee>> companyListMap = Repository.getRepository().getEmployeesClinicMap();

        List<Employee> employees = companyListMap.get(employee.getClinic());
        if(employees == null){
            employees = new ArrayList<>();
            companyListMap.put(employee.getClinic(), employees);
        }

        employees.add(employee);
        employee.getClinic().getEmployees().add(employee);
//        clinic.getEmployees().add(employee);

        System.out.println("Funcionário criado com sucesso!!!");
        Repository.getRepository().serialize("users.repo");
    }
}
