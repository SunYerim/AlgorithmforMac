import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, minDiff = Integer.MAX_VALUE;
    static Node[] city;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

    // 선거구 두 개 list
    static ArrayList<Integer> area1;
    static ArrayList<Integer> area2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        city = new Node[n]; // 1번 index 부터 사용한다.
        for (int i = 0; i < n; i++) {
            city[i] = new Node(i+1, Integer.parseInt(st.nextToken()));
        }

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        // 연결관계 표시
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                graph.get(i).add(tmp);
            }
        }

        // 선거구를 두 개로 나누고 => area1, area2 리스트로 분리
        area1 = new ArrayList<>();
        area2 = new ArrayList<>();


        // 두 선거구의 인구 차이의 최솟값을 출력
        // min값을 갱신해 나가면서 선거구역을 갱신
        // bfs 메서드를 통해 선거구가 올바르게 나뉘어졌는지  boolean 메서드 선언
        dfs(1);
        
        if (minDiff == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(minDiff);

    }

    // 최솟값은 가능한 모든 선거구 조합에 대해 인구 차이를 계산하고 가장 작은 값으로 갱신해나가는 방식
    // 모든 지역을 순회하면서 선거구를 나눈다 -> dfs를 이용해서 선거구를 나누는 로직과 최솟값을 갱신
    private static void dfs(int idx) {
        // 기저
        if (idx == n+1) {
            if (isValid(area1) && isValid(area2)) {
                int sum1 = 0, sum2 = 0;
                for (int i : area1) {
                    sum1 += city[i-1].persons;
                }
                for (int j : area2) {
                    sum2 += city[j-1].persons;
                }

                //System.out.println("sum1: "+sum1+" sum2: "+sum2);
                minDiff = Math.min(minDiff, Math.abs(sum1-sum2));
            }

            return;
        }

        area1.add(idx);
        dfs(idx+1);
        area1.remove(area1.size()-1);

        area2.add(idx);
        dfs(idx+1);
        area2.remove(area2.size()-1);
    }

    // 선거구가 올바르게 나뉘어졌는지. -> 선거구 두개의 size가 n이 안되면 두 구역으로 나눌 수 없는 것임.
    private static boolean isValid(ArrayList<Integer> area) {

        if (area.isEmpty()) return false;

        // 방문 배열
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new LinkedList<>();

        // 초기 세팅
        visited[area.get(0)] = true;
        queue.offer(area.get(0));

        int count = 1;

        // BFS를 통해 같은 선거구 내에서 모든 도시가 이동한지를 확인한다.
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int i : graph.get(current-1)) {
                if(!visited[i] && area.contains(i)){
                    visited[i] = true;
                    queue.offer(i);
                    count++;
                }
            }

        }
        if (count == area.size()) {
            return true;
        }
        return false;
    }

    static class Node {
        int index, persons;

        public Node(int index, int persons) {
            this.index = index;
            this.persons = persons;
        }
    }
}