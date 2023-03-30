package at.htl.controll;

import at.htl.entity.Answer;
import at.htl.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
    @Inject
    EntityManager em;
    public Person getMatch(Person ownPerson) {
        TypedQuery<Person> query = em.createQuery("select p from Person p " +
                        "where p.coupleId = :coupleId and p.id != :ownId ", Person.class)
                .setParameter("coupleId", ownPerson.coupleId)
                .setParameter("ownId", ownPerson.id);
        return query.getSingleResult();
    }
}
