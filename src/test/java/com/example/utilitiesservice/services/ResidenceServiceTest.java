package com.example.utilitiesservice.services;

import com.example.utilitiesservice.models.AccountRole;
import com.example.utilitiesservice.models.Residence;
import com.example.utilitiesservice.models.User;
import com.example.utilitiesservice.repositories.ResidenceRepository;
import com.example.utilitiesservice.repositories.UserRepository;
import com.example.utilitiesservice.stubs.ResidenceStub;
import com.example.utilitiesservice.stubs.UserStub;
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
public class ResidenceServiceTest {

    private ResidenceService service;

    public final Long ID = UserStub.ID;
    public final Integer PAGE = UserStub.PAGE;
    public final Integer SIZE = UserStub.SIZE;
    public final Residence getSomeItem(){
        return ResidenceStub.getSomeResidence();
    }


    @Mock
    private ResidenceRepository repository;

    @BeforeEach()
    void setup() {
        service = new ResidenceService(repository);
    }

    @Test
    void testSuccessfulGetAll() {
        var item = getSomeItem();
        List<Residence> itemList = new ArrayList<>(Arrays.asList(item));
        Page<Residence> pageItemList = new PageImpl<>(itemList);
        Pageable paging = PageRequest.of(PAGE, SIZE);

        Mockito.when(repository.findAll(paging)).thenReturn(pageItemList);
        var result = service.getAll(PAGE, SIZE);

        assertEquals(result.size(), itemList.size());

    }

    @Test
    void testSuccessfulGetByCountryOrCityOrStreet() {
        var item = getSomeItem();
        List<Residence> itemList = new ArrayList<>(Arrays.asList(item));
        Mockito.when(repository.findByCountryOrCityOrStreet(ID, "New-York")).thenReturn(itemList);
        var result = service.getByCountryOrCityOrStreet(ID, "New-York");
        assertEquals(result.size(), itemList.size());
    }

    @Test
    void testSuccessfulGetById() {
        var item = getSomeItem();
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(item));
        var result = service.getById(ID);
        assertAll(
                () -> assertEquals(result.get().getId(), item.getId()),
                () -> assertEquals(result.get().getCity(), item.getCity()),
                () -> assertEquals(result.get().getHouse(), item.getHouse()),
                () -> assertEquals(result.get().getCountry(), item.getCountry())
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
        var captor = ArgumentCaptor.forClass(Residence.class);
        var item = getSomeItem();
        Mockito.when(repository.save(Mockito.any())).thenReturn(getSomeItem());
        var result = service.save(getSomeItem());
        Mockito.verify(repository, Mockito.atLeast(1)).save(captor.capture());
        assertAll(
                () -> assertEquals(item, captor.getValue()),
                () -> assertEquals(result.getCity(), item.getCity()),
                () -> assertEquals(result.getHouse(), item.getHouse()),
                () -> assertEquals(result.getCountry(), item.getCountry())
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
