package parprog.homeproj.main;

import java.util.ArrayList;
import java.util.List;

import parprog.homeproj.BMLogger.*;
import parprog.homeproj.Particle.AParticle;
import parprog.homeproj.Particle.IterationModeParticle;
import parprog.homeproj.Particle.TimeModeParticle;

public class MotionMain {
	public static void main(String[] args) {
		ArgParser opts = new ArgParser(args);

		Crystal pool = new Crystal(opts.getNumberOfNodes());
		int numOfParticles = opts.getNumOfParticles();
		List<Thread> particles = generateModeDependentParticles(opts, numOfParticles, pool);

		for(Thread particle: particles)
			particle.start();

		try{
			for(Thread particle: particles)
				particle.join();
		} catch(InterruptedException e){
			BMLogger.out.println("Ooops... Problems");
			BMLogger.err.println(e);
		}

		pool.showSum();
	}

	private static List<Thread> generateModeDependentParticles(ArgParser opts, int k, Crystal pool) {
		List<Thread> result = new ArrayList<Thread>();
		for(int i=0; i<k; i++) {
			AParticle particle = generateSingleParticle(opts, pool);
			result.add(new Thread(particle));
		}
		return result;
	}

	private static AParticle generateSingleParticle(ArgParser opts, Crystal pool) {
		if(opts.getMode().equals("iteration"))
			return new IterationModeParticle(opts, pool);
		else return new TimeModeParticle(opts, pool);
	}
}
