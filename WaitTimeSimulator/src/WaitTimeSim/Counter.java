package WaitTimeSim;

import WaitTimeSim.Customer;

public class Counter {
	
	
	public int counterID;
	public int progress = 0;
	public Customer customer = null;
	
	static public int counterCnt = 0;
	
	public Counter() {
		counterID = ++counterCnt;
	}

	public boolean isBusy() {
		return customer != null && progress < customer.serviceDuration;
	}
	
	public void update(int tick) {
		customer.completeTime = tick;
		customer.waitTime = customer.startTime - customer.arrivalTime;
		customer.turnAroundTime = customer.waitTime + customer.serviceDuration;
	}
	
	public void add(Customer c, int tick) {
		customer = c;
		c.startTime = tick;
	}
	
	public void printArrival(int c) {
		System.out.printf("Customer #%d is at counter %d at %d. (service duration is: %d)\n",
				customer.customerID, c, customer.startTime, customer.serviceDuration);
	}
	
	public void printLeave() {
		System.out.printf("Customer #%d has left at %d. (Wait time = %d, Turn around = %d)\n",
				customer.customerID, customer.completeTime, customer.waitTime,
				customer.turnAroundTime);
	}
	
	

}
