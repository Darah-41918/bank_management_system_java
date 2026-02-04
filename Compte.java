import java.util.ArrayList;
import java.util.List;

public abstract class Compte 
{
    protected String numero;
    protected Client proprietaire;
    protected double solde;
    protected List<Transaction> transactions;

    public Compte(String numero, Client proprietaire, double solde) 
    {
        this.numero = numero;
        this.proprietaire = proprietaire;
        this.solde = solde;
        this.transactions = new ArrayList<>();
    }

    public void deposer(double montant) 
    {
        solde += montant;
        transactions.add(new Transaction("Depot", montant));
    }

    public boolean retirer(double montant) 
    {
        if (solde >= montant) 
        {
            solde -= montant;
            transactions.add(new Transaction("Retrait", montant));
            return true;
        }
        return false;
    }

    public void afficherTransactions() 
    {
        for (Transaction t : transactions) 
        {
            System.out.println(t);
        }
    }

    public double getSolde() 
    {
        return solde;
    }

    public String getNumero() 
    {
        return numero;
    }

    public Client getProprietaire() 
    {
        return proprietaire;
    }

    public abstract String getType(); 
}