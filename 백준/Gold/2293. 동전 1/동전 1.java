import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static int K;
    private static int[] VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputNK = bf.readLine().split(" ");
        N = Integer.parseInt(inputNK[0]);
        K = Integer.parseInt(inputNK[1]);
        VALUE = new int[N];
        for (int i = 0; i < N; i++) {
            VALUE[i] = Integer.parseInt(bf.readLine());
        }

        int[] dp = new int[K + 1];
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = VALUE[i]; j <= K; j++) {
                dp[j] += dp[j - VALUE[i]];
            }
        }
        System.out.println(dp[K]);
    }
}
