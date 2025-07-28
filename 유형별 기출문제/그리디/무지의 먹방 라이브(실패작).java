class Solution {

    public int solution(int[] food_times, long k) {
        int idx = food_times.length;
        int answer = 0;
        int loop = 0;
        int cnt = 0;

        int i = 0;
        while (cnt < k) {
            if (food_times[i % idx] == 0) {
                i++; //음식이 0이면 다음 음식으로 pass
                loop++;//무한루프 체크 용 변수

                if (loop >= idx) { //음식의 개수보다 이전 if문에서 증가된 체크용 변수가 더 크다면 이젠 음식이 없다는 뜻으로 -1 반환
                    return -1;
                }

                continue;
            }
            loop = 0; //반복문에서 if문 탈출시 음식이 있다는것으로 0으로 초기화
            food_times[i % idx]--; //음식하나 먹고
            i++; //다음 음식으로 패스하고
            cnt++; //음식 먹은 횟수 늘리기

        }
        answer = food_times[i % idx]; //그게아니고 정샂적으로
        return answer;
    }

}
