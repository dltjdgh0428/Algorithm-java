import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    private static int[][] BOARD;
    private static int N ;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i<N; i++){
            String[] input = bf.readLine().split(" ");
            for(int j =0; j<N ; j++){
                pq.add(Integer.parseInt(input[j]));
            }
        }

        for(int i=0;i<N-1;i++){
            pq.remove();
        }
        System.out.println(pq.peek());

    }
}
