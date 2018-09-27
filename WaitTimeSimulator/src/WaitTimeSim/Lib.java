package WaitTimeSim;

public class Lib {
	
	public static boolean arrivalProb(double chance) {
		if (chance <= 0) {
			return false;
		}
		if (chance >= 1) {
			return true;
		}
		return Math.random() <= chance;
	}

	static public int rand(int min, int max) {
		return min + (int) ((max - min + 1) * Math.random());
	}
	
	public void printAry(int[] ary) {
		for (int i = 0; i < ary.length; i++) {
			System.out.print(ary[i] + " ");
		}
	}
	
	public static boolean differentTitles(String[] ary) {
		for (int i = 0; i < ary.length - 1; i++) {
			for (int j = i + 1; j < ary.length; j++) {
				if (ary[i] == ary[j]) {
					return false;
				}
			}
		}
		return true;
	}

}
