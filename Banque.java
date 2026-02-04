import java.util.ArrayList;
import java.util.List;

public class Banque 
{
    private String nom;
    private List<Client> clients;

    public Banque(String nom)
    {
        this.nom = nom;
        this.clients = new ArrayList<>();
    }

    public void ajouterClient(Client client) 
    {
        clients.add(client);
    }

    public List<Client> getClients() 
    {
        return clients;
    }

    public List<Compte> getTousLesComptes() 
    {
        List<Compte> tousLesComptes = new ArrayList<>();
        for (Client client : clients) 
        {
            tousLesComptes.addAll(client.getComptes());
        }
        return tousLesComptes; 
    }

    public Client rechercherClient(String numero) 
    {
        for (Client c : clients) 
        {
            if (c.getNumero().equals(numero)) 
            {
                return c;
            }
        }
        return null;
    }

    public Compte rechercherCompte(String numeroCompte) 
    {
        for (Client client : clients) 
        {
            for (Compte compte : client.getComptes()) 
            {
                if (compte.getNumero().equals(numeroCompte)) 
                {
                    return compte;
                }
            }
        }
        return null;
    }
}
