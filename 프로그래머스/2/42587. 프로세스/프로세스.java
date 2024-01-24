import java.util.LinkedList;
import java.util.Queue;

class Solution {

        static class Process {
        int priorities;
        int location;

        public Process(int p, int l) {
            this.priorities = p;
            this.location = l;
        }

    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Process> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Process(priorities[i], i));
        }
        //처리 완료
        while (!queue.isEmpty()) {
            Process process = queue.remove();
            int p = process.priorities;
            int l = process.location;
            boolean hasHigh = false;

            for (Process PR : queue) {
                if (PR.priorities > p) {
                    queue.add(process);
                    hasHigh = true;
                    break;
                }
            }

            if (!hasHigh) {
                answer++;
                if (l == location) {
                    return answer;
                }
            }
        }
        return answer;
    }
}