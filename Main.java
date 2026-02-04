import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Banque banque = new Banque("maBanque");

        CSVHandler.chargerClients(banque, "clients.csv");
        CSVHandler.chargerComptes(banque, "comptes.csv");
        CSVHandler.chargerTransactions(banque, "transactions.csv");

        int choix = -1;
        while (choix != 0) 
        {
            System.out.println(" Bienvenue dans le système de gestion de MaBanque !");
            System.out.println("Veuillez choisir une option dans le menu ci-dessous.\n");
            System.out.println("Menu principal :");
            System.out.println("1- Ajouter un client");
            System.out.println("2- Lister les clients");
            System.out.println("3- Ajouter un compte");
            System.out.println("4- Lister les comptes des clients");
            System.out.println("5- Déposer");
            System.out.println("6- Retirer");
            System.out.println("7- Virement");
            System.out.println("8- Afficher balance");
            System.out.println("9- Afficher transactions");
            System.out.println("10- Appliquer intérêts");
            System.out.println("0- Quitter");
            System.out.println("Votre choix : ");

            String input = sc.nextLine(); 
            try 
            {
                choix = Integer.parseInt(input); 
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Entrée invalide. Veuillez saisir un chiffre entre 0 et 9.");
                continue;
            }

            switch (choix) 
            {
                case 1:
                    System.out.println("Ajout d'un nouveau client à la banque.");
                    System.out.print("Veuillez entrer le numéro du client : \n");
                    System.out.print("Numéro du client : ");
                    String numero = sc.nextLine();
                    if (banque.rechercherClient(numero) != null) {
                    System.out.println("Ce numéro de client existe déjà. Veuillez en choisir un autre.");
                    break;
                    }
                    System.out.print("Veuillez entrer le nom du client : \n");
                    System.out.print("Nom du client : ");
                    String nom = sc.nextLine();
                    banque.ajouterClient(new Client(numero, nom));
                    System.out.println("Client ajouté avec succès !");
                    break;

                case 2:
                    System.out.println("Liste des clients :");
                    for (Client c : banque.getClients()) 
                    {
                        System.out.println("➡ " + c.getNumero() + " - " + c.getNom());
                    }
                    break;

                case 3: 
                    System.out.println("Création d’un nouveau compte pour un client.");
                    System.out.print("Numéro du client : ");
                    String numClientCompte = sc.nextLine();
                    Client clientCompte = banque.rechercherClient(numClientCompte);
                    if (clientCompte == null) 
                    {
                    System.out.println("Client non trouvé !");
                    break;
                    }
                    System.out.print("Numéro du nouveau compte : ");
                    String numCompte = sc.nextLine();
                    System.out.print("Type (Epargne ou Courant) : ");
                    String typeCompte = sc.nextLine();
                    if (typeCompte.equalsIgnoreCase("Epargne")) {
                    System.out.print("Taux d'intérêt (ex: 0.05) : ");
                    double taux = Double.parseDouble(sc.nextLine());
                    clientCompte.ajouterCompte(new CompteEpargne(numCompte, clientCompte, 0.0, taux));
                    System.out.println("Compte épargne ajouté !");
                    } 
                    else if (typeCompte.equalsIgnoreCase("Courant")) 
                    {
                    System.out.print("Découvert autorisé : ");
                    double decouvert = Double.parseDouble(sc.nextLine());
                    clientCompte.ajouterCompte(new CompteCourant(numCompte, clientCompte, 0.0, decouvert));
                    System.out.println("Compte courant ajouté !");
                    } 
                    else 
                    {
                    System.out.println("Type de compte inconnu !");
                    }
                    break;
                
                case 4:
                    System.out.println("Liste des comptes de tous les clients :");
                    for (Client c : banque.getClients()) {
                    System.out.println("Client : " + c.getNom() + " (" + c.getNumero() + ")");
                    for (Compte compte : c.getComptes()) {
                    String type = (compte instanceof CompteEpargne) ? "Épargne" :
                                  (compte instanceof CompteCourant) ? "Courant" : "Inconnu";
                    System.out.println("Compte " + compte.getNumero() + " | Type : " + type + " | Solde : " + compte.getSolde() + " HTG");
                        }
                    }
                    break;

                case 5: 
                    System.out.println("Dépôt d'argent sur un compte.");
                    System.out.print("Numéro du compte : ");
                    String numCompteDepot = sc.nextLine();
                    Compte compteDepot = banque.rechercherCompte(numCompteDepot);
                    if (compteDepot == null) 
                    {
                    System.out.println("Compte non trouvé !");
                    break;
                    }
                    System.out.print("Montant à déposer : ");
                    double montantDepot = Double.parseDouble(sc.nextLine());
                    compteDepot.deposer(montantDepot);
                    System.out.println("Dépôt effectué !");
                    break;

                case 6: 
                    System.out.println("Retrait d'argent d'un compte.");
                    System.out.print("Numéro du compte : ");
                    String numCompteRetrait = sc.nextLine();
                    Compte compteRetrait = banque.rechercherCompte(numCompteRetrait);
                    if (compteRetrait == null) 
                    {
                    System.out.println("Compte non trouvé !");
                    break;
                    }
                    System.out.print("Montant à retirer : ");
                    double montantRetrait = Double.parseDouble(sc.nextLine());
                    boolean succesRetrait = compteRetrait.retirer(montantRetrait);
                    if (succesRetrait) 
                    {
                    System.out.println("Retrait effectué !");
                    } 
                    else 
                    {
                    System.out.println("Fonds insuffisants !");
                    }
                    break;

                case 7: 
                    System.out.println("Virement entre deux comptes.");
                    System.out.print("Numéro du compte source : ");
                    String numCompteSource = sc.nextLine();
                    System.out.print("Numéro du compte destination : ");
                    String numCompteDest = sc.nextLine();
                    Compte compteSource = banque.rechercherCompte(numCompteSource);
                    Compte compteDest = banque.rechercherCompte(numCompteDest);
                    if (compteSource == null || compteDest == null) 
                    {
                    System.out.println("Un des comptes n'a pas été trouvé !");
                    break;
                    }
                    System.out.print("Montant du virement : ");
                    double montantVirement = Double.parseDouble(sc.nextLine());
                    if (compteSource.retirer(montantVirement)) 
                    {
                    compteDest.deposer(montantVirement);
                    compteSource.transactions.add(new Transaction("Virement Débit", montantVirement));
                    compteDest.transactions.add(new Transaction("Virement Crédit", montantVirement));
                    System.out.println("Virement effectué !");
                    } 
                    else 
                    {
                    System.out.println("Fonds insuffisants pour le virement !");
                    }
                    break;

                case 8: 
                    System.out.print("Numéro du compte : ");
                    String numCompteSolde = sc.nextLine();
                    Compte compteSolde = banque.rechercherCompte(numCompteSolde);
                    if (compteSolde == null) 
                    {
                    System.out.println("Compte non trouvé !");
                    break;
                    }
                    System.out.println("Solde du compte " + numCompteSolde + " : " + compteSolde.getSolde());
                    break;

                case 9: 
                    System.out.print("Numéro du compte : ");
                    String numCompteTrans = sc.nextLine();
                    Compte compteTrans = banque.rechercherCompte(numCompteTrans);
                    if (compteTrans == null) 
                    {
                    System.out.println("Compte non trouvé !");
                    break;
                    }
                    System.out.println("Transactions du compte " + numCompteTrans + " :");
                    compteTrans.afficherTransactions();
                    break;
                    

                case 10: 
                    System.out.println("Application des intérêts aux comptes épargne...");
                    double totalInterets = 0;
                    for (Compte compte : banque.getTousLesComptes()) 
                    {
                    if (compte instanceof CompteEpargne) 
                    {
                    CompteEpargne ce = (CompteEpargne) compte;
                    double ancienSolde = ce.getSolde();
                    ce.appliquerInterets();
                    double interet = ce.getSolde() - ancienSolde;
                    totalInterets += interet;
                }
            }
                   System.out.println("Intérêts appliqués à tous les comptes épargne.");
                   System.out.println("Total des intérêts crédités : " + totalInterets + " HTG");
                   break;
    

                case 0:
                    System.out.println("Merci d'avoir utilisé le système bancaire !");
                    break;

                default:
                    System.out.println("Choix non valide. Veuillez réessayer.");
            }
        }

        CSVHandler.sauvegarderClients(banque, "clients.csv");
        CSVHandler.sauvegarderComptes(banque, "comptes.csv");
        CSVHandler.sauvegarderTransactions(banque, "transactions.csv");
    }
}