package com.example.utilitiesservice.services;

import com.example.utilitiesservice.models.Bill;
import com.example.utilitiesservice.models.UtilityMeter;
import com.example.utilitiesservice.repositories.BillRepository;
import com.example.utilitiesservice.repositories.UtilityMeterRepository;
import com.example.utilitiesservice.stubs.BillStub;
import com.example.utilitiesservice.stubs.UtilityMeterStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UtilityMeterServiceTest {

    private UtilityMeterService service;

    public final Long ID = UtilityMeterStub.ID;
    public final Integer PAGE = UtilityMeterStub.PAGE;
    public final Integer SIZE = UtilityMeterStub.SIZE;
    public final UtilityMeter getSomeItem(){
        return UtilityMeterStub.getSomeUtilityMeter();
    }


    @Mock
    private UtilityMeterRepository repository;

    @BeforeEach()
    void setup() {
        service = new UtilityMeterService(repository);
    }

    @Test
    void testSuccessfulGetAll() {
        var item = getSomeItem();
        List<UtilityMeter> itemList = new ArrayList<>(Arrays.asList(item));
        Page<UtilityMeter> pageItemList = new PageImpl<>(itemList);
        Pageable paging = PageRequest.of(PAGE, SIZE);

        Mockito.when(repository.findAll(paging)).thenReturn(pageItemList);
        var result = service.getAll(PAGE, SIZE);

        assertEquals(result.size(), itemList.size());

    }

    @Test
    void testSuccessfulGetById() {
        var item = getSomeItem();
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(item));
        var result = service.getById(ID);
        assertAll(
                () -> assertEquals(result.get().getId(), item.getId()),
                () -> assertEquals(result.get().getCurrentIndicators(), item.getCurrentIndicators())
        );
    }

    @Test
    void testNoSuccessfulGetById() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        var result = service.getById(ID);
        assertEquals(result, Optional.empty());

    }

    @Test
    void testSuccessfulSave() {
        var captor = ArgumentCaptor.forClass(UtilityMeter.class);
        var item = getSomeItem();
        Mockito.when(repository.save(Mockito.any())).thenReturn(getSomeItem());
        var result = service.save(getSomeItem());
        Mockito.verify(repository, Mockito.atLeast(1)).save(captor.capture());
        assertAll(
                () -> assertEquals(item, captor.getValue()),
                () -> assertEquals(result.getCurrentIndicators(), item.getCurrentIndicators())
        );
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(repository, Mockito.atLeast(1)).deleteById(captor.capture());
        assertEquals(ID, captor.getValue());
    }
}
