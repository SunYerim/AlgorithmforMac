import java.awt.color.ICC_ColorSpace;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class swea1227 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int startX, startY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int T = 1; T <= 10; T++) {
            br.readLine(); // 테스트케이스 입력받기 위함.
            map = new int[100][100];
            visited = new boolean[100][100];

            // map 입력
            for (int i = 0; i < 100; i++) {
                String string = br.readLine();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = string.charAt(j) - '0';

                    if (map[i][j] == 2) {
                        startX = i;
                        startY = j;
                    }
                }
            }

            if (dfs(new Node(startX, startY, 2)) == 1) {
                System.out.println("#"+T+" "+1);
            } else {
                System.out.println("#"+T+" "+0);
            }
        }
    }

    public static int dfs(Node start) {
        Stack<Node> stack = new Stack<>();
        // 일단 push 후 visited 처리
        stack.push(start);

        while (!stack.isEmpty()) {
            Node next = stack.pop();
            for (int i = 0; i < 4; i++) {
                // 4방향 탐색
                int nx = dx[i] + next.x;
                int ny = dy[i] + next.y;

                if (nx >= 0 && nx < 100 && ny >= 0 && ny < 100) {
                    if (map[nx][ny] == 0) {
                        stack.push(new Node(nx, ny, 0));
                        map[nx][ny] = 1;
                    } else if (map[nx][ny] == 3) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    // node클래스 생성
    static class Node {
        int x;
        int y;
        int type; // 길, 벽, 출발지, 도착지
        public Node (int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
