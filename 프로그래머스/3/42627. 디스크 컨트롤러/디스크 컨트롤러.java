import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        List<Disk> jobList = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++) {
            jobList.add(new Disk(i, jobs[i][0], jobs[i][1]));
        }
        jobList.sort((a, b) -> a.requestTime - b.requestTime);
        
        PriorityQueue<Disk> pq = new PriorityQueue<>();
        int currTime = 0;
        int completedJobs = 0;
        int jobIdx = 0;
        int totalTurnaroundTime = 0;
        
        while (completedJobs < jobs.length) {
            // 도착한 것들 넣기
            while (jobIdx < jobs.length && jobList.get(jobIdx).requestTime <= currTime) {
                pq.offer(jobList.get(jobIdx++));
            }
            
            // 비어있다면
            if (pq.isEmpty()) {
                currTime = jobList.get(jobIdx).requestTime;
            } else {
                Disk curr = pq.poll();
                totalTurnaroundTime += (currTime + curr.duration - curr.requestTime);
                currTime += curr.duration;
                completedJobs++;
            }
        }
        
        return totalTurnaroundTime / jobs.length;
        
    }
    
    static class Disk implements Comparable<Disk> {
        int id;
        int requestTime;
        int duration;
        
        public Disk(int id, int requestTime, int duration) {
            this.id = id;
            this.requestTime = requestTime;
            this.duration = duration;
        }
        
        @Override
        public int compareTo(Disk o) {
            if (this.duration != o.duration) {
                return this.duration - o.duration;
            } // 소요시간
            if (this.requestTime != o.requestTime) {
                return this.requestTime - o.requestTime;
            } // 요청시각
            return this.id - o.id; // 작업 번호
        }
        
    }
}
