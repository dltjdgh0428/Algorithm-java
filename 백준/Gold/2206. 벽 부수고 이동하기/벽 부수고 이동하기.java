import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static boolean[][][] visited;
    private static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Point {
        int x, y, distance, wall;

        Point(int x, int y, int distance, int wall) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.wall = wall;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        visited = new boolean[N][M][2]; // 마지막 차원은 벽을 부수고 방문했는지의 여부

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 1, 0)); // 시작 위치, 거리 1, 벽을 부수지 않은 상태
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.x == N - 1 && current.y == M - 1) {
                return current.distance;
            }

            for (int[] direction : directions) {
                int nextX = current.x + direction[0];
                int nextY = current.y + direction[1];

                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) {
                    if (map[nextX][nextY] == 0 && !visited[nextX][nextY][current.wall]) {
                        visited[nextX][nextY][current.wall] = true;
                        queue.add(new Point(nextX, nextY, current.distance + 1, current.wall));
                    } else if (map[nextX][nextY] == 1 && current.wall == 0) { // 벽을 부수고 지나갈 경우
                        visited[nextX][nextY][1] = true; // 벽을 부수고 방문했다고 표시
                        queue.add(new Point(nextX, nextY, current.distance + 1, 1));
                    }
                }
            }
        }
        return -1; // 도착할 수 없는 경우
    }
}
