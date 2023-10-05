import java.util.*;

public class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations); // 배열을 오름차순으로 정렬

        int n = citations.length;
        int hIndex = 0;

        // 인용 횟수와 인용된 논문의 수를 비교하여 h-index 계산
        for (int i = 0; i < n; i++) {
            int count = n - i; // 현재 인용 횟수 이상인 논문 수
            if (citations[i] >= count) {
                hIndex = count;
                break;
            }
        }

        return hIndex;
    }
}
