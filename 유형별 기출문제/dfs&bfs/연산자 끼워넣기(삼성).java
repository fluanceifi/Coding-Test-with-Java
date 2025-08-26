import java.util.*;

class Main{

    public static int n;
    public static ArrayList<Integer> arr = new ArrayList<>();
    public static int plus, sub, mul, divi;
    public static int min = (int) 1e9, max = (int) -1e9;

    public static void dfs(int i, int now){
        if(i == n){
            min = Math.min(min, now);
            max = Math.max(max, now);
        }
        else{
            if(plus > 0){
                plus--;
                dfs(i+1, now + arr.get(i));
                plus++;
            }
            if(sub > 0){
                sub--;
                dfs(i+1, now - arr.get(i));
                sub++;
            }
            if(mul > 0){
                mul--;
                dfs(i+1, now * arr.get(i));
                mul++;
            }
            if(divi > 0){
                divi--;
                dfs(i+1, now / arr.get(i));
                divi++;
            }
        }
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            arr.add(sc.nextInt());
        }

        plus = sc.nextInt();
        sub = sc.nextInt();
        mul = sc.nextInt();
        divi = sc.nextInt();

        dfs(1, arr.get(0));

        System.out.println(max);
        System.out.println(min);

    }
}
