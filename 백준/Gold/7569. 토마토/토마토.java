import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int N;
    private static int M;
    private static int H;
    private static int[][][] VISITED;
    private static int[][] DIR = {{-1, 0, 0}, {1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, -1}, {0, 0, 1}};

    static class Point {
        int x, y, z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bf.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        H = Integer.parseInt(input[2]);
        VISITED = new int[N][M][H];

        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            for (int yy = 0; yy < M; yy++) {
                String[] line = bf.readLine().split(" ");
                for (int xx = 0; xx < N; xx++) {
                    VISITED[xx][yy][i] = Integer.parseInt(line[xx]);
                    if (VISITED[xx][yy][i] == 1) {
                        queue.add(new Point(xx, yy, i));
                    }
                }
            }
        }
        bfs(queue);
        int minDate = 0;
        for (int i = 0; i < H; i++) {
            for (int yy = 0; yy < M; yy++) {
                for (int xx = 0; xx < N; xx++) {
                    if(VISITED[xx][yy][i] == -1){
                        continue;
                    }else if(VISITED[xx][yy][i] ==0){
                        minDate = -1;
                        System.out.println(minDate);
                        return;
                    }else{
                        minDate = Math.max(VISITED[xx][yy][i]-1,minDate);
                    }
                }
            }
        }
        System.out.println(minDate);
    }

    private static void bfs(Queue<Point> queue) {
        while (!queue.isEmpty()) {
            Point point = queue.remove();
            int x = point.x;
            int y = point.y;
            int z = point.z;
            for (int[] dir : DIR) {
                int move_x = x + dir[0];
                int move_y = y + dir[1];
                int move_z = z + dir[2];
                if (canMove(move_x, move_y, move_z) && VISITED[move_x][move_y][move_z] == 0) {
                    VISITED[move_x][move_y][move_z] = VISITED[x][y][z] + 1;
                    queue.add(new Point(move_x,move_y,move_z));
                }
            }
        }
    }

    private static boolean canMove(int x, int y, int z) {
        return x >= 0 && y >= 0 && z >= 0 && x < N && y < M && z < H;
    }
}
