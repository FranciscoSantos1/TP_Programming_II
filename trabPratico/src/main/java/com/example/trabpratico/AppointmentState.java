import java.io.Serializable;

public enum AppointmentState implements Serializable {
    PENDING,
    CANCELLED,
    PROCESSED,
    NOTPAYED,
    FINISHED
}
