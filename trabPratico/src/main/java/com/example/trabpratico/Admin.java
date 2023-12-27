public class Admin extends User{

    public Admin(){}

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin(int id, String username, String password, String email, String fullName, String phoneNumber, String address, String NIF, String CCNumber) {
        super(id, username, password, email, fullName, phoneNumber, address, NIF, CCNumber);
    }
}
