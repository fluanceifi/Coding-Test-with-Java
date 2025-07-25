import java.util.*;

class Main{

    public static ArrayList<Integer> arrList = new ArrayList<>();

    public static int n, result;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            arrList.add(sc.nextInt());
        }
        Collections.sort(arrList);

        //확인할 금액
        result = 1;
        for(int i = 0 ; i < n ; i++){
            //만약 확인할 금액보다 더 높은 화폐단위가 존재할경우 확인할 금액은 만들 수 없는 금액이 된다.
            if(result < arrList.get(i)) break;
            //확인할 금액과 같거나 작은 금액이 존재하면 이제부턴 "확인할 금액 + 존재하는 화폐단위"가 "확인할 금액"이 되고 그 다음 화폐단위와 비교해서 만들 수 있는 금액인지 확인해야 된다.
            result += arrList.get(i);
        }

        System.out.println(result);

    }
}
