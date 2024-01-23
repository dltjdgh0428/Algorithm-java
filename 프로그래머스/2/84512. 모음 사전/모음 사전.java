import java.util.ArrayList;
import java.util.List;

class Solution {
    private static char[] D = {'A', 'E', 'I', 'O', 'U'};
    private static String WORD;
    private static int ANS = 0;
    private static int ANSWER =0;

    public static int solution(String word) {
        WORD = word;
        re(new ArrayList<>());
        return ANSWER;
    }

    private static void re(List<Character> word) {
        if (word.size() >= 5) {
        } else {
            for (int i = 0; i < D.length; i++) {
                word.add(D[i]);
                ANS += 1;
                String a ="";
                for(int j = 0 ; j<word.size();j++){
                    a+=word.get(j);
                }
                if(a.equals(WORD)){
                    ANSWER=ANS;
                }
                re(word);
                word.remove(word.size() - 1);
            }
        }
    }
}