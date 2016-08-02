package it.dewi.supersimplestocks;

import java.util.Date;

public class Trade {
	
	private Date 		timestamp;
	private Long 		quantityOfShares;
	public enum 		OperEnum{Buy, Sell};
	private OperEnum 	operation;
	private double		price;
	private GlobalBeverageCorpExchange gbce;
	
	public GlobalBeverageCorpExchange getGbce() {
		return gbce;
	}
	public void setGbce(GlobalBeverageCorpExchange gbce) {
		this.gbce = gbce;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Long getQuantityOfShares() {
		return quantityOfShares;
	}
	public void setQuantityOfShares(Long quantityOfShares) {
		this.quantityOfShares = quantityOfShares;
	}
	public OperEnum getOperation() {
		return operation;
	}
	public void setOperation(OperEnum operation) {
		this.operation = operation;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Trade [timestamp=" + timestamp + ", quantityOfShares=" + quantityOfShares + ", operation=" + operation
				+ ", price=" + price + ", gbce=" + gbce + "]";
	}
	

}
