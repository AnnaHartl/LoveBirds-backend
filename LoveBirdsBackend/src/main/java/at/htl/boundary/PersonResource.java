package at.htl.boundary;

import at.htl.controll.PersonRepository;
import at.htl.entity.Person;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Path("person")
public class PersonResource {
    @Inject
    PersonRepository personRepository;

    @GET
    @Path("/{firstName}/{coupleId}")
    @Transactional
    public Person createPerson(@PathParam("firstName") String firstName, @PathParam("coupleId") String coupleId){
        //prüfen ob Person eingeladen wurde von einer Partnerin
        // oder neu dazu kommt

        Person p = new Person();
        p.firstName = firstName;
        if(Objects.equals(coupleId, " ")){
            //Neue Person
            p.coupleId = generateString();
        }
        else
            p.coupleId = coupleId;

        personRepository.persist(p);

        return p;
    }

    @GET
    @Path("/delete")
    @Transactional
    public void deleteAll(){
        personRepository.deleteAll();
    }

    @GET
    @Path("getMatch/{personId}")
    public Person createPerson(@PathParam("personId") Long personId){
        Person p = personRepository.findById(personId);

        return personRepository.getMatch(p);
    }

    @GET
    @Path("/all")
    public List<Person> getAll(){
        return personRepository.listAll();
    }

    public String generateString(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        int length = 7;

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
