import java.util.*;

public class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int[] command = commands[i];

            int start = command[0] - 1;
            int end = command[1];
            int k = command[2];

            ArrayList<Integer> sortList = new ArrayList<>();

            for (int j = start; j < end; j++) {
                sortList.add(array[j]);
            }
            sortList.sort(null);
            answer[i] = sortList.get(k - 1);
        }

        return answer;
    }
}
