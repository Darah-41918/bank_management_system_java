public class CompteEpargne extends Compte 
{
    private double tauxInteret;

    public CompteEpargne(String numero, Client proprietaire, double solde, double tauxInteret) 
    {
        super(numero, proprietaire, solde);
        this.tauxInteret = tauxInteret;
    }

    public void appliquerInterets() 
    {
    double interet = getSolde() * tauxInteret;
    deposer(interet);
    transactions.add(new Transaction("Intérêts ajoutés", interet));
    }

    public void appliquerInteret() 
    {
        double interet = solde * tauxInteret;
        deposer(interet);
    }

    public void setTauxInteret(double taux) 
    {
        this.tauxInteret = taux;
    }

    public double getTauxInteret() 
    {
        return this.tauxInteret;
    }

    @Override
    public String getType() 
    {
        return "Epargne";
    }
}
