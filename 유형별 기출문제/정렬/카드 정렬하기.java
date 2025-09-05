import java.util.*;

class Card implements Comparable<Card>{
    private int cnt;

    public Card(int cnt){
        this.cnt = cnt;
    }

    public int getCnt(){
        return cnt;
    }

    @Override
    public int compareTo(Card other){
        return Integer.compare(this.cnt, other.cnt);
    }
}

class Main{
    public static int n, result;
    public static int sum = 0;
    public static PriorityQueue<Card> pq = new PriorityQueue<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            int cnt = sc.nextInt();
            pq.add(new Card(cnt));
        }

        while(!pq.isEmpty()){
            Card a = pq.poll();
            Card b = pq.poll();

            if(b == null) {
                result = sum;
                break;
            }
            int aCnt = a.getCnt();
            int bCnt = b.getCnt();

            sum += aCnt + bCnt;
            pq.add(new Card(aCnt + bCnt));
        }


        System.out.println(result);
    }
}
