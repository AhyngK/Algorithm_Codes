import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    LinkedList<Integer> deque = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        Main bj = new Main();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <N; i++) {
            String[] s = bf.readLine().split(" ");
            if(s[0].equals("push_back")){
                bj.push_back(Integer.parseInt(s[1]));
            }
            else if (s[0].equals("push_front")) {
                bj.push_front(Integer.parseInt(s[1]));
            }
            else if (s[0].equals("pop_front")) {
                sb.append(bj.pop_front());
                sb.append("\n");
            }
            else if (s[0].equals("pop_back")) {
                sb.append(bj.pop_back());
                sb.append("\n");
            }
            else if (s[0].equals("size")) {
                sb.append(bj.size());
                sb.append("\n");
            }
            else if (s[0].equals("empty")) {
                sb.append(bj.empty());
                sb.append("\n");
            }
            else if (s[0].equals("front")) {
                sb.append(bj.front());
                sb.append("\n");
            }
            else if (s[0].equals("back")) {
                sb.append(bj.back());
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
    void push_front (int x){
        deque.add(0,x);
    }
    void push_back(int x){
        deque.add(x);
    }

    int pop_front(){
        if(size()==0){
            return -1;
        }
        else {
            return deque.removeFirst();
        }
    }

    int pop_back(){
        if(size()==0){
            return -1;
        }
        else {
            return deque.removeLast();
        }
    }

    int size(){
        return deque.size();
    }
    int empty(){
        if(size()==0){
            return 1;
        }
        else {
            return 0;
        }
    }

    int front(){
        if(size()==0){
            return -1;
        }
        else {
            return deque.get(0);
        }

    }

    int back(){
        if(size()==0){
            return -1;
        }
        else {
            return deque.get(deque.size()-1);
        }
    }


}
