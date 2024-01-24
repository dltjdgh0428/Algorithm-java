import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    private static int N;
    private static int CHARGE_COUNT;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputSet = bf.readLine().split(" ");
        N = Integer.parseInt(inputSet[0]);
        CHARGE_COUNT = Integer.parseInt(inputSet[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        String[] input = bf.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(input[i]));
        }
        int[] times = new int[CHARGE_COUNT];

        while (!pq.isEmpty()) {
            int time = pq.remove();
            int lowCount = 0;
            for(int i =0;i<CHARGE_COUNT;i++){
                if(times[i]<times[lowCount]){
                    lowCount=i;
                }
            }
            times[lowCount] += time;
        }
        int answer = 0;
        for(int ans : times){
            answer = Math.max(ans,answer);
        }
        System.out.println(answer);
    }
}
