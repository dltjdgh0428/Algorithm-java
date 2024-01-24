import java.util.HashMap;

class Solution {
    public static String solution(String[] participant, String[] completion) {
        HashMap<String,Integer> people = new HashMap<>();

        for(String p : participant){
            people.put(p, people.getOrDefault(p,0)+1);
        }
        for(String c : completion){
            people.put(c,people.get(c)-1);
        }
        for(String anwser : people.keySet()){
            if(people.get(anwser)!=0){
                return anwser;
            }
        }

        return null;
    }
}