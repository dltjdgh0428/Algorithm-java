import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static class Jewel {
        int weight;
        int money;

        public Jewel(int weight, int money) {
            this.weight = weight;
            this.money = money;
        }
    }

    static class Bag {
        int max_weight;
        int money;

        public Bag(int max_weight, int money) {
            this.max_weight = max_weight;
            this.money = money;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputNK = bf.readLine().split(" ");
        int n = Integer.parseInt(inputNK[0]);
        int k = Integer.parseInt(inputNK[1]);

        Comparator<Jewel> myBest = new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                return o2.money - o1.money;
            }
        };
        Comparator<Jewel> myWeight = new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                return o1.weight - o2.weight;
            }
        };
        Comparator<Bag> myBag = new Comparator<Bag>() {
            @Override
            public int compare(Bag o1, Bag o2) {
                if (o1.max_weight != o2.max_weight) {
                    return o1.max_weight - o2.max_weight;
                } else {
                    return o1.money - o2.money;
                }
            }
        };
        PriorityQueue<Jewel> jewel_W = new PriorityQueue<>(myWeight);
        PriorityQueue<Jewel> jewel_M = new PriorityQueue<>(myBest);
        PriorityQueue<Bag> Bags = new PriorityQueue<>(myBag);

        for (int i = 0; i < n; i++) {
            String[] inputJ = bf.readLine().split(" ");
            jewel_W.add(new Jewel(Integer.parseInt(inputJ[0]), Integer.parseInt(inputJ[1])));
        }
        for (int i = 0; i < k; i++) {
            int input = Integer.parseInt(bf.readLine());
            Bags.add(new Bag(input, 0));
        }
        /**
         * 아 이것도 똑같은거 같은데
         * 1.  무게가 되는 주얼리들만 골라 그리고 그중 가장 비싼걸 넣어
         *
         */
        long sum = 0;

        while(!Bags.isEmpty()){
            Bag bag = Bags.remove();
//            System.out.println(bag.max_weight+"의 가방이 보석탐색");
            while(!jewel_W.isEmpty()){
                Jewel j = jewel_W.remove();

                if(bag.max_weight>=j.weight){
//                    System.out.println("가방에 들어갈만한보석 발견 무게는"+j.weight);
                    jewel_M.add(j);
                }else{
                    jewel_W.add(j);
                    break;
                }
            }

            if(!jewel_M.isEmpty()){
                Jewel j = jewel_M.remove();
//                System.out.println(bag.max_weight+"크기가방에"+ j.weight+"무게 삽입");
                sum+=j.money;
            }
        }
        System.out.println(sum);
    }
}
