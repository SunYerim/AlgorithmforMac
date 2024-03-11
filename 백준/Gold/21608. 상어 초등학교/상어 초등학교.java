import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    // 순서
    static int[] students;
    static HashSet<Integer>[] hashset;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        hashset = new HashSet[N*N+1];
        students = new int[N*N+1];

        for (int i = 1; i <= N*N; i++) {
            hashset[i] = new HashSet<>();
        }

        for (int i = 1; i <= N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            students[i] = num;
            // 친한친구 목록
            for (int j = 0; j < 4; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                hashset[num].add(tmp);
            }
        }

        // 초기에는 고정
        map[1][1] = students[1];

        for (int i = 2; i <= N*N; i++) {
            setStudent(i);
        }


        // 만족도
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0 ; j < N; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (!isIn(nx, ny)) continue;
                    if (hashset[map[i][j]].contains(map[nx][ny])) cnt++;
                }
                if (cnt == 1) sum += 1;
                else if (cnt == 2) sum += 10;
                else if (cnt == 3) sum += 100;
                else if (cnt == 4) sum += 1000;
            }
        }
        System.out.println(sum);

    }

    // 범위 내에 존재하는지
    private static boolean isIn(int x, int y) {
        return (x>=0 && y>=0 && x < N && y < N);
    }

    // 인접한 칸
    private static Student getStudent(int x, int y, int tmp) {
        int cnt = 0;
        int emptyCnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isIn(nx, ny)) continue;
            // 친한친구
            if (hashset[students[tmp]].contains(map[nx][ny])) cnt++;
            if (map[nx][ny] == 0) emptyCnt++;
        }

        return new Student(x, y, cnt, emptyCnt);
    }


    private static void setStudent(int tmp) {
        // 정렬
        PriorityQueue<Student> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) continue;
                pq.add(getStudent(i, j, tmp));
            }
        }

        Student selected = pq.poll();

        map[selected.x][selected.y] = students[tmp];
    }

    // 학생 객체화 -> 필요한 정렬조건 적용
    static class Student implements Comparable<Student> {
        int x, y, cnt, emptyCnt;

        public Student(int x, int y, int cnt, int emptyCnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.emptyCnt = emptyCnt;
        }
        @Override
        public int compareTo(Student o) {
            if (this.cnt == o.cnt) {
                if (this.emptyCnt == o.emptyCnt) {
                    if (this.x == o.x) {
                        // 열의 오름차순
                        return this.y - o.y;
                    }
                    // 행의 오름차순
                    return this.x - o.x;
                }
                // 비어있는 자리 수 내림차순
                return o.emptyCnt - this.emptyCnt;
            }
            // 친한 친구 수 내림차순
            return o.cnt - this.cnt;
        }
    }

}