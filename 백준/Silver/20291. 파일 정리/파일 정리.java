
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
    static int n;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        // HashMap 선언
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String txt = br.readLine();
            // 분리합니다.
            String[] files = txt.split("\\.");
            String extensions = files[1];
            map.put(extensions, map.getOrDefault(extensions, 0) + 1);
        }

        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);

        for (String key : keySet) {
            sb.append(key).append(" ").append(map.get(key)).append("\n");
        }

        System.out.println(sb.toString());
    }

}
