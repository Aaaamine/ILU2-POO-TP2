package frontiere;

import controleur.ControlEmmenager;

public class BoundaryEmmenager {
	private ControlEmmenager controlEmmenager;

	public BoundaryEmmenager(ControlEmmenager controlEmmenager) {
		this.controlEmmenager = controlEmmenager;
	}

	public void emmenager(String nomVisiteur) {
		if (controlEmmenager.isHabitant(nomVisiteur)) {
			// Si le visiteur est déjà présent dans le village [cite: 92, 106]
			System.out.println("Mais vous êtes déjà un habitant du village !");
		} else {
			int choixUtilisateur = -1;
			do {
				StringBuilder question = new StringBuilder();
				question.append("Êtes-vous :\n");
				question.append("1 - un druide\n");
				question.append("2 - un gaulois\n");
				// Lecture du choix utilisateur [cite: 92, 113]
				choixUtilisateur = Clavier.entrerEntier(question.toString());
				
				switch (choixUtilisateur) {
				case 1:
					// Cas d'un druide [cite: 92, 121]
					emmenagerDruide(nomVisiteur);
					break;

				case 2:
					// Cas d'un gaulois [cite: 92, 129]
					System.out.println("Bienvenue villageois " + nomVisiteur);
					int force = Clavier.entrerEntier("Quelle est votre force ?");
					controlEmmenager.ajouterGaulois(nomVisiteur, force);
					break;

				default:
					System.out.println("Vous devez choisir le chiffre 1 ou 2 !");
					break;
				}
			} while (choixUtilisateur != 1 && choixUtilisateur != 2);
		}
	}

	private void emmenagerDruide(String nomVisiteur) {
		System.out.println("Bienvenue druide " + nomVisiteur);
		int forceDruide = Clavier.entrerEntier("Quelle est votre force ?");
		
		int effetPotionMin;
		int effetPotionMax;
		
		// Boucle de vérification de la force de potion [cite: 92, 116, 120]
		do {
			effetPotionMin = Clavier.entrerEntier("Quelle est la force de potion la plus faible que vous produisez ?");
			effetPotionMax = Clavier.entrerEntier("Quelle est la force de potion la plus forte que vous produisez ?");
			
			if (effetPotionMax < effetPotionMin) {
				System.out.println("Attention Druide, vous vous êtes trompé entre le minimum et le maximum");
			}
		} while (effetPotionMax < effetPotionMin);
		
		// Ajout final via le contrôleur [cite: 92, 127]
		controlEmmenager.ajouterDruide(nomVisiteur, forceDruide, effetPotionMin, effetPotionMax);
	}
}
