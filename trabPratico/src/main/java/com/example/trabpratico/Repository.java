import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class    Repository implements Serializable {
    private static Repository repo = null;

    private Map<String, Customer> customersMap = new HashMap<>();               // Mapa de <NIF, Customer>
    private Map<String, CompanyOwner> companyOwnersMap = new HashMap<>();       // Mapa de <NIF, CompanyOwner>
    private Map<CompanyOwner, Company> companiesMap = new HashMap<>();          // Mapa de <CompanyOwner, Company>
    private Map<String, Company> companiesMapNIF = new HashMap<>();             // Mapa de <NIF, Company>
    private Map<String, Company> companiesLocation = new HashMap<>();         // Mapa de <Location, Company>
    private Map<Integer, Admin> adminsMap = new HashMap<>();                    // Mapa de <idAdmin, Admin>
    private Map<String, Employee> employeesMap= new HashMap<>();                // Mapa de <NIF, Employee>
    private Map<Integer, Service> services = new HashMap<>();                   // Mapa de <ServiceId, Service>
    private Map<Integer, Appointment> appointmentsMap = new HashMap<>();        // Mapa de <AppointmentId, Appointment>
    private Map<Company, Clinic> CompanieClinicsMap = new HashMap<>();                  // Mapa de <Company, Clinic>
    protected Map<Clinic, Employee> employeesClinicMap = new HashMap<>();       // Mapa de <Clinic, Employee>
    public Map<String, Clinic> clinicsMap = new HashMap<>();


    public Repository (){};

    public Map<String, Customer> getCustomers() {
        return customersMap;
    }
    public Map<String, CompanyOwner> getCompanyOwners() {
        return companyOwnersMap;
    }
    public Map<CompanyOwner, Company> getCompanyFromCompanyOwner() {
        return companiesMap;
    }
    public Map<String, Company> getCompaniesNIF() {
        return companiesMapNIF;
    }
    public Map<String, Company> getCompaniesLocation(){
        return companiesLocation;
    }
    public Map<Integer, Admin> getAdmins() {return adminsMap;}
    public Map<String, Employee> getEmployees() {return employeesMap;}
    public Map<Integer, Service> getServices() {return services;}
    public Map<Integer, Appointment> getAppointments(){return appointmentsMap;}
    public Map<Company, Clinic> getCompanieClinicsMap(){return CompanieClinicsMap;}
    public Map<String, Clinic> getClinicsMap(){return clinicsMap;}
    public Map<Clinic, Employee> getEmployeesClinicMap(){return employeesClinicMap;}

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

    public static Repository deserialize(String filename)
            throws ClassNotFoundException, IOException {
        Repository repo = null;
        try{
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            repo = (Repository) in.readObject();
            in.close();
            fileIn.close();
        }
        catch(IOException ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
        catch(ClassNotFoundException ex){
            System.out.println("Repositorio class not found. " + " "
                    + ex.getMessage());
            return null;
        }
        return repo;
    }

}
