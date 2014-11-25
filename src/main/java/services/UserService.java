package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import models.User;
import java.util.List;

/**
 * Created by Мадина on 21.11.2014.
 */
public class UserService {
    /*@PersistenceContext(unitName = "default")
    private EntityManager em;

    public List<User> getAll() {
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        return query.getResultList();
    }*/
}
