import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* logic
    1. graph 탐색
    2. 두 개씩 연결관계 확인해가면서 */
public class Main {
    static int n, m;
    static boolean isPossible = true;
    static int[] travel;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        travel = new int[m]; // 여행 경로를 담는 배열
        // 리스트 초기화
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        // 연결관계
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    // 어차피 map에서부터 i-j, j-i 연결관계 설정이 되어있음.
                    list.get(i).add(j);
                }
                if (i == j) list.get(i).add(j);
            }
        }

        // 여행 경로를 담기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            travel[i] = Integer.parseInt(st.nextToken())-1;
        }

        isTravel();

        // boolean배열으로
        if (isPossible) System.out.println("YES");
        else System.out.println("NO");

    }

    // 여행이 가능한 경로인지 판단하는 메서드
    private static void isTravel(){
        // 앞 뒤 두 개씩 비교하면서
       for (int i = 0; i < m-1; i++) {
           // 조건이 성립하면 true
           if (compareTwo(travel[i], travel[i+1])) isPossible = true;
           // 조건이 성립하지 않으면 즉시 false처리 후 break
           else {
               isPossible = false;
               break;
           }

       }
    }

    private static boolean compareTwo(int start, int end) {
        // start - end간에 연결관계가 성립하는지 판단하는 메서드
        Queue<Integer> queue = new LinkedList<>();
        // 인접한 두 지점간에 이동이 가능한지 보는 것이므로 boolean배열 사용 가능
        boolean[] visited = new boolean[n];
        queue.offer(start); // 시작점 넣기
        visited[start] = true;
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            for (int i = 0; i < list.get(tmp).size(); i++) {
                int next = list.get(tmp).get(i);
                if (next == end) return true;
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }

            }
        }
        return false;
    }
}