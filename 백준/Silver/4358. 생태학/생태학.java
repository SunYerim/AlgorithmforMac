import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;


public class Main {

    static HashMap<String ,Integer> trees;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        trees =  new HashMap<>();
        double cnt = 0;
        String treeName;

        while ((treeName = br.readLine()) != null && !treeName.isEmpty()) {
            trees.put(treeName, trees.getOrDefault(treeName, 0) + 1);
            cnt++;
        }

        List<String> keySet = new ArrayList<>(trees.keySet());
        Collections.sort(keySet);

        for (String key : keySet) {
            double percent = (double) trees.get(key) * 100 / cnt;
            sb.append(key).append(" ").append(String.format("%.4f", percent)).append("\n");
        }

        System.out.print(sb.toString());

    }

}
