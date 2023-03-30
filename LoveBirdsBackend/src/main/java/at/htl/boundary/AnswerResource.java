package at.htl.boundary;

import at.htl.controll.AnswerRepository;
import at.htl.controll.AnsweredQuestionRepository;
import at.htl.controll.PersonRepository;
import at.htl.entity.Answer;
import at.htl.entity.AnsweredQuestion;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.time.LocalDate;
import java.util.List;

@Path("answers")
public class AnswerResource {
    @Inject
    AnswerRepository answerRepository;

    @Inject
    AnsweredQuestionRepository answeredQuestionRepository;

    @Inject
    PersonRepository personRepository;

    @GET
    @Path("/{questionId}")
    public List<Answer> getAnswersForQuestion(@PathParam("questionId") Long questionId){
        return answerRepository.getAnswersForQuestion(questionId);
    }

    @GET
    @Transactional
    @Path("/answerQuestion/{personId}/{answerId}")
    public void answerQuestion(@PathParam("personId") Long personId,@PathParam("answerId") Long answerId){
        //zur Assotiationstablle anfügen

        AnsweredQuestion a = new AnsweredQuestion();
        a.answer = answerRepository.findById(answerId);
        a.person = personRepository.findById(personId);
        a.dateAnswered = LocalDate.now();
        answeredQuestionRepository.persist(a);
        
    }
}
