package ifasthq.fx.regionalfx.model;

import java.math.BigDecimal;

public class FundFlow {
	private String contractNo;
	private String currency;
	private BigDecimal amount;

	public FundFlow() {
	}

	public FundFlow(String contractNo, String currency, BigDecimal amount) {
		this.contractNo = contractNo;
		this.currency = currency;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Convert{" +
			"contractNo='" + contractNo + '\'' +
			", currency='" + currency + '\'' +
			", amount=" + amount +
			'}';
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
