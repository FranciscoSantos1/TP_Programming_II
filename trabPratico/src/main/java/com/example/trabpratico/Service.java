import java.io.Serializable;

public class Service implements Serializable {
    private String serviceName;
    private Integer serviceId;
    private double servicePrice;
    private Clinic clinic;

    public Service(){
    }
    public Service(String serviceName, double servicePrice, Clinic clinic) {
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.clinic = clinic;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
