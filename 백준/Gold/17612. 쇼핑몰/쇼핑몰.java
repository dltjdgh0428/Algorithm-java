import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static class User {
        long id;
        int time;
        int counter;

        public User(long id, int time, int counter) {
            this.id = id;
            this.time = time;
            this.counter = counter;
        }
    }

    /**
     * point.1 계산이 동시에 끝났다면 빠른 번호부터 배정한다.
     * point.2 계산이 동시에 끝났다면 늦은 카운터 번호부터 나간다.
     * <p>
     * 동작 그럼 일단 Q 에 counter만큼 넣어 그리고 size가 다 찼다면 빼는데
     * (Q 빨리끝나는 순의 User순으로 정렬함 끝나는 숫자가 같다면 counter순 큰 순서로 정렬)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] setting = bf.readLine().split(" ");
        int n = Integer.parseInt(setting[0]);
        int counter = Integer.parseInt(setting[1]);

        Comparator<User> myUser = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.time != o2.time) {
                    return o1.time - o2.time;
                } else {
                    return o2.counter - o1.counter;
                }
            }
        };

        PriorityQueue<User> pq = new PriorityQueue<>(myUser);
        PriorityQueue<Integer> cq = new PriorityQueue<>();
        Queue<User> q = new LinkedList<>();
        long sum = 0;
        long outCount = 1;

        for (int i = 0; i < n; i++) {
            String[] input = bf.readLine().split(" ");
            q.add(new User(Integer.parseInt(input[0]), Integer.parseInt(input[1]), -1));
        }
        for (int i = 1; i < counter+1; i++) {
            if (!q.isEmpty()) {
                User inUer = q.remove();
                pq.add(new User(inUer.id, inUer.time, i));
            }
        }
        while (!pq.isEmpty()) {// 계산할 손님이 있다면 계속한다.
            int time = pq.peek().time; //가장 적게 남은 계산시간을 고려
            for (User user : pq) {
                user.time -= time;
            }
            while (!pq.isEmpty() && pq.peek().time == 0) { // 만약 나갈 사람이 있다.
                User outUser = pq.remove(); // 그럼 내보낸다
                cq.add(outUser.counter);  // 그리고 계산대 Queue에 삽입한다.
                sum += outCount * outUser.id;  // 그리고 몇번째로 나갔는지 숫자 세준다~
                outCount++;

            }
            while (!q.isEmpty() && !cq.isEmpty()) { //만약 들어올 사람도 있고 계산대도 있다
                User inUser = q.remove();
                int inCounter = cq.remove();
                pq.add(new User(inUser.id, inUser.time, inCounter));
            }
        }
        System.out.println(sum);

    }


}
