import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Frog> frogs;
    static List<Log>[] adj;
    static int[] lotusToFrog;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        frogs = new ArrayList<>();
        adj = new ArrayList[N + 1];
        lotusToFrog = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int number = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            frogs.add(new Frog(number++, a, b, c, d));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            frogs.get(i).lotusA = a;
            frogs.get(i).lotusB = b;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adj[u].add(new Log(v, t));
            adj[v].add(new Log(u, t));
        }

        // dfs
        dfs(1);

        System.out.println("NO");


    }

    public static void dfs(int frogIdx) {
        // 기저
        if (frogIdx == N + 1) {
            if (finalCheck()) {
                System.out.println("YES");
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= N; i++) {
                    sb.append(lotusToFrog[i]).append(" ");
                }

                System.out.println(sb.toString().trim());
                System.exit(0);
            }
            return;
        }

        // 유도
        Frog currentFrog = frogs.get(frogIdx - 1);

        // 시도할 연꽃 후보들
        int[] targets = {currentFrog.lotusA, currentFrog.lotusB};

        for (int i = 0; i < 2; i++) {
            int target = targets[i];

            // 연꽃이 비어있다면
            if (lotusToFrog[target] == 0) {
                lotusToFrog[target] = currentFrog.number;
                dfs(frogIdx + 1);
                lotusToFrog[target] = 0;
            }

            if (currentFrog.lotusA == currentFrog.lotusB) {
                break;
            }

        }

    }

    // 모든 통나무의 대화 조건을 확인
    public static boolean finalCheck() {
        for (int i = 1; i <= N; i++) {
            for (Log log : adj[i]) {
                int j = log.to; // 목적지
                int topic = log.topic; // 대화

                // 두 연꽃에 모두 개구리가 있고 대화 주제 흥미도가 다르면 false
                if (lotusToFrog[i] != 0 && lotusToFrog[j] != 0) {
                    if (frogs.get(lotusToFrog[i] - 1).interests[topic] != frogs.get(
                        lotusToFrog[j] - 1).interests[topic]) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    static class Frog {

        int number;
        int[] interests = new int[5]; // 1:음식, 2:취미, 3:가족, 4:철학
        int lotusA, lotusB;

        public Frog(int number, int f, int h, int fam, int p) {
            this.number = number;
            this.interests[1] = f;
            this.interests[2] = h;
            this.interests[3] = fam;
            this.interests[4] = p;
        }
    }

    static class Log {

        int to, topic;

        public Log(int to, int topic) {
            this.to = to;
            this.topic = topic;
        }
    }

}
