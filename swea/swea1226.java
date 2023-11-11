import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Stack;

public class swea1226 {
    static int[][] map;
    static int startX, startY;
    static int[] dx = {1, -1, 0, 0}; // 동서남북
    static int[] dy = {0, 0, -1, 1}; // 동서남북
    public static void main(String[] args) throws IOException {
        // 미로에서 '도달 여부'를 물어보는 거니까 dfs 사용
        // 좌표값을 stack에 넣어서 로직 실행
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 10; i++) {
            br.readLine(); // 테스트 케이스 입력 받는 용도
            map = new int[16][16];
            for (int j = 0; j < 16; j++) {

                String string = br.readLine();
                for (int k = 0; k < 16; k++) {
                    map[j][k] = string.charAt(k) - '0';
                    if (map[j][k] == 2) {
                        // 출발지 좌표 값 저장
                        startX = j;
                        startY = k;
                    }

                }
            }

            if (dfs(new Node(startX, startY, 2)) == 1) {
                System.out.println("#"+i+" 1");
            } else {
                System.out.println("#"+i+" 0");
            }

        }

    }

    public static int dfs(Node node) {
        Stack<Node> stack = new Stack<>();
        // 시작 노드를 push 한다.
        stack.push(node);

        while (!stack.isEmpty()) {
            Node next = stack.pop();

            for (int i = 0; i < 4; i++) {
                int nx = next.x + dx[i];
                int ny = next.y + dy[i];

                if (nx < 0 || nx >= 16 || ny < 0 || ny >= 16) {
                    continue;
                }
                else {
                    // 범위 내에 있으면.
                    // 길이라면 push
                    if (map[nx][ny] == 0) {
                        stack.push(new Node(nx, ny, 0));
                        map[nx][ny] = 1;
                    } else if (map[nx][ny] == 3) {
                        return 1;
                    }

                    // 마지막에 pop한 요소의 type이 3이라면 1을 반환, 아니라면 0을 반환.

                }

            }
        }
        return 0;
    }

    public static class Node {
        int x;
        int y;
        int type; // 0: 길, 1: 벽, 2: 출발, 3: 도착

        public Node (int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

    }
}
