package edu.schoo21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import edu.school21.services.UsersServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UsersServiceImplTest {
    private UsersRepository usersRepository;
    private UsersServiceImpl usersService;


    @BeforeEach
    public void init() {
        usersRepository = mock(UsersRepository.class);
        usersService = new UsersServiceImpl(usersRepository);
    }


    @Test
    public void CorrectLoginPassword(){
        String login = "test";
        String password = "password";

        User user = new User(1L, login, password, false);
        when(usersRepository.findByLogin(login)).thenReturn(user);
        boolean res = usersService.authenticate(login, password);

        Assertions.assertTrue(res);
        Assertions.assertTrue(user.isAuthenticationStatus());

        verify(usersRepository, times(1)).update(user);
    }

    @Test
    public void IncorrectLoginPassword(){
        String login = "test";
        String password = "password";
        when(usersRepository.findByLogin(login)).thenThrow(EntityNotFoundException.class);

        boolean res = usersService.authenticate(login, password);

        Assertions.assertFalse(res);
    }

    @Test
    public void testAuthenticateIncorrectPassword() throws EntityNotFoundException, AlreadyAuthenticatedException {
        String login = "testuser";
        String password = "testpassword";
        User user = new User(1L, login, "wrongpassword", false);

        when(usersRepository.findByLogin(login)).thenReturn(user);

        boolean result = usersService.authenticate(login, password);

        Assertions.assertFalse(result);
        Assertions.assertFalse(user.isAuthenticationStatus());

        verify(usersRepository, never()).update(user);
    }
}
