package ifasthq.fx.regionalfx.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NettingReport {
	private LocalDate transactDate;
	private LocalDateTime reportTime;
	private BigDecimal totalValueRequestSGD;
	private BigDecimal totalValueConvertSGD;
	private BigDecimal totalValueReduceSGD;
	private BigDecimal costSavingSGD;
	private BigDecimal totalSurplusSGD;
	private BigDecimal totalProfitSGD;

	public NettingReport() {
	}

	public NettingReport(LocalDate transactDate, LocalDateTime reportTime, BigDecimal totalValueRequestSGD, BigDecimal totalValueConvertSGD, BigDecimal totalValueReduceSGD, BigDecimal costSavingSGD, BigDecimal totalSurplusSGD, BigDecimal totalProfitSGD) {
		this.transactDate = transactDate;
		this.reportTime = reportTime;
		this.totalValueRequestSGD = totalValueRequestSGD;
		this.totalValueConvertSGD = totalValueConvertSGD;
		this.totalValueReduceSGD = totalValueReduceSGD;
		this.costSavingSGD = costSavingSGD;
		this.totalSurplusSGD = totalSurplusSGD;
		this.totalProfitSGD = totalProfitSGD;
	}

	@Override
	public String toString() {
		return "NettingReport{" +
			"transactDate=" + transactDate +
			", reportTime=" + reportTime +
			", totalValueRequestSGD=" + totalValueRequestSGD +
			", totalValueConvertSGD=" + totalValueConvertSGD +
			", totalValueReduceSGD=" + totalValueReduceSGD +
			", costSavingSGD=" + costSavingSGD +
			", totalSurplusSGD=" + totalSurplusSGD +
			", totalProfitSGD=" + totalProfitSGD +
			'}';
	}

	public LocalDate getTransactDate() {
		return transactDate;
	}

	public void setTransactDate(LocalDate transactDate) {
		this.transactDate = transactDate;
	}

	public LocalDateTime getReportTime() {
		return reportTime;
	}

	public void setReportTime(LocalDateTime reportTime) {
		this.reportTime = reportTime;
	}

	public BigDecimal getTotalValueRequestSGD() {
		return totalValueRequestSGD;
	}

	public void setTotalValueRequestSGD(BigDecimal totalValueRequestSGD) {
		this.totalValueRequestSGD = totalValueRequestSGD;
	}

	public BigDecimal getTotalValueConvertSGD() {
		return totalValueConvertSGD;
	}

	public void setTotalValueConvertSGD(BigDecimal totalValueConvertSGD) {
		this.totalValueConvertSGD = totalValueConvertSGD;
	}

	public BigDecimal getTotalValueReduceSGD() {
		return totalValueReduceSGD;
	}

	public void setTotalValueReduceSGD(BigDecimal totalValueReduceSGD) {
		this.totalValueReduceSGD = totalValueReduceSGD;
	}

	public BigDecimal getCostSavingSGD() {
		return costSavingSGD;
	}

	public void setCostSavingSGD(BigDecimal costSavingSGD) {
		this.costSavingSGD = costSavingSGD;
	}

	public BigDecimal getTotalSurplusSGD() {
		return totalSurplusSGD;
	}

	public void setTotalSurplusSGD(BigDecimal totalSurplusSGD) {
		this.totalSurplusSGD = totalSurplusSGD;
	}

	public BigDecimal getTotalProfitSGD() {
		return totalProfitSGD;
	}

	public void setTotalProfitSGD(BigDecimal totalProfitSGD) {
		this.totalProfitSGD = totalProfitSGD;
	}
}