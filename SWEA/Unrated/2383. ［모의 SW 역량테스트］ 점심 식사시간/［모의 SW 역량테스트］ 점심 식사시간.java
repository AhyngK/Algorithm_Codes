import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	static int minTime = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			minTime = Integer.MAX_VALUE;
			int size = Integer.parseInt(br.readLine());
			int[][] rooms = new int[size][size];
			for(int i=0; i<rooms.length; i++) {
				rooms[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			// 계단 위치 파악, 사람들 수 파악
			int[][] stairs = findStairs(rooms);
			List<int[]> people = findPeople(rooms, stairs);
			
			// 팀 나누는 재귀 호출
			List<int[]> firstStair = new ArrayList<>();
			List<int[]> secondStair = new ArrayList<>();
			recursionSeperate(0, people, firstStair, secondStair, stairs);
			
			System.out.println("#"+tc+" "+minTime);
		}
		
	}
	// 두 그룹의 사람들에 대해 계단 소요 시간 판별 메서드
	static int timeSum(List<int[]> firstStair, List<int[]> secondStair, int[][] stair) {
		List<int[]> firstTemp = new ArrayList<>();
		List<int[]> secondTemp = new ArrayList<>();
		
		for(int i=0; i<firstStair.size();i++) {
			int[] temp = firstStair.get(i).clone();
			firstTemp.add(temp);
		}
		for(int i=0; i<secondStair.size();i++) {
			int[] temp = secondStair.get(i).clone();
			secondTemp.add(temp);
		}
		
		// 정렬 조건 정리
		Comparator<int[]> firstStairPeople = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2]- o2[2];
			}
		};
		Comparator<int[]> secondStairPeople = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[3]-o2[3];
			}
		};
		
		// 각각 계단 최소시간으로 정렬
		Collections.sort(firstTemp,firstStairPeople);
		Collections.sort(secondTemp,secondStairPeople);
		
		int time = 0;
		
		List<int[]> firstStairWaitingQue = new LinkedList<>();
		List<int[]> secondStairWaitingQue = new LinkedList<>();
		
		while(true) {
			time+=1;
			
			// Que waiting
			int index = 0;
			int index1 = 0;
			int person =3;
			int person1 =3;
			
			while(index<person && index<firstStairWaitingQue.size()) {
				if(firstStairWaitingQue.get(index)[4]==0) {
					firstStairWaitingQue.remove(index);
					continue;
					}
				firstStairWaitingQue.get(index)[4]-=1;
				index+=1;
			}
			while(index1<person1 && index1<secondStairWaitingQue.size()) {
				if(secondStairWaitingQue.get(index1)[4]==0) {
					secondStairWaitingQue.remove(index1);
					continue;
					}
				secondStairWaitingQue.get(index1)[4]-=1;
				index1+=1;
			}			
	
			// 사람들 이동시키기
			if(firstTemp.size()!=0) {
				// 사람들 순회하며 이동, 기다림 시간 체크
				for(int i=0; i<firstTemp.size(); i++) {
					// move
					firstTemp.get(i)[2]-=1;
					// 도착한 경우 대기 큐에 추가
					if(firstTemp.get(i)[2]==0) {
						// countdown start
						firstTemp.get(i)[4] = stair[0][2];
						firstStairWaitingQue.add(firstTemp.get(i));
						firstTemp.remove(i);
						i--;
						continue;
					}
				}
			}
			if(secondTemp.size()!=0) {
				for(int i=0; i<secondTemp.size(); i++) {
					// move
					secondTemp.get(i)[3]-=1;
					// 도착한 경우 대기 큐에 추가
					if(secondTemp.get(i)[3]==0) {
						// countdown start
						secondTemp.get(i)[4] = stair[1][2];
						secondStairWaitingQue.add(secondTemp.get(i));
						secondTemp.remove(i);
						i--;
						continue;
					}
				}
			}
			
			if(firstTemp.isEmpty() && secondTemp.isEmpty() && firstStairWaitingQue.isEmpty() && secondStairWaitingQue.isEmpty()) {
				break;
			}
		}
		return time;
	}
	
	
	// 각각 사람을 두 계단으로 나누는 메서드
	static void recursionSeperate(int index, List<int[]> people, List<int[]> firstStair, List<int[]> secondStair, int[][] stairs) {
		// reached the end of people
		if(index==people.size()) {
			// 시간 재는 함수 호출
			int currentTime = timeSum(firstStair, secondStair, stairs);
			minTime = Math.min(currentTime, minTime);
//			System.out.println("one end");
			return;
		}
		
		// choose firstStair
		firstStair.add(people.get(index));
		recursionSeperate(index+1, people, firstStair, secondStair, stairs);
		firstStair.remove(people.get(index));
		
		// choose secondStair
		secondStair.add(people.get(index));
		recursionSeperate(index+1, people, firstStair, secondStair, stairs);
		secondStair.remove(people.get(index));
	}
	
	// 계단 두 곳의 위치, 길이 저장
	static int[][] findStairs(int[][] rooms) {
		int[][] stairs = new int[2][3];
		int index = 0;
		for(int i=0; i<rooms.length; i++) {
			for(int j=0; j<rooms.length; j++) {
				if(rooms[i][j]>=2) {
					stairs[index] = new int[] {i,j,rooms[i][j]};
					index++;
				}
			}
		}
		return stairs;
	}
	
	// 사람들의 위치 및 계단 두곳까지의 거리 저장
	static List<int[]> findPeople (int[][] rooms, int[][] stairs){
		List<int[]> people = new ArrayList<>();
		for(int i=0; i<rooms.length; i++) {
			for(int j=0; j<rooms.length; j++) {
				if(rooms[i][j]==1) {
					// one Person
					int[] onePerson = new int[5];
					onePerson[0] = i; // x
					onePerson[1] = j; // y
					onePerson[2] = Math.abs(i-stairs[0][0])+Math.abs(j-stairs[0][1]);
					onePerson[3] = Math.abs(i-stairs[1][0])+Math.abs(j-stairs[1][1]);	
					onePerson[4] = 0;
					people.add(onePerson);
				}
			}
		}
		return people;
	}
}
