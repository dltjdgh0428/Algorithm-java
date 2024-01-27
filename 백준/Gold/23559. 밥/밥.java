import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static class Day {
        int big;
        int small;

        public Day(int big, int small) {
            this.big = big;
            this.small = small;
        }
    }

    /**
     * 그럼 큰돈으로 정렬하고
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = bf.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int money = Integer.parseInt(input1[1]);

        Comparator<Day> myBig = new Comparator<Day>() {
            @Override
            public int compare(Day o1, Day o2) {
                return (o2.big - o2.small) - (o1.big - o1.small);
            }
        };

        PriorityQueue<Day> pq_big = new PriorityQueue<>(myBig);
        for (int i = 0; i < n; i++) {
            String[] input = bf.readLine().split(" ");
            pq_big.add(new Day(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            Day day = pq_big.remove();
            int big = day.big;
            int small = day.small;
            if (money - 5000 >= (n - i - 1) * 1000) {
                if (big > small) {
                    money -= 5000;
                    max += big;
                } else {
                    money -= 5000;
                    max += small;
                }
            } else {
                money -= 1000;
                max += small;
            }
        }
//        System.out.println(money);
        System.out.println(max);
    }
}
