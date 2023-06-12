import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BJ6603_01_failed {
    static List<String> toPrint = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(array[0]==0){
                break;
            }
            int[] arrayTemp = new int[array.length-1];
            for (int i = 1; i <array.length ; i++) {
                arrayTemp[i-1] = array[i];
            }
            Arrays.sort(arrayTemp);

            toString(arrayTemp);
            while (function(arrayTemp)!=-1){
            }
            print();
            toPrint.clear();
            System.out.println();
        }

    }
    static int function(int[]array){
        int first = -1;
        for (int i = array.length-1; i >0 ; i--) {
            if(array[i-1]<array[i]){
                first =i-1;
                break;
            }
        }

        if(first==-1){
            return -1;
        }
        else {
            int last =-1;
            for (int i = first+1; i <array.length ; i++) {
                if(last==-1 && array[i]>array[first]){
                    last =i;
                }
                else if (last!=-1 && array[i]>array[first] && array[i]<array[last]) {
                    last =i;
                }
            }
            swap(array,first,last);

            first++;
            last = array.length-1;
            while (first<last){
                swap(array,first,last);
                first++;
                last--;
            }

            toString(array);
            return 0;
        }
    }
    static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    static void toString(int[] array){
        int[] array1 = new int[6];
        for (int i = 0; i <array1.length ; i++) {
            array1[i] = array[i];
        }
        Arrays.sort(array1);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            result.append(array1[i]);
            result.append(" ");
        }

        if(!toPrint.contains(result.toString())){
            toPrint.add(result.toString());
        }

    }
    static void print(){
        for (int i = 0; i < toPrint.size(); i++) {
            System.out.println(toPrint.get(i));
        }
    }
}
