import java.util.*;

class Node{
    private int pos1Y;
    private int pos1X;
    private int pos2Y;
    private int pos2X;
    private int dist;

    public Node(int pos1Y, int pos1X, int pos2Y, int pos2X, int dist){
        this.pos1Y = pos1Y;
        this.pos1X = pos1X;
        this.pos2Y = pos2Y;
        this.pos2X = pos2X;
        this.dist = dist;
    }

    public int getPos1Y(){
        return pos1Y;
    }
    public int getPos1X(){
        return pos1X;
    }
    public int getPos2Y(){
        return pos2Y;
    }
    public int getPos2X(){
        return pos2X;
    }
    public int getDist(){
        return dist;
    }
}

class Solution {

    public ArrayList<Node> getNextPos(Node pos, int[][] board){
        ArrayList<Node> nextPos = new ArrayList<>();

        //상 하 = x축 기준,  좌 우 = y축 기준
        int[] dy = {-1,1,0,0};
        int[] dx = {0,0,-1,1};

        for(int i = 0 ; i < 4 ; i++) {
            int nextPos1Y = pos.getPos1Y() + dy[i];
            int nextPos1X = pos.getPos1X() + dx[i];
            int nextPos2Y = pos.getPos2Y() + dy[i];
            int nextPos2X = pos.getPos2X() + dx[i];
            int nextDist = pos.getDist() + 1;

            //이동하려는 칸에 벽이 없다면 추가함.
            if (board[nextPos1Y][nextPos1X] == 0 && board[nextPos2Y][nextPos2X] == 0) {
                nextPos.add(new Node(nextPos1Y, nextPos1X, nextPos2Y, nextPos2X, nextDist));
            }
        }
        int[] hor = {-1, 1};
        //로봇이 수평이라면 가로로 회전이다.
        // 위로 회전할 때 / 아래로 회전할 때를 비교 한다.
        if(pos.getPos1Y() == pos.getPos2Y()){
            for(int j = 0 ; j < 2 ; j++){
                //1과 2 둘 중 어느 것도 회전 축으로 사용할 수 있다. 하지만 둘 다 회전 조건이 만족되어야 회전할 수 있는 조건이 완성된다.
                if(board[pos.getPos1Y() + hor[j]][pos.getPos1X()] == 0 && board[pos.getPos2Y() + hor[j]][pos.getPos2X()] == 0){
                    nextPos.add(new Node(pos.getPos1Y(), pos.getPos1X(), pos.getPos2Y() + hor[j], pos.getPos1X(), pos.getDist() + 1)); //pos 1이 회전축
                    nextPos.add(new Node(pos.getPos1Y() + hor[j], pos.getPos2X(), pos.getPos2Y(), pos.getPos2X(), pos.getDist() + 1)); //pos 2가 회전축
                }
                }
            }
        int[] ver = {-1, 1};
        //로봇이 수직이라면 세로로 회전이다.
        // 좌로 회전할 때 / 우로 회전할 때를 비교 한다.
        if(pos.getPos1X() == pos.getPos2X()){
            //수평과 마찬가지로 어느쪽으로돈 축으로 사용 가능, 하지만 둘다 회전 조건이 만족해야 됨.
            for(int j = 0 ; j < 2 ; j++){
                if(board[pos.getPos1Y()][pos.getPos1X() + ver[j]] == 0 && board[pos.getPos2Y()][pos.getPos2X() + ver[j]] == 0){
                    nextPos.add(new Node(pos.getPos1Y(), pos.getPos1X(), pos.getPos1Y(), pos.getPos2X() + ver[j], pos.getDist() + 1));// 1이 축일때
                    nextPos.add(new Node(pos.getPos2Y(), pos.getPos1X() + ver[j], pos.getPos2Y(), pos.getPos2X(), pos.getDist() + 1)); // 2가 축일때
                }
            }
        }


        return nextPos;
    }


    public int solution (int[][] board) {
        int n = board.length;
        int[][] newBoard = new int[n+2][n+2];

        //주위에 벽을 세워 계산 편하게 만들기
        for(int i = 0 ; i < n + 2 ; i++){
            for(int j = 0 ; j < n + 2 ; j++){
                newBoard[i][j] = 1;
            }
        }
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                newBoard[i+1][j+1] = board[i][j];
            }
        }

        //BFS 수행
        Node pos = new Node(1,1,1,2,0); //시작 위치 및 앞으로 사용할 위치 인스턴스
        Queue<Node> q = new LinkedList<>(); //큐로 진행하면 BFS로 인해 가장 먼저도착하는 경로가 가장 거리가 짧을 것이다.
        q.offer(pos);
        ArrayList<Node> visited = new ArrayList<>(); //방문한 곳은 중복 방문하지 않도록 저장해둔다.
        visited.add(pos);

        while(!q.isEmpty()){
            pos = q.poll();

            //(n,n)에 도달했다면, 거리 반환
            if((pos.getPos1Y() == n && pos.getPos1X() == n)||(pos.getPos2Y() == n && pos.getPos2X() == n)){
                return pos.getDist();
            }

            //그게 아니라면 현 위치에서 방문할 수 있는 위치 확인
            ArrayList<Node> nextPos = getNextPos(pos, newBoard);

            for(int i = 0 ; i < nextPos.size() ; i++){
                boolean check = true;
                pos = nextPos.get(i);
                for(int j = 0 ; j < visited.size() ; j++){
                    if(pos.getPos1X() == visited.get(j).getPos1X() && pos.getPos2X() == visited.get(j).getPos2X() && pos.getPos1Y() == visited.get(j).getPos1Y() && pos.getPos2Y() == visited.get(j).getPos2Y()){
                        check = false;
                        break;
                    }
                }
                if(check){
                    q.offer(pos);
                    visited.add(pos);
                }
            }
        }
        return 0;

    }
}



