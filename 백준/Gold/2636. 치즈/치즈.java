import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(line[j]);
            }
        }

        int time = 0;
        int lastCheese = 0;
        while (true) {
            visited = new boolean[N][M];
            int cheeseCount = meltCheese();
            if (cheeseCount == 0) {
                break;
            }
            lastCheese = cheeseCount;
            time++;
        }

        System.out.println(time);
        System.out.println(lastCheese);
    }

    static int meltCheese() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                    if (board[nx][ny] == 0) {
                        queue.add(new int[]{nx, ny});
                    } else {
                        count++;
                    }
                    visited[nx][ny] = true;
                    board[nx][ny] = 0;
                }
            }
        }

        return count;
    }
}
