package br.com.banco.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.banco.domain.filter.specification.service.TransferSpecService;
import br.com.banco.domain.filter.specification.service.TransferSpecService.FilterTransfer;
import br.com.banco.domain.model.BankTransfer;

@ExtendWith(MockitoExtension.class)
public class BankTransferServiceImplTest {

    @Mock
    private TransferSpecService transferSpecService;

    @Mock
    private FilterTransfer filter = new FilterTransfer();
    
    @BeforeEach
    void setUp() {
        filter = new FilterTransfer();
        filter.setOwner("Fulano");
        filter.setDateMin("01-07-2023");
        filter.setDateMax("31-07-2023");
    }
    
    @Test
    void deveFiltrarComSucesso() {
        List<BankTransfer> mockTransfers = new ArrayList<>();
        when(transferSpecService.filterTransfers(filter)).thenReturn(mockTransfers);

        List<BankTransfer> transfers = transferSpecService.filterTransfers(filter);
        
        for(BankTransfer bt : transfers) {
    		Assertions.assertThat(bt.getId()).isNotNull();
    		Assertions.assertThat(bt.getBankAccount().getOwner()).isEqualTo("Fulano");
        }
        


    }
}
