import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long a, b;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        dfs(4);
        dfs(7);

        System.out.println(answer);

    }

    public static void dfs(long num) {
        if (num > b) {
            return;
        }
        if (num >= a) {
//            System.out.println(num);
            answer++;
        }

        dfs(num * 10 + 4);
        dfs(num * 10 + 7);
    }

}
