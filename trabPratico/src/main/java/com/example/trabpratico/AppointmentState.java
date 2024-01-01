import java.io.Serializable;

public enum AppointmentState implements Serializable {
    PROCESSADA,
    REALIZADA,      //realiza mas nao paga
    PAGA,           // paga mas nao realizada
    FINALIZADA,     //paga e realizada
    CANCELADA
}
