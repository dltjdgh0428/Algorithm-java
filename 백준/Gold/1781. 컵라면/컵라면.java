import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static class Ramen {
        int count;
        int deadline;

        public Ramen(int deadline, int count) {
            this.count = count;
            this.deadline = deadline;
        }
    }

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        Comparator<Ramen> myDead = new Comparator<Ramen>() {
            @Override
            public int compare(Ramen o1, Ramen o2) {
                if (o1.deadline != o2.deadline) {
                    return o1.deadline - o2.deadline;
                } else {
                    return o1.count - o2.count;
                }
            }
        };
        Comparator<Ramen> myCount = new Comparator<Ramen>() {
            @Override
            public int compare(Ramen o1, Ramen o2) {
                if (o1.count != o2.count) {
                    return o1.count - o2.count;
                } else {
                    return o1.deadline - o2.deadline;
                }
            }
        };

        PriorityQueue<Ramen> pq = new PriorityQueue<>(myDead);
        PriorityQueue<Ramen> pqPick = new PriorityQueue<>(myDead);
        PriorityQueue<Ramen> pqLow = new PriorityQueue<>(myCount);

        for (int i = 0; i < N; i++) {
            String[] input = bf.readLine().split(" ");
            pq.add(new Ramen(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        int viewCount = 0;
        int minCount = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            viewCount++;
            Ramen ramen = pq.remove();
            int count = ramen.count;
            int deadline = ramen.deadline;
            /**
             * step 1 . 데드라인 순으로 정렬 pq_Pick.size<deadline 그냥넣음
             * step 2.  만약에 size == deadline
             * 그럼 데드라인 나랑 같은 애들까지 다뽑은다음에 거기서 제일 돈 안주는애 뺴고 넣음
             * 정리하면서 넣는 Pick에 size가 데드라인을 넘을수가 없음.
             *
             * 잠깐 근데 이미 정렬돼서 데드라인순으로 오는거면 만약 사이즈랑 같으면 그중 가장 낮은걸 버리면되는거아닌가
             */
            if (pqLow.size() < deadline) {
                pqLow.add(new Ramen(deadline, count));
            } else if (pqLow.size() == deadline) {
                pqLow.remove();
                pqLow.add(new Ramen(deadline,count));
            }
        }//while

        int sum = 0;
        while (!pqLow.isEmpty()) {
            Ramen c = pqLow.remove();
//            System.out.println(c.deadline + " : " + c.count);
            sum += c.count;
        }
        System.out.println(sum);

    }
}
