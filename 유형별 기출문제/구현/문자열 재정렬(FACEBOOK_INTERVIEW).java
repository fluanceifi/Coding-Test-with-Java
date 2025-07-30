    import java.util.*;

    class Main{
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            int sum = 0;
            String s = sc.nextLine();
            ArrayList<Character> str = new ArrayList<>();
            
            
            //숫자 기준
            for(int i = 0 ; i < s.length() ; i++){
                if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    sum += (s.charAt(i) - '0');
                }
                else{
                    str.add(s.charAt(i));
                }
            }
            /*
            // 문자열 기준
            for (int i = 0; i < str.length(); i++) {
                // 알파벳인 경우 결과 리스트에 삽입
                if (Character.isLetter(str.charAt(i))) {
                result.add(str.charAt(i));
                }
                // 숫자는 따로 더하기
                else {
                    value += str.charAt(i) - '0';
                }
            }
             */

            Collections.sort(str);

            for (Character char1 : str) {
                System.out.print(char1);
            }
            System.out.print(sum);

        }
    }
