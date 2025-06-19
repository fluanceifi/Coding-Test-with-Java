class Main {
    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2};
        int cnt = 0;
        int[] count = new int[100];

        for(int num : arr){
            count[num]++;
        }
        
        for(int i = 0 ; i < count.length ; i++){
            while(count[i] != cnt){
                System.out.print(i + " ");
                cnt++;
            }
            cnt = 0;
        }
    }
}
