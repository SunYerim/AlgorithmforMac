import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        int ans = 0;

        // 높이가 낮아지는 순간

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
            }

            if (!stack.isEmpty() && stack.peek() == y) {
                continue;
            }

            if (y > 0) {
                stack.push(y);
                ans++;
            }



        }

        System.out.println(ans);


    }

}
