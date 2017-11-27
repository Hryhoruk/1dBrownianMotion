package parprog.homeproj.main;

import java.util.Arrays;

import parprog.homeproj.BMLogger.BMLogger;

public class Crystal {
	private final int n;
	private int[] state;

	public Crystal(int n) {
		this.n = n;
		state = new int[n];
		for (int i=0; i<n; i++)
			state[i] = 0;
	}

	public int getNumberOfNodes() {
		return n;
	}

	public synchronized void collectData(int pos) {
		state[pos]++;
	}

	public void showSum() {
		int sum = 0;
		for (int i=0; i<n; i++)
			sum = sum + state[i];

		if(BMLogger.debug != null) {
			BMLogger.debug.println("Check sum is " + sum);
			BMLogger.debug.println(toString());
		}
	}

	@Override
	public String toString() {
		return "Crystal has " + n + " nodes.\nFinal state is " + Arrays.toString(state);
	}

}
