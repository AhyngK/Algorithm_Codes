import java.util.Scanner;

public class BJ10974_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = i+1;
        }
        print(array);

        while (nextArray(array)!=-1){
            print(array);
        }
    }
    static void print(int [] array){
        for(int a:array){
            System.out.print(a+" ");
        }
        System.out.println();
    }
    static int nextArray(int[] array){
        int index = -1;
        for (int i = array.length-1; i>0 ; i--) {
            if(array[i-1]<array[i]){
                index = i-1;
                break;
            }
        }

        if(index==-1){
            return -1;
        }
        else {
            int nextIndex = -1;
            for (int i = index+1; i <array.length ; i++) {
                if(nextIndex==-1 && array[i]>array[index]){
                    nextIndex =i;
                }
                else if (nextIndex!=-1 && array[i]<array[nextIndex] && array[i]>array[index]) {
                    nextIndex =i;
                }
            }
            swap(array,index,nextIndex);

            int first = index+1;
            int last = array.length-1;
            while(first<last){
                swap(array,first,last);
                first++;
                last--;
            }
            return 0;
        }
    }

    static void swap (int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
