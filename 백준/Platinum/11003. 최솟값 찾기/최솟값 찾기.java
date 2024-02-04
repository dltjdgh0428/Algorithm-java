import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        Deque<int[]> dq = new ArrayDeque<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] NL = bf.readLine().split(" ");
        int n = Integer.parseInt(NL[0]);
        int l = Integer.parseInt(NL[1]);
        String[] input = bf.readLine().split(" ");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(input[i]);
            while (!dq.isEmpty() && dq.peekLast()[0] > num) {
                dq.pollLast();
            }
            dq.add(new int[]{num, i});
            if (dq.peekFirst()[1] < i - l + 1) {
                dq.pollFirst();
            }
            sb.append(dq.peekFirst()[0]).append(" ");
        }

        System.out.println(sb.toString());
    }
}
