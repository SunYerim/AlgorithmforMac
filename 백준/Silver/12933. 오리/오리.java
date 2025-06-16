import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static char[] duck = {'q', 'u', 'a', 'c', 'k'};
    static int answer = 0, idx = 0;
    static char[] quacks;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        quacks = br.readLine().toCharArray();
        visited = new boolean[quacks.length];

        if (quacks[0] != 'q' || quacks.length % 5 != 0) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < quacks.length; i++) {
            List<Character> list = new ArrayList<>();

            for (int j = i; j < quacks.length; j++) {
                if (!visited[j] && quacks[j] == duck[idx]) {
                    idx++;
                    list.add(quacks[j]);
                    visited[j] = true;
                }
                if (idx == 5) {
                    idx = 0;
                }
            }

            if (!list.isEmpty()) {
                if (list.get(list.size() - 1) != 'k') {
                    System.out.println(-1);
                    return;
                }
                answer++;
            }
        }

        System.out.println(answer);
    }

}
