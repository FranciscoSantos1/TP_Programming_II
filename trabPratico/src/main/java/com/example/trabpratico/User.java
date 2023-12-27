import java.io.Serializable;
import java.util.Date;

public abstract class User implements Serializable {
    protected int id;
    protected String username;
    protected String password;
    protected String email;
    protected String fullName;
    protected String phoneNumber;
    protected String address;
    protected String NIF;
    protected String CCNumber;

    public User(){}

    public User(int id, String username, String password, String email, String fullName, String phoneNumber, String address, String NIF, String CCNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.NIF = NIF;
        this.CCNumber = CCNumber;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getCCNumber() {
        return CCNumber;
    }

    public void setCCNumber(String CCNumber) {
        this.CCNumber = CCNumber;
    }
}
