import java.util.*;


class Position{
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}


class Combination{
    private int n; //치킨집 개수
    private int r; //선택할 치킨집 개수
    private int[] now; //현재 조합
    private ArrayList<ArrayList<Position>> result; //모든 조합

    public ArrayList<ArrayList<Position>> getResult(){
        return result;
    }

    public Combination(int n , int r){
        this.n = n;
        this.r = r;
        this.now = new int[r];
        this.result = new ArrayList<ArrayList<Position>>();
    }

    public void combination(ArrayList<Position> chicken, int depth, int index, int target) {
        //선택할 치킨집 개수를 다 채웠다면
        if(depth == r){
            ArrayList<Position> temp = new ArrayList<>();
            for(int i = 0 ; i < now.length; i++){
                temp.add(chicken.get(now[i]));
            }
            result.add(temp);
            return;
        }
        if(target == n) return; //target은 chicken의 인덱스 개념이다. target == n이 되면 모든 치킨집을 선택한 것으로 재귀가 종료된다.
        now[index] = target;//백트래킹을 하게되면 DFS처럼 동작하겠지? 그러면 가지를 뻗는 1번과 2번 재귀를 통해 target은 증가될 것이고 now에 치킨의 인덱스를 담게된다.
        combination(chicken, depth + 1, index + 1, target + 1); //1. 방금 그 치킨집을 선택하고 target을 증가시켜 다음 치킨집을 가르킨다.
        combination(chicken, depth, index, target + 1); //2. 현재 치킨집은 선택안하고 target만 증가시켜 다음 치킨집을 가르킨다.
    }
}


class Main{

    public static int n, m; // n = ArraySize, m = Chicken Num
    public static int[][] arr = new int[51][51];
    public static ArrayList<Position>chicken = new ArrayList<>();
    public static ArrayList<Position>house = new ArrayList<>();

    public static int getSum(ArrayList<Position> chickenList){
        int result = 0;
        //모든 집에 대하여
        for(int i = 0 ; i < house.size() ; i++){
            //하나의 집에 대해
            int hx = house.get(i).getX();
            int hy = house.get(i).getY();
            //다른 모든 치킨집과의 거리를 비교 후
            int temp = (int) 1e9;
            for(int j = 0 ; j < chickenList.size() ; j++){
                int cx = chickenList.get(j).getX();
                int cy = chickenList.get(j).getY();
                temp = Math.min(temp, Math.abs(hx-cx) + Math.abs(hy-cy));
            }
            //가장 가까운 치킨집만 거리를 더한다.
            result += temp;
        }
        //치킨의 합
        return result;
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for(int r = 1 ; r <= n ; r++){
            for(int c = 1 ; c <= n ; c++){
                arr[r][c] = sc.nextInt();
                if(arr[r][c] == 1) house.add(new Position(r, c)); //집 일때
                else if(arr[r][c] == 2) chicken.add(new Position(r, c)); //치킨집 일때
            }
        }

        //최대 M개를 골랐을 때 도시 치킨 거리의 최솟값을 출력하라 ==> 치킨집이 많을 수록 최소값은 줄어든다. 즉 M개를 골랐을 때의 조합 생각하면 된다.
        Combination comb = new Combination(chicken.size(), m);
        comb.combination(chicken, 0, 0, 0); //치킨집을 선택하는 조합을 실행한다.
        ArrayList<ArrayList<Position>> chickenList = comb.getResult(); //그 조합이 담긴 리스트를 가져온다.

        //이제 실질적으로 치킨 거리의 합이 최소가 되도록 계산 후 출력한다.
        int result = (int) 1e9;
        for(int i = 0 ; i < chickenList.size() ; i++){
            result = Math.min(result, getSum(chickenList.get(i)));
        }
        System.out.println(result);



    }
}
