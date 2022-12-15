package struct;

import input.CredentialsIn;
import input.UserIn;

import java.util.ArrayList;

public class User {

    private int magicNumber = 15;
    private Credentials credentials = new Credentials();

    private int tokensCount;

    private int numFreePremiumMovies = this.magicNumber;

    private ArrayList<Movie> purchasedMovies = new ArrayList<Movie>();

    private ArrayList<Movie> watchedMovies = new ArrayList<Movie>();

    private ArrayList<Movie> likedMovies = new ArrayList<Movie>();

    private ArrayList<Movie> ratedMovies = new ArrayList<Movie>();

    private  ArrayList<Movie> currentMoviesList = new ArrayList<Movie>();

    public User(final UserIn user) {
        this.credentials = new Credentials(user.getCredentials());
        this.numFreePremiumMovies = this.magicNumber;
    }

    public User(final User user) {
        this.credentials = new Credentials(user.getCredentials());
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
        this.tokensCount = user.getTokensCount();
        this.purchasedMovies = new ArrayList<>();
        for (Movie it : user.getPurchasedMovies()) {
            this.purchasedMovies.add(it);
        }
        this.watchedMovies = new ArrayList<>();
        for (Movie it : user.getWatchedMovies()) {
            this.watchedMovies.add(it);
        }
        this.likedMovies = new ArrayList<>();
        for (Movie it : user.getLikedMovies()) {
            this.likedMovies.add(it);
        }
        this.ratedMovies = new ArrayList<>();
        for (Movie it : user.getRatedMovies()) {
            this.ratedMovies.add(it);
        }
        this.credentials.setAccountType("standard");
    }

    public User(final Credentials cred) {
        this.credentials = new Credentials(cred);
        this.numFreePremiumMovies = this.magicNumber;

    }

    public User() {

    }

    public User(final String name, final String password,
                final String accountType, final String country,
                final int balance) {
        this.credentials.setName(name);
        this.credentials.setPassword(password);
        this.credentials.setAccountType(accountType);
        this.credentials.setCountry(country);
        this.credentials.setBalance(balance);
    }

    public User(final CredentialsIn credentials) {
        this.credentials = new Credentials(credentials);
        this.numFreePremiumMovies = this.magicNumber;
    }

    public final Credentials getCredentials() {
        return credentials;
    }

    public final void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public final int getTokensCount() {
        return tokensCount;
    }

    public final void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public final int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public final void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public final ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public final void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public final ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public final void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public final ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public final void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public final ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public final void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public final ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public final void setCurrentMoviesList(final ArrayList<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    @Override
    public final String toString() {
        return "User{"
                +
                "credentials=" + credentials
                +
                '}';
    }
}
