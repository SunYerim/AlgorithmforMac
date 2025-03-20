import java.util.*;

class Solution {
    static PriorityQueue<Disk> pq;
    public int solution(int[][] jobs) {
        int totalTime = 0; // 총 대기시간
        int currTime = 0; // 현재시간
        int completedJobs = 0; // 완료된 작업 개수
        int jobIdx = 0; // jobs 배열 인덱스
        pq = new PriorityQueue<>();
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        while (completedJobs < jobs.length) {
            // 현재 시간보다 작은 요청 시간을 가진 작업들을 순차적으로 pq에 추가
            while (jobIdx < jobs.length && jobs[jobIdx][0] <= currTime) {
                pq.add(new Disk(jobIdx, jobs[jobIdx][0], jobs[jobIdx][1]));
                jobIdx++;
            }
            
            // 대기큐가 비어있으면
            if (pq.isEmpty()) {
                currTime = jobs[jobIdx][0];
            } else {
                Disk curr = pq.poll();
                currTime += curr.time;
                totalTime += (currTime - curr.request);
                completedJobs++;
            }
        }
        
        return totalTime / jobs.length;
    }
    
    static class Disk implements Comparable<Disk>{
        int number, request, time;
        public Disk(int number, int request, int time) {
            this.number = number;
            this.request = request;
            this.time = time;
        }
        @Override
        public int compareTo (Disk o) {
            if (this.time == o.time) {
                if (this.request == o.request) {
                    return this.number - o.number;
                }
                return this.request - o.request;
            }
            return this.time - o.time;
        }
    }
}