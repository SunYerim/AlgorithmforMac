import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for (int i = 0; i < n - 1; i++) {
            String s = br.readLine();
            map.put(s, map.getOrDefault(s, 0) - 1);
        }

        // 남아있는 것
        for (String key : map.keySet()) {
            if (map.get(key) != 0) {
                System.out.println(key);
                break;
            }
        }
    }

}
