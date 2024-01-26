import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static class Number{
        int num,inputCount;
        public Number(int num,int inputCount){
            this.num = num;
            this.inputCount = inputCount;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Comparator<Number> my = new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                return o2.num-o1.num;
            }
        };
        PriorityQueue<Number> pq = new PriorityQueue<>(my);

        for (int i = 0; i < 9; i++) {
            int input = Integer.parseInt(bf.readLine());
            pq.add(new Number(input,i+1));
        }
        System.out.println(pq.peek().num);
        System.out.println(pq.peek().inputCount);
    }
}
