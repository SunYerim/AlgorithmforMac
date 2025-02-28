import java.util.*;

class Solution {
    /*
    - 해당 번호 나올때까지 스택에 상자를 넣는다.
    - 현재 숫자이면, 상자를 싣는다.
    - 주문 번호가 스택에 있으면 상자를 싣는다.
    - 둘 다 없으면 break;
    */
    static Stack<Integer> boxes = new Stack<>();
    public int solution(int[] order) {
        int answer = 0;
        int site = 1; // 택배 번호 추적
        
        for (int i = 0; i < order.length; i++) {
            // 현재 숫자가 주문 순서 될 때까지 스택에 넣음.
            for (int j = site; j < order[i]; j++) {
                boxes.push(j);
                site++;
            }
            // 현 숫자가 오더 순서라면
            if (site == order[i]) {
                site++;
                answer++;
            }
            // 스택에 있다면
            else if (!boxes.isEmpty() && boxes.peek() == order[i]) {
                boxes.pop();
                answer++;
            } else {
                break;
            }
        }
        return answer;
    }
}