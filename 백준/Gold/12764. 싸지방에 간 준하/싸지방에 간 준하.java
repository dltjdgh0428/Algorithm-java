import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static class User {
        int start, end;

        public User(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class Seat {
        int end, count, number;

        public Seat(int end, int number, int count) {
            this.end = end;
            this.number = number;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        Comparator<User> myUser = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.start - o2.start;
            }
        };
        Comparator<Seat> myBest = new Comparator<Seat>() {
            @Override
            public int compare(Seat o1, Seat o2) {
                return o1.end - o2.end;
            }
        };
        Comparator<Seat> myNumber = new Comparator<Seat>() {
            @Override
            public int compare(Seat o1, Seat o2) {
                return o1.number - o2.number;
            }
        };
        /**
         * 자 그럼 end순 정렬된것을 만들어서 된다 그럼 number순 정렬q
         * 에 넣어 그다음 삽입이 완료됐을때 다시 end순 정렬로 삽입하고
         * 출력할때 모든걸 number순 정렬로 다시 넣어서 하면
         * PQ가 총 3개가 필요한건가???
         * 입력용 USER PQ
         * 최적화용 SEAT PQ2
         * 출력용 NUMBER PQ3
         */
        PriorityQueue<User> pq = new PriorityQueue<>(myUser);
        PriorityQueue<Seat> pq2 = new PriorityQueue<>(myBest);
        PriorityQueue<Seat> pq3 = new PriorityQueue<>(myNumber);
        for (int i = 0; i < n; i++) {
            String[] input = bf.readLine().split(" ");
            pq.add(new User(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }


        while (!pq.isEmpty()) {
            User user = pq.remove();
            /**
             * step 1 . 일단 가능한 자리를 pq3에 넣어서 정렬
             * 2. 그이후 peek값을 수정해서 다시 pq2에 넣음
             * 여기서 pq3는 다시 pq2에 넣을 필요가 없음 어차피 pq
             */
//            System.out.println("내가 들어갈 곳을 찾고있어 나는"+user.end+"에 끝나");
            while(!pq2.isEmpty()){
                Seat seat = pq2.remove();
                // 여기서 내가 앉을 수 있는 자리를 모두 선별
                if (seat.end <= user.start) {
                    //만약 앉을 수 있는자리다!
                    //그러면 앉을수 있는자리들을 Number순으로 정렬하는
                    //PQ로 이동시킴
                    pq3.add(seat);
                } else {
                    pq2.add(seat);
                    break;
                }
            }

            if (pq3.isEmpty()) {//만약 들어갈 자리가 없다!
//                System.out.println("들어갈 자리가 없어"+user.end+"에 끝나는데"+(pq2.size()+1)+"자리에 배정");
                pq2.add(new Seat(user.end, pq2.size()+1, 1));
            } else { // 들어갈 자리가 있다!
                Seat seat = pq3.remove();
//                System.out.println("들어갈 자리가 있네 그중에"+seat.number+"에 앉아"+seat.end+"에 끝나서");
                pq2.add(new Seat(user.end, seat.number, seat.count + 1));
            }
        }
        while (!pq2.isEmpty()) {
            pq3.add(pq2.remove());
        }
        System.out.println(pq3.size());
        while (!pq3.isEmpty()) {
            Seat seat = pq3.remove();
            System.out.print(seat.count + " ");
        }

    }
}
