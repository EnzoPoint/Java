import java.util.Scanner;

public class Play extends Game {

    public Play(String pseudoArtist, Integer money, String label, Integer popularity, Integer sonsDone, Integer sonsSortie) {
        super(pseudoArtist, money, label, popularity, sonsDone, sonsSortie);
    }

    /**
     * Affiche les stats du joueur
     */
    @Override
    public void Status() {
        Scanner reader = new Scanner(System.in);
        System.out.println("\nVoici vos stats : \nVotre Argent : \n" + money + " coins \nVotre Label : \n" + label + "\nVotre Popularité : \n" + popularity + "%" + "\nVos sons produits : \n" + sonsSortie + "\nVos sons sorties : \n" + sonsDone);
        reader.nextLine();
    }

    /**
     * Ajoute de l'argent
     */
    @Override
    public void addMoney() {
        //if (label != "Independent") money += 500 * 20 / 100;
        if (sonsSortie > 0) {
            if (sonsSortie != 1) money += sonsSortie * 500;
            money += 750;
        } else {
            money += 500;
        }
    }

    /**
     * Ajoute de la popularité
     */
    @Override
    public void addPopularity() {
        if (sonsSortie > 0) {
            if (sonsSortie != 1) popularity += sonsSortie * 10;
            popularity += 15;
        } else {
            popularity += 10;
        }
    }

    /**
     * Faire un achat sur le shop puis
     * @return sur la fonction convertion vers sont inventaire
     */
    @Override
    public void achatShop() {

        Scanner reader = new Scanner(System.in);

        System.out.println("\nVoulez vous achetez quelque chose ? (1 = oui / 2 = non) :");

        int shopBuy = Integer.parseInt(reader.next());
        if (shopBuy == 1) {

            boolean shopEnd = true;

            while (shopEnd) {
                System.out.println("Quel produit voulez-vous achetez ? (0 = exit / 1 = premier items / 2 = second ...) : ");
                int itemsTo = Integer.parseInt(reader.next());

                System.out.println(itemsTo);
                if (itemsTo != 0) {
                    if (positionItems.size() >= itemsTo) {
                        if (money >= shopItems.get(positionItems.get(itemsTo))) {
                            Object textValue = shopItems.keySet().toArray()[itemsTo];
                            Integer priceToPaid = shopItems.get(positionItems.get(itemsTo));
                            System.out.println("Vous êtes sur le point d'acheter un article / Votre solde actuel : " + money + " coins / Le prix du produit : " + shopItems.get(positionItems.get(itemsTo)) + " coins");
                            System.out.println("Confirmation d'achat (1 = Achat Valider / 2 = Non !)");
                            int validPurchase = Integer.parseInt(reader.next());
                            if (validPurchase == 1) {
                                if (itemsTo == 1 && sonsDone == 0) {
                                    System.out.println("Vous ne pouvez pas sortir de musique, il faut d'abord en faire, acheter une séance au studio en premier !");
                                } else {
                                    /// Add to Inventaire
                                    convertAchatToInventaire(itemsTo);
                                    /// Enleve l'argent
                                    money -= priceToPaid;
                                    // TO DO ADD BAG
                                }
                            }
                        } else {
                            System.out.println("Vous n'avez pas assez d'argent pour achéter l'article séléctioner / Votre solde actuel : " + money + " coins / Le prix du produit : " + shopItems.get(positionItems.get(itemsTo)) + " coins");
                            shopEnd = false;
                        }
                    }
                } else {
                    shopEnd = false;
                }
            }
        }
    }

    /**
     * Ajout de l'inventaire
     */
    @Override
    public void convertAchatToInventaire(Integer items) {

        if (items == 1) {
            sonsDone -= 1;
            sonsSortie += 1;
        }

        if (items == 2) {
            sonsDone += 2;
        }

        if (items == 3) {
            sonsDone += 1;
        }

    }

    /**
     * Affiche l'inventaire
     */
    @Override
    public void Inventaire() {
        Scanner reader = new Scanner(System.in);
        if (sonsSortie > 0 || sonsDone > 0) {
            System.out.println("Musique produit et prêt a être sortie : " + sonsDone + "\nMusique sortie : " + sonsSortie);
        } else {
            System.out.println("Il semblerais que vous n'ayez encore rien dans votre Inventaire");
        }
        reader.nextLine();
    }

    /**
     * Check si le level est fini :)
     */
    @Override
    public void checkIfLevelDone() {
        if (popularity >= 100) {
            System.out.println("Bien joué !! Tu est arrivé la case A, tu a donc fini le niveau 1 \nMalheuresement pendant le passage au niveau 2, tu est tombé sur une polémique tellement grande que tu a perdu toute ta popularité, il faudra donc dans le niveau 2, regagné ta popularité !!");
            startedGame = false;
        } else {
            System.out.println("Malheuresement tu est arrivé a la case A mais tu n'a pas les prérequis pour passé le niveau 1, il te faut 100% de popularité ou plus mais tu n'en a que " + popularity + "% continue a naviguer sur la MAP pour gagné en popularité et reviens sur la case quand tu a les prérequis pour gagner !");
        }
    }
}
