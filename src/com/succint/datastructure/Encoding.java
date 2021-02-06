package com.succint.datastructure;

import java.util.ArrayList;

import com.succint.utile.Rank;
import com.succint.utile.Select;

public class Encoding {

	/*
	 * return the parent of a node in a tree
	 * 
	 * @parameter : bit vector of a tree
	 */

	public static int parentOfANode(int k, ArrayList<Integer> S) {
		int y = Select.select1(S, k);
		int j = Rank.rank0(S, y);
		return j;
	}

	/*
	 * This method return Arraylist of children in a tree she takes bit vector in a
	 * parameter come from a tree
	 */
	public static ArrayList<Integer> getChildrenOfNode(ArrayList<Integer> S, int vertex) {
		ArrayList<Integer> tabChildren = new ArrayList<Integer>();
		int y = Select.select0(S, vertex);
		int p = 1;
		// System.out.println(y);
		for (int i = y; i < S.size(); i++) {
			if (S.get(i) == 1) {
				tabChildren.add(Rank.rank1(S, y + p));
				p++;
			} else {
				break;
			}
		}
		return tabChildren;
	}

	/**
	 * 
	 ************************* Gloud *******************
	 **/

	/*
	 * This method takes a ternary vector of a graph and return children of a list
	 * of children
	 * 
	 * @param ArrayList<Integer> S : Ternary vector , ArrayList<Integer> H : array
	 * of shadow node , int k : element
	 * 
	 * @return set of element
	 */
	public static ArrayList<Integer> childrenGloud(ArrayList<Integer> S, ArrayList<Integer> H, int k) {
       ArrayList<Integer>children = new  ArrayList<Integer>();
		int x  = Select.select0(S, k) + 1 ;
       while (S.get(x) != 0) {
		if (S.get(x) == 1) {
			children.add(Rank.rank1(S, x));
		}else {
			children.add(H.get(Rank.rank2(S, x) - 1));
		}
		x++;
	}
       return children;
	}
}
