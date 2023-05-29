import java.util.Scanner;
import java.util.Stack;

public class BJ9012 {
    public static void main(String[] args) {

        // 줄 개수와 괄호들 입력받는 부분, 괄호 문자열 하나를 원소로 갖는 배열
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        scanner.nextLine();

        String[] parens = new String[number];
        for (int i = 0; i < number; i++) {
            parens[i] = scanner.nextLine();
        }

        for (int i = 0; i < parens.length; i++) {
            System.out.println(check(parens[i]));
        }
    }

    // 괄호 확인하는 메서드
    static String check(String s){
        // 괄호를 뒤에서부터 하나씩 꺼내서 Stack에 넣고, 매칭되는 괄호가 있으면 꺼내는 방식으로 구현
        // 문자열로 온 괄호들을 Stack에 넣는 것 하나와, 괄호들을 매칭시켜볼 스택 하나 생성
        Stack <Character> temp = new Stack<Character>();
        Stack <Character> sToC = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            sToC.add(s.charAt(i));
        }

        // Stack을 활용해 괄호 매칭 부분
        while(sToC.contains(')') || !sToC.isEmpty()) {
            // 만약 맨 뒤의 괄호가 ) 일경우, 임시 스택에 추가
            if(sToC.peek()==')') {
                temp.add(sToC.pop());
            }
            // 만약 맨 뒤 괄호가 (일 경우, 임시 스택에서 ) 괄호 하나 제거}
            else {
                // 닫히는 괄호 없이 여는 괄호 있을 경우 바로 종료
                if(temp.isEmpty()){
                    return "NO";
                }
                else {
                    sToC.pop();
                    temp.pop();
                }
            }
        }

        // 결과 판정, 두 스택이 모두 빈 스택이 아니라면 NO
        if(!sToC.isEmpty()||!temp.isEmpty()){
            return "NO";
        }
        else {
            return "YES";
        }
    }
}
