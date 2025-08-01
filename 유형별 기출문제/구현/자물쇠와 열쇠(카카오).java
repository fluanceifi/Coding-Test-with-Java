class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;

        //자물쇠를 기존 크기의 3배로 만든다. CV처럼 convolution masking처리 할 이미지를 생성한다 생각하면 됨.
        int[][] newlock = new int[n * 3][n * 3];

        //중앙부분에 lock 삽입
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ;j++){
                newlock[i+n][j+n] = lock[i][j];
            }
        }

        //4가지 방향에 대해서 확인
        for(int rotation = 0 ; rotation < 4 ; rotation++){
            key = rotationMatrixBy90Degree(key); //1번 회전당 90도씩 회전하게됨.
            //이동할 공간
            for(int y = 1 ; y < n * 2 ; y++){       //왜 1부터 n * 2까지 인건가?
                for(int x = 1 ; x < n * 2 ; x++){   //새롭게 넓혀 만든 newlock이 왼쪽 위[1][1]부터 오른쪽 아래[n*2-1][n*2-1]까지의 범위만 적용시킨다면 키가 90도씩 회전하면서 모든 경우의 수를 고려할 수 있기 때문이다.
                    //좌물쇠에 열쇠 넣어보기
                    for(int i = 0 ; i < m ; i++){
                        for(int j = 0 ; j < m ; j++){
                            newlock[y + i][x + j] += key[i][j];
                        }
                    }

                    //열쇠가 맞는지 확인하는 과정
                    if(check(newlock)) return true;
                    //입력한 열쇠 다시 빼는 작업
                    for(int i = 0 ; i < m ; i++){
                        for(int j = 0 ; j < m ; j++){
                            newlock[y + i][x + j] -= key[i][j];
                        }
                    }

                }
            }
        }
        return false;
    }

    public static boolean check(int[][] newlock) {
        int lockLength = newlock.length / 3;

        for(int i = lockLength ; i < lockLength * 2; i++ ){
            for(int j = lockLength ; j < lockLength * 2; j++){
                if(newlock[i][j] != 1){
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] rotationMatrixBy90Degree(int[][] a) {
        int n = a.length; //행
        int m = a[0].length; //열
        int[][] result = new int[n][m]; //키 크기에 맞게 생성

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                result[j][n - i - 1] = a[i][j]; //이건 그림 그려봐야 알 수 있는 내용임 --> [2][0]위치에 1이 존재할때 90도 회전시 [0][0]이 되야 함. -> 일단 [i][]를 [j][]로 전치하는 건 주대각선 기준으로 전치되는 것이고, 여기서 [][j]를 [][n-i-1]로 바꾸게 되면 y축 기준으로 전치가 되버리니깐 90도 전치가 됨.
            }
        }
        return result;
    }
}
