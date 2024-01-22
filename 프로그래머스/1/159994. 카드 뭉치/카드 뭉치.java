class Solution {
    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        String[] answer = {"Yes", "No"};
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < goal.length; i++) {
            if (cards1.length > count1 && goal[i].equals(cards1[count1])) {
                count1++;
                continue;
            }
            if (cards2.length > count2 && goal[i].equals(cards2[count2])) {
                count2++;
                continue;
            }
            return answer[1];
        }
        return answer[0];
    }
}