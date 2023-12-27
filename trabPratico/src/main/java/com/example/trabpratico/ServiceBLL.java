public class ServiceBLL {
    public static void createService(Service service, Clinic clinic) {
        service.setClinic(clinic);
        Repository.getRepository().getServices().put(service.getServiceId(), service);
        Repository.getRepository().getClinicsMap().get(clinic.getNIF()).getServices().put(service, service.getServicePrice());
        System.out.println("Serviço criado com sucesso!!!");
        Repository.getRepository().serialize("users.repo");
    }
}
