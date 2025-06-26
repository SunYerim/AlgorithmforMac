import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int T;
    static List<Map<Integer, Problem>> problemsByTeam;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 팀의 개수
            int k = Integer.parseInt(st.nextToken()); // 문제의 개수
            int t = Integer.parseInt(st.nextToken()); // 나의 팀
            int m = Integer.parseInt(st.nextToken()); // 로그 엔트리 수

            List<Team> teams = new ArrayList<>();
            problemsByTeam = new ArrayList<>();

            // init
            for (int j = 0; j <= n; j++) {
                teams.add(new Team(j));
                problemsByTeam.add(new HashMap<>());
            }

            for (int j = 1; j <= m; j++) {
                st = new StringTokenizer(br.readLine());
                int teamId = Integer.parseInt(st.nextToken()); // 팀 아이디
                int problemNumber = Integer.parseInt(st.nextToken()); // 문제 번호
                int score = Integer.parseInt(st.nextToken()); // 획득한 점수

                Map<Integer, Problem> currTeamProblems = problemsByTeam.get(teamId);

                // 점수가 갱신되는 경우
                if (currTeamProblems.containsKey(problemNumber)) {
                    Problem problem = currTeamProblems.get(problemNumber);
                    if (problem.score < score) {
                        problem.score = score;
                    }
                    problem.time = j;
                } else {
                    currTeamProblems.put(problemNumber, new Problem(problemNumber, score, j));
                }

                // 정보 수정
                teams.get(teamId).count++;
                teams.get(teamId).finalTime = j;
            }

            // 최종 점수를 계산
            for (int j = 1; j <= n; j++) {
                int totalScore = 0;
                for (Problem p : problemsByTeam.get(j).values()) {
                    totalScore += p.score;
                }
                teams.get(j).score = totalScore;
            }

            teams.remove(0);

            Collections.sort(teams);

            // debugging
//            for (int j = 0; j < teams.size(); j++) {
//                System.out.println(teams.get(j).teamNumber + " " + teams.get(j).score);
//            }

            for (int rank = 0; rank < teams.size(); rank++) {
                if (teams.get(rank).teamNumber == t) {
                    sb.append(rank + 1).append("\n");
                    break;
                }
            }
        }
        System.out.print(sb.toString());
        
    }

    static class Problem {

        int number, score, time;

        public Problem(int number, int score, int time) {
            this.number = number;
            this.score = score;
            this.time = time;
        }

    }

    static class Team implements Comparable<Team> {

        int teamNumber, score, count, finalTime;

        public Team(int teamNumber, int score, int count, int finalTime) {
            this.teamNumber = teamNumber;
            this.score = score;
            this.count = count;
            this.finalTime = finalTime;
        }

        public Team(int teamNumber) {
            this.teamNumber = teamNumber;
        }

        @Override
        public int compareTo(Team o) {
            if (this.score == o.score) {
                if (this.count == o.count) {
                    return this.finalTime - o.finalTime;
                }
                return this.count - o.count;
            }
            return o.score - this.score;
        }
    }

}
