
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* logic
    1. 칸 안에 있거나 터널이 연결되있을 동안은 계속 진행된다.
    2. bfs 메서드 실행
    3. 그럼 터널이 있는 모든 지점에서 bfs를 실행했을 때, l이되면 count를 증가시키는 방식으로
    4. 상 -> 1, 2, 5, 6 / 하 -> 1, 2, 4, 7 / 좌 -> 1, 3, 4, 5 / 우 -> 1, 3, 6, 7
    5. 제한 시간이 l이다 !!*/
public class Solution {
    static int T, n, m, r, c, l;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++){
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            map = new int[n][m];
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 맨홀뚜껑이 있는 위치는 r, c이다.
            int ans = bfs(r, c);

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs(int x, int y) {
        int total = 1; // 맨홀뚜껑도 포함한다.
        visited[x][y] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y, 1));

        // 큐가 비어있지 않은 동안
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int tmpX = tmp.x;
            int tmpY = tmp.y;
            int currentTime = tmp.time;

            if (currentTime == l) {
                return total;
            }

            // 4방향으로 탐색을 하는데 조건이 걸린다.-> 상하좌우 이동 유무
            if (map[tmpX][tmpY] == 1) {
                for (int i = 0; i < 4; i++) {
                    int nx = tmpX + dx[i];
                    int ny = tmpY + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] != 0) {
                        // 상
                        if (i == 0) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                        // 하
                        if (i == 1) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                        // 좌
                        if (i == 2) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                        // 우
                        if (i == 3) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                    }
                }
            } if (map[tmpX][tmpY] == 2) {
                for (int i = 0; i < 2; i++) {
                    int nx = tmpX + dx[i];
                    int ny = tmpY + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] != 0) {
                        // 상
                        if (i == 0) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                        // 하
                        if (i == 1) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                    }
                }
            } if (map[tmpX][tmpY] == 3) {
                for (int i = 2; i < 4; i++) {
                    int nx = tmpX + dx[i];
                    int ny = tmpY + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] != 0) {
                        // 좌
                        if (i == 2) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                        // 우
                        if (i == 3) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                    }
                }

            } if (map[tmpX][tmpY] == 4) {
                for (int i = 0; i < 4; i++) {
                    int nx = tmpX + dx[i];
                    int ny = tmpY + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] != 0) {
                        // 상
                        if (i == 0) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                        // 우
                        if (i == 3) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                    }
                }

            } if (map[tmpX][tmpY] == 5) {
                for (int i = 0; i < 4; i++) {
                    int nx = tmpX + dx[i];
                    int ny = tmpY + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] != 0) {
                        // 하
                        if (i == 1) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                        // 우
                        if (i == 3) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                    }
                }

            } if (map[tmpX][tmpY] == 6) {
                for (int i = 1; i < 3; i++) {
                    int nx = tmpX + dx[i];
                    int ny = tmpY + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] != 0) {
                        // 하
                        if (i == 1) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                        // 좌
                        if (i == 2) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                    }
                }

            } if (map[tmpX][tmpY] == 7) {
                for (int i = 0; i < 3; i++) {
                    int nx = tmpX + dx[i];
                    int ny = tmpY + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] != 0) {
                        // 상
                        if (i == 0) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                        // 좌
                        if (i == 2) {
                            if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
                                queue.offer(new Node(nx, ny, currentTime+1));
                                visited[nx][ny] = true;
                                total++;
                            }
                        }
                    }
                }
            }
        }
        return total;
    }

    static class Node {
        int x, y, time;
        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}