// 프로그래머스 -> 두 큐 합 같게 만들기
import java.util.*;

class queueTotal {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> list1 = new LinkedList<>();
        Queue<Integer> list2 = new LinkedList<>();

        for (int num : queue1) {
            list1.add(num);
        }

        for (int num : queue2) {
            list2.add(num);
        }

        int sum1 = 0;
        int sum2 = 0;

        for (int i : list1) {
            sum1 += i;
        }
        for (int i : list2) {
            sum2 += i;
        }

        int count = 0; // 연산 횟수
        int end = (list1.size() + list2.size()) * 2; // -1 반환 count 기준 값

        while (sum1 != sum2) {
            // 두 큐 합의 차이
            int diff = sum1 - sum2;
            // sum1 > sum2이면
            if (diff > 0) {
                int element = list1.remove(); // sum1 앞 요소 pop -> sum2에 insert처리
                sum1 -= element;
                sum2 += element;
                list2.add(element); // list2의 맨 뒤에 삽입
                // sum2 > sum1
            } else {
                int element = list2.remove();
                sum2 -= element;
                sum1 += element;
                list1.add(element); // list1의 맨 뒤에 삽입
            }

            count++; // 연산 횟수 증가

            // 두 큐의 합이 같아질 수 없는 경우 처리
            if (count > end) {
                return -1;
            }
        }

        return count;
    }
}
