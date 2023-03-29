package at.htl.boundary;

import at.htl.controll.QuestionRepository;
import at.htl.entity.AnsweredQuestion;
import at.htl.entity.Question;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("questions")
public class QuestionResource {
    @Inject
    QuestionRepository questionRepository;

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
}
