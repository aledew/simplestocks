package it.dewi.supersimplestocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class StockHandler {
	
	private static Map<GlobalBeverageCorpExchange, List<Trade>> tradeMap = new HashMap<GlobalBeverageCorpExchange, List<Trade>>();
	private static Random 		randGen = new Random();
	private static Map<GlobalBeverageCorpExchange, Double> gbceTotals= new HashMap<GlobalBeverageCorpExchange, Double>();

	public static void main(String[] args) {
		
		Random rand = new Random();
		
		List<GlobalBeverageCorpExchange> stockList =  Collections.unmodifiableList(Arrays.asList(GlobalBeverageCorpExchange.values()));
		
		/*Generate a random ticket price with a limit of 100*/
		double randNum = randGen.nextInt(100) + randGen.nextDouble();
		
		System.out.println("Calculate Dividend Yeld and P/E Ratio");
		
			
		for(GlobalBeverageCorpExchange stockSym: GlobalBeverageCorpExchange.values()){
			
			System.out.println(stockSym.name());
			System.out.println("Dividend Yeld ::" + stockSym.calculateDividendYeld(randNum));
			System.out.println("P/E Ratio ::" + stockSym.calcPERatio(randNum));
			
		}
		
		System.out.println("----------------------------------------------------");
		
		
		System.out.println("Record trades");
		int tradesNumber = randGen.nextInt(100);

		//Fill tradeList with a randomNumber of trades
		for (int i=0; i<=tradesNumber;i++){
			Trade trd = TradeBuilder.create(stockList.get(rand.nextInt(stockList.size())));
			
			System.out.println(trd.toString());
			List<Trade> listKey = null;
			if(tradeMap.get(trd.getGbce())==null){
				listKey = new ArrayList<Trade>();
				listKey.add(trd);
				tradeMap.put(trd.getGbce(), listKey);
			} else{
				listKey = tradeMap.get(trd.getGbce());
				listKey.add(trd);
			}
			//tradelist.add(TradeBuilder.create(stockList.get(rand.nextInt(stockList.size()))));
			
		}
		
		// Calculate Totals for every single stock Type
		for(GlobalBeverageCorpExchange gbce: tradeMap.keySet()){
			
			
			gbceTotals.put(gbce, stockPrice(tradeMap.get(gbce)));
		}
		
		//Print totals 
		System.out.println("Totals");
		
		for(GlobalBeverageCorpExchange total : gbceTotals.keySet()){
			
			System.out.println(total + " " + gbceTotals.get(total));
		}
		
		//Print All share Index
		System.out.println("All Share Index");
		
		
		System.out.println("All Share ::" + allShareIndex());
	}

	public static double stockPrice (List<Trade> tradelist){
		
		GregorianCalendar gc = new GregorianCalendar();
		
		gc.setTime(new Date());
		
		gc.add(Calendar.MINUTE, -15);
		
		double stockPrice = 0d;
		
		for(Trade trd : tradelist){
			
			if(trd.getTimestamp().after(gc.getTime())){
				
				stockPrice +=trd.getPrice()* tradelist.size()/tradelist.size();
				
			}
		}
		
		return stockPrice;
	}
	
	public static double allShareIndex(){
		
		double allShareTot= 0d;
		
		for(GlobalBeverageCorpExchange total : gbceTotals.keySet()){
			
			allShareTot *= gbceTotals.get(total);
		}
		
			
		return nthRoot(gbceTotals.size(), allShareTot);
	}
	

	
	/*Not mine found it googling . I was not able to develop an efficient one myself*/

    private static double nthRoot(int n, double A) {
          double x0 = 1;
          boolean accurate = false;
          while (!accurate) {
              double x1 = (1 / (double)n) * ((n - 1) * x0 + A / pow(x0, n - 1));
              accurate = accurate(x0, x1);
              x0 = x1;
          }
          return x0;
      }
   
   
      private static boolean accurate(double x0, double x1) {
          return Math.abs(x1-x0) < 0.000001;
      }



      static double pow(double x, int n) {
             if(n == 0) {
                 return 1;
             }
             return x * pow(x, n-1);
         }


	
}
