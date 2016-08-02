package it.dewi.supersimplestocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum GlobalBeverageCorpExchange{
	TEA(ExcType.Common, 0, null, 100), POP(ExcType.Common, 8, null, 100), ALE(ExcType.Common, 23, null, 60), GIN(ExcType.Preferred, 8, 2, 100),
	JOE(ExcType.Common, 13, null, 250);
	
	private enum ExcType{Common, Preferred}
	
	private ExcType type;
	private int 	lastDividend;
	private Integer	fixedDividend;
	private int 	parValue;
	private static 	List<Trade> tradelist = new ArrayList<Trade>();
	private static	Random 		randGen = new Random();
	
	
	GlobalBeverageCorpExchange(ExcType type, int lastDividend, Integer fixedDividend, int parValue){
		
		this.type 			= type;
		this.lastDividend 	= lastDividend;
		this.fixedDividend	= fixedDividend;
		this.parValue		= parValue;
	}
	
	public double calculateDividendYeld(double tickerPrice){
		
		switch(this.type){
			case Common:
				return this.lastDividend / tickerPrice;
				
		
			case Preferred:
				return (this.fixedDividend/this.parValue)/tickerPrice;
				
			default:
				return 0d;
		}
		
	}
	
	public double calcPERatio(double tickerPrice){
		 return tickerPrice/this.lastDividend;
				 
	}
	
	
	
	private void recordTrade(){
		
		tradelist.add(TradeBuilder.create(this));
		
	}
	
	
	
	public static void main(String args[]){
		
		for(GlobalBeverageCorpExchange stockSym: GlobalBeverageCorpExchange.values()){
			
			double randNum = randGen.nextInt(100) + randGen.nextDouble();
			
			System.out.println("random Value::" + randNum);
			
			
			System.out.println(stockSym.name());
			System.out.println("Dividend Yeld ::" + stockSym.calculateDividendYeld(randNum));
			System.out.println("P/E Ratio ::" + stockSym.calcPERatio(randNum));
			
			stockSym.recordTrade();
			
		}
		
		
		System.out.println("recorded Trades::" );
		
		for(Trade tr: tradelist){
			System.out.println(tr.toString());
		}
		
		
	} 
}
