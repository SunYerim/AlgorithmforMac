
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static HashMap<String, Boolean> company;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        company = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String type = st.nextToken();
            // enter일때
            if (type.equals("enter")) {
                company.put(name, true);
            }
            else if (type.equals("leave")) {
                // leave일때
                company.replace(name, false);
            }

        }

        // hashmap -> 배열
        Object[] arrKey = company.keySet().toArray();
        Arrays.sort(arrKey, Collections.reverseOrder());

        for (Object s : arrKey) {
            if (company.get(s)) {
                System.out.println(s);
            }
        }
    }
}
