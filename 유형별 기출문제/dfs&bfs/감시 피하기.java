import java.util.*;

class Position{
    private int y;
    private int x;

    public Position(int i, int j){
        this.y = i;
        this.x = j;
    }

    public int getY(){
        return y;
    }
    public int getX(){
        return x;
    }
}

class Combination{
    private int n;
    private int r;
    private int[] now;
    private ArrayList<ArrayList<Position>> result;

    public Combination(int n, int r){
        this.n = n;
        this.r = r;
        this.now = new int[r];
        this.result = new ArrayList<ArrayList<Position>>();
    }

    public ArrayList<ArrayList<Position>> getResult(){
        return result;
    }

    public void combination(ArrayList<Position> arr, int depth, int index, int target){
        if(depth == r){
            ArrayList<Position> temp = new ArrayList<>();
            for(int i = 0 ; i < now.length; i++){
                temp.add(arr.get(now[i]));
            }
            result.add(temp);
            return;
        }
        if(target == n) return; //n은 space.size() -> 공간이 없으면 끝내라

        //모든 조합을 생성하는 재귀방식
        now[index] = target;
        combination(arr, depth + 1, index + 1, target +1);
        combination(arr, depth, index, target +1);

    }
}


class Main{
    public static int n;
    public static char[][] arr = new char[6][6];
    public static ArrayList<Position> teachers = new ArrayList<>();
    public static ArrayList<Position> spaces = new ArrayList<>();


    public static boolean watch(int y, int x, int direction){
        //왼쪽
        if(direction == 0){
            while(x >= 0){
                if(arr[y][x] == 'S') return true;
                if(arr[y][x] == 'O') return false;
                x--; // <- 순서로 탐색한다. (우에서 좌로 움직이면서 탐색해야 벽을 만날시 false if문에 도달함.)
            }
        }
        //오른쪽
        if(direction == 1){
            while(x < n){
                if(arr[y][x] == 'S') return true;
                if(arr[y][x] == 'O') return false;
                x++; // -> 순서로 탐색한다. (좌에서 우로 움직이면서 탐색해야 벽을 만날시 false if문에 도달하거나 복도 크기를 넘지 못함)
            }
        }
        //위쪽
        if(direction == 2){
            while(y >= 0){
                if(arr[y][x] == 'S') return true;
                if(arr[y][x] == 'O') return false;
                y--;
            }
        }
        //아래쪽
        if(direction == 3){
            while(y < n){
                if(arr[y][x] == 'S') return true;
                if(arr[y][x] == 'O') return false;
                y++;
            }
        }
        return false;
    }

    public static boolean process(){
        //모든 선생 위치를 하나씩 확인
        for(int i = 0 ; i < teachers.size() ; i++){
            int y = teachers.get(i).getY();
            int x = teachers.get(i).getX();

            //4가지 방향 탐지 (좌 우 상 하)
            for(int j = 0; j < 4 ; j++){
                if(watch(y, x, j)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                arr[i][j] = sc.next().charAt(0);
                if(arr[i][j] == 'T')
                    teachers.add(new Position(i, j));
                if(arr[i][j] == 'X')
                    spaces.add(new Position(i, j));
            }
        }

        Combination comb = new Combination(spaces.size(), 3);
        comb.combination(spaces, 0, 0 , 0);
        ArrayList<ArrayList<Position>> spaceList = comb.getResult();


        boolean found = false;

        for(int i = 0 ; i < spaceList.size() ; i++){
            //장애물 설치
            for(int j = 0 ; j < spaceList.get(j).size() ; j++){
                int y = spaceList.get(i).get(j).getY();
                int x = spaceList.get(i).get(j).getX();
                arr[y][x] = 'O';
            }
            if(!process()){
                //정답일 경우
                found = true;
                break;
            }
            //설치된 장애물 없애기
            for(int j = 0 ; j < spaceList.get(j).size() ; j++){ //now의 크기가 3으로 고정되어있어서 get(j).size() 생략 후 3으로 작성해도 된다.
                int y = spaceList.get(i).get(j).getY();
                int x = spaceList.get(i).get(j).getX();
                arr[y][x] = 'X';
            }
        }

        if(found) System.out.println("YES");
        else System.out.println("NO");

    }
}
