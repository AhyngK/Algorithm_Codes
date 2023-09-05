import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 다이나믹 프로그래밍
    // N의 문제에 대해, N-1의 동일하지만 작은 문제로 나누어 해결
    // 각 일을 상담을 진행 할지, 말지 결정해야 하는 문제
    // 각 일별로 상담이 끝나는 날에 최대값을 기록
    // 각 일별 상담에 대해 (현재 날짜 이전가장 나중에 끝난 상담 금액 + 현재 상담 금액)의 최대값을 갱신해나간다
    // 각 일별 상담에 대해 (이전 상담의 종료로 기록되어 있는 금액, 이전 끝난 상담의 최대 금액) 중 큰 값으로 갱신

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] maxVal = new int[N+1];

        int max = 0;
        for (int i = 1; i < maxVal.length; i++) {
            st = new StringTokenizer(br.readLine());
            int lastingDay = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            // 현재 이전 끝난 상담 중 마지막으로 끝난 상담의 총 합 가격 가져오기
            int lastBiggest = 0;
            for (int j = i-1; j > 0; j--) {
                if(maxVal[j]!=0){
                    lastBiggest = maxVal[j];
                    break;
                }
            }

            // 현재 날에 대해 이전 날짜에 끝난 누적 합과 현재 날짜에 끝난 누적합 중 큰 값을 저장
            maxVal[i] = Math.max(maxVal[i],lastBiggest);

            // 현재 상담이 끝나는 날의 값을 갱신
            // (이전에 끝난 마지막 상담 + 현재 상담의 비용)과 기존 기록되어 있던 비용 중 최대값 산정
            if(i+lastingDay-1<maxVal.length){
                maxVal[i+lastingDay-1] = Math.max(lastBiggest+price,maxVal[i+lastingDay-1]);
            }

            // 가장 큰 값 갱신
            max = Math.max(max,maxVal[i]);
        }
        System.out.println(max);
    }
}
