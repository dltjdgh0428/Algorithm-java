import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static List<Character> CHARLIST = new ArrayList<>();
    private static List<Character> SELECT = new ArrayList<>();
    private static char[] AEIOU = {'a', 'e', 'i', 'o', 'u'};
    private static int S;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bf.readLine().split(" ");
        S = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        String[] inputString = bf.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            CHARLIST.add(inputString[i].charAt(0));
        }
        Comparator<Character> comparator = new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1 - o2;
            }
        };
        CHARLIST.sort(comparator);
        backtracking(0, 0);

    }

    private static void backtracking(int start, int count) {
        if (count == S && checkAeiou()) {
            for (char a : SELECT) {
                System.out.print(a);
            }
            System.out.println();
            return;
        }

        for (int i = start; i < CHARLIST.size(); i++) {
            SELECT.add(CHARLIST.get(i));
            backtracking(i + 1, count + 1);
            SELECT.remove(SELECT.size() - 1);
        }
    }

    private static boolean checkAeiou() {
        int aeiouCount = 0;
        for (char c : SELECT) {
            for (char aeiou : AEIOU) {
                if (aeiou == c) {
                    aeiouCount++;
                }
            }
        }
        if (aeiouCount >= 1 && SELECT.size()-aeiouCount >= 2) {
            return true;
        } else {
            return false;
        }
    }
}
