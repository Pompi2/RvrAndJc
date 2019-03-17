package com.hashik.rvrandjc.repositories;

/**
 * The type User repository that uses singleton pattern
 */
public class UserRepository {
    private static UserRepository userRepository;

    /**
     * Get instance user repository.
     *
     * @return the user repository
     */
    public static UserRepository getInstance(){
        if (userRepository!=null) {
            return userRepository;
        }
        return new UserRepository();
    }


}
