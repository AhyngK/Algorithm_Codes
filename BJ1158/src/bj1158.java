import java.util.ArrayList;
import java.util.Scanner;

public class bj1158 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int personNum = scanner.nextInt();
        int deleteNum = scanner.nextInt();

        LinkedList ls = new LinkedList();
        ls.Head = new Node(1);
        Node a = ls.Head;

        // 여기서 리스트에 변수를 넣을 때, Head가 정의되어 있지 않으면 nullpointer오류 발생
        // a.next도 null이 아니게 해야 함
        // 이부분 오류 때문에 한참 걸림
        for (int i = 2; i <=personNum ; i++) {
            ls.add(a,i);
            a = a.next;
        }

        ArrayList<Integer> nums = new ArrayList<>();
        while (!ls.isEmpty()){
            Node x = ls.Head;
            // 맨 끝에 Head 하나 남았을 때 뽑기
            if(ls.Head.next==null){
                nums.add(ls.Head.number);
                break;
            }
            // Head를 안뽑아주면 여기 next 부분에서 오류가 날 것 같다
            for (int i = 1; i < deleteNum; i++) {
                x=x.next;
            }
            ls.Head = x;
            nums.add(ls.delete(x));
        }

        System.out.print("<");
        for (int i = 0; i < nums.size()-1; i++) {
            System.out.print(nums.get(i)+", ");
        }
        System.out.print(nums.get(nums.size()-1)+">");
    }
}

class LinkedList{
    Node Head;

    LinkedList(){
        Head =null;}
    void add (Node pre, int data){
        // 원형 연결리스트를 위해 두번재 요소 미리 파악한 뒤 Head에 next를 연결시켜 둬야 함
        if(Head.next==null){
            Node second = new Node(data);
            Head.next =second;
            second.next = Head;
        }
        else {
            Node node = new Node(data);
            node.next = pre.next;
            pre.next = node;
        }
    }
    int delete(Node cur){
        // 노드가 하나도 없을 때
        if(isEmpty()){
            return 0;
        }
        // 만약 원소 한개가 남았을때, cur은 Head일 것이다
        else if(cur==cur.next){
            Head=null;
            return cur.number;
        }
        else {
            Node prev = find(cur);
            prev.next = cur.next;
            if (Head == cur) {
                Head = cur.next;
            }
            return cur.number;
        }
    }

    Node find(Node target){
        Node x = Head;
        while(x.next!=target){
            x=x.next;
        }
        return x;
    }

    boolean isEmpty (){
        if(Head==null){
            return true;
        }
        return false;
    }
}

class Node{
    int number;
    Node next;

    Node(){}
    Node(int data){
        number =data;
    }
}
