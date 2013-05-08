package demo;

import scala.concurrent.duration.Duration;
import akka.actor.UntypedActor;

public class Listener extends UntypedActor {
	static class PiApproximation {
		private final double pi;
		private final Duration duration;

		public PiApproximation(double pi, Duration duration) {
			this.pi = pi;
			this.duration = duration;
		}

		public double getPi() {
			return pi;
		}

		public Duration getDuration() {
			return duration;
		}
	}
	
	public void onReceive(Object message) {
		if (message instanceof PiApproximation) {
			PiApproximation approximation = (PiApproximation) message;
			System.out
					.println(String
							.format("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s",
									approximation.getPi(),
									approximation.getDuration()));
			getContext().system().shutdown();
		} else {
			unhandled(message);
		}
	}
}
