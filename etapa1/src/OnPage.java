import input.ActionsIn;
import input.FiltersIn;
import struct.Credentials;
import struct.CurrentPage;
import struct.Movie;
import struct.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.Comparator;

public final class OnPage {

    private OnPage() {

    }
    static void rateOnPage(final ObjectMapper objectMapper, final ArrayNode output,
                           final ArrayList<Movie> movies, final CurrentPage currentPage,
                           final ActionsIn iterate) {
        if (iterate.getFeature().equals("rate")) {
            // acum sa incercam la rate
            String movieeee = "";
            int found = 0;
            int rate = -1;
            if (currentPage.getCurrentMovie() != null) {
                movieeee = currentPage.getCurrentMovie().getName();
                for (Movie itMovvv : currentPage.getCurrentUser().getWatchedMovies()) {
                    if (itMovvv.getName().equals(movieeee)) {
                        found = 1;
                        break;
                    }
                }
                rate = iterate.getRate();
            }


            if (currentPage.getPageName().equals("see details") && found == 1 && rate >= 0 && rate <= 5) {
                String movieName = currentPage.getCurrentMovie().getName();
                Movie searchedMov = null;

                for (Movie itMov : movies) {
                    if (itMov.getName().equals(movieName)) {
                        searchedMov = itMov;
                    }
                }
                // am gasit searcheMov
                //searchedMov.setNumLikes(searchedMov.getNumLikes() + 1);
                //acum trbuie sa stez noul rating
                int n = searchedMov.getNumRatings();
                searchedMov.setRating((rate + n * searchedMov.getRating()) / (n + 1));
                searchedMov.setNumRatings(searchedMov.getNumRatings() + 1);
                currentPage.getCurrentUser().getRatedMovies().add(searchedMov);
                // l-am si adaugat la filme cumparate
                // acum trebuie doar sa afisez tot
                ObjectNode helpp = objectMapper.createObjectNode();
                ObjectNode auxErr;
                auxErr = null;
                helpp.put("error", auxErr);
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<Movie> auxxCurrMovieList = new ArrayList<>();
                auxxCurrMovieList.add(searchedMov);

                for (Movie itMovies : auxxCurrMovieList) {
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
                credent.put("password", currentPage.getCurrentUser().getCredentials()
                        .getPassword());
                credent.put("accountType", currentPage.getCurrentUser().getCredentials()
                        .getAccountType());
                credent.put("country", currentPage.getCurrentUser().getCredentials()
                        .getCountry());
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

    static void watchOnPage(final ObjectMapper objectMapper,
                            final ArrayNode output, final ArrayList<Movie> movies,
                            final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getFeature().equals("watch")) {

            String movieeee = "";
            int found = 0;
            if (currentPage.getCurrentMovie() != null) {
                movieeee = currentPage.getCurrentMovie().getName();
                for (Movie itMovvv : currentPage.getCurrentUser().getPurchasedMovies()) {
                    if (itMovvv.getName().equals(movieeee)) {
                        found = 1;
                        break;
                    }
                }
            }

            if (currentPage.getPageName().equals("see details") && found == 1) {
                String movieName = currentPage.getCurrentMovie().getName();
                Movie searchedMov = null;

                for (Movie itMov : movies) {
                    if (itMov.getName().equals(movieName)) {
                        searchedMov = itMov;
                    }
                }
                // am gasit searcheMov
                currentPage.getCurrentUser().getWatchedMovies().add(searchedMov);
                // l-am si adaugat la filme cumparate
                // acum trebuie doar sa afisez tot
                ObjectNode helpp = objectMapper.createObjectNode();
                ObjectNode auxErr;
                auxErr = null;
                helpp.put("error", auxErr);
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<Movie> auxxCurrMovieList = new ArrayList<>();
                auxxCurrMovieList.add(searchedMov);

                for (Movie itMovies : auxxCurrMovieList) {
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
                credent.put("name", currentPage.getCurrentUser()
                        .getCredentials().getName());
                credent.put("password", currentPage.getCurrentUser()
                        .getCredentials().getPassword());
                credent.put("accountType", currentPage.getCurrentUser()
                        .getCredentials().getAccountType());
                credent.put("country", currentPage.getCurrentUser()
                        .getCredentials().getCountry());
                credent.put("balance", Integer.toString(currentPage
                        .getCurrentUser().getCredentials().getBalance()));

                help.set("credentials", credent);
                help.put("tokensCount", currentPage.getCurrentUser()
                        .getTokensCount());
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

    static void purchaseOnPage(final ObjectMapper objectMapper, final ArrayNode output,
                               final ArrayList<Movie> movies, final CurrentPage currentPage,
                               final ActionsIn iterate) {
        if (iterate.getFeature().equals("purchase")) {
            Movie searchedMov = null;

            if (currentPage.getCurrentMovie() != null) {
                String movieName = currentPage.getCurrentMovie().getName();

                if (currentPage.getCurrentUser().getCredentials().getAccountType().equals("premium")
                        && currentPage.getCurrentUser().getNumFreePremiumMovies() > 0) {
                    currentPage.getCurrentUser().setNumFreePremiumMovies(currentPage
                            .getCurrentUser().getNumFreePremiumMovies() - 1);
                } else {
                    currentPage.getCurrentUser().setTokensCount(currentPage.getCurrentUser()
                            .getTokensCount() - 2);
                }

                for (Movie itMov : movies) {
                    if (itMov.getName().equals(movieName)) {
                        searchedMov = itMov;
                    }
                }
            }

            // am gasit searcheMov
            if (searchedMov != null) {
                currentPage.getCurrentUser().getPurchasedMovies().add(searchedMov);
                // l-am si adaugat la filme cumparate
                // acum trebuie doar sa afisez tot
                ObjectNode helpp = objectMapper.createObjectNode();
                ObjectNode auxErr;
                auxErr = null;
                helpp.put("error", auxErr);
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<Movie> auxxCurrMovieList = new ArrayList<>();
                auxxCurrMovieList.add(searchedMov);

                for (Movie itMovies : auxxCurrMovieList) {
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
                credent.put("name", currentPage.getCurrentUser()
                        .getCredentials().getName());
                credent.put("password", currentPage.getCurrentUser().getCredentials()
                        .getPassword());
                credent.put("accountType", currentPage.getCurrentUser().getCredentials()
                        .getAccountType());
                credent.put("country", currentPage.getCurrentUser().getCredentials()
                        .getCountry());
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

    static void likeOnPage(final ObjectMapper objectMapper, final ArrayNode output,
                           final ArrayList<Movie> movies, final CurrentPage currentPage,
                           final ActionsIn iterate) {
        if (iterate.getFeature().equals("like")) {
            String movieeee = "";
            int found = 0;
            if (currentPage.getCurrentMovie() != null) {
                movieeee = currentPage.getCurrentMovie().getName();
                for (Movie itMovvv : currentPage.getCurrentUser().getWatchedMovies()) {
                    if (itMovvv.getName().equals(movieeee)) {
                        found = 1;
                        break;
                    }
                }
            }


            if (currentPage.getPageName().equals("see details") && found == 1) {
                String movieName = currentPage.getCurrentMovie().getName();
                Movie searchedMov = null;

                for (Movie itMov : movies) {
                    if (itMov.getName().equals(movieName)) {
                        searchedMov = itMov;
                    }
                }
                // am gasit searcheMov
                searchedMov.setNumLikes(searchedMov.getNumLikes() + 1);
                currentPage.getCurrentUser().getLikedMovies().add(searchedMov);
                // l-am si adaugat la filme cumparate
                // acum trebuie doar sa afisez tot
                ObjectNode helpp = objectMapper.createObjectNode();
                ObjectNode auxErr;
                auxErr = null;
                helpp.put("error", auxErr);
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<Movie> auxxCurrMovieList = new ArrayList<>();
                auxxCurrMovieList.add(searchedMov);

                for (Movie itMovies : auxxCurrMovieList) {
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
                credent.put("name", currentPage.getCurrentUser()
                        .getCredentials().getName());
                credent.put("password", currentPage.getCurrentUser()
                        .getCredentials().getPassword());
                credent.put("accountType", currentPage.getCurrentUser()
                        .getCredentials().getAccountType());
                credent.put("country", currentPage.getCurrentUser()
                        .getCredentials().getCountry());
                credent.put("balance", Integer.toString(currentPage.getCurrentUser()
                        .getCredentials().getBalance()));

                help.set("credentials", credent);
                help.put("tokensCount", currentPage.getCurrentUser()
                        .getTokensCount());
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

    static void buyPremiumAccountOnPage(final ObjectMapper objectMapper, final ArrayNode output,
                                        final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getFeature().equals("buy premium account")) {
            if (10 < currentPage.getCurrentUser().getTokensCount()) {
                // all good
                currentPage.getCurrentUser().setTokensCount(currentPage
                        .getCurrentUser().getTokensCount() - 10);
                currentPage.getCurrentUser().getCredentials().setAccountType("premium");
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

    static void buyTokensOnPage(final ObjectMapper objectMapper, final ArrayNode output,
                                final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getFeature().equals("buy tokens")) {
            if (currentPage.getPageName().equals("upgrades")) {
                int countwanted = iterate.getCount();
                int balanceUser = currentPage.getCurrentUser().getCredentials().getBalance();
                if (countwanted <= balanceUser) {
                    // toate bune, achizitionam token urile
                    currentPage.getCurrentUser().setTokensCount(countwanted);
                    currentPage.getCurrentUser().getCredentials()
                            .setBalance(balanceUser - countwanted);
                } else {
                    // toate rele, nu putem achizitiona suficiente
                    ObjectNode help = objectMapper.createObjectNode();
                    help.put("error", "Error");
                    ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                    ArrayList<String> currMovieList = new ArrayList<>();
                    for (String itCurrMovieList : currMovieList) {
                        outCurrMoviesList.add(itCurrMovieList);
                    }
                    help.set("currentMoviesList tokens", outCurrMoviesList);
                    ObjectNode aux = objectMapper.createObjectNode();
                    aux = null;
                    help.put("currentUser", aux);
                    output.add(help);
                }
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

    static void filterOnPage(final ObjectMapper objectMapper, final ArrayNode output,
                             final ArrayList<Movie> movies, final CurrentPage currentPage,
                             final ActionsIn iterate) {
        if (iterate.getFeature().equals("filter")) {
            if (currentPage.getPageName().equals("movies")) {
                // e totul ok
                FiltersIn filter = iterate.getFilters();
                ArrayList<Movie> currMovieList = new ArrayList<>();
                for (Movie itMovie : movies) {
                    int banned = 0;
                    for (String itString : itMovie.getCountriesBanned()) {
                        if (currentPage.getCurrentUser().getCredentials()
                                .getCountry().equals(itString)) {
                            banned = 1;
                            break;
                        }
                    }
                    if (banned == 0) {
                        currMovieList.add(itMovie);
                    }
                }
                ArrayList<Movie> remember = currMovieList;
                ArrayList<Movie> trueCurrMovieList = new ArrayList<>();
                // acum am in currentMovieList filemele ce le pot vedea
                if (iterate.getFilters().getContains() != null) {
                    // avem actori
                    if (iterate.getFilters().getContains().getActors() != null) {
                        for (Movie itMov : remember) {
                            int hasActor = 1;
                            for (int i = 0; i < iterate.getFilters()
                                    .getContains().getActors().size(); ++i) {
                                if (!itMov.getActors().contains(iterate
                                        .getFilters().getContains().getActors().get(i))) {
                                    hasActor = 0;
                                    break;
                                }
                            }
                            if (hasActor == 1) {
                                trueCurrMovieList.add(itMov);
                            }
                        }
                        currMovieList = trueCurrMovieList;
                    }
                    ArrayList<Movie> remember2 = currMovieList;
                    ArrayList<Movie> trueTruecCurrMovieList = new ArrayList<>();
                    if (iterate.getFilters().getContains().getGenre() != null) {
                        for (Movie itMov : remember2) {
                            int hasGenre = 1;
                            for (int i = 0; i < iterate.getFilters()
                                    .getContains().getGenre().size(); ++i) {
                                if (!itMov.getGenres().contains(iterate
                                        .getFilters().getContains().getGenre().get(i))) {
                                    hasGenre = 0;
                                    break;
                                }
                            }
                            if (hasGenre == 1) {
                                trueTruecCurrMovieList.add(itMov);
                            }
                        }
                        currMovieList = trueTruecCurrMovieList;
                    }
                }
                //acum avem partea de sortare

                //aici o sa sortam currentMoviesList

                if (filter.getSort().getDuration() != null
                        && filter.getSort().getRating() == null) {
                    currMovieList.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(final Movie o1, final Movie o2) {
                            if (o1.getDuration() > o2.getDuration()) {
                                return 1;
                            }
                            if (o1.getDuration() < o2.getDuration()) {
                                return -1;
                            }
                            return 0;
                        }
                    });
                }
                if (filter.getSort().getDuration() != null
                        && filter.getSort().getRating() != null) {
                    if (filter.getSort().getRating().equals("increasing")
                            && filter.getSort().getDuration().equals("decreasing")) {
                        currMovieList.sort(new Comparator<Movie>() {
                            @Override
                            public int compare(final Movie o1, final Movie o2) {

                                if (o1.getDuration() < o2.getDuration()) {
                                    return 1;
                                }
                                if (o1.getDuration() > o2.getDuration()) {
                                    return -1;
                                }
                                if (o1.getRating() > o2.getRating()) {
                                    return 1;
                                }
                                if (o1.getRating() < o2.getRating()) {
                                    return -1;
                                }
                                return 0;
                            }
                        });
                    } else {
                        currMovieList.sort(new Comparator<Movie>() {
                            @Override
                            public int compare(final Movie o1, final Movie o2) {
                                if (o1.getDuration() > o2.getDuration()) {
                                    return 1;
                                }
                                if (o1.getDuration() < o2.getDuration()) {
                                    return -1;
                                }
                                if (o1.getRating() > o2.getRating()) {
                                    return 1;
                                }
                                if (o1.getRating() < o2.getRating()) {
                                    return 1;
                                }
                                return 0;
                            }
                        });
                    }

                }
                if (filter.getSort().getDuration() == null
                        && filter.getSort().getRating() != null) {
                    currMovieList.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(final Movie o1, final Movie o2) {
                            if (o1.getRating() > o2.getRating()) {
                                return 1;
                            }
                            if (o1.getRating() < o2.getRating()) {
                                return -1;
                            }
                            return 0;
                        }
                    });
                }

                ArrayList<Movie> currrMovieList = currMovieList;
                //ArrayList<Movie> currrMovieList = currMovieList;

                ObjectNode help = objectMapper.createObjectNode();
                ObjectNode helpp = objectMapper.createObjectNode();
                ObjectNode auxErr;
                auxErr = null;
                helpp.put("error", auxErr);

                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                currentPage.getCurrentUser().setCurrentMoviesList(currMovieList);
                for (Movie iterMovies : currMovieList) {
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
                    ajutor.put("numRatings",
                            iterMovies.getNumRatings());
                    outCurrMoviesList.add(ajutor);
                }
                helpp.set("currentMoviesList", outCurrMoviesList);

                ObjectNode auxCred = objectMapper.createObjectNode();
                Credentials cred = currentPage.getCurrentUser()
                        .getCredentials();
                auxCred.put("name", cred.getName());
                auxCred.put("password", cred.getPassword());
                auxCred.put("accountType", cred.getAccountType());
                auxCred.put("country", cred.getCountry());
                auxCred.put("balance", Integer.toString(cred
                        .getBalance()));
                help.put("credentials", auxCred);
                help.put("tokensCount", currentPage.getCurrentUser()
                        .getTokensCount());
                help.put("numFreePremiumMovies", currentPage.getCurrentUser()
                        .getNumFreePremiumMovies());

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
                ArrayNode outPurchasedMovies = objectMapper.createArrayNode();

                for (Movie iterMovies : currentPage.getCurrentUser()
                        .getPurchasedMovies()) {
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
                    ajutor.put("numRatings", iterMovies
                            .getNumRatings());
                    outPurchasedMovies.add(ajutor);
                }
                help.set("purchasedMovies", outPurchasedMovies);
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

    static void searchOnPage(final ObjectMapper objectMapper, final ArrayNode output,
                             final ArrayList<Movie> movies, final CurrentPage currentPage,
                             final ActionsIn iterate) {
        if (iterate.getFeature().equals("search")) {
            if (currentPage.getPageName().equals("movies")) {
                String start = iterate.getStartsWith();
                ArrayList<Movie> currMovieList = new ArrayList<>();
                for (Movie itMovie : movies) {
                    int banned = 0;
                    for (String itString : itMovie.getCountriesBanned()) {
                        if (currentPage.getCurrentUser().getCredentials()
                                .getCountry().equals(itString)) {
                            banned = 1;
                            break;
                        }
                    }
                    if (banned == 0) {
                        currMovieList.add(itMovie);
                    }
                }
                ArrayList<Movie> curMovieHelper = new ArrayList<>();
                for (Movie itMov : currMovieList) {
                    if (itMov.getName().startsWith(start)) {
                        curMovieHelper.add(itMov);
                    }
                }
                ObjectNode help = objectMapper.createObjectNode();
                ObjectNode helpp = objectMapper.createObjectNode();
                ObjectNode auxErr;
                auxErr = null;
                helpp.put("error", auxErr);
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                for (Movie itMovies : curMovieHelper) {
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

                ObjectNode auxCred = objectMapper.createObjectNode();
                Credentials cred = currentPage.getCurrentUser().getCredentials();
                auxCred.put("name", cred.getName());
                auxCred.put("password", cred.getPassword());
                auxCred.put("accountType", cred.getAccountType());
                auxCred.put("country", cred.getCountry());
                auxCred.put("balance", Integer.toString(cred.getBalance()));
                help.put("credentials", auxCred);
                help.put("tokensCount", 0);
                help.put("numFreePremiumMovies", currentPage.getCurrentUser()
                        .getNumFreePremiumMovies());

                ArrayNode outPurchasedMovies = objectMapper.createArrayNode();

                for (Movie iterMovies : currentPage.getCurrentUser()
                        .getPurchasedMovies()) {
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
                for (Movie iterMovies : currentPage.getCurrentUser()
                        .getWatchedMovies()) {
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
                for (Movie iterMovies : currentPage.getCurrentUser()
                        .getLikedMovies()) {
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
                for (Movie iterMovies : currentPage.getCurrentUser()
                        .getRatedMovies()) {
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
                help.set("currentMoviesList nu suntem pe pagina de movies",
                        outCurrMoviesList);
                ObjectNode aux = objectMapper.createObjectNode();
                aux = null;
                help.put("currentUser", aux);
                output.add(help);
                //eroare nu suntem pe pagina de movies
            }
        }
    }

    static void registerOnPage(final ObjectMapper objectMapper, final ArrayNode output,
                               final ArrayList<User> users, final CurrentPage currentPage,
                               final ActionsIn iterate) {
        if (iterate.getFeature().equals("register")) {
            if (currentPage.getPageName().equals("register") && currentPage
                    .getCurrentUser() == null) {
                //iterate.getCredentials()
                String name = iterate.getCredentials().getName();
                String password = iterate.getCredentials().getPassword();
                String accType = iterate.getCredentials().getAccountType();
                String country = iterate.getCredentials().getCountry();
                int balance = iterate.getCredentials().getBalance();
                User newUser = new User(name, password, accType, country, balance);

                int found = 0;
                for (User itUser : users) {
                    if (newUser.getCredentials().getPassword()
                            .equals(itUser.getCredentials().getPassword())
                            && newUser.getCredentials().getName()
                            .equals(itUser.getCredentials().getName())) {
                        found = 1;
                        break;
                    }
                }

                if (found == 1) {
                } else {
                    users.add(newUser);
                    currentPage.setCurrentUser(newUser);
                    currentPage.setPageName("homepage");
                    //facem si o afisare
                    ObjectNode help = objectMapper.createObjectNode();
                    ObjectNode helpp = objectMapper.createObjectNode();
                    ObjectNode auxErr;
                    auxErr = null;
                    helpp.put("error", auxErr);
                    ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                    for (String itMovies : new ArrayList<String>()) {
                        outCurrMoviesList.add(itMovies);
                    }
                    helpp.set("currentMoviesList", outCurrMoviesList);

                    ObjectNode auxCred = objectMapper.createObjectNode();
                    Credentials cred = newUser.getCredentials();
                    auxCred.put("name", cred.getName());
                    auxCred.put("password", cred.getPassword());
                    auxCred.put("accountType", cred.getAccountType());
                    auxCred.put("country", cred.getCountry());
                    auxCred.put("balance", Integer.toString(cred.getBalance()));
                    help.put("credentials", auxCred);
                    help.put("tokensCount", 0);
                    help.put("numFreePremiumMovies", newUser
                            .getNumFreePremiumMovies());

                    ArrayNode outPurchasedMovies = objectMapper.createArrayNode();

                    for (Movie iterMovies : currentPage.getCurrentUser()
                            .getPurchasedMovies()) {
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

                }
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
                currentPage.setPageName("homepage");
            }
        }
    }

    static void loginOnPage(final ObjectMapper objectMapper,
                            final ArrayNode output, final ArrayList<User> users,
                            final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getFeature().equals("login")) {
            if (currentPage.getPageName().equals("login") && currentPage.getCurrentUser() == null) {

                // acuma trebuie sa verificam ca asta e in baza noastra de date
                User find = null;
                for (User it : users) {
                    if (it.getCredentials().getName()
                            .equals(iterate.getCredentials().getName())
                            && it.getCredentials().getPassword()
                            .equals(iterate.getCredentials().getPassword())) {
                        // am gasit pe ala;
                        find = it;
                    }
                }
                if (find != null) {
                    //l-am gasit in baza noastra de date
                    currentPage.setCurrentUser(find);
                    currentPage.setPageName("homepage");
                    //facem si o afisare
                    ObjectNode help = objectMapper.createObjectNode();
                    ObjectNode helpp = objectMapper.createObjectNode();
                    ObjectNode auxErr;
                    auxErr = null;
                    helpp.put("error", auxErr);
                    ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                    for (String itMovies : new ArrayList<String>()) {
                        outCurrMoviesList.add(itMovies);
                    }
                    helpp.set("currentMoviesList", outCurrMoviesList);

                    ObjectNode auxCred = objectMapper.createObjectNode();
                    Credentials cred = find.getCredentials();
                    auxCred.put("name", cred.getName());
                    auxCred.put("password", cred.getPassword());
                    auxCred.put("accountType", cred.getAccountType());
                    auxCred.put("country", cred.getCountry());
                    auxCred.put("balance", Integer.toString(cred.getBalance()));
                    help.put("credentials", auxCred);
                    help.put("tokensCount", 0);
                    help.put("numFreePremiumMovies", find.getNumFreePremiumMovies());

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
                    // nu l-am gasit in baza noastra de date
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
                    currentPage.setPageName("homepage");
                }
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
                currentPage.setPageName("homepage");
            }
        }
    }
}
