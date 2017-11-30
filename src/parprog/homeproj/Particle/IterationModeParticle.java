package parprog.homeproj.Particle;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import parprog.homeproj.main.ArgParser;
import parprog.homeproj.main.Crystal;

public class IterationModeParticle extends AParticle {
	private final Float p;
	private final int iter_num;

	public IterationModeParticle(ArgParser opts, Crystal pool, CyclicBarrier barrier) {
		super(pool, barrier);
		p = opts.getProbability();
		iter_num = opts.getIterationNum();
	}

	@Override
	public void run() {
		long seed = Thread.currentThread().getId();
		Random rnd = new Random(System.currentTimeMillis() + seed);
		for (int i = 0; i < iter_num; i++) {
			Float step = rnd.nextFloat();
			if (step < p)
				 moveRight();
			else moveLeft();
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

}
