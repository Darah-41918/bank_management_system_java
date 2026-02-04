public class CompteCourant extends Compte 
{
    private double decouvertAutorise;

    public CompteCourant(String numero, Client proprietaire, double solde, double decouvertAutorise) 
    {
        super(numero, proprietaire, solde);
        this.decouvertAutorise = decouvertAutorise;
    }

    public double getDecouvertAutorise() 
    {
        return this.decouvertAutorise;
    }

    @Override
    public boolean retirer(double montant) 
    {
        if (solde + decouvertAutorise >= montant) 
        {
            solde -= montant;
            transactions.add(new Transaction("Retrait", montant));
            return true;
        }
        return false;
    }

    @Override
    public String getType() 
    {
        return "Courant";
    }
}
