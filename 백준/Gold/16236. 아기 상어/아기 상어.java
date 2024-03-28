import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count_eat = 0; // 물고기를 먹은 횟수
    static int shark_size = 2; // 아기상어 초기 크기
    static int total_dist = 0;
    static int babyShark_x = 0, babyShark_y = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
    }

    private static Shark bfs(Shark babyShark) {
        Queue<Shark> queue = new ArrayDeque<>();
        ArrayList<Shark> list = new ArrayList<>();
        queue.add(babyShark);
        visited[babyShark.x][babyShark.y] = true;

         while (!queue.isEmpty()) {
             Shark curr = queue.poll();
             for (int i = 0; i < 4; i++) {
                 int nx = curr.x + dx[i];
                 int ny = curr.y + dy[i];
                 if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                 if (visited[nx][ny]) continue;
                 if (curr.size < map[nx][ny]) continue; // 나보다 더 큰 물고기면 continue
                 visited[nx][ny] = true;
                 queue.add(new Shark(nx, ny, curr.size, curr.dist+1));
                 // 먹을 수 있는 물고기면 list에 넣는다.
                 if (map[nx][ny] != 0 && curr.size > map[nx][ny]) {
                     list.add(new Shark(nx, ny, curr.size, curr.dist+1));
                 }
             }

         }
         // 먹을 물고기의 위치를 정한다.
         Collections.sort(list);
         if (!list.isEmpty()) return list.get(0);
         else return null;
    }

    private static boolean foundSmallFish(int size) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 || map[i][j] == 9) continue;
                if (map[i][j] < size) return true;
            }
        }
        return false;
    }

    private static void solve() {
        // 아기상어의 첫 위치를 찾는다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 9) {
                    babyShark_x = i;
                    babyShark_y = j;
                    map[i][j] = 0; // 0으로 바꿔줘야 상어가 이동할 수 있는 칸이 된다.
                }
            }
        }

        // 나보다 작은 물고기가 남아있으면 계속해서 탐색한다.
        while (foundSmallFish(shark_size)) {
            visited = new boolean[n][n]; // 방문배열 초기화해주어야함.
            Shark move = bfs(new Shark(babyShark_x, babyShark_y, shark_size, total_dist));
            // 만약 물고기를 못 찾았다.
            if (move == null) {
                System.out.println(total_dist);
                return;
            }

            // 물고기를 찾았다 -> 아기 상어 위치를 물고기 위치로 이동시킨다.
            babyShark_x = move.x;
            babyShark_y = move.y;

            // 그 물고기 칸은 0으로 갱신
            map[babyShark_x][babyShark_y] = 0;
            count_eat++; // 물고기 먹은 수 증가시키는데,
            if (count_eat == move.size) {
                // 먹은 갯수랑 상어 사이즈가 동일하면
                shark_size = move.size + 1;
                count_eat = 0; //먹은 횟수 초기화
            } else {
                shark_size = move.size; // 이동거리를 업데이트
            }
            total_dist = move.dist;

        }
        System.out.println(total_dist);
    }


    static class Shark implements Comparable<Shark> {
        private int x, y, size, dist;
        public Shark(int x, int y, int size, int dist) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.dist = dist;
        }

        // 1. 가장 가까운 물고기
        // 2. 가장 위에 있는 물고기
        // 3. 가장 왼쪽에 있는 물고기

        // 비교조건 그대로 구현
        @Override
        public int compareTo(Shark o) {
            if (this.dist != o.dist) {
                return this.dist - o.dist;
            } else if (this.x != o.x) {
                return this.x - o.x;
            } else {
                return this.y - o.y;
            }
        }
    }


}