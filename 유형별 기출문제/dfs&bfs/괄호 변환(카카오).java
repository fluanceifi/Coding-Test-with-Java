class Solution {
    //균형이 잡혔는가?
    public int balancedIdx(String p){
        int cnt = 0;
        
        for(int i = 0 ; i < p.length() ; i++){
            if(p.charAt(i) == '(') cnt++;
            else{
                cnt--;
            }
            if(cnt == 0) return i;
        }
        
        return -1;
    }
    //올바른가?
    public boolean checkProper(String u){
        int cnt = 0;
        //if(cnt == 0)를 먼저만나게 되면 불균형
        for(int i = 0 ; i < u.length() ; i++){
            if(u.charAt(i) == '(') cnt++;
            else{
                if(cnt == 0) return false;
                cnt--;
            }
        }
        return true;
    }
    
    public String solution(String p) {
        String answer = "";
        
        if(p.equals("")) return "";
        //균형이 맞는 범위
        int idx = balancedIdx(p);
        //범위를 기준으로 나눈다.
        String u = p.substring(0, idx+1);
        String v = p.substring(idx+1);

        //올바르면 재귀로 넘어감
        if(checkProper(u)){
            answer += u + solution(v);
        }
        //올바르지 못하면 해당 로직을 따뤄야함.
        else{
            answer = "(";
            answer += solution(v);
            answer += ")";
            
            u = u.substring(1, u.length()-1);
            String temp = "";
            for(int i = 0 ; i < u.length() ; i++){
                if(u.charAt(i) == '(') temp += ")";
                else temp += "(";
            }
            answer += temp;
        }
        
        
        return answer;
    }
}
