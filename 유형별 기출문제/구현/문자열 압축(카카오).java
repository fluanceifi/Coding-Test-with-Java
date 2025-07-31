class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for(int step = 1 ; step < s.length()/2+1 ;step++){
            String compressed = ""; //압축을 확인 할 때 쓸 문자열
            String prev = s.substring(0,step); //현재 단위 문자(문자열)
            int cnt = 1; //반복 횟수 
            
            for(int j = step ; j < s.length() ; j += step){ //step만큼씩 증가하는 j
                String sub = ""; //prev의 비교대상
                
                for(int k = j ; k < j + step ; k++){// 처음엔 j부터 step단위만큼만 sub에 문자를 저장
                    if(k < s.length()) sub += s.charAt(k);
                }
                
                //비교해서 서로 같으면 cnt증가, 다르면 cnt값 확인 후 압축된 문자열에 연결
                if(prev.equals(sub)) cnt++;
                else{
                    compressed += (cnt >= 2) ? cnt+prev : prev;
                    
                    //이제 다른걸 알았으니 비교하해당 위치부터 다시 시작
                    sub = "";
                    for(int k = j ; k < j+ step ; k++){
                        if(k < s.length()) sub += s.charAt(k);
                    }
                    prev = sub;
                    cnt = 1;
                }
            }
            
            //만약 문자열 s의 길이가 prev로 나누어 떨어지지 않는다면 
            //문자열 s의 마지막 부분은  "j < s.length() ; j += step"에 의해 만족 못하니깐, 이어붙여줌
            compressed += (cnt >= 2) ? cnt + prev : prev;
            
            //마지막으로 매번 step에 대해 끝날 때 마다 비교해준다.
            answer = Math.min(compressed.length(), answer); 
        }
       
        return answer;
    }
}
