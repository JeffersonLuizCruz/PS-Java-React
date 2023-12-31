package br.com.banco.domain.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.banco.domain.filter.specification.TransferSpec;
import br.com.banco.domain.filter.specification.service.TransferSpecService;
import br.com.banco.domain.model.BankTransfer;
import br.com.banco.domain.repository.BankTransferRepository;
import br.com.banco.domain.service.BankTransferService;
import lombok.AllArgsConstructor;

@AllArgsConstructor @Service 
public class BankTransferServiceImpl implements BankTransferService, TransferSpecService{

	@Autowired private final BankTransferRepository transferRepository;
	
	@Override
	public List<BankTransfer> findAllByBankAccountId(Long id) {
		return transferRepository.findAllByBankAccountId(id);
	}

	@Override
	public List<BankTransfer> filterTransfers(FilterTransfer filter) {
		Specification<BankTransfer> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(StringUtils.hasText(filter.getOwner())) {
			specs = specs.and(TransferSpec.nameLike(filter));
		}
		
	    LocalDate dateMin = parseLocalDate(filter.getDateMin());
	    LocalDate dateMax = parseLocalDate(filter.getDateMax());
		specs = specs.and(TransferSpec.dateBetween(dateMin, dateMax));
		
		List<BankTransfer> transfers = transferRepository.findAll(specs);
		
        return transfers;
	}
	
	private LocalDate parseLocalDate(String dateStr) {
	    if (dateStr == null || dateStr.isEmpty()) {
	        return null;
	    }

	    try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        return LocalDate.parse(dateStr.replace("/", "-"), formatter);
	    } catch (DateTimeParseException e) {
	    	// TODO - Criar uma exception personalizada
	        // Tratar caso a data fornecida não esteja em formato válido
	        // Pode ser lançada uma exceção, retornar null ou tomar outra ação apropriada
	        e.printStackTrace();
	        return null;
	    }
	}

}
