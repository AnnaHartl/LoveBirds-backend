package at.htl.controll;

import at.htl.entity.Answer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class AnswerRepository implements PanacheRepository<Answer> {
    @Inject
    EntityManager em;
    public List<Answer> getAnswersForQuestion(Long questionId) {
        TypedQuery<Answer> query = em.createQuery("select a from Answer a where  question.id = :id", Answer.class)
                .setParameter("id", questionId);
        return query.getResultList();
    }
}
