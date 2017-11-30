package parprog.homeproj.main;

import java.util.Arrays;

import parprog.homeproj.BMLogger.BMLogger;

public class Crystal {
	private final int n;
	private int[] state;

	public Crystal(int n, int k) {
		this.n = n;
		state = new int[n];
		state[0] = k;
		for (int i=1; i<n; i++)
			state[i] = 0;
	}

	public int getNumberOfNodes() {
		return n;
	}

	public synchronized void pushParticle(int pos) {
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

		BMLogger.out.println(toString());
	}

	@Override
	public String toString() {
		return "Crystal has " + n + " nodes.\nFinal state is " + Arrays.toString(state);
	}

	public synchronized void popParticle(int pos) {
		state[pos]--;
	}

}
