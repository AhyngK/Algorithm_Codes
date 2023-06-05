import java.util.*;

public class BJ2529_01 {
    static String Max = "0";
    static String Least = "9999999999";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfBracket = scanner.nextInt();
        scanner.nextLine();
        String bracketsRaw = scanner.nextLine();
        String[] brackets = bracketsRaw.split(" ");

        int index =0;
        boolean[] check = new boolean[10];
        Arrays.fill(check,false);
        int before =0;
        int[] answer = new int[brackets.length+1];

        numbersCheck(index,brackets,check,before,answer);
        System.out.println(Max);
        System.out.println(Least);
    }

    static void numbersCheck(int index, String[] brackets,boolean[] check, int before, int[] answer){
        if(index==brackets.length+1){
            int pow =0;
            StringBuffer answerInt = new StringBuffer();
            for (int i = 0; i<answer.length; i++) {
                answerInt.append(answer[i]);
            }
            if(Long.parseLong(answerInt.toString())<Long.parseLong(Least)){
                Least = answerInt.toString();
            }
            if(Long.parseLong(answerInt.toString())>Long.parseLong(Max)){
                Max = answerInt.toString();
            }
            // System.out.println(answerInt);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if(check[i]){
                continue;
            }
            else {
                if(index==0 || brackets[index-1].equals(">") && before>i || brackets[index-1].equals("<") && before< i ){
                    answer[index] = i;
                    check[i] = true;
                    numbersCheck(index+1,brackets,check,i,answer);
                    check[i] = false;
                    answer[index] =-1;
                }
                else {
                    continue;
                }
            }
        }
    }
}
