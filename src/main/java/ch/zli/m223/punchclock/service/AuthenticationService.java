package ch.zli.m223.punchclock.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

import ch.zli.m223.punchclock.domain.User;

@RequestScoped
public class AuthenticationService {
    @Inject
    private EntityManager entityManager;
    public String generateValidJwtToken(String username){
        String token =
            Jwt.issuer("https://zli.ch/issuer") 
            .upn(username) 
            .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
            .claim(Claims.birthdate.name(), "2001-07-13")
            .expiresIn(Duration.ofHours(1)) 
            .sign();
        return token;
    }

    public boolean checkIfUserExists(User user) {
        var query = entityManager.createQuery(
            "SELECT COUNT(*) FROM User WHERE username = :name AND password = :password"
        );
        query.setParameter("name", user.getUsername());
        query.setParameter("password", user.getPassword());
        var result = query.getSingleResult();
        return (long)result == 1;
    }

    @Transactional
    public User createUser(User user) {
        entityManager.merge(user);
        return user;
    }

    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }

    @Transactional
    public void delete(Long id) {
        User entryToDelete = getSingleUser(id);
        entityManager.remove(entryToDelete);
    }

    public User getSingleUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void updateEntry(User user) {
        entityManager.merge(user);
    }
}
