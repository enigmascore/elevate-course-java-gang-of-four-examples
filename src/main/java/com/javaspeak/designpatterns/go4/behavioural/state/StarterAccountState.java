package com.javaspeak.designpatterns.go4.behavioural.state;

import java.math.BigDecimal;

/**
 * This is the state object for the Starter Bank Account Type where the criteria for having one is
 * determined by the current annual Salary. This state object will be automatically upgraded to a
 * StandardAccountState if the salary rises to or above the salary defined in the enum:
 * <p>
 * SalaryGrade.STANDARD_ACCOUNT.getSalary().
 * <p>
 * Similarly the account will be automatically upgraded to a PremiereAccountState if the salary
 * rises to:
 * <p>
 * SalaryGrade.PREMIERE_ACCOUNT.getSalary()
 * <p>
 * The other method calls do not result in the State Object being switched for another one.
 * However the other methods have different method implementations amongst the state objects. For
 * example calling getOverdraft() on a PremiereAccountState gives an overdraft of 5000 while
 * calling getOverdraft() on StandardAccountState gives an overdraft of 700.
 *
 * @author John Dickerson - 22 February 2020
 */
public final class StarterAccountState extends AbstractAccountState {

    /**
     * Creates a StarterAccountState with the given salary and balance.
     *
     * @param salary
     *      the annual salary of the account holder
     *
     * @param balance
     *      the current balance of the account
     */
    public StarterAccountState( int salary, BigDecimal balance ) {

        super( salary, balance );
    }


    @Override
    public void setSalary( StateContext stateContext, int salary ) {

        if ( salary >= SalaryGrade.PREMIERE_ACCOUNT.getSalary() ) {

            stateContext.changeState( new PremiereAccountState( salary, getBalance() ) );
        }
        else if ( salary >= SalaryGrade.STANDARD_ACCOUNT.getSalary() ) {

            stateContext.changeState( new StandardAccountState( salary, getBalance() ) );
        }
        else {

            retainSalary( salary );
        }
    }


    @Override
    public int getOverdraft() {

        return 0;
    }


    @Override
    public String getAccountName() {

        return "Starter Account";
    }
}
