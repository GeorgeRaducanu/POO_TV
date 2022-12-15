import struct.CurrentPage;
import struct.Movie;
import struct.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionsIn;
import input.Input;
import input.MovieIn;
import input.UserIn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(final String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode output = objectMapper.createArrayNode();

        Input inData = Input.getInstance();
        inData = objectMapper.readValue(new File(args[0]), Input.class);

        //bagam toti userii
        ArrayList<User> users = new ArrayList<User>();
        for (UserIn it : inData.getUsers()) {
            users.add(new User(it));
        }

        //bagam toate filmele
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (MovieIn it : inData.getMovies()) {
            movies.add(new Movie(it));
        }

        CurrentPage currentPage = CurrentPage.getInstance();
        currentPage = new CurrentPage("homepage");
        currentPage.setCurrentUser(null);

        for (ActionsIn iterate : inData.getActions()) {
            if (iterate.getType().equals("change page")) {
                PageSolver.changePageCommands(objectMapper, output, movies, currentPage, iterate);
            }
            if (iterate.getType().equals("on page")) {
                PageSolver.onPageCommands(objectMapper, output, users, movies,
                        currentPage, iterate);
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);

    }
}
