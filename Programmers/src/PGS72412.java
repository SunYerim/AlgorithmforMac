import java.util.*;

class Solution {

    public int[] solution(String[] info, String[] query) {
        List<Applicant> applicants = new ArrayList<>();

        // 지원자 정보를 파싱하여 리스트에 저장
        for (String infoString : info) {
            applicants.add(new Applicant(infoString));
        }

        int[] result = new int[query.length];

        // 모든 조건에 대한 해시 맵을 미리 계산
        // 총 4*3*3*3 = 108
        Map<String, List<Integer>> conditions = new HashMap<>();
        for (String language : new String[]{"cpp", "java", "python", "-"}) {
            for (String job : new String[]{"backend", "frontend", "-"}) {
                for (String career : new String[]{"junior", "senior", "-"}) {
                    for (String food : new String[]{"chicken", "pizza", "-"}) {
                        conditions.put(language + job + career + food, new ArrayList<>());
                    }
                }
            }
        }

        // 조건에 만족하는 지원자들의 점수를 conditions map에 추가.
        for (Applicant applicant : applicants) {
            for (String language : new String[]{applicant.language, "-"}) {
                for (String job : new String[]{applicant.job, "-"}) {
                    for (String career : new String[]{applicant.career, "-"}) {
                        for (String food : new String[]{applicant.food, "-"}) {
                            String conditionKey = language + job + career + food;
                            conditions.get(conditionKey).add(applicant.score);
                        }
                    }
                }
            }
        }

        // 점수 리스트를 오름차순 정렬 -> binary search로 개발팀이 원하는 점수 이상의 지원자 수를 세아리기 위해 .
        for (List<Integer> scores : conditions.values()) {
            Collections.sort(scores);
        }

        // 쿼리를 처리하면서 조건에 해당하는 지원자 수를 세기
        for (int i = 0; i < query.length; i++) {
            String[] queryParts = query[i].split(" ");
            // 개발팀이 원하는 조건
            String conditionKey = queryParts[0] + queryParts[2] + queryParts[4] + queryParts[6];
            // 개발팀이 원하는 최소 점수
            int score = Integer.parseInt(queryParts[7]);

            // conditionKey에 해당하는 값 없을 경우에는 빈 리스트 반환
            List<Integer> scores = conditions.getOrDefault(conditionKey, new ArrayList<>());
            int count = scores.size() - binarySearch(scores, score);
            result[i] = count;
        }

        return result;
    }


    private int binarySearch(List<Integer> scores, int target) {
        int left = 0;
        int right = scores.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (scores.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // 지원자의 정보를 저장하는 applicant 클래스
    public static class Applicant {
        String language;
        String job;
        String career;
        String food;
        int score;

        // info 정보를 받아와서, 공백 기준으로 split 처리 후 정보 저장
        public Applicant(String info) {
            String[] parts = info.split(" ");
            this.language = parts[0];
            this.job = parts[1];
            this.career = parts[2];
            this.food = parts[3];
            this.score = Integer.parseInt(parts[4]);
        }
    }
}