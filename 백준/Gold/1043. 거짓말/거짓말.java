import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] parties;
    static List<Integer> truthPeople;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int peopleNum = Integer.parseInt(st.nextToken());
        int partyNum = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int truthPeopleNum = Integer.parseInt(st.nextToken());
        truthPeople = new ArrayList<>();
        for (int i = 0; i < truthPeopleNum; i++) {
            truthPeople.add(Integer.parseInt(st.nextToken()));
        }

        // 파티 배열 안에 리스트로 참가자를 담는 리스트[] 생성, 참가자 담기
        parties = new List[partyNum];
        boolean[] partiesCheck = new boolean[partyNum];
        for (int i = 0; i < parties.length; i++) {

            parties[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int partyPeople = Integer.parseInt(st.nextToken());
            for (int j = 0; j < partyPeople; j++) {
                int currentPerson = Integer.parseInt(st.nextToken());
                parties[i].add(currentPerson);
                // 만약 현재 참여자가 진실을 알고 있다면, 진실을 말해야 하는 파티로 표기
                if(truthPeople.contains(currentPerson)){
                    partiesCheck[i] = true;
                }
            }
        }

        // 각 파티를 순회하며 진실을 말해야만 하는 파티인지 확인, 만약 맞다면 함수 호출
        for (int i = 0; i < partiesCheck.length; i++) {
            if(partiesCheck[i]){
                eachParty(i);
            }
        }
        // 파티 삭제 끝난 뒤 남은 파티의 개수 세기, 반환
        int count = 0;
        for (int i = 0; i < parties.length; i++) {
            if(!parties[i].isEmpty()){
                count++;
            }
        }
        System.out.println(count);
    }
    // 이번 파티가 진실을 말해야만 하는 파티인 경우, 연관된 모든 파티를 삭제하는 메서드
    static void eachParty(int index){

        // 만약 현재 파티가 비어있을 경우 반환
        if(parties[index].isEmpty()){
            return;
        }

        // 현재 파티가 비어있지 않을 경우, 삭제할 예정이므로 리스트에 복사해둔 뒤 삭제
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < parties[index].size(); i++) {
            temp.add(parties[index].get(i));
        }
        parties[index] = new ArrayList<>();

        // 모든 파티를 순회하며 현재 파티를 복하해둔 리스트의 원소가 포함되어 있는지 체크, 있다면 함수 재귀 호출
        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < parties.length; j++) {
                if(parties[j].contains(temp.get(i))){
                    eachParty(j);
                }
            }
        }
    }
}