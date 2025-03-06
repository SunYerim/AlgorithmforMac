import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int round = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < enemy.length; i++) {
            // 현재 병사로 공격이 가능하다면
            if (n - enemy[i] >= 0) {
                n -= enemy[i]; // 병사 수 계산
                pq.add(enemy[i]);
                round++;
            } else {
                // 무적권 사용 가능하다면
                if (k > 0) {
                    // pq가 비어있지 않다면
                    if (!pq.isEmpty()) {
                        if (pq.peek() > enemy[i]) {
                            // 복원
                            n += pq.poll();
                            n -= enemy[i];
                            pq.add(enemy[i]);
                        }
                    }
                    k--;
                    round++;
                }
                // 사용이 안되면 종료
                else {
                    break;
                }
            }
        }
        return round;
    }
}