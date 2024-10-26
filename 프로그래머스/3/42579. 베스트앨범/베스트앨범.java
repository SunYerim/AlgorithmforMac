import java.util.*;

class Solution {
    static HashMap<String, Integer> num;
    static ArrayList<Integer> tmp;
    static HashMap<String, ArrayList<Song>> music;
    public int[] solution(String[] genres, int[] plays) {
        tmp = new ArrayList<>();
        num = new HashMap<>(); // 장르별 총 개수
        music = new HashMap<>(); // 장르에 속하는 노래 및 재생횟수
        for (int i = 0; i < plays.length; i++) {
            String genre = genres[i];
            int playCount = plays[i];
            
            // 장르별 재생 횟수 업데이트
            num.put(genre, num.getOrDefault(genre, 0) + playCount);
            
            // 장르별 노래 리스트 업데이트
            if (!music.containsKey(genre)) {
                music.put(genre, new ArrayList<>());
            }
            music.get(genre).add(new Song(i, playCount));
        }
            // 장르별 총 재생횟수 기준으로 내림차순 정렬
            List<String> sortedGenres = new ArrayList<>(num.keySet());
            sortedGenres.sort((a, b) -> num.get(b) - num.get(a));
                        
            for (String genre2 : sortedGenres) {
                List<Song> songs = music.get(genre2);
                Collections.sort(songs);
                
                tmp.add(songs.get(0).idx);
                if (songs.size() > 1) {
                    tmp.add(songs.get(1).idx);
                }
            
        }
        
        int[] answer = new int[tmp.size()];
        
        for (int i = 0; i < tmp.size(); i++) {
            answer[i] = tmp.get(i);
        }
        
        
        return answer;
    }
    
    static class Song implements Comparable<Song>{
        int idx;
        int plays;
        
        public Song(int idx, int plays) {
            this.idx = idx;
            this.plays = plays;
        }
        
        @Override
        public int compareTo(Song o) {
            if (this.plays == o.plays) {
                return this.idx - o.idx;
            }
            return o.plays - this.plays;
        }
    }
}