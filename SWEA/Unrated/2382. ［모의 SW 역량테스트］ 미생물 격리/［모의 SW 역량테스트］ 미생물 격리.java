import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int T, n, m, k, ans;
    static Node[][] map, map2;
    static Queue<Node> queue;
    static int[] dx = {-1, 0, 1, 0}; // 상좌하우
    static int[] dy = {0, -1, 0, 1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            ans = 0;
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken()); // m시간 후 미생물의 갯수
            k = Integer.parseInt(st.nextToken()); // 최초 배치되어있는 미생물 군집의 갯수

            map = new Node[n][n];
            map2 = new Node[n][n];
            queue = new LinkedList<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                if (direction == 1) direction = 0;
                else if (direction == 3) direction = 1;
                else if (direction == 4) direction = 3;

                map[x][y] = new Node(x, y, count, 0, direction, count);
                queue.add(new Node(x, y, count, direction, 0, count));
            }

            disperation();


            sb.append(count()).append("\n");
        }
        System.out.println(sb.toString());
    }

    // 매 시간마다 실행하는 메서드 -> ans 갱신
    private static void disperation() {
        int nx, ny, ndir, ntime, idx=0, nnum, nmaxNum;
        // 한 칸으로 여러 미생물이 이동하는 경우 .... -> 매시간 이동시키는 과정에서 충돌하는 경우 발생 (map하나로 불가능할듯 -> 2개 사용)
        Node[][] prevMap, currMap;

        while (!queue.isEmpty()) {
            // 시간에 따른 map순서 결정 ( 매 순서마다 바꾼다.)
            if (idx % 2 == 0) {
                prevMap = map; currMap = map2;
            } else {
                prevMap = map2; currMap = map;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (curr.time >= m) return;
                if (curr.count != prevMap[curr.x][curr.y].count) continue;

                prevMap[curr.x][curr.y] = null;

                nx = curr.x + dx[curr.dir];
                ny = curr.y + dy[curr.dir];
                nnum = curr.count;
                ndir = curr.dir;
                ntime = curr.time + 1;
                nmaxNum = curr.maxCount;

                // 이동하는 방향이 약품이 쳐져있는 구역일때
                if (nx == 0 || ny == 0 || nx == n-1 || ny == n-1) {
                    ndir = (ndir + 2) % 4; // 방향 반대로
                    nnum /= 2;
                    nmaxNum = nnum;
                    if (nnum == 0) continue;
                }
                // 이동하려는 방향으로 1개 이상의 미생물 군집이 들어오는 경우
                else if (currMap[nx][ny] != null) {
                    // 기존에 있던 미생물이 더 크다면
                    if (currMap[nx][ny].maxCount > nnum) {
                        ndir = currMap[nx][ny].dir;
                        nmaxNum = currMap[nx][ny].maxCount;
                    }
                    nnum += currMap[nx][ny].count;
                } else {
                    // 아무것도 없을 때
                    nmaxNum = nnum;
                }

                currMap[nx][ny] = new Node(nnum, ndir, ntime, nmaxNum);
                queue.offer(new Node(nx, ny, nnum, ndir, ntime, nmaxNum));
            }
            idx++;
        }


    }

    private static long count() {
        Node[][] tmp;
        // 시간에 따라
        if (m % 2 == 0) {
            tmp = map;
        } else {
            tmp = map2;
        }
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tmp[i][j] != null) {
                    count += tmp[i][j].count;
                }
            }
        }
        return count;
    }

    static class Node {
        private int x, y, count, dir, time, maxCount;

        public Node(int count ,int dir, int time, int maxCount) {
            this.count = count;
            this.dir = dir;
            this.time = time;
            this.maxCount = maxCount;
        }

        public Node (int x, int y, int count, int dir, int time, int maxCount) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.dir = dir;
            this.time = time;
            this.maxCount = maxCount;
        }
    }
}