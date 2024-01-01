import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class    Repository implements Serializable {
    private static Repository repo = null;

    private Map<String, Customer> customersMap = new HashMap<>();               // Mapa de <NIF, Customer>
    private Map<String, CompanyOwner> companyOwnersMap = new HashMap<>();       // Mapa de <NIF, CompanyOwner>
    private Map<CompanyOwner, List<Company>> companiesMap = new HashMap<>();    // Mapa de <CompanyOwner, List<Company>>
    private Map<String, Company> companiesMapNIF = new HashMap<>();             // Mapa de <NIFOwner, Company>
    private Map<String, Company> companiesLocation = new HashMap<>();           // Mapa de <Location, Company>
    private Map<Integer, Admin> adminsMap = new HashMap<>();                    // Mapa de <idAdmin, Admin>
    private Map<String, Employee> employeesMap= new HashMap<>();                // Mapa de <NIF, Employee>
    private Map<Clinic, List<Service>> servicesClinicMap = new HashMap<>();     // Mapa de <Clinic, List<Service>>
    private Map<String, Service> services = new HashMap<>();                    // Mapa de <clinicNIF, Service>
    private Map<String, List<Appointment>> appointmentsMap = new HashMap<>();   // Mapa de <NIFCustomer, Appointment>
    private Map<Company, List<Clinic>> CompanieClinicsMap = new HashMap<>();    // Mapa de <Company, List<Clinic>>
    private Map<Clinic, List<Employee>> employeesClinicMap = new HashMap<>();   // Mapa de <Clinic, Employee>
    private Map<String, Clinic> clinicsMap = new HashMap<>();                   // Mapa de <NIF, Clinic>
    private Map<String, List<Clinic>> clinicsPerCompanyOner = new HashMap<>();  // Mapa de <NIFCompanyOwner, List<Clinic>>



    public Repository (){};

    public Map<String, Customer> getCustomers() {
        return customersMap;
    }
    public Map<String, CompanyOwner> getCompanyOwners() {
        return companyOwnersMap;
    }
    public Map<CompanyOwner, List<Company>> getCompanyFromCompanyOwner() {
        return companiesMap;
    }
    public Map<String, Company> getCompany() {
        return companiesMapNIF;
    }
    public Map<String, Company> getCompaniesLocation(){
        return companiesLocation;
    }
    public Map<Integer, Admin> getAdmins() {return adminsMap;}
    public Map<String, Employee> getEmployees() {return employeesMap;}
    public Map<String, Service> getServices() {return services;}
    public Map<Clinic, List<Service>> getServicesClinicMap(){return servicesClinicMap;}
    public Map<String, List<Appointment>> getAppointments(){return appointmentsMap;}
    public Map<Company, List<Clinic>> getCompanieClinicsMap(){return CompanieClinicsMap;}
    public Map<String, Clinic> getClinicsMap(){return clinicsMap;}
    public Map<Clinic, List<Employee>> getEmployeesClinicMap(){return employeesClinicMap;}
    public Map<String, List<Clinic>> getClinicsPerCompanyOwner(){return clinicsPerCompanyOner;}

    public static Repository getRepository(){

        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        if (repo == null)
            repo = new Repository();
        lock.unlock();

        return repo;
    }

    public void serialize(String filename) {
        // Serialize an object to file
        try{
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + filename);
        }catch(IOException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void deserialize(String filename) {
        try{
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            repo = (Repository) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Serialized data is loaded from " + filename);
        }
        catch(IOException ex){
            System.out.println("ErrorDeserialize: " + ex.getMessage());
        } catch(ClassNotFoundException ex){
            System.out.println("Repository class not found. " + ex.getMessage());
        }
    }

}
