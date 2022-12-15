import input.ActionsIn;
import struct.CurrentPage;
import struct.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public final class ChangePage {

    private ChangePage() {

    }
    static void upgradesChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                   final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getPage().equals("upgrades")) {
            if (currentPage.getCurrentUser() != null) {
                currentPage.setPageName("upgrades");
                currentPage.setCurrentMovie(null);
                currentPage.setMoviesList(null);
            } else {
                ObjectNode auxx = objectMapper.createObjectNode();
                auxx.put("eroare la upgrade", "eroare");
                output.add(auxx);
            }
        }
    }

    static void seeDetailsChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                     final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getPage().equals("see details")) {
            String movieee = iterate.getMovie();
            int found = 0;
            Movie searchedMovie = null;

            for (Movie iter : currentPage.getCurrentUser().getCurrentMoviesList()) {
                if (iter.getName().equals(movieee)) {
                    found = 1;
                    searchedMovie = iter;
                }
            }
            int banned = 0;
            if (found == 1) {
                currentPage.setCurrentMovie(searchedMovie);
                for (String itStr : searchedMovie.getCountriesBanned()) {
                    if (itStr.equals(currentPage.getCurrentUser().getCredentials().getCountry())) {
                        banned = 1;
                        break;
                    }
                }
            }

            if (found == 1 && banned == 0) {
                // o printare buna
                currentPage.setCurrentMovie(searchedMovie);
                currentPage.getCurrentUser().setCurrentMoviesList(new ArrayList<>());
                currentPage.getCurrentUser().getCurrentMoviesList().add(searchedMovie);
                //current movie list ul numai filmul pe cae il caut
                ObjectNode helpp = objectMapper.createObjectNode();
                ObjectNode auxErr;
                auxErr = null;
                helpp.put("error", auxErr);
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<Movie> auxCurrMovieList = new ArrayList<>();
                auxCurrMovieList.add(searchedMovie);

                for (Movie itMovies : auxCurrMovieList) {
                    ObjectNode ajutor = objectMapper.createObjectNode();
                    ajutor.put("name", itMovies.getName());
                    ajutor.put("year", itMovies.getYear());
                    ajutor.put("duration", itMovies.getDuration());

                    ArrayNode noName = objectMapper.createArrayNode();
                    for (String itStr : itMovies.getGenres()) {
                        noName.add(itStr);
                    }
                    ajutor.set("genres", noName);

                    ArrayNode noName1 = objectMapper.createArrayNode();
                    for (String itStr : itMovies.getActors()) {
                        noName1.add(itStr);
                    }
                    ajutor.set("actors", noName1);

                    ArrayNode noName2 = objectMapper.createArrayNode();
                    for (String itStr : itMovies.getCountriesBanned()) {
                        noName2.add(itStr);
                    }
                    ajutor.set("countriesBanned", noName2);

                    ajutor.put("numLikes", itMovies.getNumLikes());
                    ajutor.put("rating", itMovies.getRating());
                    ajutor.put("numRatings", itMovies.getNumRatings());
                    outCurrMoviesList.add(ajutor);
                }

                helpp.set("currentMoviesList", outCurrMoviesList);

                ObjectNode help = objectMapper.createObjectNode();

                ObjectNode credent = objectMapper.createObjectNode();
                credent.put("name", currentPage.getCurrentUser().getCredentials().getName());
                credent.put("password", currentPage.getCurrentUser()
                        .getCredentials().getPassword());
                credent.put("accountType", currentPage.getCurrentUser().getCredentials()
                        .getAccountType());
                credent.put("country", currentPage.getCurrentUser().getCredentials().getCountry());
                credent.put("balance", Integer.toString(currentPage.getCurrentUser()
                        .getCredentials().getBalance()));

                help.set("credentials", credent);
                help.put("tokensCount", currentPage.getCurrentUser().getTokensCount());
                help.put("numFreePremiumMovies", currentPage.getCurrentUser()
                        .getNumFreePremiumMovies());

                ArrayNode outPurchasedMovies = objectMapper.createArrayNode();

                for (Movie iterMovies : currentPage.getCurrentUser().getPurchasedMovies()) {
                    ObjectNode ajutor = objectMapper.createObjectNode();
                    ajutor.put("name", iterMovies.getName());
                    ajutor.put("year", iterMovies.getYear());
                    ajutor.put("duration", iterMovies.getDuration());

                    ArrayNode noName = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getGenres()) {
                        noName.add(itStr);
                    }
                    ajutor.set("genres", noName);

                    ArrayNode noName1 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getActors()) {
                        noName1.add(itStr);
                    }
                    ajutor.set("actors", noName1);

                    ArrayNode noName2 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getCountriesBanned()) {
                        noName2.add(itStr);
                    }
                    ajutor.set("countriesBanned", noName2);

                    ajutor.put("numLikes", iterMovies.getNumLikes());
                    ajutor.put("rating", iterMovies.getRating());
                    ajutor.put("numRatings", iterMovies.getNumRatings());
                    outPurchasedMovies.add(ajutor);
                }
                help.set("purchasedMovies", outPurchasedMovies);

                ArrayNode outWatchedMovies = objectMapper.createArrayNode();
                for (Movie iterMovies : currentPage.getCurrentUser().getWatchedMovies()) {
                    ObjectNode ajutor = objectMapper.createObjectNode();
                    ajutor.put("name", iterMovies.getName());
                    ajutor.put("year", iterMovies.getYear());
                    ajutor.put("duration", iterMovies.getDuration());

                    ArrayNode noName = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getGenres()) {
                        noName.add(itStr);
                    }
                    ajutor.set("genres", noName);

                    ArrayNode noName1 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getActors()) {
                        noName1.add(itStr);
                    }
                    ajutor.set("actors", noName1);

                    ArrayNode noName2 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getCountriesBanned()) {
                        noName2.add(itStr);
                    }
                    ajutor.set("countriesBanned", noName2);

                    ajutor.put("numLikes", iterMovies.getNumLikes());
                    ajutor.put("rating", iterMovies.getRating());
                    ajutor.put("numRatings", iterMovies.getNumRatings());
                    outWatchedMovies.add(ajutor);
                }
                help.set("watchedMovies", outWatchedMovies);


                ArrayNode outLikedMovies = objectMapper.createArrayNode();
                for (Movie iterMovies : currentPage.getCurrentUser().getLikedMovies()) {
                    ObjectNode ajutor = objectMapper.createObjectNode();
                    ajutor.put("name", iterMovies.getName());
                    ajutor.put("year", iterMovies.getYear());
                    ajutor.put("duration", iterMovies.getDuration());

                    ArrayNode noName = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getGenres()) {
                        noName.add(itStr);
                    }
                    ajutor.set("genres", noName);

                    ArrayNode noName1 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getActors()) {
                        noName1.add(itStr);
                    }
                    ajutor.set("actors", noName1);

                    ArrayNode noName2 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getCountriesBanned()) {
                        noName2.add(itStr);
                    }
                    ajutor.set("countriesBanned", noName2);

                    ajutor.put("numLikes", iterMovies.getNumLikes());
                    ajutor.put("rating", iterMovies.getRating());
                    ajutor.put("numRatings", iterMovies.getNumRatings());
                    outLikedMovies.add(ajutor);
                }
                help.set("likedMovies", outLikedMovies);

                ArrayNode outratedMovies = objectMapper.createArrayNode();
                for (Movie iterMovies : currentPage.getCurrentUser().getRatedMovies()) {
                    ObjectNode ajutor = objectMapper.createObjectNode();
                    ajutor.put("name", iterMovies.getName());
                    ajutor.put("year", iterMovies.getYear());
                    ajutor.put("duration", iterMovies.getDuration());

                    ArrayNode noName = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getGenres()) {
                        noName.add(itStr);
                    }
                    ajutor.set("genres", noName);

                    ArrayNode noName1 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getActors()) {
                        noName1.add(itStr);
                    }
                    ajutor.set("actors", noName1);

                    ArrayNode noName2 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getCountriesBanned()) {
                        noName2.add(itStr);
                    }
                    ajutor.set("countriesBanned", noName2);

                    ajutor.put("numLikes", iterMovies.getNumLikes());
                    ajutor.put("rating", iterMovies.getRating());
                    ajutor.put("numRatings", iterMovies.getNumRatings());
                    outratedMovies.add(ajutor);
                }
                help.set("ratedMovies", outratedMovies);
                helpp.set("currentUser", help);
                output.add(helpp);
                currentPage.setPageName("see details");
            } else {
                ObjectNode help = objectMapper.createObjectNode();
                currentPage.setCurrentMovie(null);
                help.put("error", "Error");
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<String> currMovieList = new ArrayList<>();
                for (String itCurrMovieList : currMovieList) {
                    outCurrMoviesList.add(itCurrMovieList);
                }
                help.set("currentMoviesList", outCurrMoviesList);
                ObjectNode aux = objectMapper.createObjectNode();
                aux = null;
                help.put("currentUser", aux);
                output.add(help);
            }


        }
    }

    static void moviesChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                 final ArrayList<Movie> movies, final CurrentPage currentPage,
                                 final ActionsIn iterate) {
        if (iterate.getPage().equals("movies")) {
            // incercam sa trecem pe pagina de movies acuma
            if (currentPage.getCurrentUser() != null) {

                currentPage.setPageName("movies");
                ArrayList<Movie> possibleMovies = new ArrayList<>();

                for (Movie itMovie : movies) {
                    int banat = 0;
                    for (String itCountry : itMovie.getCountriesBanned()) {
                        if (itCountry.equals(currentPage.getCurrentUser()
                                .getCredentials().getCountry())) {
                            banat = 1;
                            break;
                        }
                    }
                    if (banat == 0) {
                        possibleMovies.add(itMovie);
                    }
                }

                // in possible movies avem toate filmele
                currentPage.setMoviesList(possibleMovies);
                ObjectNode helpp = objectMapper.createObjectNode();
                ObjectNode auxErr;
                auxErr = null;
                helpp.put("error", auxErr);
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();

                currentPage.getCurrentUser().setCurrentMoviesList(possibleMovies);

                for (Movie itMovies : currentPage.getMoviesList()) {
                    ObjectNode ajutor = objectMapper.createObjectNode();
                    ajutor.put("name", itMovies.getName());
                    ajutor.put("year", itMovies.getYear());
                    ajutor.put("duration", itMovies.getDuration());

                    ArrayNode noName = objectMapper.createArrayNode();
                    for (String itStr : itMovies.getGenres()) {
                        noName.add(itStr);
                    }
                    ajutor.set("genres", noName);

                    ArrayNode noName1 = objectMapper.createArrayNode();
                    for (String itStr : itMovies.getActors()) {
                        noName1.add(itStr);
                    }
                    ajutor.set("actors", noName1);

                    ArrayNode noName2 = objectMapper.createArrayNode();
                    for (String itStr : itMovies.getCountriesBanned()) {
                        noName2.add(itStr);
                    }
                    ajutor.set("countriesBanned", noName2);

                    ajutor.put("numLikes", itMovies.getNumLikes());
                    ajutor.put("rating", itMovies.getRating());
                    ajutor.put("numRatings", itMovies.getNumRatings());
                    outCurrMoviesList.add(ajutor);
                }

                helpp.set("currentMoviesList", outCurrMoviesList);

                ObjectNode help = objectMapper.createObjectNode();

                ObjectNode credent = objectMapper.createObjectNode();
                credent.put("name", currentPage
                        .getCurrentUser().getCredentials().getName());
                credent.put("password", currentPage
                        .getCurrentUser().getCredentials().getPassword());
                credent.put("accountType", currentPage
                        .getCurrentUser().getCredentials().getAccountType());
                credent.put("country", currentPage.getCurrentUser()
                        .getCredentials().getCountry());
                credent.put("balance", Integer
                        .toString(currentPage.getCurrentUser()
                                .getCredentials().getBalance()));

                help.set("credentials", credent);
                help.put("tokensCount", currentPage.getCurrentUser().getTokensCount());
                help.put("numFreePremiumMovies", currentPage
                        .getCurrentUser().getNumFreePremiumMovies());

                ArrayNode outPurchasedMovies = objectMapper.createArrayNode();

                for (Movie iterMovies : currentPage.getCurrentUser().getPurchasedMovies()) {
                    ObjectNode ajutor = objectMapper.createObjectNode();
                    ajutor.put("name", iterMovies.getName());
                    ajutor.put("year", iterMovies.getYear());
                    ajutor.put("duration", iterMovies.getDuration());

                    ArrayNode noName = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getGenres()) {
                        noName.add(itStr);
                    }
                    ajutor.set("genres", noName);

                    ArrayNode noName1 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getActors()) {
                        noName1.add(itStr);
                    }
                    ajutor.set("actors", noName1);

                    ArrayNode noName2 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getCountriesBanned()) {
                        noName2.add(itStr);
                    }
                    ajutor.set("countriesBanned", noName2);

                    ajutor.put("numLikes", iterMovies.getNumLikes());
                    ajutor.put("rating", iterMovies.getRating());
                    ajutor.put("numRatings", iterMovies.getNumRatings());
                    outPurchasedMovies.add(ajutor);
                }
                help.set("purchasedMovies", outPurchasedMovies);

                ArrayNode outWatchedMovies = objectMapper.createArrayNode();
                for (Movie iterMovies : currentPage.getCurrentUser().getWatchedMovies()) {
                    ObjectNode ajutor = objectMapper.createObjectNode();
                    ajutor.put("name", iterMovies.getName());
                    ajutor.put("year", iterMovies.getYear());
                    ajutor.put("duration", iterMovies.getDuration());

                    ArrayNode noName = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getGenres()) {
                        noName.add(itStr);
                    }
                    ajutor.set("genres", noName);

                    ArrayNode noName1 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getActors()) {
                        noName1.add(itStr);
                    }
                    ajutor.set("actors", noName1);

                    ArrayNode noName2 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getCountriesBanned()) {
                        noName2.add(itStr);
                    }
                    ajutor.set("countriesBanned", noName2);

                    ajutor.put("numLikes", iterMovies.getNumLikes());
                    ajutor.put("rating", iterMovies.getRating());
                    ajutor.put("numRatings", iterMovies.getNumRatings());
                    outWatchedMovies.add(ajutor);
                }
                help.set("watchedMovies", outWatchedMovies);


                ArrayNode outLikedMovies = objectMapper.createArrayNode();
                for (Movie iterMovies : currentPage.getCurrentUser().getLikedMovies()) {
                    ObjectNode ajutor = objectMapper.createObjectNode();
                    ajutor.put("name", iterMovies.getName());
                    ajutor.put("year", iterMovies.getYear());
                    ajutor.put("duration", iterMovies.getDuration());

                    ArrayNode noName = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getGenres()) {
                        noName.add(itStr);
                    }
                    ajutor.set("genres", noName);

                    ArrayNode noName1 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getActors()) {
                        noName1.add(itStr);
                    }
                    ajutor.set("actors", noName1);

                    ArrayNode noName2 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getCountriesBanned()) {
                        noName2.add(itStr);
                    }
                    ajutor.set("countriesBanned", noName2);

                    ajutor.put("numLikes", iterMovies.getNumLikes());
                    ajutor.put("rating", iterMovies.getRating());
                    ajutor.put("numRatings", iterMovies.getNumRatings());
                    outLikedMovies.add(ajutor);
                }
                help.set("likedMovies", outLikedMovies);

                ArrayNode outratedMovies = objectMapper.createArrayNode();
                for (Movie iterMovies : currentPage.getCurrentUser().getRatedMovies()) {
                    ObjectNode ajutor = objectMapper.createObjectNode();
                    ajutor.put("name", iterMovies.getName());
                    ajutor.put("year", iterMovies.getYear());
                    ajutor.put("duration", iterMovies.getDuration());

                    ArrayNode noName = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getGenres()) {
                        noName.add(itStr);
                    }
                    ajutor.set("genres", noName);

                    ArrayNode noName1 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getActors()) {
                        noName1.add(itStr);
                    }
                    ajutor.set("actors", noName1);

                    ArrayNode noName2 = objectMapper.createArrayNode();
                    for (String itStr : iterMovies.getCountriesBanned()) {
                        noName2.add(itStr);
                    }
                    ajutor.set("countriesBanned", noName2);

                    ajutor.put("numLikes", iterMovies.getNumLikes());
                    ajutor.put("rating", iterMovies.getRating());
                    ajutor.put("numRatings", iterMovies.getNumRatings());
                    outratedMovies.add(ajutor);
                }
                help.set("ratedMovies", outratedMovies);
                helpp.set("currentUser", help);
                output.add(helpp);


            } else {
                ObjectNode help = objectMapper.createObjectNode();
                help.put("error", "Error");
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<String> currMovieList = new ArrayList<>();
                for (String itCurrMovieList : currMovieList) {
                    outCurrMoviesList.add(itCurrMovieList);
                }
                help.set("currentMoviesList", outCurrMoviesList);
                ObjectNode aux = objectMapper.createObjectNode();
                aux = null;
                help.put("currentUser", aux);
                output.add(help);
            }
        }
    }

    static void logoutChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                 final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getPage().equals("logout")) {
            if (currentPage.getCurrentUser() != null) {
                // ne delogam
                currentPage.setPageName("homepage");
                currentPage.setCurrentUser(null);
                currentPage.setCurrentMovie(null);
                currentPage.setMoviesList(null);
            } else {
                // nu ne putem deloga cu succes
                ObjectNode help = objectMapper.createObjectNode();
                help.put("error", "Error");
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<String> currMovieList = new ArrayList<>();
                for (String itCurrMovieList : currMovieList) {
                    outCurrMoviesList.add(itCurrMovieList);
                }
                help.set("currentMoviesList", outCurrMoviesList);
                ObjectNode aux = objectMapper.createObjectNode();
                aux = null;
                help.put("currentUser", aux);
                output.add(help);
            }
            currentPage.setCurrentUser(null);
        }
    }

    static void loginChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getPage().equals("login")) {
            // incercam sa intram pe pagina de login
            if (currentPage.getCurrentUser() == null && currentPage
                    .getPageName().equals("homepage")) {
                currentPage.setPageName("login");
            } else {
                //eroare nu putem sa schimbam pe pagina de login
                ObjectNode help = objectMapper.createObjectNode();
                help.put("error", "Error");
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<String> currMovieList = new ArrayList<>();
                for (String itCurrMovieList : currMovieList) {
                    outCurrMoviesList.add(itCurrMovieList);
                }
                help.set("currentMoviesList", outCurrMoviesList);
                ObjectNode aux = objectMapper.createObjectNode();
                aux = null;
                help.put("currentUser", aux);
                output.add(help);
            }
        }
    }

    static void registerChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                   final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getPage().equals("register")) {
            if (currentPage.getCurrentUser() == null && currentPage
                    .getPageName().equals("homepage")) {
                currentPage.setPageName("register");
            } else {
                // eroare ca nu putem schimba pe pagina de register
                ObjectNode help = objectMapper.createObjectNode();
                help.put("error", "Error");
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<String> currMovieList = new ArrayList<>();
                for (String itCurrMovieList : currMovieList) {
                    outCurrMoviesList.add(itCurrMovieList);
                }
                help.set("currentMoviesList", outCurrMoviesList);
                ObjectNode aux = objectMapper.createObjectNode();
                aux = null;
                help.put("currentUser", aux);
                output.add(help);
            }
        }
    }

}
