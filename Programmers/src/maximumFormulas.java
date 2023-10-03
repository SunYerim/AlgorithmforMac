import java.util.*;

class maximumFormulas {
    public long solution(String expression) {
        long answer = 0;

        // 연산자 우선순위 조합 리스트로 만들어줌.
        List<List<String>> operatorPriority = new ArrayList<>();
        operatorPriority.add(Arrays.asList("*", "+", "-"));
        operatorPriority.add(Arrays.asList("*", "-", "+"));
        operatorPriority.add(Arrays.asList("+", "*", "-"));
        operatorPriority.add(Arrays.asList("+", "-", "*"));
        operatorPriority.add(Arrays.asList("-", "*", "+"));
        operatorPriority.add(Arrays.asList("-", "+", "*"));

        for (List<String> priority : operatorPriority) {
            // 숫자와 연산자를 파싱하여 리스트에 저장
            List<String> tokens = tokenize(expression);
            // 우선순위에 따라 연산을 수행하고 결과를 저장
            long result = calculateWithPriority(tokens, priority);
            // 최댓값 갱신
            answer = Math.max(answer, Math.abs(result));
        }

        return answer;
    }

    // 수식을 숫자와 연산자로 분리하는 메서드
    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-' || c == '*') {
                tokens.add(current.toString());
                current = new StringBuilder();
                tokens.add(String.valueOf(c));
            } else {
                current.append(c);
            }
        }

        tokens.add(current.toString());
        return tokens;
    }

    // 우선순위에 따라 연산을 수행하는 메서드
    private long calculateWithPriority(List<String> tokens, List<String> priority) {
        for (String op : priority) {
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).equals(op)) {
                    long num1 = Long.parseLong(tokens.get(i - 1));
                    long num2 = Long.parseLong(tokens.get(i + 1));
                    long result = performOperation(num1, num2, op);
                    // 연산에 사용된 숫자와 연산자를 제거하고 결과를 삽입
                    tokens.remove(i - 1);
                    tokens.remove(i - 1);
                    tokens.remove(i - 1);
                    tokens.add(i - 1, String.valueOf(result));
                    i--; // 인덱스 조정
                }
            }
        }

        return Long.parseLong(tokens.get(0));
    }

    // 두 숫자와 연산자를 이용해 연산을 수행하는 메서드
    private long performOperation(long num1, long num2, String operator) {
        if (operator.equals("*")) {
            return num1 * num2;
        } else if (operator.equals("+")) {
            return num1 + num2;
        } else {
            return num1 - num2;
        }
    }
}
