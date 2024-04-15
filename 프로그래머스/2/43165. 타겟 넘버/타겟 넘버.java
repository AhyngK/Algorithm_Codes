import java.util.*;
import java.io.*;

class Solution {
    
    int count;
    int target;
    int[] numbers;
    
    public int solution(int[] numbers, int target) {
        this.target = target;
        this.numbers = numbers;
        recursion(0, new boolean[numbers.length]);
        return count;
    }
    void recursion(int index, boolean[] isPlus){
        if(index == isPlus.length){
            figureAnswer(isPlus);
            return;
        }
        recursion(index+1, isPlus); 
        isPlus[index] = true;
        recursion(index+1, isPlus);    
        isPlus[index] = false;
    }
    void figureAnswer(boolean[] isPlus){
        int temp = 0;
        for(int i=0; i<isPlus.length; i++){
            if(isPlus[i]){
                temp += numbers[i];
                continue;
            }
            temp -= numbers[i];
        }
        if(temp == target){
            count++;
        }
    }
}