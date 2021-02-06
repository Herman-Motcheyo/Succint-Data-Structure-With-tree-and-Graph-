package com.succint.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T> {
	int cmpt = 0;
	private int nbrVertex;
	private LinkedList<Node<T>> tab[];
	public Node<T> tabSommet[];
	public Node<T> root;

	public Tree(int nbrVertex) {
		this.nbrVertex = nbrVertex;
		this.tab = new LinkedList[this.nbrVertex];
		for (int i = 0; i < tab.length; i++)
			tab[i] = new LinkedList<Node<T>>();

		tabSommet = new Node[nbrVertex];
	}

	public void addNode(T value) {
		if (cmpt == 0) {
			this.root = new Node<T>();
			this.root.index = cmpt;
			this.root.value = value;
			this.tabSommet[cmpt] = this.root;
			this.cmpt += 1;
		} else {
			Node<T> node = new Node<T>();
			node.index = cmpt;
			node.value = value;
			this.tabSommet[cmpt] = node;
			this.cmpt += 1;
		}
	}

	public void addRoot(T root) {
		if (cmpt == 0) {
			this.root = new Node<T>();
			this.root.index = cmpt;
			this.root.value = root;
			this.tabSommet[cmpt] = this.root;
			this.cmpt += 1;
		} else{
			addNode(root);
			System.out.println("impossible d\'ajouter une nouvelle racine , la racine est: [" + this.root.value + "]");
		}
	}

	public void addEdge(int index1, int index2) {
		if (index1 > nbrVertex && index2 > nbrVertex) {
			System.out.println("erreur");
		} else {
			Node<T> temp1 = null;
			Node<T> temp2 = null;
			for (int i = 0; i < tabSommet.length; i++) {
				if (tabSommet[i].index == index1)
					temp1 = tabSommet[i];

				if (tabSommet[i].index == index2)
					temp2 = tabSommet[i];
			}

			// System.out.println(temp1.value +" " + temp2.value);
			this.tab[index1].addFirst(temp2);
			this.tab[index2].addFirst(temp1);
		}
	}

	public void addEdge(T value1, T value2) {
		Node<T> temp1 = null;
		Node<T> temp2 = null;
		for (int i = 0; i < tabSommet.length; i++) {
			if (tabSommet[i].value.equals(value1) == true)
				temp1 = tabSommet[i];

			if (tabSommet[i].value.equals(value2) == true)
				temp2 = tabSommet[i];
		}
		// on verifie que les sommets entrer exitent et qu'il ne sont pas identiques
		if (temp1 != null && temp2 != null && temp1.index != temp2.index) {
			/*
			 * dans le cas d'un arbre la on verifie que la racine n'est pas le fils du
			 * sommet dont on veut creer la liaison
			 */
			if (temp2.index != this.root.index) {
				/*
				 * dans le cas d'un arbre on verifie que le fils qu'on passe en parametre n'est
				 * pas deja le père de celui qui est passé en paramètre
				 */
				if (this.tab[temp2.index].contains(temp1) == false) {
					/**
					 * on verifie si le fils qu'on veu inserer n'est pas deja contenu dans la liste
					 * des fils du nœud parent
					 */
					if (this.tab[temp1.index].contains(temp2) == false) {
						this.tab[temp1.index].add(temp2);
					}
				} else {
					System.out.println("impossible de créer cette liaison [" + temp1.value
							+ "] est deja le fils de [ + " + temp2.value + "]");
				}
			} else {
				System.out.println("la racine [" + this.root.value + "] ne peut pas etre le fils d'un sommet");
			}
		} else {
			System.out.println("impossible de créer cette liaison");
		}
	}

	public void printTree() {
		for (int i = 0; i < tabSommet.length; i++) {
			if (tabSommet[i].index == 0) {
				System.out.print("root: " + tabSommet[i].value + "--->");
			} else {
				System.out.print(tabSommet[i].value + "--->");
			}
			for (int j = 0; j < tab[i].size(); j++) {
				System.out.print(" " + tab[i].get(j).value);
			}
			System.out.println();
		}
	}

	public LinkedList<Node<T>> getChildren(int index) {
		if (index > this.nbrVertex) {
			return null;
		} else {
			return this.tab[index];
		}
	}

	public int getIndex(T node) {
		for (int i = 0; i < tabSommet.length; i++) {
			if (node.equals(tabSommet[i].value) == true) {
				return tabSommet[i].index;
			}
		}
		return -1;
	}

	public int getIndexInGraphe(T nodeValue) {
		for (int i = 0; i < tabSommet.length; i++) {
			if (nodeValue.equals(tabSommet[i].value)) {
				return tabSommet[i].indexInGraphe;
			}
		}
		return -1;
	}

	public ArrayList<T> breathFirstSearch(T root) {
		int indexRoot = getIndex(root);
		int index = 0;
		ArrayList<T> tabParcours = new ArrayList<T>();
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		if (indexRoot != -1) {
		      queue.add(tabSommet[indexRoot]);
		      tabSommet[indexRoot].marked = true;
		      while (queue.isEmpty() == false) {
		        Node<T> temp = queue.remove();
		        index = getIndex(temp.value);
		        tabSommet[index].marked = true;
		        LinkedList<Node<T>> children = getChildren(index);
		        for (Node<T> node : children) {
		          if (node.marked == false) {
		            queue.add(node);
		            node.marked = true;
		          }
		        }
		        tabParcours.add(temp.value);
		      }
		    }
		
		for (int i = 0; i < tabSommet.length; i++) {
			tabSommet[i].marked = false;
		}
		return tabParcours;
	}

	public ArrayList<Integer> loudEncoding(T root) {
		int indexRoot = getIndex(root);
		int index = 0, cmpt = 1;
		ArrayList<Integer> tabCode = new ArrayList<Integer>();
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		if (indexRoot != -1) {
			tabCode.add(1);
			tabCode.add(0);
			queue.add(tabSommet[indexRoot]);
			tabSommet[indexRoot].indexInGraphe = cmpt;
			cmpt++;
			tabSommet[indexRoot].marked = true;
			while (queue.isEmpty() == false) {
		        Node<T> temp = queue.remove();
		        index = getIndex(temp.value);
		        LinkedList<Node<T>> children = getChildren(index);
		        for (Node<T> node : children) {
		          node.indexInGraphe = cmpt;
		          cmpt++;
		          queue.add(node);
		          node.marked = true;
		          tabCode.add(1);
		        }
		        tabCode.add(0);
		      }

		}
		
		
		for (int i = 0; i < tabSommet.length; i++) {
			tabSommet[i].marked = false;
		}
		return tabCode;
	}

}
