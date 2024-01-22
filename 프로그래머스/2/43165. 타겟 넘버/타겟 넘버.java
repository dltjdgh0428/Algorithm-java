class Solution {

    private static int[] NUMBERS;
    private static int TARGET;
    private static int COUNT = 0;

    public static int solution(int[] numbers, int target) {
        NUMBERS = numbers;
        TARGET = target;
        re(0, 0);
        return COUNT;
    }

    private static void re(int count, int sum) {
        if (count == NUMBERS.length) {
            if (sum == TARGET) {
                COUNT+=1;
            }
            return;
        }
        re(count + 1, sum + NUMBERS[count]);
        re(count + 1, sum - NUMBERS[count]);
    }
}