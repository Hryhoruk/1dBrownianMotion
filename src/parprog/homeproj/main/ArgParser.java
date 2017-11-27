package parprog.homeproj.main;

import parprog.homeproj.BMLogger.BMLogger;

/**
 * Argument parser.
 * @author Vasyl Hryhoruk
 * @date 4.11.2017
 */
public class ArgParser {
	private String mode;
	private float it = 0;
	private float time = 0;
	private int iteration_num = -1;
	private Integer n;
	private Integer k;
	private Float p;

		public ArgParser(String[] args) {
			if(args.length == 0)
				showHelpMessage();

			int max_i = args.length;
			int i=0;
			try{
				while(i < max_i) {
					String arg = args[i];
					if(arg.equals("--help") || arg.equals("-h")) {
						showHelpMessage();
					}
					else if(arg.equals("-dbstd")) {
						BMLogger.debug = System.out;
					}
					else if(arg.startsWith("-it") || arg.startsWith("--iterationtime")) {
						assert((mode==null || mode.equals("time")) && it==0);
						mode = "time";
						it = Float.parseFloat(arg.substring(arg.indexOf("=")+1));
						if(it<=0){
							BMLogger.out.println("Iteration time should be bigger than 0.");
							System.exit(0);
						}

					}
					else if(arg.startsWith("-t") || arg.startsWith("--time")) {
						assert((mode==null || mode.equals("time")) && time==0);
						mode = "time";
						time = Float.parseFloat(arg.substring(arg.indexOf("=")+1));
						if(time<=0){
							BMLogger.out.println("Execution time should be bigger than 0.");
							System.exit(0);
						}

					}
					else if(arg.startsWith("-in") || arg.startsWith("--iterationnumber")) {
						assert(mode==null && time==0);
						mode = "iteration";
						iteration_num = Integer.parseInt(arg.substring(arg.indexOf("=")+1));
						if(iteration_num<0){
							BMLogger.out.println("Iteration number should be not less than 0.");
							System.exit(0);
						}

					}
					else if(arg.startsWith("-")) {
						BMLogger.out.println("Unhandled option. See help message.");
						System.exit(0);
					}
					else {
						//handle mandatory options
						if(n==null && k==null && p==null &&
								i+2<max_i){
							n = Integer.parseInt(arg);
							arg = args[++i];
							if(n<=0){
								BMLogger.out.println("Number of nodes should be bigger than 0.");
								System.exit(0);
							}
							k = Integer.parseInt(arg);
							if(k<=0){
								BMLogger.out.println("Number of particles should be bigger than 0.");
								System.exit(0);
							}
							arg = args[++i];
							p = Float.parseFloat(arg);
							if(p>1 || p<0){
								BMLogger.out.println("Probability should be from 0 to 1.");
								System.exit(0);
							}
						} else {
							BMLogger.out.println("Unhandled option. See help message.");
							System.exit(0);
						}
					}
					i++;
				}
			}catch(Exception e) {
				BMLogger.out.println("Error occure while parsing arguments. Make sure " +
						"options have form -[option]=<value>||--[option]=<value>.");
				System.exit(0);
			}
			if(n==null || k==null || p==null || mode==null){
				BMLogger.out.println("Mandatory option should be defined. See help message.");
				System.exit(0);
			}
		}

		@Override
		public String toString() {
			return "ArgParser [mode=" + mode + ", it=" + it + ", time=" + time
					+ ", iteration_num=" + iteration_num + ", n=" + n + ", k="
					+ k + ", p=" + p + "]";
		}

		private void showHelpMessage() {
			BMLogger.out.println("1-D brownian motion simulation tool.");
			BMLogger.out.println("Usage: brmotion [-options] n k p [-mode]");

			BMLogger.out.println("Mandatory options:");
			BMLogger.out.println("  n                  number of nodes in crystal");
			BMLogger.out.println("  k                  number of particles");
			BMLogger.out.println("  p                  probability of a particle");
			BMLogger.out.println("                     to jump to the right\n");

			BMLogger.out.println("Mode options:");
			BMLogger.out.println("  Defines tool mode. By default iteration mode enabled.");
			BMLogger.out.println("  For time mode:");
			BMLogger.out.println("    If set, defines time mode.");
			BMLogger.out.println("      -it, --iterationtime=<value>   time of one iteration");
			BMLogger.out.println("      -t, --time=<value>             execution time of the");
			BMLogger.out.println("                                     program");
			BMLogger.out.println("  For iteration mode:");
			BMLogger.out.println("    If set, defines iteration mode.");
			BMLogger.out.println("      -in, --iterationnumber=<value> number of iterations.\n");

			BMLogger.out.println("Additional options include:");
			BMLogger.out.println("  -h, --help         show this message.");
			BMLogger.out.println("  -dbstd             write debug info on a screen.");
			BMLogger.out.println("                     Debug info is disabled by default.");
			System.exit(0);
		}

		public Float getProbability() {
			return p;
		}

		public Float getIterationTime() {
			return it;
		}

		public Float getTimeOfExecusion() {
			return time;
		}

		public Integer getNumberOfNodes() {
			return n;
		}

		public int getIterationNum() {
			return iteration_num;
		}

		public String getMode() {
			return mode;
		}

		public int getNumOfParticles() {
			return k;
		}
}
