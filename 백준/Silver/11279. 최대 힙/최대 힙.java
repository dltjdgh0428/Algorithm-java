import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i =0;i<n;i++){
            int input = Integer.parseInt(bf.readLine());
            if(input==0){
                if (pq.size()>0){
                    System.out.println(pq.remove());
                }
                else{
                    System.out.println(0);
                }
            }
            else{
                pq.add(input);
            }
        }
    }
}
