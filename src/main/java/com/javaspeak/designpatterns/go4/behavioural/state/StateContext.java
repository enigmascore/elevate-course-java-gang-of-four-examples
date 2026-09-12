package com.javaspeak.designpatterns.go4.behavioural.state;

import java.math.BigDecimal;

/**
 * The StateContext holds the current state object and delegates its method calls to it.  In this
 * example StateApplication implements this interface.
 * <p>
 * A state object is passed the StateContext in its setSalary(..) method so that it can call
 * changeState(..) to switch the current state object for another one.
 *
 * @author John Dickerson - 22 February 2020
 */
public interface StateContext {

    /**
     * Switches the current state object for the given one.  Called by the state objects
     * themselves when a salary change crosses an account threshold.
     *
     * @param newState
     *      the state object to switch to
     */
    void changeState( AbstractAccountState newState );


    /**
     * Returns the annual salary of the account holder.
     *
     * @return the annual salary
     */
    int getSalary();


    /**
     * Sets the annual salary of the account holder.  Delegates to the current state object,
     * which may switch itself for another state object (upgrading or downgrading the account).
     *
     * @param salary
     *      the new annual salary
     */
    void setSalary( int salary );


    /**
     * Returns the current balance of the account.
     *
     * @return the current balance
     */
    BigDecimal getBalance();


    /**
     * Sets the balance of the account.  Changing the balance never switches the state object:
     * the account type depends on the annual salary, not on the current balance.
     *
     * @param balance
     *      the new balance
     */
    void setBalance( BigDecimal balance );


    /**
     * Returns the overdraft limit of the current account type.
     *
     * @return the overdraft limit
     */
    int getOverdraft();


    /**
     * Returns the name of the current account type.
     *
     * @return the account name, e.g. "Starter Account"
     */
    String getAccountName();
}
