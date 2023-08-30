import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder a = new StringBuilder();
    static StringBuilder b = new StringBuilder();
    static StringBuilder c = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int Nodes = Integer.parseInt(br.readLine());
        Node[] tree = new Node[Nodes];
        String[][] treeTemp = new String[tree.length][2];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }

        for (int i = 0; i < Nodes; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i].data = st.nextToken();
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            if(!s1.equals(".")){
                treeTemp[i][0] = s1;
            }
            if(!s2.equals(".")){
                treeTemp[i][1] = s2;
            }
        }

        for (int i = 0; i < treeTemp.length; i++) {
            if(treeTemp[i][0]!=null){
                int index = findIndex(treeTemp[i][0], tree);
                tree[i].leftChild = tree[index];
            }
            if(treeTemp[i][1]!=null){
                int index = findIndex(treeTemp[i][1], tree);
                tree[i].rightChild = tree[index];
            }
        }
        travel(tree[0]);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
    static void travel(Node current){
        a.append(current.data);
        if(current.leftChild!=null){
            travel(current.leftChild);
        }
        b.append(current.data);
        if(current.rightChild!=null){
            travel(current.rightChild);
        }
        c.append(current.data);
    }
    static int findIndex(String s, Node[] tree){
        for (int i = 0; i < tree.length; i++) {
            if(tree[i].data.equals(s)){
                return i;
            }
        }
        return -1;
    }
    static class Node {
        String data;
        Node leftChild;
        Node rightChild;
        Node(){}
        Node(String data){
            this.data = data;
        }
    }
}