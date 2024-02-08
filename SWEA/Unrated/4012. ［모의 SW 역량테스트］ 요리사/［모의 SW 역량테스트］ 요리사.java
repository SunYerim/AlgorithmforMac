import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, R; // T = R : A음식에 사용할 식재료 수
    static int[][] S; // 시너지 정보
    static int[] numbers; // N개의 식재료 중 A 음식이 사용할 식재료 번호
    static int min; // 두 음식 간의 맛의 차이 최솟값
    private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			
			N = Integer.parseInt(in.readLine());
			
			R = N / 2;
			
			S = new int[N][N];
			
			numbers = new int[R];
			
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(split[j]);
				}
			}
			
			comb(0, 0);
			sb.append(min).append("\n");
		}
		
		
		System.out.println(sb);
	}

	private static void comb(int cnt, int start) {
		
		// 기저 부분
		if (cnt == R) {
			// A음식에 사용할 재료번호와 B음식에 사용할 재료번호 저장
			int[] listA = new int[R];
			int[] listB = new int[R];
			
			boolean[] isSelected = new boolean[N]; // A음식 식재료 체크
			int indexA = 0;
			for (int i = 0; i < R; i++) {
				listA[indexA++] = numbers[i];
				isSelected[numbers[i]] = true;
			}
			
			// A음식에 쓰고 남은 식재료를 B음식 목록에 저장
			int indexB = 0;
			for (int i = 0; i < N; i++) {
				if (!isSelected[i]) {
					listB[indexB++] = i;
				}
			}
			
			int A = 0; // A음식 시너지의 합
			int B = 0; // B음식 시너지의 합
			
			for (int i = 0; i < R - 1; i++) {
				for (int j = i; j < R; j++) {
					A += S[listA[i]][listA[j]] + S[listA[j]][listA[i]];
					B += S[listB[i]][listB[j]] + S[listB[j]][listB[i]];
				}
			}
			
			int abs = Math.abs(A - B);
			
			if (abs < min) {
				min = abs;
			}
			
			return;
		}
	
		// 유도 부분
		for (int i = start; i < N; i++) {
			numbers[cnt] = i;
			
			comb(cnt + 1, i + 1);
		}
		
	}
	
}