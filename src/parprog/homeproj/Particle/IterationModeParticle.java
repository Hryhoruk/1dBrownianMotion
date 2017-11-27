package parprog.homeproj.Particle;

import java.util.Random;

import parprog.homeproj.main.ArgParser;
import parprog.homeproj.main.Crystal;

public class IterationModeParticle extends AParticle {
	private final Float p;
	private final int iter_num;

	public IterationModeParticle(ArgParser opts, Crystal pool) {
		super(pool);
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
		}
		pool.collectData(this.getPosition());
	}

}
