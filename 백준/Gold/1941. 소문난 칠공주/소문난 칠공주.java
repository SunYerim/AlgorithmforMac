import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[][] map;
    static boolean[][] visited;
    static int answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static List<int[]> selectedList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        // 이다솜파 학생들 기준으로만 bfs를 돌립니다.
        answer = 0;

        comb(0, 0);

        System.out.println(answer);

    }

    private static void comb(int start, int count) {
        // 기저
        if (count == 7) {
            if (isValid()) {
                answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            int x = i / 5;
            int y = i % 5;
            selectedList.add(new int[]{x, y});
            comb(i + 1, count + 1);
            selectedList.remove(selectedList.size() - 1);
        }
    }

    // 7명이 인접하고 'S'가 4명 이상인지 확인합니다.
    private static boolean isValid() {
        int dasomCount = 0;
        visited = new boolean[5][5];

        // bfs
        Queue<int[]> queue = new LinkedList<>();
        queue.add(selectedList.get(0));
        visited[selectedList.get(0)[0]][selectedList.get(0)[1]] = true;
        int connectedStudent = 1;

        if (map[selectedList.get(0)[0]][selectedList.get(0)[1]] == 'S') {
            dasomCount++;
        }

        int idx = 1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int nx = curr[0];
            int ny = curr[1];

            for (int i = 0; i < 4; i++) {
                int newX = nx + dx[i];
                int newY = ny + dy[i];

                for (int[] pos : selectedList) {
                    if (pos[0] == newX && pos[1] == newY && !visited[newX][newY]) {
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                        connectedStudent++;
                        if (map[newX][newY] == 'S') {
                            dasomCount++;
                        }
                    }
                }
            }
        }
        return dasomCount >= 4 && connectedStudent == 7;
    }

}
