package struct;

import input.CredentialsIn;

public class Credentials {
    private String name;
    private String password = "";
    private String accountType = "";
    private String country = "";
    private int balance;

    public Credentials(final CredentialsIn credentials) {
        this.name = credentials.getName();
        this.password = credentials.getPassword();
        this.accountType = credentials.getAccountType();
        this.country = credentials.getCountry();
        this.balance = credentials.getBalance();
    }

    public Credentials(final Credentials credentials) {
        this.name = credentials.getName();
        this.password = credentials.getPassword();
        this.accountType = credentials.getAccountType();
        this.country = credentials.getCountry();
        this.balance = credentials.getBalance();
    }

    public Credentials() {

    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(final String password) {
        this.password = password;
    }

    public final String getAccountType() {
        return accountType;
    }

    public final void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public final String getCountry() {
        return country;
    }

    public final void setCountry(final String country) {
        this.country = country;
    }

    public final int getBalance() {
        return balance;
    }

    public final void setBalance(final int balance) {
        this.balance = balance;
    }

    @Override
    public final String toString() {
        return "CredentialsIn{"
                +
                "name='" + name + '\''
                +
                ", password='" + password + '\''
                +
                ", accountType='" + accountType + '\''
                +
                ", country='" + country + '\''
                +
                ", balance=" + balance
                +
                '}';
    }
}
