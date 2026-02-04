import java.time.LocalDate;

public class Transaction 
{
    private String type; 
    private double montant;
    private LocalDate date;

    public Transaction(String type, double montant) 
    {
        this.type = type;
        this.montant = montant;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() 
    {
        return type + ";" + montant + ";" + date;
    }
}
