import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] parties;
    static List<Integer> truthPeople;
    static int maxParties = Integer.MIN_VALUE;

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

        parties = new List[partyNum];
        boolean[] partiesCheck = new boolean[partyNum];

        for (int i = 0; i < parties.length; i++) {
            // 각 파티의 참가인원 리스트에 넣어주기
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

        for (int i = 0; i < partiesCheck.length; i++) {
            if(partiesCheck[i]){
                eachParty(i);
            }
        }
        int count = 0;
        for (int i = 0; i < parties.length; i++) {
            if(!parties[i].isEmpty()){
                count++;
            }
        }
        System.out.println(count);
    }
    static void eachParty(int index){
        if(parties[index].isEmpty()){
            return;
        }

        // Party List Copy
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < parties[index].size(); i++) {
            temp.add(parties[index].get(i));
        }
        parties[index] = new ArrayList<>();

        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < parties.length; j++) {
                if(parties[j].contains(temp.get(i))){
                    eachParty(j);
                }
            }
        }
    }
}
