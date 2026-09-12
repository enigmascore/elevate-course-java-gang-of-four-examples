package com.javaspeak.designpatterns.go4.behavioural.state;

import java.math.BigDecimal;

/**
 * The AbstractAccountState class which all state objects extend implements this State interface.
 * <p>
 * The interface is sealed: the set of account states is closed (Starter, Standard and Premiere,
 * all extending AbstractAccountState), which allows callers to switch over the current state
 * exhaustively using pattern matching.
 *
 * @author John Dickerson - 22 February 2020
 */
public sealed interface State permits AbstractAccountState {

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
     * Sets the annual salary of the account holder.  Depending on the new salary the state
     * object may ask the StateContext to switch the current state object for another one
     * (upgrading or downgrading the account).
     *
     * @param stateContext
     *      the StateContext holding the current state object, used to change state
     *
     * @param salary
     *      the new annual salary
     */
    void setSalary( StateContext stateContext, int salary );


    /**
     * Returns the annual salary of the account holder.
     *
     * @return the annual salary
     */
    int getSalary();


    /**
     * Returns the overdraft limit of this account type.
     *
     * @return the overdraft limit
     */
    int getOverdraft();


    /**
     * Returns the name of this account type.
     *
     * @return the account name, e.g. "Starter Account"
     */
    String getAccountName();
}
