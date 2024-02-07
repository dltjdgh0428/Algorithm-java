import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    private static int N;
    private static int M;
    private static List<List<Node>> BOARD;
    private static int START;
    private static int END;
    private static int[] dist;
    private static final int INF = Integer.MAX_VALUE;

    static class Node {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        BOARD = new ArrayList<>();
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        for (int i = 0; i <= N; i++) {
            List<Node> arr = new ArrayList<>();
            BOARD.add(arr);
        }
        for (int i = 0; i < M; i++) {
            String[] input = bf.readLine().split(" ");
            int v1 = Integer.parseInt(input[0]);
            int v2 = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);
            BOARD.get(v1).add(new Node(v2, weight));
//            BOARD.get(v2).add(new Node(v1, weight));
        }
        String[] input = bf.readLine().split(" ");
        START = Integer.parseInt(input[0]);
        END = Integer.parseInt(input[1]);

        dijkstra(START);

//        for(int a : dist){
//            System.out.print(a +" ");
//        }
//        System.out.println();
        System.out.println(dist[END]);
    }

    public static void dijkstra(int start) {
        Comparator<Node> c = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.weight - o2.weight;
            }
        };
        PriorityQueue<Node> pq = new PriorityQueue<>(c);
        boolean[] visited = new boolean[dist.length];
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.end]) continue;
            visited[current.end] = true;

            for (Node node : BOARD.get(current.end)) {
                if (dist[node.end] > dist[current.end] + node.weight) {
                    dist[node.end] = dist[current.end] + node.weight;
                    pq.offer(new Node(node.end, dist[node.end]));
                }
            }
        }
    }

}
