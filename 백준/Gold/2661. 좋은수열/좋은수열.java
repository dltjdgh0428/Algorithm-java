import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static boolean FINISHED = false;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        backtracking(1, "1");
    }

    private static void backtracking(int depth, String num) {
        if (FINISHED) return;
        if (N == depth) {
            System.out.println(num);
            FINISHED = true;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (isValid(num + i)) {
                backtracking(depth + 1, num + i);
            }
        }
    }

    private static boolean isValid(String num) {
        int l = num.length();
        for (int i = 1; i <= l / 2; i++) {
            if (num.substring(l - i).equals(num.substring(l - 2 * i, l - i))) {
                return false;
            }
        }
        return true;
    }
}
