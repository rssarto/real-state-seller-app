package com.realstate.realstatesellerapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "RISK_FACTOR_THRESHOLD")
@IdClass(value = RiskFactorThreshold.RiskFactorThresholdId.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RiskFactorThreshold {
	
	@Id
	@OneToOne
    @JoinColumn(referencedColumnName = "id")
	private AccountType accountType;
	
	@Id
	private Integer threshold;
	
	@Enumerated(EnumType.STRING)
	private RiskType riskType;
	
	@EqualsAndHashCode
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class RiskFactorThresholdId implements Serializable {
		private AccountType accountType;
		private Integer threshold;
	}
	
	private enum RiskType {
		LOW, MEDIUM, HIGH
	}

}
