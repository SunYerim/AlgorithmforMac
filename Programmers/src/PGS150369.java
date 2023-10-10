import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int delivery = 0;
        int pickup = 0;

        for (int i = n-1; i >= 0; i--) {
            // 배달해야되는 양 delivery 갱신
            delivery += deliveries[i];
            pickup += pickups[i];

            // 택배원이 수거하거나 배달할 수 있을때 동안,
            while (delivery > 0 || pickup > 0) {
                delivery -= cap;
                pickup -= cap;
                answer += 2 * (i+1);
            }
        }

        return answer;
    }
}