import java.util.Arrays;
import java.util.Scanner;

public class BJ11052_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int toBuy = sc.nextInt();
        sc.nextLine();

        // 카드들의 값을 입력 받고, 인덱스와 카드 개수를 맞춰주기 위해 배열을 복사
        int[] temp = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] price = new int[toBuy+1];
        price[0] =0;
        for (int i = 0; i < temp.length; i++) {
            price[i+1] = temp[i];
        }

        // 1-N까지 구매할 카드에 개수에 따라 최대 금액을 저장할 배열
        int[] biggestPrice = new int[toBuy+1];
        biggestPrice[0] =0;

        // 1-N까지 구매할 카드의 개수에 따라 최대 금액 구하는 부분
        // 2개의 카드를 구매한다고 했을때, (1개 구매의 최대 금액+1개팩 구매 가격), (2개팩 구매 가격) 을 저장해두고, 최대값 산출
        for (int i = 1; i <biggestPrice.length ; i++) {
            int[] temp1= new int [i];
            for (int j = 1; j <=i ; j++) {
                temp1[j-1] = price[j]+biggestPrice[i-j];
            }
            Arrays.sort(temp1);
            biggestPrice[i] = temp1[temp1.length-1];
        }

        System.out.println(biggestPrice[toBuy]);
    }
}
