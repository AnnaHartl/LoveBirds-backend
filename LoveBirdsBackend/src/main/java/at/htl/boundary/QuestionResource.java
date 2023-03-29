package at.htl.boundary;

import at.htl.controll.AnswerRepository;
import at.htl.controll.QuestionRepository;
import at.htl.entity.Answer;
import at.htl.entity.AnsweredQuestion;
import at.htl.entity.Category;
import at.htl.entity.Question;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("questions")
public class QuestionResource {
    @Inject
    QuestionRepository questionRepository;

    @Inject
    AnswerRepository answerRepository;

    @GET
    @Path("/{personId}")
    public List<Question> getQuestions(@PathParam("personId") Long personId){
        //prüfen ob die person schon 3 Fragen beantwortet hat
        // prüfen ob die Person die erste des Paares ist und fragen anfordert
        // auch das aus jeder Categorie a bisi was dabei ist
        return questionRepository.listAll();
    }

    @GET
    @Path("anseredQuestions/{personId}")
    public List<AnsweredQuestion> getSameAnsweredQuestions(@PathParam("personId") Long personId){
        //alle gleiche Fragen zurück
        // villeicht bei  der App zwischen alter und neuer Liste unterscheiden
        return null;
    }

    @GET
    @Path("setup")
    @Transactional
    public void setUp(){
        Question q1 = new Question();
        Question q2 = new Question();

        q1.questionText = "Would you rather go to the theater or the movies as a couple?";
        q2.questionText = "Would you rather argue all night to resolve a conflict or end the argument unresolved before bed?";
        q1.category = q2.category = Category.COMMUNICATION;

        questionRepository.persist(q1);
        questionRepository.persist(q2);

        Answer a1 = new Answer();
        Answer a2 = new Answer();
        Answer a3 = new Answer();
        Answer a4 = new Answer();

        a1.question=a2.question = q1;
        a1.answerText = "Theater";
        a2.answerText = "Movies";

        a3.question=a4.question = q2;
        a3.answerText = "Resolve";
        a4.answerText = "Sleep over it";

        answerRepository.persist(a1);
        answerRepository.persist(a2);
        answerRepository.persist(a3);
        answerRepository.persist(a4);

    }

    @GET
    @Path("tearDown")
    @Transactional
    public void tearDown(){

        answerRepository.deleteAll();
        questionRepository.deleteAll();

    }
}
