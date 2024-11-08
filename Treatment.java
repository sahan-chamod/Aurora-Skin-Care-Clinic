
public class Treatment {
    private String name;
    private double price; 
    private double registrationFee;

    public Treatment(String name, double price, double registrationFee) {
        this.name = name;
        this.price = price;
        this.registrationFee = registrationFee;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public double calculateTotalWithTax() {
        double total = price + registrationFee;
        double tax = total * 0.25; 
        return Math.round((total + tax) * 100.0) / 100.0; 
    }
}

