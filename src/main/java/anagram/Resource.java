package anagram;

import java.util.List;
import java.util.Set;

import anagram.Words.Word;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Resource {
    @GET
    @Path("/permutations/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> generatePermutations(@PathParam("word") String word) {
        return Task.generatePermutations(word);
    }

    @GET
    @Path("/valid/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Word> findValidPermutations(@PathParam("word") String word) {
        return Task.findValidPermutations(word);
    }
}
