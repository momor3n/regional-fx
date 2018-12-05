package ifasthq.fx.regionalfx.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FxTransaction {
	private String quoteName;
	private String orderId;
	private String fromCurrency;
	private String toCurrency;
	private BigDecimal fromAmount;
	private BigDecimal toAmount;
	private BigDecimal execRate;
	private LocalDateTime execTime;

	public FxTransaction() {
	}

	public FxTransaction(String quoteName, String orderId, String fromCurrency, String toCurrency, BigDecimal fromAmount, BigDecimal toAmount, BigDecimal execRate, LocalDateTime execTime) {
		this.quoteName = quoteName;
		this.orderId = orderId;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.fromAmount = fromAmount;
		this.toAmount = toAmount;
		this.execRate = execRate;
		this.execTime = execTime;
	}

	@Override
	public String toString() {
		return "FxTransaction{" +
			"quoteName='" + quoteName + '\'' +
			", orderId='" + orderId + '\'' +
			", fromCurrency='" + fromCurrency + '\'' +
			", toCurrency='" + toCurrency + '\'' +
			", fromAmount=" + fromAmount +
			", toAmount=" + toAmount +
			", execRate=" + execRate +
			", execTime=" + execTime +
			'}';
	}

	public String getQuoteName() {
		return quoteName;
	}

	public void setQuoteName(String quoteName) {
		this.quoteName = quoteName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public BigDecimal getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}

	public BigDecimal getToAmount() {
		return toAmount;
	}

	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
	}

	public BigDecimal getExecRate() {
		return execRate;
	}

	public void setExecRate(BigDecimal execRate) {
		this.execRate = execRate;
	}

	public LocalDateTime getExecTime() {
		return execTime;
	}

	public void setExecTime(LocalDateTime execTime) {
		this.execTime = execTime;
	}
}
