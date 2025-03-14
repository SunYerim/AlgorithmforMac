import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer = Integer.MAX_VALUE;
    static List<Node> ladders;
    static List<Node> snakes;
    static int[] dice = {1, 2, 3, 4, 5, 6};
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ladders = new ArrayList<>();
        snakes = new ArrayList<>();
        visited = new boolean[101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ladders.add(new Node(start, end));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            snakes.add(new Node(start, end));
        }

        bfs(1, 0); // 1번칸에서 시작하고, 0초 경과

        System.out.println(answer);

    }

    private static void bfs(int number, int time) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{number, time});
        visited[number] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currNum = curr[0];
            int currTime = curr[1];

            for (int i = 0; i < 6; i++) {
                // 기저
                int nextNum = currNum + dice[i];
                if (nextNum == 100) {
                    answer = currTime + 1;
                    return;
                }

                if (nextNum < 0 || nextNum > 100 || visited[nextNum]) continue;

                // 뱀 시작
                for (int j = 0; j < M; j++) {
                    Node snake = snakes.get(j);
                    if (nextNum == snake.start) {
                        nextNum = snake.end;
                    }
                }


                // 사다리 시작
                for (int j = 0; j < N; j++) {
                    Node ladder = ladders.get(j);
                    if (nextNum == ladder.start) {
                        nextNum = ladder.end;
                    }
                }

                // 뱀도 사다리도 모두 아닌 경우 -> 주사위 굴렸을 때 100 넘어가면 break
                queue.offer(new int[]{nextNum, currTime + 1});
                visited[nextNum] = true;
            }
        }
    }

    static class Node {
        int start, end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
