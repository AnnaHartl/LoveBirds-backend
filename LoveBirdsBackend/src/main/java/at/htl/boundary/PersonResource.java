package at.htl.boundary;

import at.htl.entity.Person;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("person")
public class PersonResource {

    @POST
    public Person createPerson(String firstName, String coupleId){
        //prüfen ob Person eingeladen wurde von einer Partnerin
        // oder neu dazu kommt
        return null;
    }
}
