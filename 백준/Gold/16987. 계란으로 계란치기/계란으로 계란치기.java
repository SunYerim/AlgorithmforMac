
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 순서대로 칠 필요가 없음 -> 최대로 깰 수 있는 경우만
    static int n, answer = 0;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        // 계란 저장
        Egg[] eggs = new Egg[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(d, w);
        }

        brokeEgg(0, eggs);

        System.out.println(answer);

    }

    private static void brokeEgg(int idx, Egg[] list) {
        // 종료조건
        if (idx == n) {
            int sum = 0;
            for (int i = 0; i < list.length; i++) {
                if (list[i].durability <= 0) {
                    sum++;
                }
            }
            answer = Math.max(answer, sum);
            return;
        }

        // 현재 손에 든 계란의 상태
        if (list[idx].durability > 0) {
            boolean nextExists = false;
            for (int i = 0; i < n; i++) {
                // 손에 든 계란과 같은 idx?
                if (idx == i) {
                    continue;
                }
                // 다른 계란 깨져있는가?
                if (list[i].durability <= 0) {
                    continue;
                }

                nextExists = true;
                // 부딪히기
                list[idx].durability -= list[i].weight;
                list[i].durability -= list[idx].weight;
                brokeEgg(idx + 1, list);

                // 되돌림
                list[idx].durability += list[i].weight;
                list[i].durability += list[idx].weight;
            }
            // 칠 계란 없으면 다음 거 치기
            if (!nextExists) {
                brokeEgg(idx + 1, list);
            }
        } else {
            brokeEgg(idx + 1, list);
        }

    }

    static class Egg {

        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

}
