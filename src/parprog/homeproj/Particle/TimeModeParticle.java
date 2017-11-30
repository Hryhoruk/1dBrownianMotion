package parprog.homeproj.Particle;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import parprog.homeproj.main.ArgParser;
import parprog.homeproj.main.Crystal;

public class TimeModeParticle extends AParticle {
	private final Float p;
	private final Float iter_time;
	private final Float time_of_exe;

	public TimeModeParticle(ArgParser opts, Crystal pool, CyclicBarrier barrier) {
		super(pool, barrier);
		p = opts.getProbability();
		iter_time = opts.getIterationTime();
		time_of_exe = opts.getTimeOfExecusion();
	}

	@Override
	public void run() {
		Integer iteration_num = (int) Math.floor(time_of_exe / iter_time);
		long seed = Thread.currentThread().getId();
		Random rnd = new Random(System.currentTimeMillis() + seed);
		long iter_time_int = (long) Math.floor(iter_time*1000);
		long curr = 0;
		for (int i = 0; i < iteration_num; i++) {
			curr = System.currentTimeMillis();
			Float step = rnd.nextFloat();
			if (step < p)
				moveRight();
			else moveLeft();
			try {
				barrier.await();
				Thread.sleep(iter_time_int - (System.currentTimeMillis() - curr));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

}
