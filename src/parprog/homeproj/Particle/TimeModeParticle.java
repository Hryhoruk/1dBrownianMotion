package parprog.homeproj.Particle;

import java.util.Random;

import parprog.homeproj.main.ArgParser;
import parprog.homeproj.main.Crystal;

public class TimeModeParticle extends AParticle {
	private final Float p;
	private final Float iter_time;
	private final Float time_of_exe;

	public TimeModeParticle(ArgParser opts, Crystal pool) {
		super(pool);
		p = opts.getProbability();
		iter_time = opts.getIterationTime();
		time_of_exe = opts.getTimeOfExecusion();
	}

	@Override
	public void run() {
		Integer iteration_num = (int) Math.floor(time_of_exe / iter_time);
		long seed = Thread.currentThread().getId();
		Random rnd = new Random(System.currentTimeMillis() + seed);
		for (int i = 0; i < iteration_num; i++) {
			Float step = rnd.nextFloat();
			if (step < p)
				moveRight();
			else moveLeft();
		}
		pool.collectData(this.getPosition());
	}

}
