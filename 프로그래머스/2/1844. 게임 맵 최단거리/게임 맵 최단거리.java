import java.util.LinkedList;
import java.util.Queue;
class Solution {
    private static int N;
    private static int M;
    private static int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        int[][] visited = new int[N][M];
        bfs(maps, visited, 0, 0);
        if (visited[N - 1][M - 1] == 0) {
            return -1;
        }
        return visited[N - 1][M- 1];
    }

    private static void bfs(int[][] maps, int[][] visited, int x, int y) {

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                visited[i][j] = 0;
            }
        }
        visited[x][y] = 1;


        while (!queue.isEmpty()) {
            Point point = queue.remove();
            int xx = point.x;
            int yy = point.y;

            for (int i = 0; i < DIR.length; i++) {
                int xxx = xx + DIR[i][0];
                int yyy = yy + DIR[i][1];

                if (canMove(xxx, yyy) && maps[xxx][yyy] == 1 && visited[xxx][yyy] == 0) {
                    queue.add(new Point(xxx, yyy));
                    visited[xxx][yyy] = visited[xx][yy] + 1;
                    if(xxx == N && yyy==N){
                        queue.clear();
                    }
                }
                
            }
        }
    }

    private static boolean canMove(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}