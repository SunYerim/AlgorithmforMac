import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int n, m;
    static HashMap<Integer, Integer> problems;
    static TreeSet<Problem> problemAsc; // 난이도 오름차순, 번호 오름차순
    static TreeSet<Problem> problemDesc; // 난이도 내림차순, 번호 내림차순
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        problems = new HashMap<>();

        // 정렬
        problemAsc = new TreeSet<>((a, b) -> {
            if (a.level != b.level) {
                return a.level - b.level; // 난이도 오름차순
            } else {
                return a.number - b.number; // 번호 오름차순
            }
        });

        problemDesc = new TreeSet<>((a, b) -> {
            if (a.level != b.level) {
                return b.level - a.level;
            } else {
                return b.number - a.number;
            }
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int problemNum = Integer.parseInt(st.nextToken());
            int problemLevel = Integer.parseInt(st.nextToken());
            addProblem(problemNum, problemLevel);
        }


        // 입력받기
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            if (tmp.equals("add")) {
                int probNum = Integer.parseInt(st.nextToken());
                int probLevel = Integer.parseInt(st.nextToken());
                addProblem(probNum, probLevel);
            } else if (tmp.equals("recommend")) {
                int recType = Integer.parseInt(st.nextToken());
                if (recType == 1) {
                    // 가장 어려운 문제의 번호 출력 -> 어려운 문제 여러개라면 문제 번호가 큰 것으로
                    Problem hardest = problemDesc.first();
                    sb.append(hardest.number).append("\n");
                } else if (recType == -1) {
                    // 가장 쉬운 문제 번호 출력 -> 쉬운 문제 여러개라면 가장 문제 번호 작은 것으로
                    Problem easiest = problemAsc.first();
                    sb.append(easiest.number).append("\n");
                }

            } else if (tmp.equals("solved")) {
                // 삭제
                int probNum = Integer.parseInt(st.nextToken());
                removeProblem(probNum);
            }
        }
        System.out.println(sb.toString());
    }

    static void addProblem(int number, int level) {
        Problem problem = new Problem(number, level);
        if (problems.containsKey(number)) {
            removeProblem(number);
        }
        problems.put(number, level);
        problemDesc.add(problem);
        problemAsc.add(problem);
    }

    static void removeProblem(int number) {
        int level = problems.get(number);
        Problem problem = new Problem(number, level);
        problems.remove(number);
        problemAsc.remove(problem);
        problemDesc.remove(problem);
    }

    static class Problem {
        int number;
        int level;

        public Problem(int number, int level) {
            this.number = number;
            this.level = level;
        }
    }
}