import java.util.*;

class Node {
    private int time;
    private char direc;

    public Node(int x, char c){
        this.time = x;
        this.direc = c;
    }

    public int getTime(){
        return this.time;
    }

    public char getDirec(){
        return this.direc;
    }
}

class Position {
    private int y;
    private int x;

    public Position(int y, int x){
        this.x = x;
        this.y = y;
    }

    public int getY(){
        return this.y;
    }

    public int getX(){
        return this.x;
    }
}


class Main{

    public static int n, k, l;
    public static int[][] board = new int[101][101];
    public static ArrayList<Node> info = new ArrayList<>();

    public static int[] dy = {0, 1, 0, -1}; //동 남 서 북
    public static int[] dx = {1, 0, -1, 0};

    public static int snake(){
        int y = 1, x = 1; // 시작위치
        board[y][x] = 2; //뱀이 존재하는 곳은 2로 표시
        int direction = 0; //처음은 동쪽
        int time = 0; //시작한 뒤 지난 '초'
        int idx = 0; //다음에 회전할 정보

        //뱀이 차지하고 있는 위치 정보 (꼬리가 앞쪽)
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(y, x));

        while(true){
            int ny = y + dy[direction];
            int nx = x + dx[direction];
 
            //보드 범위 안이고, 뱀의 몸통 위치가 아니라면
            if(1 <= ny && n >= ny && 1 <= nx && n >= nx && board[ny][nx] != 2){
                //사과가 없다면 이동 후 꼬리 제거
                if(board[ny][nx] == 0){
                    board[ny][nx] = 2; //이동
                    q.offer(new Position(ny, nx)); //위치 저장
                    Position prev = q.poll(); //이전 뱀 머리 가져오고
                    board[prev.getY()][prev.getX()] = 0; //위치 제거
                }
                //사과가 있다면 꼬리 그대로 둠.
                if(board[ny][nx] == 1){
                    board[ny][nx] = 2; //이동
                    q.offer(new Position(ny, nx));//꼬리 저장
                }
            }
            //보드 범위 밖(벽)이고, 뱀의 몸통 위치라면 종료
            else{
                time++;
                break;
            }

            //다음 위치 저장
            y = ny;
            x = nx;
            time++;
            if(idx < l && time == info.get(idx).getTime()){
                direction = turn(direction, info.get(idx).getDirec());
                idx++;
            }
        }

        return time;
    }

    public static int turn(int direction, char c) {
        if (c == 'L')
            direction = (direction == 0) ? 3 : direction - 1; //동 남 서 북에서 "북"이면 왼쪽은 "동"이므로 인덱스를 맞춰준다. / 그 외에는 -1한 값이 왼쪽
        else
            direction = (direction + 1) % 4; //오른쪽은 인덱스에서 +1한 값이기 때문에 %4로 인덱스만 맞춰주면됨.

        return direction;
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //보드 크기
        n = sc.nextInt();
        //사과 개수
        k = sc.nextInt();
        for(int i = 0 ; i < k ; i++){
            int y = sc.nextInt();
            int x = sc.nextInt();
            board[y][x] = 1;
        }
        //회전 수
        l = sc.nextInt();
        for(int i = 0 ; i < l ; i++){
            int x = sc.nextInt();
            char c = sc.next().charAt(0);
            info.add(new Node(x, c));
        }


        int result = snake();
        System.out.println(result);
    }
}
