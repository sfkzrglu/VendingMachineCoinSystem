package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App 
{
    public static void main( String[] args ) throws IOException 
    {
        Database db=new Database();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("Enter fee:");
            String inputStr = reader.readLine();
            double intputValue=Double.parseDouble(inputStr)*100;
            int max=0;
            //5pln to 1gr
            int[] spentCoins=new int[]{0,0,0,0,0,0,0,0,0};
            while(intputValue!=0)
            {
                CoinTypes biggestCoin=CoinTypes.findBiggestCoinToSubtract(intputValue,max);
                if (biggestCoin!=null) {
                    int coinAmount=db.getAmount(biggestCoin);
                    if (coinAmount>0) {
                        intputValue-=biggestCoin.getValue()*100;
                        coinAmount--;
                        db.update(biggestCoin, coinAmount); 
                        spentCoins[biggestCoin.ordinal()]++;
                        
                    }else{
                        max++;
                    }   
                }else{
                      System.out.println("no coin left");
                      break;
                }     
            }
            for (int i = 0; i < spentCoins.length; i++) {
                if (spentCoins[i]>0) {
                  System.out.println("spend "+spentCoins[i]+" "+CoinTypes.values()[i]);  
                 }           
            }
        }
        
    }

}
