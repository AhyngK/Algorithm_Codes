import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean check = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            check = true;
            String[] paper = br.readLine().split("");
            int start = 0;
            int end = paper.length-1;
            int middle = (start+end)/2;
            split(start,middle,end,paper);
            if(check){
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
    static void split(int start, int middle, int end, String[] paper){

        boolean temp = true;
        int middleLeft = middle-1;
        int middleRight = middle+1;

        while (middleLeft>=start && middleRight<=end){
            if(paper[middleLeft].equals(paper[middleRight])){
                check = false;
                break;
            }
            middleLeft-=1;
            middleRight+=1;
        }

        if(temp && (end-start)>2){
            middleLeft = middle-1;
            middleRight = middle+1;
            split(start,(start+middleLeft)/2,middleLeft, paper);
            split(middleRight,(middleRight+end)/2,end,paper);
        }
    }
}

