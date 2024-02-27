import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static Node[] count;
    static char[][] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int ans = 0;
        StringBuilder sb = new StringBuilder();
        tmp = new char[n][m];

        // 입력 받아놓고
        for (int i = 0; i < n; i++) {
            String dna = br.readLine();
            for (int j = 0; j < m; j++) {
                tmp[i][j] = dna.charAt(j);
            }
        }

        for (int i = 0; i < m; i++) {
            count = new Node[4];
            // 초기화
            count[0] = new Node('A', 0);
            count[1] = new Node('C', 0);
            count[2] = new Node('G', 0);
            count[3] = new Node('T', 0);

            for (int j = 0; j < n; j++) {
                char alphabet = tmp[j][i];
                if (alphabet == 'A') {
                    count[0].count++;
                }
                else if (alphabet == 'C') {
                    count[1].count++;
                }
                else if (alphabet == 'G') {
                    count[2].count++;
                }
                else if (alphabet == 'T') {
                    count[3].count++;
                }
            }

            Arrays.sort(count, Collections.reverseOrder());

            sb.append(count[0].c);
            ans += n - count[0].count;


        }
        System.out.println(sb);
        System.out.println(ans);
    }

    static class Node implements Comparable<Node> {
        char c;
        int count;

        public Node(char c, int count) {
            this.c = c;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }

    private static int sameDnaCount() {
        int ans = 0;
        int[] alpha = new int[4]; // a, g, c, t 순서
        // 세로 기준으로

        return ans;
    }
}