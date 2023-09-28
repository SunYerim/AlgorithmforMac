// # 처리 유의 => # 붙어있는 문자를 다른 문자로 치환해준다.
import java.util.*;
class song {
    public String solution(String m, String[] musicinfos) {
        List<Song> songlist = new ArrayList<Song>();

        // # 포함 문자 치환
        m = replaceString(m);

        for (int i = 0; i < musicinfos.length; i++){
            String[] musicinfo = musicinfos[i].split(","); // 구분자 기준으로 나눔.
            // 재생 시간 분단위로
            int[] start = convertTimeToMinutes(musicinfo[0]);
            int[] end = convertTimeToMinutes(musicinfo[1]);

            int totalTime = (end[0] - start[0]) * 60 + (end[1] - start[1]);

            Song song = new Song();
            String title = musicinfo[2];
            String contents = replaceString(musicinfo[3]);

            song.title = title;
            song.time = totalTime;

            String total = "";

            int div = totalTime / contents.length();

            for (int j = 0; j < div; j++) {
                total += contents;
            }

            if (!"".equals(total) && totalTime % contents.length() != 0)
                total += contents.substring(0, totalTime % contents.length());
            if ("".equals(total))
                total = contents.substring(0, totalTime);

            song.lyrics = total;

            songlist.add(song);
        }
        int maxTime = 0;
        String maxTitle = "";

        for (int i = 0; i < songlist.size(); i++){
            Song song = songlist.get(i);

            if (song.lyrics.contains(m)) {
                if (maxTime < song.time) {
                    maxTitle = song.title;
                    maxTime = song.time;
                }
            }
        }
        if (!"".equals(maxTitle))
            return maxTitle;
        return "(None)";
    }

    public String replaceString(String s) {
        return s.replaceAll("A#", "V").replaceAll("C#", "W").replaceAll("D#", "X").replaceAll("F#", "Y").replaceAll("G#", "Z");
    }

    // 시간: 분 -> 분으로 변환해주는 함수
    private int[] convertTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return new int[]{hours, minutes};
    }

    class Song {
        int time; // 시간
        String title; // 제목
        String lyrics; // 가사
    }

}

