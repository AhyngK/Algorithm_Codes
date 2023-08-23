import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int switchNum = Integer.parseInt(br.readLine());
		int[] switches = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int studentNum = Integer.parseInt(br.readLine());
		for(int tc=0; tc<studentNum; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			
			if(student == 1) {
				switches = studentY(index, switches);
			}
			else {
				switches = studentX(index, switches);
			}
		}
		int indexAns = 0;
		while(indexAns<switches.length) {
			System.out.print(switches[indexAns]+" ");
			if(indexAns!=0 && (indexAns+1)%20==0) {
				System.out.print("\n");
			}
			indexAns++;
		}
	}
	static int[] studentY(int index, int[] switches) {
		for(int i=0; i<switches.length; i++) {
			if((i+1)%index==0) {
				switches[i] = check(switches[i]);
			}
		}
		return switches;
	}
	static int[] studentX(int index, int[] switches) {
		int realIndex = index-1;
		for(int i=0; i<=switches.length-realIndex; i++) {
			// 첫번째 스위치는 그냥 바꾼다
			if(i==0) {
				switches[realIndex] = check(switches[realIndex]);
			}
			// 첫번째가 아닌 경우, 한칸씩 옆으로 가면서 그 반대것과 같은지 확인
			else {
				int currentIndex = realIndex-i;
				int other = realIndex+i;
				if(currentIndex>=0 && other<switches.length && switches[other]==switches[currentIndex]) {
					switches[currentIndex] = check(switches[currentIndex]);
					switches[other] = check(switches[other]);
				}
				else {
					break;
				}
			}
		}
		return switches;
	}
	static int check(int a) {
		if(a==1) {
			return 0;
		}
		else {
			return 1;
		}
	}

}
