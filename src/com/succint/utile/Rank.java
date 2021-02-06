package com.succint.utile;

import java.util.ArrayList;

/*
 * 
 * rank b (S, k) function counts the number of elements b (most often bits) in the prefix of
 * an array S up to some index k. In other words, this function tells us the number of "b"
 * we have from the first position to k th position. Mathematically, this function is expressed
 * as follow:
 *         rank b (S, k) = |{i, i ∈ [1, k] and S[i] = b}|.
 *         */
public class Rank {

	/*
	 * 
	 * return number of 0 in K eme bit
	 */
	public static int rank0(ArrayList<Integer> S, int k) {
		int rank = 0;

		if (k < 0 || k >= S.size()) {
			System.out.println("invalid index position " + k);
		}

		for (int i = 0; i < k; i++) {
			if (S.get(i) == 0) {
				rank = rank + 1;
			}
		}
		return rank;
	}

	/*
	 * 
	 * return number of 1 in k eme bit
	 */
	public static int rank1(ArrayList<Integer> S, int k) {
		int rank = 0;

		if (k < 0 || k >= S.size()) {
			System.out.println("invalid index position " + k);
		}

		for (int i = 0; i < k; i++) {
			if (S.get(i) == 1) {
				rank++;
			}
		}
		return rank;
	}

	/*
	 * @parameter ArrayList<Interger> S is the Ternary vector come from Graph Gloud
	 * 
	 * @paramter int k index of an element
	 * 
	 * @return number of 2 in k eme bit
	 */
	public static int rank2(ArrayList<Integer> S, int k) {
		int rank = 0;
		if (k < 0 || k >= S.size()) {
			System.out.println("invalid index position ");
		}
		for (int i = 0; i < S.size(); i++) {
			if (S.get(i) == 2)
				rank++;
		}
		return rank;
	}
}
