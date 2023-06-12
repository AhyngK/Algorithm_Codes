import java.util.*;

public class BJ10972_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        List<Integer>nums = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            nums.add(scanner.nextInt());
        }

        // 뒤에서부터 오름차순이 끝난, 바꿔야 할 수의 위치를 frontIndex로 찾기
        int frontIndex = -1;
        for (int i = nums.size()-1; i>0 ; i--) {
            if(nums.get(i)>nums.get(i-1)){
                frontIndex = i-1;
                break;
            }
        }

        // 만약 frontIndex가 그대로 -1이라면 마지막 순열이므로 -1 출력
        if(frontIndex==-1){
            System.out.println(-1);
        }
        else {
            // frontIndex와 자리바꿈할 그 다음으로 큰 수를 찾기, 인덱스를 toChange에 저장
            int toChange = -1;
            for (int i = 0; i < nums.size(); i++) {
                if(nums.get(frontIndex)<nums.get(i) && nums.get(i)>nums.get(frontIndex)){
                    toChange = i;
                }
            }
            // frontIndex, toChange 위치 수 자리바꿈
            swap(nums,frontIndex,toChange);

            // frontIndex 다음 위치수부터 맨 마지막 수와 자리바꿈
            int front = frontIndex+1;
            int last = nums.size()-1;
            while (front<last){
                swap(nums,front,last);
                front++;
                last--;
            }

            // 출력문
            for (int i = 0; i < nums.size()-1; i++) {
                System.out.print(nums.get(i)+" ");
            }
            System.out.print(nums.get(nums.size()-1));
        }
    }

    static void swap(List<Integer> A, int index1, int index2){
        int temp = A.get(index1);
        A.set(index1,A.get(index2));
        A.set(index2,temp);
    }
}
