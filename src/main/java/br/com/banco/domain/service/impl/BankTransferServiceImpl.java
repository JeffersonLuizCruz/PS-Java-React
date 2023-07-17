package br.com.banco.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.banco.domain.model.BankTransfer;
import br.com.banco.domain.repository.BankTransferRepository;
import br.com.banco.domain.service.BankTransferService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BankTransferServiceImpl implements BankTransferService{

	@Autowired private final BankTransferRepository transferRepository;
	
	@Override
	public List<BankTransfer> findAllByBankAccountId(Long id) {
		return transferRepository.findAllByBankAccountId(id);
	}

}
