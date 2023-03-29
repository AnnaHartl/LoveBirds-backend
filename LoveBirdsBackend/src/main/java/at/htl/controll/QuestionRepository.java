package at.htl.controll;

import at.htl.entity.Question;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuestionRepository implements PanacheRepository<Question> {
}
