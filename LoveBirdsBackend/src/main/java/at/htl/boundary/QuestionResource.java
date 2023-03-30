package at.htl.boundary;

import at.htl.controll.AnswerRepository;
import at.htl.controll.AnsweredQuestionRepository;
import at.htl.controll.PersonRepository;
import at.htl.controll.QuestionRepository;
import at.htl.entity.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("questions")
public class QuestionResource {
    @Inject
    QuestionRepository questionRepository;

    @Inject
    AnsweredQuestionRepository answeredQuestionRepository;

    @Inject
    AnswerRepository answerRepository;

    @Inject
    PersonRepository personRepository;

    @GET
    @Path("/{personId}")
    public List<Question> getQuestions(@PathParam("personId") Long personId) {
        //prüfen ob die person schon 3 Fragen beantwortet hat
        // prüfen ob die Person die erste des Paares ist und fragen anfordert
        // auch das aus jeder Categorie a bisi was dabei ist
        Person p = personRepository.findById(personId);
        int count = answeredQuestionRepository.getAnsweredQuestionsToday(p);
        System.out.println(count);
        List<Question> questions = new ArrayList<>();

        //Das Kontingent wurde erfüllt
        if (count == 3) {
            return questions;
        }


        //Fragen die die eine Person schon geantwortet hat
        List<Question> ownQuestions = answeredQuestionRepository.getAnsweredQuestionOfPerson(p);
        Person partner = personRepository.getMatch(p);

        //Fragen die die Partner person beantwortet hat
        List<Question> partnerQuestions = answeredQuestionRepository.getAnsweredQuestionOfPerson(partner);


        System.out.println(ownQuestions.size());
        System.out.println(partnerQuestions.size());

        if (partnerQuestions.size() > ownQuestions.size()) {
            //so hat mehr fragen beantwortet
            return getDifferenceOfQuestions(ownQuestions, partnerQuestions);
        }

        //3 Random fragen wählen
        return getRandomQuestions();

        //return questionRepository.listAll();
    }

    private List<Question> getDifferenceOfQuestions(List<Question> ownQuestions, List<Question> partnerQuestions) {
        partnerQuestions.removeAll(ownQuestions);
        return partnerQuestions;
    }

    private List<Question> getRandomQuestions() {
        List<Question> questions = new ArrayList<>();
        List<Question> all = questionRepository.listAll();

        Random random = new Random();


        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(all.size());
            questions.add(all.get(index));
        }
        return questions;
    }

    @GET
    @Path("anseredQuestions/{personId}")
    public List<AnsweredQuestion> getSameAnsweredQuestions(@PathParam("personId") Long personId) {
        //alle gleiche Fragen zurück
        // villeicht bei  der App zwischen alter und neuer Liste unterscheiden
        Person own = personRepository.findById(personId);
        Person match = personRepository.getMatch(own);
        return answeredQuestionRepository.getAllAnsweredQuestions(own, match);
    }

    @GET
    @Path("setup")
    @Transactional
    public void setUp() {
        Question q1 = new Question("Would you rather go to the theater or the movies as a couple?", Category.RECREATION);
        Answer a1 = new Answer("Theater", q1);
        Answer a2 = new Answer("Movies", q1);
        questionRepository.persist(q1);
        answerRepository.persist(a1);
        answerRepository.persist(a2);

        Question q2 = new Question("Would you rather argue all night to resolve a conflict or end the argument unresolved before bed?", Category.COMMUNICATION);
        Answer a3 = new Answer("Resolve", q2);
        Answer a4 = new Answer("Sleep over it", q2);
        questionRepository.persist(q2);
        answerRepository.persist(a3);
        answerRepository.persist(a4);

        Question q3 = new Question("Would you rather get up early or stay up late?", Category.COMMUNICATION);
        questionRepository.persist(q3);
        answerRepository.persist(new Answer("Early Bird", q3));
        answerRepository.persist(new Answer("Night Owl", q3));

        Question q4 = new Question("Would you rather talk on the phone or text?", Category.COMMUNICATION);
        questionRepository.persist(q4);
        answerRepository.persist(new Answer("Talk", q4));
        answerRepository.persist(new Answer("Text", q4));

        Question q5 = new Question("Would you rather be able to fly or read minds?", Category.FUN);
        questionRepository.persist(q5);
        answerRepository.persist(new Answer("Fly", q5));
        answerRepository.persist(new Answer("Read Minds", q5));


        Question q6 = new Question("Would you rather live in a big city or a small town?", Category.COMMUNICATION);
        questionRepository.persist(q6);
        answerRepository.persist(new Answer("City", q6));
        answerRepository.persist(new Answer("Town", q6));

        q4 = new Question("Would you rather get stranded by yourself on a desert island, or stranded on a snow mountain?", Category.FUN);
        questionRepository.persist(q4);
        answerRepository.persist(new Answer("Irland", q4));
        answerRepository.persist(new Answer("Snow Mountain", q4));

        q4 = new Question("Would you rather cook for your significant other or cook as a couple?", Category.RECREATION);
        questionRepository.persist(q4);
        answerRepository.persist(new Answer("Cook for so", q4));
        answerRepository.persist(new Answer("Cook together", q4));


    }

    @GET
    @Path("tearDown")
    @Transactional
    public void tearDown() {
        answeredQuestionRepository.deleteAll();
        answerRepository.deleteAll();
        questionRepository.deleteAll();

    }
}
