import java.util.Scanner;

public class BJ15652_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int index =0;
        int i =0;
        int[] numbers = new int[N];
        int[] answer = new int[M];
        for (int j = 0; j < numbers.length; j++) {
            numbers[j] = j+1;
        }

        choice(index,M,numbers, answer,i);
    }
    static void choice(int index, int M, int[] numbers, int[] answer, int i){
        if(index==M){
            for(int a:answer){
                System.out.print(a+" ");
            }
            System.out.println();
            return;
        }

        for (int j = i; j <numbers.length; j++) {
            answer[index] = numbers[j];
            choice(index+1,M,numbers,answer,j);
        }
    }

}
