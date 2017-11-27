package parprog.homeproj.main;

import parprog.homeproj.BMLogger.*;

public class MotionMain {
	public static void main(String[] args) {
		ArgParser opts = new ArgParser(args);
		BMLogger.out.println(opts.toString());
	}
}
