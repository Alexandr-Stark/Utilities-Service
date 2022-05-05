package com.example.utilitiesservice.services;

import com.example.utilitiesservice.models.AccountRole;
import com.example.utilitiesservice.repositories.AccountRoleRepository;
import com.example.utilitiesservice.stubs.AccountRoleStub;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountRoleServiceTest {

    private AccountRoleService service;
    @Mock
    private AccountRoleRepository accountRoleRepository;

    @BeforeEach()
    void setup() {
        service = new AccountRoleService(accountRoleRepository);
    }

    @Test
    void testSuccessfulGetAll() {
        var role = AccountRoleStub.getSomeAccountRole();
        List<AccountRole> roleList = new ArrayList<>(Arrays.asList(role));
        Page<AccountRole> pageRoleList = new PageImpl<>(roleList);
        Pageable paging = PageRequest.of(AccountRoleStub.PAGE, AccountRoleStub.SIZE);

        Mockito.when(accountRoleRepository.findAll(paging)).thenReturn(pageRoleList);
        var result = service.getAll(AccountRoleStub.PAGE, AccountRoleStub.SIZE);

        assertEquals(result.size(), roleList.size());

    }

    @Test
    void testSuccessfulGetById() {
        var role = AccountRoleStub.getSomeAccountRole();
        Mockito.when(accountRoleRepository.findById(Mockito.any())).thenReturn(Optional.of(role));
        var result = service.getById(AccountRoleStub.ID);
        System.out.println(result);
        assertAll(
                () -> assertEquals(result.get().getId(), role.getId()),
                () -> assertEquals(result.get().getRoleName(), role.getRoleName())
        );
    }

    @Test
    void testNoSuccessfulGetById() {
        Mockito.when(accountRoleRepository.findById(Mockito.any())).thenReturn(Optional.empty());
//        var exception = assertThrows(NoSuchElementException.class, () -> service.getById(AccountRoleStub.ID));
//        assertEquals(exception.getMessage(), "No value present");
        var result = service.getById(AccountRoleStub.ID);
        assertEquals(result, Optional.empty());

    }

    @Test
    void testSuccessfulSave() {
        var captor = ArgumentCaptor.forClass(AccountRole.class);
        var role = AccountRoleStub.getSomeAccountRole();
        Mockito.when(accountRoleRepository.save(Mockito.any())).thenReturn(AccountRoleStub.getSomeAccountRole());
        var result = service.save(AccountRoleStub.getSomeAccountRole());
        Mockito.verify(accountRoleRepository, Mockito.atLeast(1)).save(captor.capture());
        assertAll(
                () -> assertEquals(role, captor.getValue()),
                () -> assertEquals(role.getRoleName(), result.getRoleName())
        );
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(AccountRoleStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(accountRoleRepository, Mockito.atLeast(1)).deleteById(captor.capture());
        assertEquals(AccountRoleStub.ID, captor.getValue());
    }

}
