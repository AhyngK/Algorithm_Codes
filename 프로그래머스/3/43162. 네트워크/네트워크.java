import java.util.*;

class Solution {
    List<List<Integer>> graph = new ArrayList();
    int[] mapping;
    int count = 0;
    
    public int solution(int n, int[][] computers) {
        mapping = new int[computers.length];
        Arrays.fill(mapping, -1);
        
        for(int i=0; i<computers.length; i++){
            graph.add(new ArrayList());
        }
        
        for(int i=0; i<computers.length; i++){
            for(int j=i+1; j<computers.length; j++){
                if(computers[i][j] == 1){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        figureNetworks();
        return count;
    }
    
    private void figureNetworks(){
        for(int i=0; i<mapping.length; i++){
            if(mapping[i] == -1){
                count += 1;
                mapNetworks(i);
            }
        }
    }
    
    private void mapNetworks(int index){
        Queue<Integer> queue = new LinkedList();
        queue.add(index);
        
        while(!queue.isEmpty()){
            int current = queue.poll();
            mapping[current] = count;
            for(int i=0; i<graph.get(current).size(); i++){
                if(mapping[graph.get(current).get(i)] == -1){
                    queue.add(graph.get(current).get(i));
                }
            }
        }
    }
}