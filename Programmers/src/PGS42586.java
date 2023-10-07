/*logic
    1. queue를 사용해서 푸는 문제
    2. 객체지향적으로 코드를 짜보려 고민하자 ..!
    3. 생각해보니 queue를 사용하지 않아도 될 것 같다.
        - 배포까지 남은 일자를 계산해주는 메서드를 하나 분리시키고, 각 계산을 해서 새로운 배열에 add
        - 해당 배열을 queue 이용해서 풀어나가도 될 듯 하다.*/
import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> remainProcess = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int days = solutionDay(progresses[i], speeds[i]);
            remainProcess.add(days);
        }
        // queue에서 첫번째 요소 pop 하면서 뒤에 요소 poll 후 비교 -> 작으면 빼고, 크면 놔둠.
        // pop과 poll한 요소 개수 더해서 answer배열에 추가.
        // queue 끝날때까지 계속 진행
        List<Integer> answerList = new ArrayList<>();
        int days = remainProcess.poll();
        int count = 1;

        while (!remainProcess.isEmpty()) {
            int nextDays = remainProcess.poll();

            if (nextDays <= days) {
                count++;
            } else {
                answerList.add(count);
                count = 1;
                days = nextDays;
            }
        }

        answerList.add(count); // 마지막 작업 그룹 추가

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    // 각 작업을 끝마치기까지 몇 일이 소요되는지 계산하는 메서드
    public static int solutionDay(int progress, int speed) {
        int day = 0;
        while (progress < 100) {
            progress += speed;
            day++;
        }
        return day;
    }
}