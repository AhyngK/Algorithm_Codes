import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] board = new int[19][19];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        boolean black = false;
        boolean white = false;
        int answerX = 0;
        int answerY = 0;

        label: for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

            // Case 1 : 현재 바둑알이 검은색일 때
                if(board[i][j]==1){

                    // 1. 가로 오른쪽 확인
                    if(j==0 || board[i][j-1]!=1){
                        // 본인+옆 4개칸 확인, 오목이 만들어지지 않는 경우 중단
                        boolean check = true;
                        for (int k = 0; k < 5; k++) {
                            if(j+k == board.length || board[i][j+k] != 1){
                                check = false;
                                break;
                            }
                        }
                        // 오목인 경우, 맨끝 다음칸 확인
                        if(check && (j+5== board.length || board[i][j+5]!=1)){
                            black = true;
                            answerX = i;
                            answerY = j;
                            break label;
                        }
                    }

                    // 2. 세로 아래쪽 확인
                    if(i==0 || board[i-1][j]!=1){
                        // 본인+아래 4개칸 확인, 오목이 만들어지지 않는 경우 중단
                        boolean check = true;
                        for (int k = 0; k < 5; k++) {
                            if(i+k == board.length || board[i+k][j] != 1){
                                check = false;
                                break;
                            }
                        }
                        // 오목인 경우, 맨끝 다음칸 확인
                        if(check && (i+5== board.length || board[i+5][j]!=1)){
                            black = true;
                            answerX = i;
                            answerY = j;
                            break label;
                        }
                    }

                    // 3. 오른쪽 대각선 위 : row가 끝이거나, column이 맨 첫칸이거나, 이전 오른쪽 대각선 아래칸이 같은 색이 아닌 경우 진입
                    if(i==board.length-1 || j==0 || board[i+1][j-1]!=1){
                        // 본인+오른쪽 위 4개칸 확인, 오목이 만들어지지 않는 경우 중단
                        boolean check = true;
                        for (int k = 0; k < 5; k++) {
                            if(j+k == board.length || i-k < 0 || board[i-k][j+k] != 1){
                                check = false;
                                break;
                            }
                        }
                        // 오목인 경우, 맨끝 다음칸 확인
                        if(check && (j+5== board.length || i-5==-1 ||board[i-5][j+5]!=1)){
                            black = true;
                            answerX = i;
                            answerY = j;
                            break label;
                        }
                    }

                    // 4. 오른쪽 대각선 아래 : row가 첫칸이거나, column이 맨 첫칸이거나, 이전 왼쪽 대각선 윗칸이 같은 색이 아닌 경우 진입
                    if(i==0 || j==0 || board[i-1][j-1]!=1){
                        // 본인+오른쪽 아래 4개칸 확인, 오목이 만들어지지 않는 경우 중단
                        boolean check = true;
                        for (int k = 0; k < 5; k++) {
                            if(i+k == board.length || j+k==board.length || board[i+k][j+k] != 1){
                                check = false;
                                break;
                            }
                        }
                        // 오목인 경우, 맨끝 다음칸 확인
                        if(check && (i+5== board.length || j+5== board.length || board[i+5][j+5]!=1)){
                            black = true;
                            answerX = i;
                            answerY = j;
                            break label;
                        }
                    }
                }

                // Case 2. 현재 바둑알이 흰색일 때
                if(board[i][j]==2){

                    // 1. 가로 오른쪽 확인
                    if(j==0 || board[i][j-1]!=2){
                        // 본인+옆 4개칸 확인, 오목이 만들어지지 않는 경우 중단
                        boolean check = true;
                        for (int k = 0; k < 5; k++) {
                            if(j+k == board.length || board[i][j+k] != 2){
                                check = false;
                                break;
                            }
                        }
                        // 오목인 경우, 맨끝 다음칸 확인
                        if(check && (j+5== board.length || board[i][j+5]!=2)){
                            white = true;
                            answerX = i;
                            answerY = j;
                            break label;
                        }
                    }

                    // 2. 세로 아래쪽 확인
                    if(i==0 || board[i-1][j]!=2){
                        // 본인+아래 4개칸 확인, 오목이 만들어지지 않는 경우 중단
                        boolean check = true;
                        for (int k = 0; k < 5; k++) {
                            if(i+k == board.length || board[i+k][j] != 2){
                                check = false;
                                break;
                            }
                        }
                        // 오목인 경우, 맨끝 다음칸 확인
                        if(check && (i+5== board.length || board[i+5][j]!=2)){
                            white = true;
                            answerX = i;
                            answerY = j;
                            break label;
                        }
                    }

                    // 3. 오른쪽 대각선 위 : row가 끝이거나, column이 맨 첫칸이거나, 이전 오른쪽 대각선 아래칸이 같은 색이 아닌 경우 진입
                    if(i==board.length-1 || j==0 || board[i+1][j-1]!=2){
                        // 본인+오른쪽 위 4개칸 확인, 오목이 만들어지지 않는 경우 중단
                        boolean check = true;
                        for (int k = 0; k < 5; k++) {
                            if(j+k == board.length || i-k < 0 || board[i-k][j+k] != 2){
                                check = false;
                                break;
                            }
                        }
                        // 오목인 경우, 맨끝 다음칸 확인
                        if(check && (j+5== board.length || i-5==-1 ||board[i-5][j+5]!=2)){
                            white = true;
                            answerX = i;
                            answerY = j;
                            break label;
                        }
                    }

                    // 4. 오른쪽 대각선 아래 : row가 첫칸이거나, column이 맨 첫칸이거나, 이전 왼쪽 대각선 윗칸이 같은 색이 아닌 경우 진입
                    if(i==0 || j==0 || board[i-1][j-1]!=2){
                        // 본인+오른쪽 아래 4개칸 확인, 오목이 만들어지지 않는 경우 중단
                        boolean check = true;
                        for (int k = 0; k < 5; k++) {
                            if(i+k == board.length || j+k==board.length || board[i+k][j+k] != 2){
                                check = false;
                                break;
                            }
                        }
                        // 오목인 경우, 맨끝 다음칸 확인
                        if(check && (i+5== board.length || j+5== board.length || board[i+5][j+5]!=2)){
                            white = true;
                            answerX = i;
                            answerY = j;
                            break label;
                        }
                    }
                }
            }
        }

        int winner = 0;
        if(black){
            winner = 1;
        }
        else if (white) {
            winner = 2;
        }

        System.out.println(winner);
        if(winner!=0){
            System.out.println((answerX+1)+" "+(answerY+1));
        }
    }
}
