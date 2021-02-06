package com.succint.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.succint.datastructure.Encoding;
import com.succint.datastructure.Graphe;
import com.succint.datastructure.Tree;

public class Main {

	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		int choice;
		int nbreSommets = 0, nbreMaxArret;
		String value, value1, value2, root;
		int rep = 1;
		int reponse;
		do {
			System.out.println(
					"               \n          ****************Bienvenue sur le Tp301 Structure DataStructure Groupe 24 **************");
			System.out.println("\n                                    SUCCINT DATA STRUCTURE                         ");
			System.out.println("  \n                               ******** 1 Arbre   *********  ");
			System.out.println("                               ******** 2 Graphe  ********* \n ");
			System.out.print("             /// Faites un choix  svp 1 = arbre et 2 = graphe// \t  ");

			choice = clavier.nextInt();
			clavier.nextLine();
			switch (choice) {
			case 1:
				System.out.println("\n                                     ********* 1 Arbre  *********  ");
				do {
					System.out.print("  ** Entrer le nombre de sommets de votre Arbre ** \t");
					nbreSommets = clavier.nextInt();
				} while (nbreSommets <= 0);
				clavier.nextLine();
				Tree<String> tree = new Tree<String>(nbreSommets);
				for (int i = 0; i < nbreSommets; i++) {
					if (i == 0) {
						System.out.println(
								"************************ Entrer la valeur de la racine **************************");
						value = clavier.nextLine();
						tree.addRoot(value);
					} else {
						System.out.print("** Entrer la valeur du noeud [" + (i + 1) + "] \t");
						value = clavier.nextLine();
						tree.addNode(value);
					}
				}

				nbreMaxArret = nbreSommets * (nbreSommets - 1) / 2;
				System.out.println(
						"*************************** Les noeuds de l'arbre sont: **************************************");
				for (int j = 0; j < tree.tabSommet.length; j++) {
					System.out.print("[" + tree.tabSommet[j].value + "] ");
				}
				System.out.println("\n");

				System.out.println("\n         ************ Maintenant il faut creer les arretes ********      \n ");
				for (int i = 0; i < nbreMaxArret; i++) {
					System.out.println(
							"**********************************************************************************");
					System.out.print("Entrer la valeur du père \t");
					value1 = clavier.nextLine();
					System.out.print("Entrer la valeur du fils \t");
					value2 = clavier.nextLine();
					tree.addEdge(value1, value2);
					do {
						System.out.println("** voulez vous ajouter un nouveau arret oui = 1 non = 0 **");
						rep = clavier.nextInt();
					} while (rep != 0 && rep != 1);
					clavier.nextLine();
					if (rep == 0)
						break;
				}
				System.out.println(
						"                   Representation de l' Arbre sous forme de Liste d' adjascence ****                        \\n ");
				tree.printTree();
				System.out.println(
						"**********************  Le Parcours en largeur de  l'arbre est  ********************************");
				ArrayList<String> parcours = tree.breathFirstSearch(tree.root.value);
				System.out.println(parcours);
				System.out.println(
						"*****************************Le codage de l'arbre avec Lound Encoding est:***************************");
				ArrayList<Integer> code = tree.loudEncoding(tree.root.value);
				System.out.println("                       " + code + "                   ");
				System.out.println("  \n              **Operation à effectuer sur  l 'arbre n- aire** ");
				System.out.println("                    1- Parent d\' un noeud            ");
				System.out.println("                    2- Les enfants d\' un noeud            ");
				choice = clavier.nextInt();
				clavier.nextLine();
				switch (choice) {
				case 1:
					do {
						System.out.println(
								"*****************Entrer le sommet dont vous voulez connaitre le père ************************");
						String sommet = clavier.nextLine();
						int index = Encoding.parentOfANode(tree.getIndexInGraphe(sommet), code);
						for (int i = 0; i < tree.tabSommet.length; i++) {
							if (tree.tabSommet[i].indexInGraphe == index) {
								System.out.println("le père du sommet " + "[" + sommet + "]" + " est " + "["+ tree.tabSommet[i].value+ "]");
							}
						}
						do {
							System.out.println(
									"**************** voulez vous connaitre le père d'un autre sommet oui = 1 non = 0 **********");
							rep = clavier.nextInt();
						} while (rep != 0 && rep != 1);
						clavier.nextLine();
					} while (rep == 1);
					break;
				case 2:

					do {
						System.out.println(
								"*****************Entrer le sommet dont vous volez connaitre les enfants ********************");
						String sommet = clavier.nextLine();
						ArrayList<Integer> tabChildren = Encoding.getChildrenOfNode(code,
								tree.getIndexInGraphe(sommet));
						System.out.print("les enfants de [" + sommet + "] sont : ");
						for (var i = 0; i < tabChildren.size(); i++) {
							for (var j = 0; j < tree.tabSommet.length; j++) {
								if (tree.tabSommet[j].indexInGraphe == tabChildren.get(i)) {
									System.out.print("[" + tree.tabSommet[j].value + "] ");
								}
							}
						}
						System.out.println("\n");
						do {
							System.out.println(
									"**************** voulez vous connaitre les fils d'un autre sommet oui = 1 non = 0 **********");
							rep = clavier.nextInt();
						} while (rep != 0 && rep != 1);
						clavier.nextLine();
					} while (rep == 1);
					break;

				default:
					System.out.println("         Choix Incorrect    svp faite un choix correct   ");
					break;
				}

				break;
			case 2:
				System.out.println("\n                                     ******** 2 Graphe  ********* ");

				do {
					System.out.println(
							"******************* Entrer le nombre de sommets de votre graphe **************************");
					nbreSommets = clavier.nextInt();
				} while (nbreSommets <= 0);
				clavier.nextLine();
				Graphe<String> graphe = new Graphe<String>(nbreSommets);
				for (int i = 0; i < nbreSommets; i++) {
					System.out.print("  Entrer la valeur du noeud [" + (i + 1) + "] \t");
					value = clavier.nextLine();
					graphe.addNode(value);
				}

				nbreMaxArret = nbreSommets * (nbreSommets - 1) / 2;
				System.out.println(" ** Le graphe entre est **");
				for (int j = 0; j < graphe.tabSommet.length; j++) {
					System.out.print("[" + graphe.tabSommet[j].value + "] ");
				}
				System.out.println("\n");
				for (int i = 0; i < nbreMaxArret; i++) {
					System.out.println(
							"**********************************************************************************");
					System.out.print("Entrer la valeur du premier sommet \t");
					value1 = clavier.nextLine();
					System.out.print(" Entrer la valeur du deuxieme sommet \t");
					value2 = clavier.nextLine();
					graphe.addEdge(value1, value2);
					do {
						System.out.println("voulez vous ajouter un nouveau arret oui = 1 non = 0");
						rep = clavier.nextInt();
					} while (rep != 0 && rep != 1);
					clavier.nextLine();
					if (rep == 0)
						break;
				}
				System.out.println(" **La materialisation du graphe entré est ** ");
				graphe.printGraphe();
				do {
					System.out.println("  Entrer la racine  ");
					root = clavier.nextLine();
					if (graphe.getIndex(root) == -1) {
						System.out.println(
								"*******************Ce sommet n'est pas inclu dans ce graphe****************************");
					}
				} while (graphe.getIndex(root) == -1);

				System.out.println(
						"*******************Le parcours en largeur de  ce graphe est**********************************");
				if (graphe.getIndex(root) != -1) {
					ArrayList<String> parcours1 = graphe.breathFirstSearch(root);
					System.out.println(parcours1);
					System.out.println(
							"***********************Le Codage de ce graphe avec Glound est************************************");

					ArrayList<Integer> code1 = graphe.GloudEncoding(root);
					System.out.println(code1);
				}
				break;
			default:
				break;
			}
			System.out.print("Voulez vous revenir au menu principal ? (1= oui et 0 = non)");
			reponse = clavier.nextInt();
		} while (reponse != 0);

		clavier.close();
	}

}
