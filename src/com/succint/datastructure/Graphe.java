package com.succint.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graphe<T> {
	int cmpt = 0;
	private int nbrVertex;
	private LinkedList<Node<T>> tab[];
	public Node<T> tabSommet[];

	public Graphe(int nbrVertex) {
		this.nbrVertex = nbrVertex;
		this.tab = new LinkedList[this.nbrVertex];
		for (int i = 0; i < tab.length; i++)
			tab[i] = new LinkedList<Node<T>>();

		tabSommet = new Node[nbrVertex];
	}

	public void addNode(T value) {
		Node<T> node = new Node<T>();
		node.index = cmpt;
		node.value = value;
		this.tabSommet[cmpt] = node;
		this.cmpt += 1;
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
	      if (this.tab[temp2.index].contains(temp1) == false) {
	        this.tab[temp2.index].add(temp1);
	        this.tab[temp1.index].add(temp2);
	      }
	    } else {
	    	System.out.println("impossible de créer cette liaison");
	    }
	}

	public void printGraphe() {
		for (int i = 0; i < tabSommet.length; i++) {
			System.out.print(tabSommet[i].value + "--->");
			for (int j = 0; j < tab[i].size(); j++) {
				System.out.print(" " + tab[i].get(j).value);
			}
			System.out.println();
		}
	}

	public LinkedList<Node<T>> getNeighbors(int index) {
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
				LinkedList<Node<T>> neigbhors = getNeighbors(index);
				for (Node<T> node : neigbhors) {
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

	public ArrayList<Integer> GloudEncoding(T root) {
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
				LinkedList<Node<T>> neigbhors = getNeighbors(index);
				for (Node<T> node : neigbhors) {
					if (node.marked == false) {
						node.indexInGraphe = cmpt;
						cmpt++;
						queue.add(node);
						node.marked = true;
						tabCode.add(1);
					} else {
						tabCode.add(2);
					}
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
