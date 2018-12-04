package ifasthq.fx.regionalfx.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class ViewRate {

	private String quoteName;
	private BigDecimal bid;
	private BigDecimal ask;
	private String currencyBid;
	private String currencyAsk;
	private double spread;
	@DateTimeFormat(pattern = "yyyy-MM-dd THH:mm:ss")
	private LocalDateTime timestamp;

	public ViewRate() {
	}

	public ViewRate(String quoteName, BigDecimal bid, BigDecimal ask, String currencyBid, String currencyAsk, LocalDateTime timestamp) {
		this.quoteName = quoteName;
		this.bid = bid;
		this.ask = ask;
		this.currencyBid = currencyBid;
		this.currencyAsk = currencyAsk;
		this.timestamp = timestamp;
		spread = bid.subtract(ask).multiply(new BigDecimal(10000)).doubleValue();
	}

	public String getQuoteName() {
		return quoteName;
	}

	public void setQuoteName(String quoteName) {
		this.quoteName = quoteName;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public BigDecimal getAsk() {
		return ask;
	}

	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}

	public String getCurrencyBid() {
		return currencyBid;
	}

	public void setCurrencyBid(String currencyBid) {
		this.currencyBid = currencyBid;
	}

	public String getCurrencyAsk() {
		return currencyAsk;
	}

	public void setCurrencyAsk(String currencyAsk) {
		this.currencyAsk = currencyAsk;
	}

	public double getSpread() {
		return spread;
	}

	public void setSpread(double spread) {
		this.spread = spread;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
