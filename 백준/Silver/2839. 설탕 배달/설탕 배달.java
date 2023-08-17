import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] suger = {5,3};
        Scanner sc = new Scanner(System.in);
        int totalSugar = sc.nextInt();
        int count = 0;

        for (int i = totalSugar/suger[0]; i >= 0 ; i--) {
            int leftSugar = totalSugar-suger[0]*i;
            if(leftSugar%suger[1] == 0){
                count+=i;
                count+=leftSugar/suger[1];
                break;
            }
        }
        if(count==0){
            count=-1;
        }
        System.out.println(count);
    }
}
