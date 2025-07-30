import java.util.*;

class Main{
    public static int length, a, b;
    public static String s;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        s = sc.nextLine();
        length = s.length();

        for(int i = 0 ; i < length / 2  ; i++){
            a += s.charAt(i);
        }
        for(int i = length / 2 ; i < length ; i++){
            b += s.charAt(i);
        }

        if(a == b)
            System.out.println("LUCKY");
        else
            System.out.println("READY");



    }
}
