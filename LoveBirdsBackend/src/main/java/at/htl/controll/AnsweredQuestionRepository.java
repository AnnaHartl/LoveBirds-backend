package at.htl.controll;

import at.htl.entity.AnsweredQuestion;
import at.htl.entity.Person;
import at.htl.entity.Question;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.apache.derby.client.am.DateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class AnsweredQuestionRepository implements PanacheRepository<AnsweredQuestion> {
    @Inject
    EntityManager em;

    public int getAnsweredQuestionsToday(Person person) {

        TypedQuery<Question> query = em.createQuery("select aq.answer.question from AnsweredQuestion aq where aq.dateAnswered = :date and aq.person.id = :id", Question.class)
                .setParameter("date", LocalDate.now())
                .setParameter("id", person.id);
        return query.getResultList().size();
    }

    public List<Question> getAnsweredQuestionOfPerson(Person person) {
        TypedQuery<Question> query = em.createQuery("select aq.answer.question from AnsweredQuestion aq where aq.person.id = :id", Question.class)
                .setParameter("id", person.id);
        return query.getResultList();
    }
}
