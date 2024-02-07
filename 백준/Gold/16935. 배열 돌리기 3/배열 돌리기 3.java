/*logic
    1. switch case 문으로 1~6 입력이 들어올때 수행할 수 있는 배열 연산을 작성
    2. 이를 개별의 메서드로 작성하여 가독성을 높인다.*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[][] map;
    static int[] orders;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        orders = new int[R];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < R; i++) {
            switch(orders[i]) {
                case 1:
                    order1();
                    break;
                case 2:
                    order2();
                    break;
                case 3:
                    order3();
                    break;
                case 4:
                    order4();
                    break;
                case 5:
                    order5();
                    break;
                case 6:
                    order6();
                    break;

            }
        }


        // 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }

    }

    // 1번 연산 -> 상하 반전
    private static void order1() {
        int[][] result = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[N-i-1][j] = map[i][j];
            }
        }
        map = result;
    }

    // 2번 연산 -> 좌우 반전
    private static void order2() {
        int[][] result = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[i][M-j-1] = map[i][j];
            }
        }
        map = result;
    }


    // 3번 연산 -> 시계 방향
    private static void order3() {
        // 가로, 세로 길이까지 전부 바뀐다.
        int[][] result = new int[M][N];

        int col = N - 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[j][col] = map[i][j];
            }
            col--;
        }
        int temp = N;
        N = M;
        M = temp;

        map = result;
    }

    // 4번 연산
    private static void order4() {
        // 가로, 세로 길이까지 전부 바뀐다.
        int[][] result = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[M-j-1][i] = map[i][j];
            }
        }
        int temp = N;
        N = M;
        M = temp;

        map = result;
    }


    // 5번 연산 -> 4분면 이동
    private static void order5() {
        int[][] result = new int[N][M];

        int midN = N / 2;
        int midM = M / 2;

        // 1 -> 2
        for (int i = 0; i < midN; i++) {
            for (int j = 0; j < midM; j++) {
                result[i][midM+j] = map[i][j];
            }
        }

        // 2 -> 3
        for (int i = 0; i < midN; i++) {
            for (int j = midM; j < M; j++) {
                result[midN+i][j] = map[i][j];
            }
        }

        // 3 -> 4
        for (int i = midN; i < N; i++) {
            int col = 0;
            for (int j = midM; j < M; j++, col++) {
                result[i][col] = map[i][j];
            }
        }

        // 4 -> 1
        int row = 0;
        for (int i = midN; i < N; i++, row++) {
            for (int j = 0; j < midM; j++) {
                result[row][j] = map[i][j];
            }
        }
        map = result;
    }


    // 6번 연산
    private static void order6() {
        int[][] result = new int[N][M];

        int midN = N / 2;
        int midM = M / 2;

        // 1 -> 4
        for (int i = 0; i < midN; i++) {
            for (int j = 0; j < midM; j++) {
                result[midN + i][j] = map[i][j];
            }
        }

        // 4 -> 3
        for (int i = midN; i < N; i++) {
            for (int j = 0; j < midM; j++) {
                result[i][midM + j] = map[i][j];
            }
        }

        // 3 -> 2
        int row = 0;
        for (int i = midN; i < N; i++, row++) {
            for (int j = midM; j < M; j++) {
                result[row][j] = map[i][j];
            }
        }

        // 2 -> 1
        for (int i = 0; i < midN; i++) {
            int col = 0;
            for (int j = midM; j < M; j++, col++) {
                result[i][col] = map[i][j];
            }
        }
        map = result;
    }
}