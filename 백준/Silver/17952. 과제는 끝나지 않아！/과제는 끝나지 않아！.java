import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		Stack<Node> stack = new Stack<>();
		int ans = 0;
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 0이면 스택에서 뽑아와서 남은 작업을 실행
			int a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				// 1초 걸리는 작업이라면 바로 작업 완료되므로 정답에 더해주면 됨.
				if (c-1 == 0) {
					ans += b;
				}
				else {
					// 작업시간이 남았다면 stack에 추가해준다.
					stack.push(new Node(b, c-1));
				}
				
			}
			if (a == 0) {
				if (!stack.isEmpty()) {
                    Node jakup = stack.peek();
                    if (jakup.time == 1) {
                        ans += jakup.score;
                        stack.pop();
                    } else {
                        jakup.time--;
                    }
                }
			}
			
		}
		System.out.println(ans);
	}
	static class Node{
		private int score;
		private int time;
		
		public Node(int score, int time) {
			this.score = score;
			this.time = time;
		}
		
	}

}