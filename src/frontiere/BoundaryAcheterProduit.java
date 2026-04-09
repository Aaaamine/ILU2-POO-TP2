package frontiere;

import java.util.Scanner;
import controleur.ControlAcheterProduit;
import personnages.Gaulois;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Je suis désolée " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici.");
			return;
		}

		System.out.println("Quel produit voulez-vous acheter ?");
		String produit = scan.next();
		Gaulois[] vendeurs = controlAcheterProduit.rechercherVendeursProduit(produit);

		if (vendeurs == null) {
			System.out.println("Désolé, personne ne vend ce produit au marché.");
		} else {
			System.out.println("Chez quel commerçant voulez-vous acheter des " + produit + " ?");
			for (int i = 0; i < vendeurs.length; i++) {
				System.out.println((i + 1) + " - " + vendeurs[i].getNom());
			}
			
			int choixVendeur = scan.nextInt();
			String nomVendeur = vendeurs[choixVendeur - 1].getNom();
			
			System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + nomVendeur);
			System.out.println("Bonjour " + nomAcheteur);
			System.out.println("Combien de " + produit + " voulez-vous acheter ?");
			int quantiteSouhaitee = scan.nextInt();

			int quantiteAchetee = controlAcheterProduit.acheterProduit(nomVendeur, quantiteSouhaitee);

			if (quantiteAchetee == 0) {
				System.out.println(nomAcheteur + " veut acheter " + quantiteSouhaitee + " " + produit + ", malheureusement il n'y en a plus !");
			} else if (quantiteAchetee < quantiteSouhaitee) {
				System.out.println(nomAcheteur + " veut acheter " + quantiteSouhaitee + " " + produit + ", malheureusement " + nomVendeur + " n'en a plus que " + quantiteAchetee + ". " + nomAcheteur + " achète tout le stock de " + nomVendeur + ".");
			} else {
				System.out.println(nomAcheteur + " achète " + quantiteSouhaitee + " " + produit + " à " + nomVendeur + ".");
			}
		}
	}
}
