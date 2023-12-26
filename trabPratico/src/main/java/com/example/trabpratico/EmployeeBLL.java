public class EmployeeBLL {
    public static void createEmployee(Employee employee, Clinic clinic){
        employee.setClinic(clinic);
        Repository.getRepository().getEmployees().put(employee.getNIF(), employee);

        Repository.getRepository().getEmployeesClinicMap().put(employee.getClinic(), employee);

        System.out.println("Funcionário criado com sucesso!!!");
        Repository.getRepository().serialize("src\\main\\resources\\data\\users.repo");
    }
}
