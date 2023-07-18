package br.com.banco.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.domain.filter.specification.service.TransferSpecService.FilterTransfer;
import br.com.banco.domain.model.BankTransfer;
import br.com.banco.domain.service.impl.BankTransferServiceImpl;
import lombok.AllArgsConstructor;

@RestController @AllArgsConstructor
@RequestMapping(value = "/transfers")
public class BankTransferController {

	@Autowired private final BankTransferServiceImpl bankTransferService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<BankTransfer>> findAllByBankAccountId(@PathVariable Long id){
		List<BankTransfer> entity = bankTransferService.findAllByBankAccountId(id);
		return ResponseEntity.ok(entity);
	}
	
	@GetMapping
	public ResponseEntity<List<BankTransfer>> filterTransfer(
	        @RequestParam(name = "owner", required = false) String owner,
	        @RequestParam(name = "dateMin", required = false) String dateMin,
	        @RequestParam(name = "dateMax", required = false) String dateMax
	) {

	    FilterTransfer filter = FilterTransfer.builder()
	            .owner(owner)
	            .dateMin(dateMin)
	            .dateMax(dateMax)
	            .build();

	    List<BankTransfer> filterTransfer = bankTransferService.filterTransfer(filter);
	    return ResponseEntity.ok().body(filterTransfer);
	}

}
