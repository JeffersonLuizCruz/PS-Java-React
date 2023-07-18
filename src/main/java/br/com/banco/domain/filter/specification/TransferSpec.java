package br.com.banco.domain.filter.specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.banco.domain.filter.specification.service.TransferSpecService.FilterTransfer;
import br.com.banco.domain.model.BankTransfer;

public class TransferSpec {

	public static Specification<BankTransfer> nameLike(FilterTransfer filter) {
	    return (root, query, cb) -> cb.like(cb.upper(root.get("operator")), "%" + filter.getNameOperator().toUpperCase() + "%");
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
