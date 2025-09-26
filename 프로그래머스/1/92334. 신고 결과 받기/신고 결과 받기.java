import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 1. 중복 신고 제거
        Set<String> reports = new HashSet<>(Arrays.asList(report));
        
        // 2. 각 유저가 신고당한 횟수 계산하고, 동시에 누가 누구 신고했는지 기록
        Map<String, Integer> reportedCnt = new HashMap<>();
        Map<String, List<String>> reporterMap = new HashMap<>();
        
        for (String r : reports) {
            String[] parts = r.split(" ");
            String reporter = parts[0];
            String reportee = parts[1];
            reportedCnt.put(reportee, reportedCnt.getOrDefault(reportee, 0) + 1);
            
            reporterMap.putIfAbsent(reporter, new ArrayList<>());
            reporterMap.get(reporter).add(reportee);
        }
        
        // 3. 정지된 유저 목록
        Set<String> suspendUsers = new HashSet<>();
        for (Map.Entry<String, Integer> entry : reportedCnt.entrySet()) {
            if (entry.getValue() >= k) {
                suspendUsers.add(entry.getKey());
            }
        }
        
        // 4. 각 유저가 받은 메일 수 계산
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            String user = id_list[i];
            int mailCnt = 0;
            
            if (reporterMap.containsKey(user)) {
                List<String> reportedList = reporterMap.get(user);
                
                for (String s : reportedList) {
                    if (suspendUsers.contains(s)) mailCnt++;
                }
            }
            
            answer[i] = mailCnt;
        }
        
        return answer;
    }
}