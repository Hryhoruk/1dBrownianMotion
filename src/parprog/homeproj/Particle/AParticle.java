package parprog.homeproj.Particle;

import parprog.homeproj.main.Crystal;

public abstract class AParticle implements Runnable{
	private int position = 0;
	Crystal pool;
	private final int n;

	public AParticle(Crystal pool){
		this.pool = pool;
		n = pool.getNumberOfNodes();
	}

	protected void moveRight(){
		if(position == n - 1)
			position--;
		else position++;
	}

	protected void moveLeft(){
		if(position == 0)
			position++;
		else position--;
	}

	protected int getPosition() {
		return position;
	}
}
