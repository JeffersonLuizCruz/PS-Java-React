package br.com.banco.domain.filter.specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.banco.domain.filter.specification.service.TransferSpecService.FilterTransfer;
import br.com.banco.domain.model.BankAccount;
import br.com.banco.domain.model.BankTransfer;

public class TransferSpec {

	public static Specification<BankTransfer> nameLike(FilterTransfer filter) {
	    return (root, query, cb) -> {
	        Join<BankTransfer, BankAccount> bankAccountJoin = root.join("bankAccount");
	        return cb.like(cb.upper(bankAccountJoin.get("owner")), "%" + filter.getOwner().toUpperCase() + "%");
	    };
	}
	
	public static Specification<BankTransfer> dateBetween(LocalDate dateMin, LocalDate dateMax) {
	    return (root, query, cb) -> {
	        List<Predicate> predicates = new ArrayList<>();
	        
	        if (dateMin != null && dateMax != null) {
	            predicates.add(cb.between(root.get("dateAt"), dateMin, dateMax));
	        }
	        else if (dateMin != null) {
	            predicates.add(cb.greaterThan(root.get("dateAt"), dateMin));
	        }
	        else if (dateMax != null) {
	            predicates.add(cb.lessThan(root.get("dateAt"), dateMax));
	        }

	        return cb.and(predicates.toArray(new Predicate[0]));
	    };
	}
}
