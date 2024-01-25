import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static class Study {
        int start, end;

        public Study(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        Comparator<Study> myStudy = new Comparator<Study>() {
            @Override
            public int compare(Study o1, Study o2) {
                if (o1.start != o2.start) {
                    return o1.start - o2.start;
                } else {
                    return o1.end - o2.end;
                }
            }
        };

        PriorityQueue<Study> pq = new PriorityQueue<>(myStudy);
        PriorityQueue<Integer> room = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String[] input = bf.readLine().split(" ");
            pq.add(new Study(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        while (!pq.isEmpty()){
            Study study = pq.remove();

            if(room.isEmpty()){
                room.add(study.end);
                continue;
            }

            if(room.peek() <= study.start){
                room.remove();
                room.add(study.end);
            }else{
                room.add(study.end);
            }

        }
        System.out.println(room.size());
    }

}
