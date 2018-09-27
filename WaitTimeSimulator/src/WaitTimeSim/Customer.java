package WaitTimeSim;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Customer {

	static final int SD_MIN = 3;
	static final int SD_MAX = 12;
	final static int CUSTOMER_CNT = 50;
	final static int HELPER_CNT = 3;
	final static int POSSIBLE_CUST = 3;
	final static double CHANCE = 0.5;

	static public int customerCnt = 0;

	public int arrivalTime;
	public int customerID;
	public int serviceDuration;
	public int startTime;
	public int waitTime;
	public int completeTime;
	public int turnAroundTime;

	public Customer() {
		customerID = ++customerCnt;
		serviceDuration = Lib.rand(SD_MIN, SD_MAX);
	}

	@Override
	public String toString() {
		return "I am customer #" + waitTime;
	}


	public static List<Customer> simulation() {
		Queue<Customer> line = new LinkedList<>();
		List<Customer> done = new ArrayList<Customer>();
		Counter[] help = new Counter[HELPER_CNT];
		for (int i = 0; i < HELPER_CNT; i++) {
			help[i] = new Counter();
		}
		int tick = 0;
		while (done.size() != CUSTOMER_CNT) {
			System.out.println("At tick #" + tick);
			for (int i = 0; i < POSSIBLE_CUST; i++) {
				if (Lib.arrivalProb(CHANCE) && Customer.customerCnt != CUSTOMER_CNT) {
					Customer newC = new Customer();
					newC.arrivalTime = tick;
					line.add(newC);
					System.out.printf("Customer #%d arrived at %d.\nThey get in the line.\n", newC.customerID,
							newC.arrivalTime);

				}
			}
			for (Counter h : help) {
				if (!h.isBusy()) {
					if (!line.isEmpty()) {
						h.progress = 0;
						line.peek().startTime = tick;
						h.customer = line.poll();
						h.printArrival(h.counterID);
					}
				} else {
					h.progress++;
					if (h.progress == h.customer.serviceDuration) {
						h.update(tick);
						h.printLeave();
						done.add(h.customer);
						h.customer = null;
					}
				}

			}
			tick++;
			System.out.println();
		}
		System.out.println("**** SIMULATION COMPLETE ****");
		return done;
	}

	public static int waitAv(List<Customer> waits) {
		int avg = 0;
		for (int i = 0; i < waits.size(); i++) {
			avg += waits.get(i).waitTime;
		}
		return avg / waits.size();
	}

	public static int turnAv(List<Customer> waits) {
		int avg = 0;
		for (int i = 0; i < waits.size(); i++) {
			avg += waits.get(i).turnAroundTime;
		}
		return avg / waits.size();
	}

	public static void simOver(List<Customer> cust) {

		for (Customer c : cust) {
			System.out.printf("Cus |sD  |star|wait|comp|turn\n" + "%4d|%4d|%4d|%4d|%4d|%4d\n\n", c.customerID,
					c.serviceDuration, c.startTime, c.waitTime, c.completeTime, c.turnAroundTime);
		}
	}

}
