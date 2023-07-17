package br.com.banco.domain.service;

import java.util.List;

import br.com.banco.domain.model.BankTransfer;

public interface BankTransferService {

	List<BankTransfer> findAllByBankAccountId(Long id);
}
