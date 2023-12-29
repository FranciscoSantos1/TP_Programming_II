import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceBLL {
    public static void createService(Service service, Clinic clinic) {
        service.setClinic(clinic);
        Repository repo = Repository.getRepository();
        repo.deserialize("users.repo");

        Map<Clinic, List<Service>> servicesClinicMap = repo.getServicesClinicMap();

        List<Service> services = servicesClinicMap.get(service.getClinic());
        if (services == null) {
            services = new ArrayList<>();
            servicesClinicMap.put(service.getClinic(), services);
        }

        services.add(service);

        Repository.getRepository().getServices().put(service.getServiceId(), service);
        Repository.getRepository().getClinicsMap().get(clinic.getNIF()).getServices().put(service, service.getServicePrice());
        System.out.println("Serviço criado com sucesso!!!");
        Repository.getRepository().serialize("users.repo");
    }
}
