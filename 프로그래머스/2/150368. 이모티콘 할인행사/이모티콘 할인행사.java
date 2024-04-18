import java.util.*;

class Solution {
    
    int[] discounts = {10, 20, 30, 40};
    int[] emoticons;
    int[][] users;
    int[] answer = {0,0};
    
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.emoticons = emoticons;
        this.users = users;
        selectDiscountRate(0, new ArrayList());
        return answer;
    }
    
    private void selectDiscountRate(int index, List<Emoticon> figuredEmoticon){
        if(index == emoticons.length){
            int[] result = figureSoldPrice(figuredEmoticon);
            if(answer[0] < result[0] || (answer[0] == result[0] && answer[1] <= result[1])){
                answer = result.clone();
            }
            return;
        }
        
        for(int i=0; i<discounts.length; i++){
            figuredEmoticon.add(new Emoticon(emoticons[index], discounts[i]));
            selectDiscountRate(index+1, figuredEmoticon);
            figuredEmoticon.remove(figuredEmoticon.size()-1);
        }
    }
    
    private int[] figureSoldPrice(List<Emoticon> figuredEmoticon){
        int[] result = {0,0};
        
        for(int[] user: users){
            int money = 0;
            int plus = 0;
            
            for(Emoticon e: figuredEmoticon){
                if(user[0] <= e.discountRate){
                    money += e.price - (e.price * e.discountRate / 100);
                }
                if(money >= user[1]){
                    plus = 1;
                    money = 0;
                    break;
                }
            }
            result[0] += plus;
            result[1] += money;
        }
        return result;
    }
    
    class Emoticon{
        int price;
        int discountRate;
        
        Emoticon(){}
        Emoticon(int price, int discountRate){
            this.price = price;
            this.discountRate = discountRate;
        }
        
        @Override
        public String toString(){
            return this.price+" "+this.discountRate;
        }
    }
}