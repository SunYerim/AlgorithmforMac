import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    // 괄호 넣는 위치 조합 사용 문제
    // 여는 괄호와 닫는 괄호 사이에 괄호가 들어가면 안 된다.
    static int n, ans = Integer.MIN_VALUE;
    static String expression;
    static int[] numbers;
    static char[] operators;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        expression = br.readLine();
        // 연산자 배열
        numbers = new int[n/2 + 1];
        // 피연산자 배열
        operators = new char[n/2];

        int idx1 = 0;
        int idx2 = 0;

        // 짝수 번째 인덱스는 피연산자, 홀수번째 인덱스는 연산자
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                numbers[idx1++] = expression.charAt(i) - '0';
            } else {
                operators[idx2++] = expression.charAt(i);
            }
        }

        // 괄호를 1개부터 numbers 배열의 0이 아닌 갯수 / 2 만큼 추가할 수 있음.
        for (int i = 0; i <= (n/2 + 1)/2; i++) {
            boolean[] visited = new boolean[n/2+1];
            dfs(visited, i, 0, 1);
        }
        System.out.println(ans);
    }

    // 연산자를 r개 고르는 메서드
    private static void dfs(boolean[] visited, int n, int cnt, int idx) {
        if (cnt == n) {
            int i = 1;
            int res = numbers[0];

            while (i < numbers.length) {
                int m = 0;

                if (visited[i]) {
                    switch(operators[i]) {
                        case '+':
                            m = numbers[i] + numbers[i+1];
                            break;
                        case '-':
                            m = numbers[i] - numbers[i+1];
                            break;
                        case '*':
                            m = numbers[i] * numbers[i+1];
                            break;
                    }
                }
                else
                    m = numbers[i];
                switch (operators[i-1]) {
                    case '+':
                        res += m;
                        break;
                    case '-':
                        res -= m;
                        break;
                    case '*':
                        res *= m;
                        break;

                }

                if (visited[i])
                    i += 2;
                else
                    i++;
            }

            ans = Math.max(ans, res);
            return;
        }

        for (int i = idx; i < numbers.length-1; i++) {
            visited[i] = true;
            dfs(visited, n, cnt+1, i+2);
            visited[i] = false;
        }

    }

}