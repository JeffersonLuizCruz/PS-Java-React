package br.com.banco.domain.filter.specification.service;

import java.io.Serializable;
import java.util.List;

import br.com.banco.domain.model.BankTransfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface TransferSpecService {
	List<BankTransfer> filterTransfer(FilterTransfer filter);
	
	@Builder
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor
	public class FilterTransfer implements Serializable{
		private static final long serialVersionUID = 969599348207428119L;
		
		private String dateMin;
		private String dateMax;
		private String nameOperator;
	}
}
