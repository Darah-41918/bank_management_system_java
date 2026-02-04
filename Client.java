import java.util.ArrayList;
import java.util.List;

public class Client 
{
    private String numero;
    private String nom;
    private List<Compte> comptes;

    public Client(String numero, String nom) 
    {
        this.numero = numero;
        this.nom = nom;
        this.comptes = new ArrayList<>();
    }

    public void ajouterCompte(Compte compte) 
    {
        comptes.add(compte);
    }

    public List<Compte> getComptes() 
    {
        return comptes;
    }

    public String getNumero() 
    {
        return numero;
    }

    public String getNom() 
    {
        return nom;
    }

    @Override
    public String toString() 
    {
        return numero + " - " + nom;
    }
}
