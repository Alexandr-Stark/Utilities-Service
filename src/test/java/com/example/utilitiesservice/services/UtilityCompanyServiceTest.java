package com.example.utilitiesservice.services;

import com.example.utilitiesservice.models.AccountRole;
import com.example.utilitiesservice.models.UtilityCompany;
import com.example.utilitiesservice.repositories.UtilityCompanyRepository;
import com.example.utilitiesservice.stubs.AccountRoleStub;
import com.example.utilitiesservice.stubs.UtilityCompanyStub;
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
class UtilityCompanyServiceTest {

    private UtilityCompanyService service;
    @Mock
    private UtilityCompanyRepository utilityCompanyRepository;

    @BeforeEach()
    void setup() {
        service = new UtilityCompanyService(utilityCompanyRepository);
    }

    @Test
    void testSuccessfulGetAll() {
        var company = UtilityCompanyStub.getSomeUtilityCompany();
        List<UtilityCompany> companyList = new ArrayList<>(Arrays.asList(company));
        Page<UtilityCompany> pageCompanyList = new PageImpl<>(companyList);
        Pageable paging = PageRequest.of(UtilityCompanyStub.PAGE, UtilityCompanyStub.SIZE);

        Mockito.when(utilityCompanyRepository.findAll(paging)).thenReturn(pageCompanyList);
        var result = service.getAll(AccountRoleStub.PAGE, AccountRoleStub.SIZE);

        assertEquals(result.size(),companyList.size());

    }

    @Test
    void testSuccessfulGetById() {
        var company = UtilityCompanyStub.getSomeUtilityCompany();
        Mockito.when(utilityCompanyRepository.findById(Mockito.any())).thenReturn(Optional.of(company));
        var result = service.getById(UtilityCompanyStub.ID);
        assertAll(
                () -> assertEquals(result.get().getId(), company.getId()),
                () -> assertEquals(result.get().getCompanyName(), company.getCompanyName())
        );
    }

    @Test
    void testNoSuccessfulGetById() {
        Mockito.when(utilityCompanyRepository.findById(Mockito.any())).thenReturn(Optional.empty());
//        var exception = assertThrows(NoSuchElementException.class, () -> service.getById(AccountRoleStub.ID));
//        assertEquals(exception.getMessage(), "No value present");
        var result = service.getById(AccountRoleStub.ID);
        assertEquals(result, Optional.empty());

    }

    @Test
    void testSuccessfulSave() {
        var captor = ArgumentCaptor.forClass(UtilityCompany.class);
        var company = UtilityCompanyStub.getSomeUtilityCompany();
        Mockito.when(utilityCompanyRepository.save(Mockito.any())).thenReturn(UtilityCompanyStub.getSomeUtilityCompany());
        var result = service.save(UtilityCompanyStub.getSomeUtilityCompany());
        Mockito.verify(utilityCompanyRepository, Mockito.atLeast(1)).save(captor.capture());
        assertAll(
                () -> assertEquals(company, captor.getValue()),
                () -> assertEquals(company.getCompanyName(), result.getCompanyName())
        );
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(UtilityCompanyStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(utilityCompanyRepository, Mockito.atLeast(1)).deleteById(captor.capture());
        assertEquals(UtilityCompanyStub.ID, captor.getValue());
    }

}