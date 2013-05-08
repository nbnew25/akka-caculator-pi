package demo;

import akka.actor.UntypedActor;

public class Worker extends UntypedActor {
	static class Work {
		private final int start;
		private final int nrOfElements;

		public Work(int start, int nrOfElements) {
			this.start = start;
			this.nrOfElements = nrOfElements;
		}

		public int getStart() {
			return start;
		}

		public int getNrOfElements() {
			return nrOfElements;
		}
	}

	static class Result {
		private final double value;

		public Result(double value) {
			this.value = value;
		}

		public double getValue() {
			return value;
		}
	}
	
	private double calculatePiFor(int start, int nrOfElements) {
		double acc = 0.0;
		System.out.println(String.format("worker(%s) run from: %s -> %s",
				getSelf().path().name(),
				start * nrOfElements,
				((start + 1) * nrOfElements - 1)));
		for (int i = start * nrOfElements; i <= ((start + 1) * nrOfElements - 1); i++) {
			acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1);
		}
		return acc;
	}

	public void onReceive(Object message) {
		if (message instanceof Work) {
			Work work = (Work) message;
			double result = calculatePiFor(work.getStart(),
					work.getNrOfElements());
			getSender().tell(new Result(result), getSelf());
		} else {
			unhandled(message);
		}
	}
}