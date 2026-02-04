import java.io.*;
import java.util.*;

public class CSVHandler 
{

    // CHARGER les clients depuis un fichier CSV
    public static void chargerClients(Banque banque, String fichier) 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) 
        {
            String ligne;
            while ((ligne = reader.readLine()) != null) 
            {
                String[] champs = ligne.split(";");
                if (champs.length >= 2) {
                    String num = champs[0];
                    String nom = champs[1];
                    banque.ajouterClient(new Client(num, nom));
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Erreur lors du chargement des clients.");
            e.printStackTrace(); 
        }
    }

    // CHARGER les comptes
    public static void chargerComptes(Banque banque, String fichier) 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) 
        {
            String ligne;
            while ((ligne = reader.readLine()) != null) 
            {
                String[] champs = ligne.split(";");
                if (champs.length >= 5) 
                {
                    String num = champs[0];
                    String type = champs[1];
                    String clientNum = champs[2];
                    double solde = Double.parseDouble(champs[3]);
                    double extra = Double.parseDouble(champs[4]);

                    Client client = banque.rechercherClient(clientNum);
                    if (client == null) continue;

                    if (type.equalsIgnoreCase("Epargne")) 
                    {
                        client.ajouterCompte(new CompteEpargne(num, client, solde, extra));
                    } else 
                    {
                        client.ajouterCompte(new CompteCourant(num, client, solde, extra));
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Erreur lors du chargement des comptes.");
            e.printStackTrace(); 
        }
    }

    // CHARGER les transactions
    public static void chargerTransactions(Banque banque, String fichier) {
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) 
        {
            String ligne;
            while ((ligne = reader.readLine()) != null) 
            {
                String[] champs = ligne.split(";");
                if (champs.length >= 4) 
                {
                    String compteNum = champs[0];
                    String type = champs[1];
                    double montant = Double.parseDouble(champs[2]);
                    String date = champs[3]; // non utilisée pour l’instant

                    Compte compte = banque.rechercherCompte(compteNum);
                    if (compte != null) 
                    {
                        compte.transactions.add(new Transaction(type, montant)); // date actuelle
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Erreur lors du chargement des transactions.");
            e.printStackTrace(); 
        }
    }

    // SAUVEGARDER les clients
    public static void sauvegarderClients(Banque banque, String fichier) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) 
        {
            for (Client c : banque.getClients()) 
            {
                writer.write(c.getNumero() + ";" + c.getNom());
                writer.newLine();
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Erreur de sauvegarde des clients.");
        }
    }

    // SAUVEGARDER les comptes
    public static void sauvegarderComptes(Banque banque, String fichier) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) 
        {
            for (Client c : banque.getClients()) 
            {
                for (Compte compte : c.getComptes()) 
                {
                    String ligne = compte.getNumero() + ";" + compte.getType() + ";" + c.getNumero() + ";" + compte.getSolde();
                    if (compte instanceof CompteEpargne epargne) 
                    {
                        ligne += ";" + epargne.getTauxInteret(); 
                    } else if (compte instanceof CompteCourant courant) 
                    {
                        ligne += ";" + courant.getDecouvertAutorise(); 
                    }
                    writer.write(ligne);
                    writer.newLine();
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Erreur de sauvegarde des comptes.");
        }
    }

    // SAUVEGARDER les transactions
    public static void sauvegarderTransactions(Banque banque, String fichier) 
{
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) 
    {
        for (Client c : banque.getClients()) 
        {
            for (Compte compte : c.getComptes()) 
            {
                String typeCompte = compte instanceof CompteEpargne ? "Epargne" :
                                    compte instanceof CompteCourant ? "Courant" : "Inconnu";
                
                for (Transaction t : compte.transactions) 
                {
                    writer.write(compte.getNumero() + ";" + typeCompte + ";" + t.toString());
                    writer.newLine();
                }
            }
        }
    } 
    catch (IOException e) 
    {
        System.out.println("Erreur de sauvegarde des transactions.");
    }
}
}