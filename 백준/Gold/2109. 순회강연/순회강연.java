import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static class Teach {
        int pay;
        int deadline;

        public Teach(int pay, int deadline) {
            this.pay = pay;
            this.deadline = deadline;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        Comparator<Teach> myDead = new Comparator<Teach>() {
            @Override
            public int compare(Teach o1, Teach o2) {
                if (o1.deadline != o2.deadline) {
                    return o1.deadline - o2.deadline;
                } else {
                    return o1.pay - o2.pay;
                }
            }
        };
        Comparator<Teach> myPay = new Comparator<Teach>() {
            @Override
            public int compare(Teach o1, Teach o2) {
                if (o1.pay != o2.pay) {
                    return o1.pay - o2.pay;
                } else {
                    return o1.deadline - o2.deadline;
                }
            }
        };

        PriorityQueue<Teach> pqDead = new PriorityQueue<>(myDead);
        PriorityQueue<Teach> pqS =new PriorityQueue<>(myPay);
        int sum = 0;

        for (int i = 0; i < n; i++) {
            String[] input = bf.readLine().split(" ");
            pqDead.add(new Teach(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        while (!pqDead.isEmpty()) {
            Teach teach = pqDead.remove();

            if(pqS.size()<teach.deadline){
                pqS.add(teach);
            }else{
                if(pqS.size() == teach.deadline){
                    pqS.remove();
                    pqS.add(teach);
                }
            }

        }
        while (!pqS.isEmpty()) {
            Teach teach = pqS.remove();
            sum += teach.pay;
//            System.out.println("오늘의 강연은" + teach.pay + "만큼 받고 Dead:" + teach.deadline);
        }
        System.out.println(sum);


    }
}
