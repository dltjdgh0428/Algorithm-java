import java.util.ArrayList;
import java.util.List;
class Solution {
    private static int[] one = {1, 2, 3, 4, 5};
    private static int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
    private static int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    public static List<Integer> solution(int[] answers) {
        int[] answer = {0,0,0};
        for(int i = 0; i<answers.length;i++){
            if(one[i%one.length] == answers[i]){
                answer[0]++;
            }
            if(two[i%two.length] == answers[i]){
                answer[1]++;
            }
            if(three[i%three.length] == answers[i]){
                answer[2]++;
            }
        }
        int max =0;
        for(int j =0 ; j<3;j++){
            max = Math.max(answer[j],max);
        }
        List<Integer> list =new ArrayList<>();
        for(int i =0 ; i<3;i++){
            if(answer[i]==max){
                list.add(i+1);
            }
        }
        return list;
    }
}