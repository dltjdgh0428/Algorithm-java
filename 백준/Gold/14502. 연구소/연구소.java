import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int N;
    private static int M;
    private static int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int MAX = Integer.MIN_VALUE;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputNM = bf.readLine().split(" ");
        N = Integer.parseInt(inputNM[0]);
        M = Integer.parseInt(inputNM[1]);
        int[][] board = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String[] input = bf.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
        dfs(board, 0);
        System.out.println(MAX);
    }

    private static void dfs(int[][] board, int count) {
        if (count == 3) {
            count(board);
            return;
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    dfs(board, count + 1);
                    board[i][j] = 0;
                }
            }
        }
    }
    private static void count(int[][] board) {
        int count_ZERO = 0;
        int[][] tempBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            tempBoard[i] = board[i].clone();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempBoard[i][j] == 2) {
                    bfs(tempBoard, i, j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempBoard[i][j] == 0) {
                    count_ZERO++;
                }
            }
        }

        MAX = Math.max(MAX, count_ZERO);
    }

    private static void bfs(int[][] tempBoard, int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point point = queue.remove();
            int point_x = point.x;
            int point_y = point.y;

            for (int i = 0; i < DIR.length; i++) {
                int move_x = point_x + DIR[i][0];
                int move_y = point_y + DIR[i][1];
                if (canMove(move_x, move_y) && tempBoard[move_x][move_y] == 0) {
                    queue.add(new Point(move_x, move_y));
                    tempBoard[move_x][move_y] = 2;
                }
            }
        }
    }



    private static boolean canMove(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
