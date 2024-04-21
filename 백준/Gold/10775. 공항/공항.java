import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* logic
    - 서로소 집합 ... 부모 배열에서 역순으로 비행기 도킹 되있는지 탐색 (최대한 많은 비행기가 도킹)
    - 즉, 뒤에서 부터 탐색하면서 비어있으면 도킹시킨다?
    - union 시키는
    */
public class Main {
    static int g, p;
    static int[] airplanes;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());
        int count = 0;
        airplanes = new int[p+1];
        parents = new int[g+1];
        // 초기화
        for (int i = 1; i <= g; i++) {
            parents[i] = i;
        }
        // 비행기 게이트
        for (int i = 1; i <= p; i++) {
            airplanes[i] = Integer.parseInt(br.readLine());
        }

        // 본인이 도킹할 수 있는 게이트의 가장 최대 숫자에 일단 도킹시킨다.
        for (int i = 1; i <= p; i++) {
            int tmp = find(airplanes[i]);
            if (tmp != 0) {
                // 도킹시키고 이제 다른 비행기 못 들어오도록 처리
                union(tmp, tmp-1);
                count+=1;
            }
            // 도킹되어있는데 더 주차시키려하면 break
            else {
                break;
            }
        }
        System.out.println(count);
    }

    static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b)
            parents[b] = a;
        else
            parents[a] = b;
    }
}