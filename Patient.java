
public class Patient {
    private String nic;
    private String name;
    private String email;
    private String phoneNumber;

    public Patient(String nic, String name, String email, String phoneNumber) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getNic() {
        return nic;
    }
}

