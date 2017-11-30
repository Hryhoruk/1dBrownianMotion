package parprog.homeproj.Particle;

import java.util.concurrent.CyclicBarrier;

import parprog.homeproj.main.Crystal;

public abstract class AParticle implements Runnable{
	private int position = 0;
	Crystal pool;
	private final int n;
	protected final CyclicBarrier barrier;

	public AParticle(Crystal pool, CyclicBarrier barrier){
		this.pool = pool;
		this.barrier = barrier;
		n = pool.getNumberOfNodes();
	}

	protected void moveRight(){
		pool.popParticle(position);
		if(position == n - 1)
			position--;
		else position++;
		pool.pushParticle(position);
	}

	protected void moveLeft(){
		pool.popParticle(position);
		if(position == 0)
			position++;
		else position--;
		pool.pushParticle(position);
	}

	protected int getPosition() {
		return position;
	}
}
