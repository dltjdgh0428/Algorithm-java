
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		PriorityQueue<Integer> Rpq = new PriorityQueue<Integer>(Collections.reverseOrder());
		Scanner input = new Scanner(System.in);

		int re = input.nextInt();
		for (int i = 0; i < re; i++) {
			int po = input.nextInt();
			if (po == 0) {
				if (pq.isEmpty() && Rpq.isEmpty()) {
					System.out.println(0);
				} else {
					if (!pq.isEmpty() && !Rpq.isEmpty()) {
						if (Math.abs(pq.peek()) < Math.abs(Rpq.peek()))
							System.out.println(pq.poll());
						else
							System.out.println(Rpq.poll());
					}
					else {
						if(pq.isEmpty())
							System.out.println(Rpq.poll());
						else
							System.out.println(pq.poll());
					}
				}
			} else if (po > 0) {
				pq.add(po);
			} else if (po < 0) {
				Rpq.add(po);
			}
		}

	}

}