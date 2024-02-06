import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int LINE;
    private static int N;
    private static boolean[] VISITED;
    private static boolean AC = false;
    private static List<List<Integer>> MAP;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] set = bf.readLine().split(" ");

        N = Integer.parseInt(set[0]);
        LINE = Integer.parseInt(set[1]);
        MAP = new ArrayList<>();
        VISITED = new boolean[N];
        for (int i = 0; i < N; i++) {
            MAP.add(new ArrayList<>());
        }

        for (int i = 0; i < LINE; i++) {
            String[] input = bf.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            List<Integer> now = MAP.get(n);
            List<Integer> now_2 = MAP.get(m);
            now.add(m);
            now_2.add(n);
            MAP.set(n, now);
            MAP.set(m, now_2);
        }

        for (int i = 0; i < N; i++) {
            VISITED[i] = true;
            backtracking(i, 1);
            VISITED[i] = false;
        }

        if (AC) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    /**
     * 아 이거 느낌 왔다 모든 경로를 일단 저장해
     * 그다음에 시작점을 N개 만큼 잡아서 N개로 시작했을때 한번씩만 방문하고 모든 탐색이 가능한지
     * 백트래킹을 통해서 문제를 솔브하면 AC받을수 있을듯
     */

    private static void backtracking(int now, int depth) {
        if (AC) return;
        if (depth == 5) {
            AC = true;
            return;
        }

        for (int i : MAP.get(now))
            if (!VISITED[i]) {
//                System.out.println(now+":"+i+"이동했음 현재 depth:"+depth);
                VISITED[i] = true;
                backtracking(i, depth + 1);
                VISITED[i] = false;
            }
    }

}

