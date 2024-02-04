import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static class Point{
        int x,y;
        public Point(int x,int y ){
            this.x = x;
            this.y = y;
        }
    }
    private static int N;
    private static int STORE;
    private static List<Point> houses = new ArrayList<>();
    private static List<Point> chickens = new ArrayList<>();
    private static List<Point> selected = new ArrayList<>();
    private static int MIN_DISTANCES = Integer.MAX_VALUE;



    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] setting = bf.readLine().split(" ");
        N = Integer.parseInt(setting[0]);
        STORE = Integer.parseInt(setting[1]);

        for (int i = 0; i < N; i++) {
            String[] input = bf.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int point = Integer.parseInt(input[j]);
                if(point == 1){
                    houses.add(new Point(i,j));
                }else if(point == 2){
                    chickens.add((new Point(i,j)));
                }
            }
        }
        backtracking(0,0);
        System.out.println(MIN_DISTANCES);
    }
    private static void backtracking(int start , int count){
        if(count == STORE){
            int min = calculateDistanceSum();
            MIN_DISTANCES = Math.min(min,MIN_DISTANCES);
            return;
        }

        for(int i =start;i<chickens.size();i++){
            selected.add(chickens.get(i));
            backtracking(i+1,count+1);
            selected.remove(selected.size()-1);
        }
    }

    private static int calculateDistanceSum() {
        int sum = 0;
        for (Point house : houses) {
            int minDistance = Integer.MAX_VALUE;
            for (Point chicken : selected) {
                int distance = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
                minDistance = Math.min(minDistance, distance);
            }
            sum += minDistance;
        }
        return sum;
    }
}
