import java.util.Arrays;
import java.util.Scanner;

public class BJ10819_01 {
    static int biggest =-1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(array);

        while (fuctionNext(array)!=-1){
           // System.out.print(Arrays.toString(array)+", ");
            sum(array);
        }
        System.out.println(biggest);
    }

    static int fuctionNext (int[] array){
        int index =-1;
        for (int i = array.length-1; i >0 ; i--) {
            if(array[i-1]<array[i]){
                index =i-1;
                break;
            }
        }

        if(index==-1){
            return -1;
        }
        else {
            int toChange =-1;
            for (int i = index+1; i <array.length ; i++) {
                if(toChange==-1 && array[index]<array[i]){
                    toChange =i;
                }
                else if(toChange!=-1 && array[index]<array[i] && array[toChange]>array[i]){
                    toChange=i;
                }
            }
            swap(array,index,toChange);

            index +=1;
            toChange = array.length-1;
            while (index<toChange){
                swap(array,index,toChange);
                index++;
                toChange--;
            }
            return 0;
        }
    }
    static void sum(int[] array){
        int sum =0;
        for (int i = 1; i <array.length ; i+=1) {
            sum += Math.abs(array[i-1]-array[i]);
        }
        if(sum>biggest){
            biggest = sum;
        }
      //  System.out.println(sum);
    }
    static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
