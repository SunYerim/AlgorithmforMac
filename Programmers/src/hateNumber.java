import java.util.*;

public class hateNumber {
    public int[] solution(int []arr) {
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < arr.length; i++){
            if (i == 0 || arr[i] != arr[i-1]) {
                answer.add(arr[i]);
            }
        }

        // List를 배열로 변환
        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        //System.out.println(Arrays.toString(result));

        return result;

    }
}