import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int p;
    static ArrayList<Integer> list;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        p = Integer.parseInt(br.readLine());
        int total = 0;

        // p번동안
        for (int i = 1; i <= p; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            sb.append(n).append(" ");

            int[] heights = new int[20];
            for (int j = 0; j < 20; j++) {
                heights[j] = Integer.parseInt(st.nextToken());
            }

            int moves = 0;
            ArrayList<Integer> list = new ArrayList<>();

            for (int j = 0; j < 20; j++) {
                int num = heights[j];
                int k;

                for (k = 0; k < list.size(); k++) {
                    if (list.get(k) > num) {
                        break;
                    }
                }

                list.add(k, num);

                moves += list.size() - 1 -k;
            }

            sb.append(moves).append("\n");
        }
        System.out.println(sb.toString());
    }
}