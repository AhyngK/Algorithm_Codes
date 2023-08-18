import java.util.Scanner;

public class Main {
    static int biggestMoney =0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int day = scanner.nextInt();
        scanner.nextLine();
        int[] times = new int[day];
        int[] money = new int[day];
        for (int i = 0; i <day ; i++) {
            times[i] = scanner.nextInt();
            money[i] = scanner.nextInt();
            scanner.nextLine();
        }
        int index =0;
        int moneySum =0;

//        System.out.println(Arrays.toString(times));
//        System.out.println(Arrays.toString(money));

        calculation(index,times,money,moneySum);
        System.out.println(biggestMoney);
    }

    static void calculation(int index, int[]times, int[] money, int moneySum){
        //System.out.println(index+"day,moneytotal :" +moneySum);
        if(index>=times.length){
            if(index==times.length && moneySum>biggestMoney){
                biggestMoney = moneySum;
            }
            //System.out.println("end of the week : "+moneySum);
            return;
        }

        // 해당 날짜의 상담 할 경우
        int newIndex;
        int newMoneySum = moneySum;
        if(times[index]>1){
            newIndex = index+times[index];
            if(newIndex<=times.length){
                newMoneySum+=money[index];
            }
        }
        else {
            newIndex=index+1;
            newMoneySum+=money[index];
        }
        calculation(newIndex,times,money,newMoneySum);

        // 해당 날짜 상담 안할 경우
        calculation(index+1,times,money,moneySum);
    }
}
