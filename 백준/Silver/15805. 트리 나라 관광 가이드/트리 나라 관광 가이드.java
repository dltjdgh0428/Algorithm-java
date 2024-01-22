import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        int[] path = new int[N];
        String[] input = bf.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            path[i] = Integer.parseInt(input[i]);
        }
        Map<Integer, Integer> parents = findParents(path);

        System.out.println(parents.size());

        for (int i = 0; i < parents.size(); i++) {
            System.out.print(parents.getOrDefault(i, -1) + " ");
        }
    }

    public static Map<Integer, Integer> findParents(int[] path) {
        Map<Integer, Integer> parents = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        parents.put(path[0], -1);
        visited.add(path[0]);

        for (int i = 1; i < path.length; i++) {
            if (!visited.contains(path[i])) {
                parents.put(path[i], path[i-1]);
                visited.add(path[i]);
            }
        }
        return parents;
    }
}
