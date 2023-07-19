package br.com.banco.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.banco.domain.filter.specification.service.TransferSpecService.FilterTransfer;
import br.com.banco.domain.model.BankAccount;
import br.com.banco.domain.model.BankTransfer;
import br.com.banco.domain.model.enums.AccountType;
import br.com.banco.domain.service.impl.BankTransferServiceImpl;

@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BankTransferControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean 
	BankTransferServiceImpl transferSpecService;
	
	private FilterTransfer filterTransfer;
	private static final String URI_TRANSFERS = "/transfers?owner=Fulano&dateMin=01-01-2019&dateMax=01-01-2020";
	
	@BeforeEach
	public void setUp() {
		this.filterTransfer = FilterTransfer.builder()
				.owner("Fulano")
				.dateMin("01-01-2019")
				.dateMax("01-01-2020")
				.build();
	}
	
	@Test
	@DisplayName("Deve filtrar com sucesso")
	public void filterSucesso() throws Exception {
		List<BankTransfer> mockTransfers = new ArrayList<>();
		BankAccount mockAccount = BankAccount.builder()
												.id(1L)
												.owner("Fulano")
												.build();
		
		BankTransfer tranfer = BankTransfer.builder()
											.id(1L)
											.value(new BigDecimal("100.00"))
											.accountType(AccountType.DEPOSITO)
											.operator("Beltrano")
											.bankAccount(mockAccount)
											.build();
		mockTransfers.add(tranfer);
		
		BDDMockito.given(transferSpecService.filterTransfers(this.filterTransfer)).willReturn(mockTransfers);
		String json = new ObjectMapper().writeValueAsString(filterTransfer);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(URI_TRANSFERS)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json);
		
		mvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@DisplayName("Deve falahar na realizacao do filtro")
	public void deveFalhar() throws Exception {
		List<BankTransfer> mockTransfers = new ArrayList<>();
		BankAccount mockAccount = BankAccount.builder()
												.id(1L)
												.owner("Fulano")
												.build();
		
		BankTransfer tranfer = BankTransfer.builder()
											.id(1L)
											.value(new BigDecimal("100.00"))
											.accountType(AccountType.DEPOSITO)
											.operator("Beltrano")
											.bankAccount(mockAccount)
											.build();
		mockTransfers.add(tranfer);
		
		BDDMockito.given(transferSpecService.filterTransfers(this.filterTransfer)).willReturn(mockTransfers);
		String json = new ObjectMapper().writeValueAsString(filterTransfer);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(URI_TRANSFERS)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json);
		
		mvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}


}
