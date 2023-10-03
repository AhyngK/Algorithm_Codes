import java.util.Scanner;

public class Main {
    public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
    	int weight = sc.nextInt();
    	
    	int max = weight/5;
    	
    	for(int i=max;i>=0;i--) {
    		int left = weight - 5*i;
    		if(left%3==0) {
    			System.out.println(i+left/3);
    			return;
    		}
    	}
    	System.out.println(-1);
    	
    	
   }
}

