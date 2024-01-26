import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		PriorityQueue<Integer> mpq = new PriorityQueue<Integer>();
		PriorityQueue<Integer> Mpq = new PriorityQueue<Integer>(Collections.reverseOrder());

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int re = Integer.parseInt(br.readLine());
		for (int i = 0; i < re; i++) {
			int po = Integer.parseInt(br.readLine());
			if (Mpq.size() == mpq.size()) {// 맥스힙과 역힙
				Mpq.add(po);
			} else {
				mpq.add(po);
			}
			if (!mpq.isEmpty() && !Mpq.isEmpty())
				if (mpq.peek() < Mpq.peek()) {
					int tmp = mpq.poll();
					mpq.offer(Mpq.poll());
					Mpq.offer(tmp);
				}

            sb.append(Mpq.peek() + "\n");
		}
		System.out.println(sb);
		
	}

}