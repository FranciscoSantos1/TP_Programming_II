import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class    Repository implements Serializable {
    private static Repository repo = null;

    private Map<String, Customer> customersMap = new HashMap<>();
    private Map<String, CompanyOwner> companyOwnersMap = new HashMap<>();
    private Map<CompanyOwner, Company> companiesMap = new HashMap<>();
    private Map<String, Company> companiesLocalization = new HashMap<>();
    private Map<Integer, Admin> adminsMap = new HashMap<>();
    private Map<String, Employee> employeesMap= new HashMap<>();
    private Map<Integer, Service> services = new HashMap<>();
    private Map<Integer, Appointment> appointmentsMap = new HashMap<>();


    public Repository (){};

    public Map<String, Customer> getCustomers() {
        return customersMap;
    }
    public Map<String, CompanyOwner> getCompanyOwners() {
        return companyOwnersMap;
    }
    public Map<CompanyOwner, Company> getCompany() {
        return companiesMap;
    }
    public Map<String, Company> getCompaniesLocalization(){
        return companiesLocalization;
    }
    public Map<Integer, Admin> getAdmins() {return adminsMap;}
    public Map<String, Employee> getEmployees() {return employeesMap;}
    public Map<Integer, Service> getServices() {return services;}
    public Map<Integer, Appointment> getAppointments(){return appointmentsMap;}

    public static Repository getRepository(){
        if(repo == null) {
            repo = new Repository();
        }

        try {
            System.out.println("Reading from file...");
            repo = Repository.deserialize("repository.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ErrorDeserialize: " + e.getMessage());
        }
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
