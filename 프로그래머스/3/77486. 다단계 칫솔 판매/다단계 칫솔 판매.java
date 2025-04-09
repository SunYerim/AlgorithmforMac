import java.util.*;

class Solution {
    static HashMap<String, Integer> person;
    // static List<Integer>[] graph;
    static int[] answer, parent;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        person = new HashMap<>();
        parent = new int[enroll.length];
        answer = new int[enroll.length];
        
        for (int i = 0; i < enroll.length; i++) {
            person.put(enroll[i], i);
        }
        
        for (int i = 0; i < referral.length; i++) {
            parent[i] = referral[i].equals("-") ? -1 : person.get(referral[i]);
        }
        
        // amount -> 이익금
        for (int i = 0; i < amount.length; i++) {
            amount[i] *= 100;
        }
        
        for (int i = 0; i < seller.length; i++) {
            int curr = person.get(seller[i]);
            int profit = amount[i];
            while (curr != -1 && profit > 0) {
                int nextProfit = profit / 10;
                answer[curr] += (profit - nextProfit);
                curr = parent[curr];
                profit = nextProfit;
            }
        }
        
        // 이익금 분배
        // for (int i = 0; i < seller.length; i++) {
        //     int from = person.get(seller[i]);
        //     // 분배
        //     divide(from, amount[i]);
        // }
    
        return answer;
    }
    
//     public static void divide(int from, int value) {
//         int get = value / 10;
//         answer[from] += (value - get);
        
//         int size = graph[from].size();
        
//         if (size == 0) return;
        
//         for (int i = 0; i < size; i++) {
//             int next = graph[from].get(i);
//             divide(next, get);
//         }
//     }
}