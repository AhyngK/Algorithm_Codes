import java.util.Scanner;

public class BJ11723_01 {
    public static void main(String[] args) {
        StringBuilder answer = new StringBuilder();

        Scanner scanner = new Scanner(System.in);
        int sumCount = scanner.nextInt();
        scanner.nextLine();

        // 비트마스크 연산 기본 집합이 될 s
        int s =0;

        for (int i = 0; i < sumCount; i++) {
            String[] thisCalculation = scanner.nextLine().split(" ");
            //System.out.println(Arrays.toString(thisCalculation));

            if(thisCalculation[0].equals("add")){
                s = add(s,Integer.parseInt(thisCalculation[1]));
            }
            else if (thisCalculation[0].equals("remove")) {
                s = remove(s,Integer.parseInt(thisCalculation[1]));
            }
            else if (thisCalculation[0].equals("check")) {
                answer.append(check(s,Integer.parseInt(thisCalculation[1])));
                answer.append("\n");
            }
            else if (thisCalculation[0].equals("toggle")) {
                s = toggle(s,Integer.parseInt(thisCalculation[1]));
            }
            else if (thisCalculation[0].equals("all")) {
                s = all();
            }
            else if (thisCalculation[0].equals("empty")) {
                s = empty();
            }
        }
        System.out.println(answer);
    }
    static int add(int s, int x){
        return s^(1<<x);
    }

    static int remove(int s, int x){
        int binary = (1<<x)-1;
        return s&binary;
    }

    static int check(int s, int x){
        int temp = s&(1<<x);
        if(temp==0){
            return 0;
        }
        else {
            return 1;
        }
    }

    static int toggle(int s, int x){
        return s^(1<<x);
    }

    static int all(){
        int temp =0;
        for (int i = 0; i < 21; i++) {
            temp+= Math.pow(2,i);
        }
        return temp;
    }

    static int empty(){
        return 0;
    }
}
