import java.util.*;

// LRU 알고리즘 구현 문제
class LRU {
    public int solution(int cacheSize, String[] cities) {
        // 캐시 배열 사이즈가 0인 경우
        if (cacheSize == 0)
            return cities.length * 5;
        int answer = 0;
        // 캐시 연결리스트 or 배열 생성
        Queue<String> cache = new LinkedList<>();

        for (int i = 0; i < cities.length; i++) {
            // 대소문자 구분 없음
            String city = cities[i].toUpperCase();
            // 캐시에 해당 도시가 존재하면? -> hit 아니라면 -> miss
            if (cache.remove(city)) {
                cache.add(city);
                answer += 1;
            } else {
                if (cache.size() == cacheSize) {
                    cache.poll(); // 캐시의 맨 앞의 요소를 제거
                    cache.add(city); // 현재 도시를 추가
                } else {
                    cache.add(city);
                }
                answer += 5;
            }
        }

        return answer;
    }
}