class loginSuccess {
    public String solution(String[] id_pw, String[][] db) {
        String answer = "";
        String id = id_pw[0];
        String pw = id_pw[1];
        boolean found = false; // 로그인 정보를 찾았는지 여부를 확인하는 플래그

        for (int i = 0; i < db.length; i++) {
            if (id.equals(db[i][0]) && pw.equals(db[i][1])) {
                answer = "login";
                found = true; // 로그인 정보를 찾음
                break;
            } else if (id.equals(db[i][0]) && !pw.equals(db[i][1])) {
                answer = "wrong pw";
                found = true; // 아이디는 일치하나 비밀번호가 틀림
                break;
            }
        }

        if (!found) {
            answer = "fail"; // 로그인 정보를 찾지 못함
        }

        return answer;
    }
}
