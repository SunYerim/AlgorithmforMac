
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static HashMap<String, Integer> nameToId = new HashMap<>();
    static HashMap<Integer, String> idToName = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            nameToId.put(name, i);
            idToName.put(i, name);
        }

        for (int i = 1; i <= M; i++) {
            String input = br.readLine();
            if (Character.isDigit(input.charAt(0))) {
                int idx = Integer.parseInt(input);
                sb.append(idToName.get(idx));
                sb.append("\n");
            }
            else {
                sb.append(nameToId.get(input));
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());

    }

}
