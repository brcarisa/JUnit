package edu.school21.services;


import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authenticate(String login, String password){
        try {
            User user = usersRepository.findByLogin(login);
            if(user.isAuthenticationStatus()){
                throw new AlreadyAuthenticatedException("User with login " + user.getLogin() + " already authenticated");
            } else {
                if(password.equals(user.getPassword())){
                    user.setAuthenticationStatus(true);
                    usersRepository.update(user);
                    return true;
                } else {
                    return false;
                }
            }
        }catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
