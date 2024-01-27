import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static int inputBoard[][];
    public static boolean visited[][];
    public static int boardX;
    public static int boardY;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bf.readLine().split(" ");
        boardX = Integer.parseInt(input[0]);
        boardY = Integer.parseInt(input[1]);
        int boxNumber = Integer.parseInt(input[2]);
        inputBoard = new int[boardX][boardY];
        visited = new boolean[boardX][boardY];
        int[][] boxPosition = new int[boxNumber][4];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < boxNumber; i++) {
            String[] inputPosition = bf.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                boxPosition[i][j] = Integer.parseInt(inputPosition[j]);
            }
        }
        //System.out.println(Arrays.deepToString(boxPosition));

        for (int repeat = 0; repeat < boxNumber; repeat++) {
            for (int x = boxPosition[repeat][1]; x < boxPosition[repeat][3]; x++) {
                //첫번째 4각형의 x값만큼 움직임
                for (int y = boxPosition[repeat][0]; y < boxPosition[repeat][2]; y++) {
                    inputBoard[x][y] = 1;
                }
            }
        }
        for (int i = 0; i < boardX; i++) {
            for (int j = 0; j < boardY; j++) {
                if (inputBoard[i][j] == 0 && visited[i][j] == false) {
                    list.add(dfs(i, j));
                }
            }
        }
        Collections.sort(list);
        System.out.println(list.size());
        for (int item : list) {
            System.out.print(item + " ");
        }
    }

    public static int dfs(int x, int y) {
        if (!canMove(x, y) || visited[x][y] || inputBoard[x][y] == 1) {
            return 0;
        }
        visited[x][y] = true;

        int size = 1;
        size += dfs(x - 1, y);
        size += dfs(x + 1, y);
        size += dfs(x, y - 1);
        size += dfs(x, y + 1);

        return size;
    }

    public static boolean canMove(int x, int y) {
        return boardX > x && boardY > y && x >= 0 && y >= 0;
    }
}
