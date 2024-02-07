import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    static int T, N, maxCount, startRoom;
    static int[][] map;
    static boolean[][] isVisited;
    static Stack<Node> stack;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            map = new int[N+1][N+1];
            isVisited = new boolean[N+1][N+1];
            maxCount = 0;
            startRoom = 1;
            StringTokenizer st = null;

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    dfs(i, j, 1, map[i][j]);
                }
            }

            System.out.println("#"+tc+" "+startRoom+" "+maxCount);


        }
    }

    // 일단 dfs로
    // 최댓값 갱신 메소드 -> 배열로 빼서 하는 게 나을지
    private static void dfs(int startX, int startY, int count, int room) {
        //stack = new Stack<Node>();
        //stack.push(new Node(startX, startY)); // 넣고 시작
        isVisited[startX][startY] = true;
        // 상하좌우 탐색 시작
        for (int i = 0; i < 4; i++) {
            int nx = startX + dx[i];
            int ny = startY + dy[i];
            // 범위 내에 있으면서 이동하려는 칸이 내 칸보다 1 더 클 때
            if (nx >= 1 && nx <= N && ny >= 1 && ny <= N && map[nx][ny] - map[startX][startY] == 1) {
                dfs(nx, ny, count + 1, room);
            }
        }
        if (count > maxCount) {
            maxCount = count;
            startRoom = room;
        } else if (count == maxCount) {
            startRoom = Math.min(startRoom, room);
        }
        isVisited[startX][startY] = false;

    }

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}