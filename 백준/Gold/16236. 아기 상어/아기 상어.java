import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int N;
    private static int[][] board;
    private static boolean[][] visited;
    private static int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    private static int move = 0;
    private static Shark shark;
    private static Point sharkPos;

    static class Shark {
        int size = 2;
        int eat = 0;

        public void eat() {
            eat++;
            if (eat == size) {
                size++;
                eat = 0;
            }
        }
    }

    static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = bf.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(input[j]);
                if (board[i][j] == 9) {
                    sharkPos = new Point(i, j, 0);
                    board[i][j] = 0; // 상어의 시작 위치를 빈 칸으로 설정
                }
            }
        }

        shark = new Shark();
        while (true) {
            visited = new boolean[N][N];
            Point nextFish = findFish(sharkPos);
            if (nextFish == null) break; 

            // 먹이를 먹으러 이동
            move += nextFish.dist;
            shark.eat();
            board[nextFish.x][nextFish.y] = 0; 
            sharkPos = new Point(nextFish.x, nextFish.y, 0);
        }

        System.out.println(move);
    }

    private static Point findFish(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;
        Point target = null;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // 먹을 수 있는 물고기를 찾았을 때
            if (board[current.x][current.y] != 0 && board[current.x][current.y] < shark.size) {
                if (target == null || current.dist < target.dist ||
                        (current.dist == target.dist && (current.x < target.x ||
                                (current.x == target.x && current.y < target.y)))) {
                    target = current;
                }
            }

            for (int[] dir : dirs) {
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && board[nx][ny] <= shark.size) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, current.dist + 1));
                }
            }
        }
        return target;
    }
}
