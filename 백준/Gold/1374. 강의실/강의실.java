import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static class Class {
        int number;
        int start;
        int end;

        public Class(int number,int start, int end) {
            this.number = number;
            this.start = start;
            this.end = end;
        }
    }
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        Comparator<Class> myComparator = new Comparator<Class>() {
            @Override
            public int compare(Class o1, Class o2) {
                if (o1.start != o2.start) {
                    return o1.start - o2.start;
                } else {
                    return o1.end - o2.end;
                }
            }
        };
        PriorityQueue<Class> pq = new PriorityQueue<>(myComparator);
        PriorityQueue<Integer> endClass = new PriorityQueue<>();

        for(int i = 0 ; i < N;i++){
            String[] input = bf.readLine().split(" ");
            pq.add(new Class(Integer.parseInt(input[0]),Integer.parseInt(input[1]),Integer.parseInt(input[2])));
        }

        while(!pq.isEmpty()){
            Class c = pq.remove();
            if(endClass.isEmpty()){
                endClass.add(c.end);
                continue;
            }

            if(endClass.peek()<=c.start){
                endClass.remove();
                endClass.add(c.end);
            }else{
                endClass.add(c.end);
            }

        }

        System.out.println(endClass.size());
    }
}
