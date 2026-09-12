package com.javaspeak.designpatterns.go4.behavioural.state;

import java.math.BigDecimal;

/**
 * Provides common functionality between all the State implementations.
 * <p>
 * This class is sealed: the closed set of account states is StarterAccountState,
 * StandardAccountState and PremiereAccountState.  The salary and balance fields are private so
 * that state can only be changed through the State API.
 *
 * @author John Dickerson - 22 February 2020
 */
public abstract sealed class AbstractAccountState implements State
        permits StarterAccountState, StandardAccountState, PremiereAccountState {

    private int salary;
    private BigDecimal balance;

    /**
     * Creates an account state with the given salary and balance.
     *
     * @param salary
     *      the annual salary of the account holder
     *
     * @param balance
     *      the current balance of the account
     */
    protected AbstractAccountState( int salary, BigDecimal balance ) {

        this.salary = salary;
        this.balance = balance;
    }


    @Override
    public BigDecimal getBalance() {

        return balance;
    }


    @Override
    public void setBalance( BigDecimal balance ) {

        this.balance = balance;
    }


    @Override
    public int getSalary() {

        return salary;
    }


    /**
     * Records the new salary in this state object.  Subclasses call this from their
     * setSalary(..) implementations when the new salary does not cross an account threshold, so
     * no state transition is needed.
     *
     * @param salary
     *      the new annual salary
     */
    protected void retainSalary( int salary ) {

        this.salary = salary;
    }


    @Override
    public String toString() {

        return "Account Name : " + getAccountName() +
                ", Overdraft : " + getOverdraft() +
                ", balance : " + balance +
                ", salary : " + salary;
    }
}
