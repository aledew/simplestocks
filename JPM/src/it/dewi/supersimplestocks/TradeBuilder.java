package it.dewi.supersimplestocks;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import it.dewi.supersimplestocks.Trade.OperEnum;

public class TradeBuilder {

	public static Trade create(GlobalBeverageCorpExchange gbce){
		
		Random rand = new Random();
		List<OperEnum> operList =  Collections.unmodifiableList(Arrays.asList(OperEnum.values()));
		
		Trade trade  = new Trade();
		trade.setTimestamp(new Date());
		trade.setQuantityOfShares(rand.nextLong());
		trade.setOperation(operList.get(rand.nextInt(operList.size())));
		trade.setPrice(rand.nextDouble());
		trade.setGbce(gbce);
		
		return trade;
	}
}
