package br.com.banco.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.banco.domain.model.BankTransfer;

@Repository
public interface BankTransferRepository extends JpaRepository<BankTransfer, Long>{

}