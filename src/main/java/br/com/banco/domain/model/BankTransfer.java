package br.com.banco.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.banco.domain.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "transferencia")
public class BankTransfer implements Serializable{
	private static final long serialVersionUID = -1857930254503303570L;

	@Id @EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "data_transferencia", nullable = false)
	private LocalDate dateAt;
	
	@Column(name = "valor", nullable = false)
	private BigDecimal value;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private AccountType accountType;
	
	@Column(name = "nome_operador_transacao")
	private String operator;
	
	private BigDecimal totalBalance = BigDecimal.ZERO;
	
	@ManyToOne
	@JoinColumn(name = "conta_id", nullable = false)
	private BankAccount bankAccount;
}
