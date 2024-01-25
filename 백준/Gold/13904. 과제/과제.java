import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {

    private static int N;

    static class Work {
        int deadline;
        int point;

        public Work(int deadline, int point) {
            this.deadline = deadline;
            this.point = point;
        }
    }

    /**
     * 아 이거 우선순위 큐를 2개 만들어야 할거같음
     * 1. 해시 맵에 넣음 근데 key값이 있다? 그러면 점수 역정렬 PQ로 이동
     * 2. 해시 맵에 있는것과
     * 하나는 일의 시간 순서대로 그중 가장 높은거 Set으로 해서 교체
     */


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        int sum = 0;

        Comparator<Work> myComparator = new Comparator<Work>() {
            @Override
            public int compare(Work o1, Work o2) {
                if (o1.point != o2.point) {
                    return o2.point - o1.point;
                } else {
                    return o1.deadline - o2.deadline;
                }
            }
        };
        Comparator<Work> myComparator2 = new Comparator<Work>() {
            @Override
            public int compare(Work o1, Work o2) {
                if (o1.deadline != o2.deadline) {
                    return o1.deadline - o2.deadline;
                } else {
                    return o2.point - o1.point;
                }
            }
        };


        PriorityQueue<Work> pq = new PriorityQueue<>(myComparator);
        PriorityQueue<Work> pq2 = new PriorityQueue<>(myComparator2);
        HashMap<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] input = bf.readLine().split(" ");
            pq2.add(new Work(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }
        while (!pq2.isEmpty()) {
            Work work = pq2.remove();
            int d = work.deadline;
            int p = work.point;
            if (!visited.containsKey(d)) {
                visited.put(d, p);
                sum += p;
            } else {
                pq.add(new Work(d, p));
            }
        }

        while (!pq.isEmpty()) {
            Work work = pq.remove();
            int d = work.deadline;
            int p = work.point;
            for (int i = d; i > 0; i--) {
                if(!visited.containsKey(i) ){
                    visited.put(i,p);
                    sum+= visited.get(i);
                    break;
                }
                else if ( visited.get(i) < p){
                    sum-=visited.get(i);
                    pq.add(new Work(i,visited.get(i)));
                    visited.put(i,p);
                    sum+= visited.get(i);
                    break;
                }
            }

        }
//        for (int day : visited.keySet()) {
//            System.out.println(day + "쨰날 : " + visited.get(day));
//        }

        System.out.println(sum);
    }
}
