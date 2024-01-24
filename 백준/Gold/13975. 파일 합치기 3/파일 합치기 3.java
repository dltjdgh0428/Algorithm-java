import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    private static int TEST_CASE;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        TEST_CASE = Integer.parseInt(bf.readLine());
        while (TEST_CASE > 0) {
            Long sum = 0L;
            N = Integer.parseInt(bf.readLine());
            String[] input = bf.readLine().split(" ");
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (String in : input) {
                pq.add(Long.parseLong(in));
            }

            while (pq.size() > 1) {
                Long a = pq.poll();
                Long b = pq.poll();

                sum += a + b;
                pq.add(a + b);
            }
            System.out.println(sum);
            TEST_CASE--;
        }
    }
}
