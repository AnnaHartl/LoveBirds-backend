package at.htl.boundary;

import at.htl.controll.AnswerRepository;
import at.htl.entity.Answer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("answers")
public class AnswerResource {
    @Inject
    AnswerRepository answerRepository;

    @GET
    @Path("/{questionId}")
    public List<Answer> getAnswersForQuestion(@PathParam("questionId") Long questionId){
        return answerRepository.getAnswersForQuestion(questionId);
    }

    @POST
    public void answerQuestion(Long personId, Long answerId){
        //zur Assotiationstablle anfügen
    }
}
