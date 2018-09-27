package WaitTimeSim;
import java.util.List;

import WaitTimeSim.Customer;

public class WaitTime {

	public static void main(String[] args) {
		List<Customer> c = Customer.simulation();
		Customer.simOver(c);
		System.out.printf("The average wait time: %3d\n", Customer.waitAv(c));
		System.out.printf("The average turnaround time: %3d\n", Customer.turnAv(c));
		
	}

}
