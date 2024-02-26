import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] BOTTLES;
    private static final int[] SEND = {0, 0, 1, 1, 2, 2};
    private static final int[] RECEIVE = {1, 2, 0, 2, 0, 1};

    private static boolean[][][] VISITED;

    static class Bottle {
        int a, b, c;

        public Bottle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bf.readLine().split(" ");
        BOTTLES = new int[3];
        VISITED = new boolean[201][201][201];
        for (int i = 0; i < 3; i++) {
            BOTTLES[i] = Integer.parseInt(input[i]);
        }
        bfs();
    }

    private static void bfs() {
        Queue<Bottle> queue = new LinkedList<>();
        queue.add(new Bottle(0, 0, BOTTLES[2]));
        TreeSet<Integer> set = new TreeSet<>(); // TreeSet을 사용해 결과를 자동으로 정렬
        set.add(BOTTLES[2]);

        while (!queue.isEmpty()) {
            Bottle bottle = queue.poll();
            int a = bottle.a;
            int b = bottle.b;
            int c = bottle.c;

            if (VISITED[a][b][c]) continue; // 이미 방문한 상태라면 건너뜀
            VISITED[a][b][c] = true;

            if (a == 0) {
                set.add(c);
            }

            for (int i = 0; i < 6; i++) {
                int[] next = {a, b, c}; // 현재 상태를 복사
                int send = SEND[i], receive = RECEIVE[i];

                int transfer = Math.min(next[send], BOTTLES[receive] - next[receive]);
                next[send] -= transfer;
                next[receive] += transfer;

                if (!VISITED[next[0]][next[1]][next[2]]) {
                    queue.add(new Bottle(next[0], next[1], next[2]));
                }
            }
        }

        for (int amount : set) {
            System.out.print(amount + " ");
        }
    }
}
