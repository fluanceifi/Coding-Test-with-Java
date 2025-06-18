public class Main{

    public static void quickSort(int[] arr, int start, int end) {
        if(start >= end) return;
        
        int pivot = start;
        int left = start + 1;
        int right = end;

        while(left <= right){
            //left는 피벗  기준으로 더 큰 값을 찾아야함. 
            while(left <= end && arr[pivot] >= arr[left]) left++;
            //right는 피벗기준으로 더 작은 값을 찾아야함.
            while(right > start && arr[pivot] <= arr[right]) right--;
            //만약 left와 right이 엇갈렸다면, 피봇과 right을 swap한다.
            if(left > right){
                int temp = arr[pivot];
                arr[pivot] = arr[right];
                arr[right] = temp;
            }
            //아니라면, left와 right을 swap한다.
            else{
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        quickSort(arr, start, right - 1);
        quickSort(arr, right + 1, end);
    }
    public static void main(String[] args){
        int arr[] = {5, 7, 9, 0, 3, 1, 6, 2, 4, 8};
        
        quickSort(arr, 0, arr.length - 1);

        for(int num : arr) System.out.print(num + " ");
        System.out.println();
    }
}
