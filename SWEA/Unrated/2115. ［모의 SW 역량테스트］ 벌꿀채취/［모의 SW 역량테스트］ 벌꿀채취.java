import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int T, n, m, c;
    static int[][] honeys; // 벌꿀양
    static boolean[][] visited; // 이미 처리된 곳은 다시 처리하지 않게끔
    static ArrayList<ArrayList<Honey>> graph = new ArrayList<>(); // 각 행에서 c이하를 만족하면서 m개 이하로 고른 경우의수 add
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            honeys = new int[n][n];

            // 좌표에 넣고
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    honeys[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 그래프 초기화
            for (int i = 0; i < n; i++) {
                graph.add(i, new ArrayList<>());
            }

            // 각행에 대해서 메서드 실행
            for (int i = 0; i < n; i++) {
                // m개씩 잘라서 간다.
                for (int j = 0; j <= n-m; j++) {
                    Honey tmp = honeyCollection(honeys[i], j, m);
                    graph.get(i).add(tmp);
                }

            }

//
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < graph.get(i).size(); j++) {
//                    System.out.println(graph.get(i).get(j).weight);
//                }
//            }

            int maxValue = Integer.MIN_VALUE;
            // 그래프에 들어온 것들 탐색하며 두가지를 선택해야되는데, 선택범위 겹치지 않으면서 max값 return할 수 있는 경우를 ans로 지정해서 return한다.
            // 겹치는 경우는 같은 행에서 두 개를 선택할때만 고려하면 된다.
            int[] maxProfit1 = new int[n];
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (Honey honey : graph.get(i)) {
                    if (!visited[i][honey.start]) {
                        maxProfit1[i] = Math.max(maxProfit1[i], honey.weight);
                        visited[i][honey.start] = true; // 해당 벌꿀을 선택했다고 표시
                    }
                }
            }

            // 두 개의 벌꿀을 선택하여 얻을 수 있는 최대 이익을 계산
            int maxProfit = maxProfit1[0] + maxProfit1[1];
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (Honey honey1 : graph.get(i)) {
                        for (Honey honey2 : graph.get(j)) {
                            // 같은 행에서 선택하는 경우, 범위가 겹치지 않아야 함
                            if (i == j && (honey1.start <= honey2.end && honey1.end >= honey2.start)) continue;
                            // 두 벌꿀을 선택하여 얻을 수 있는 이익을 계산
                            int profit = honey1.weight + honey2.weight;

                            // 최대 이익을 갱신
                            maxProfit = Math.max(maxProfit, profit);
                        }
                    }
                }
            }
            sb.append(maxProfit).append("\n");
        }
        System.out.println(sb);
    }

    private static Honey honeyCollection(int[] row, int start, int limit) {
        // end - start = m이 된다.
        //우선 범위 내에 m만큼 벌꿀 양을 저장할 수 있는지 판별한다. -> 해당 메서드 내부에서 최댓값 갱신
        // 1부터 m까지 갯수 조합으로 isValid 메서드가 true를 반환하면 graph에 add 처리한다. -> 자동 정렬됨.

        // 완전탐색
        int maxWeight = 0;
        int maxProfit = 0;

        // limit까지의 범위에서 모든 부분집합 탐색
        for (int i = 0; i < (1<<limit); i++) {
            int tmpWeight= 0;
            int tmpProfit = 0;

            // 각 부분집합에 대해서 c를 넘지않는 범위에서 벌꿀을 채취하면서 그때의 이익 계산
            for (int j = 0; j < limit; j++) {
                if ((i & (1<<j)) != 0) {
                    tmpWeight += row[start+j];
                    tmpProfit += row[start+j] * row[start+j];

                    // 채취한 벌꿀의 양이 C를 넘는 경우 break
                    if (tmpWeight > c) {
                        tmpProfit = 0;
                        break;
                    }
                }
            }

            if (tmpProfit > maxProfit) {
                //maxWeight = tmpWeight;
                maxProfit = tmpProfit;
            }
        }

        return new Honey(start, limit, maxProfit);
    }

    static class Honey implements Comparable<Honey>{
        private int start, end, weight;

        public Honey(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight; // start와 end 사이 범위에서 나올 수 있는 제곱값의 최댓값
        }

        @Override
        public int compareTo(Honey o) {
            return this.weight - o.weight;
        }
    }
}