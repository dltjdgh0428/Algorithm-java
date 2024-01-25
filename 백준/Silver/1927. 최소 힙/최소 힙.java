
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		Scanner input = new Scanner(System.in);

		int re = input.nextInt();
		for (int i = 0; i < re; i++) {
			int po = input.nextInt();
			if (po == 0) {
				if (pq.isEmpty()) {
					System.out.println(0);
				} else {
					System.out.println(pq.poll());
				}
			} else {
				pq.add(po);
			}
		}

	}

}
