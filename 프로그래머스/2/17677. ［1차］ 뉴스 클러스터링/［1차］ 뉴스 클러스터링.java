import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0; // 유사도
        double gyo = 0; // 교집합 개수
        double hab = 0; // 합집합 개수

        // 문자열에서 두 글자씩 자르고, 영문자 이외의 문자가 없는 요소를 대문자로 변환하여 배열에 추가
        String[] strArr1 = splitAndFilter(str1);
        String[] strArr2 = splitAndFilter(str2);

        // 교집합 개수 구하기
        List<String> intersection = new ArrayList<>();
        for (int i = 0; i < strArr1.length; i++) {
            for (int j = 0; j < strArr2.length; j++) {
                if (strArr1[i].equals(strArr2[j])) {
                    intersection.add(strArr1[i]);
                    strArr2[j] = null; // 중복을 고려하여 strArr2에서 해당 요소를 null로 표시
                    gyo += 1;
                    break;
                }
            }
        }

        // 합집합 개수 구하기
        List<String> union = new ArrayList<>();
        union.addAll(Arrays.asList(strArr1));

        for (String element : strArr2) {
            if (element != null) {
                union.add(element);
            }
        }

        hab = union.size();

        if (hab == 0) { // 합집합이 0인 경우 예외 처리
            answer = 65536;
        } else {
            answer = (int)((gyo / hab) * 65536);
        }

        return answer;
    }

    // 문자열을 두 글자씩 자르고, 영문자 이외의 문자가 없는 요소를 대문자로 변환하여 배열에 추가하는 함수
    private static String[] splitAndFilter(String input) {
        List<String> resultList = new ArrayList<>();
        int length = input.length();

        for (int i = 0; i < length - 1; i++) {
            String twoChars = input.substring(i, i + 2);
            if (twoChars.matches("[a-zA-Z]+")) {
                resultList.add(twoChars.toUpperCase()); // 대문자 소문자 차이 무시 -> 전체 대문자
            }
        }

        return resultList.toArray(new String[0]);
    }
}
