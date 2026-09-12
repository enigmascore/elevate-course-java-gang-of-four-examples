package com.javaspeak.designpatterns.go4.behavioural.state;

import java.math.BigDecimal;

/**
 * Text book description:
 * <p>
 * "State: Alter an object's behaviour when its state changes. Allow an object to alter its
 * behaviour when its internal state changes. The object will appear to change its class."
 * <p>
 * This example uses the state pattern.  State objects that extend an abstract state class can be
 * switched from one to the other at runtime.
 * <p>
 * A StateContext delegates method calls to the current state object.  The StateContext object
 * has the same method names as the State objects.
 * <p>
 * During a method call the State object can decide to switch itself for another State object.
 * <p>
 * In other words some or all of the methods in the State object have the ability to switch the
 * State object for another state object.
 * <p>
 * The new state object also extends the same abstract state object and has the same method
 * signatures, however the implementation of the methods are likely to be different.  This
 * pattern uses polymorphism to modify the behaviour at runtime.
 * <p>
 * In this example the StateApplication class is the StateContext and holds a reference to the
 * current State Object extending AbstractAccountState. There are 3 classes that extend
 * AbstractAccountState: StarterAccountState, StandardAccountState and PremiereAccountState.
 * The State hierarchy is sealed, so the current state can be described with an exhaustive
 * pattern-matching switch.
 * <p>
 * This example models a Bank Account that can be upgraded or downgraded according to the current
 * annual salary of the account holder.  The setSalary( int salary ) method in StateApplication
 * delegates to the current State Object by calling its setSalary( StateContext stateContext,
 * int salary ) method.
 * <p>
 * Notice that StateApplication passes a reference of itself ( StateContext stateContext ) in the
 * setSalary(..) method.  The reason it does this is so that the State Object, e.g.
 * StandardAccountState can then call the stateContext to switch the current State object for
 * another one:
 * <pre>
 * {@code
 *     if ( salary >= SalaryGrade.PREMIERE_ACCOUNT.getSalary() ) {
 *
 *         stateContext.changeState( new PremiereAccountState( salary, getBalance() ) );
 *     }
 *     else if ( salary < SalaryGrade.STANDARD_ACCOUNT.getSalary() ) {
 *
 *         stateContext.changeState( new StarterAccountState( salary, getBalance() ) );
 *     }
 *     else {
 *
 *         retainSalary( salary );
 *     }
 * }
 * </pre>
 * In fact changing the salary via the setSalary(..) method can consequently result in the
 * account being upgraded or downgraded.  Notice that not all method calls of StateApplication
 * delegated to a state object result in the State object being swapped for another State Object.
 * For example the setBalance(..) method does not result in the state object switching, the
 * reason for this is that the current bank account type depends on the annual salary and not the
 * current bank balance.
 *
 * @author John Dickerson - 22 February 2020
 */
public class StateApplication implements StateContext {

    private AbstractAccountState accountState;

    /**
     * Initialise state with the Starter Bank Account and a balance of 200 GBP
     */
    public StateApplication() {

        accountState = new StarterAccountState( 0, new BigDecimal( "200.00" ) );
    }


    @Override
    public void changeState( AbstractAccountState newState ) {

        accountState = newState;
    }


    @Override
    public void setSalary( int salary ) {

        accountState.setSalary( this, salary );
    }


    @Override
    public int getSalary() {

        return accountState.getSalary();
    }


    @Override
    public BigDecimal getBalance() {

        return accountState.getBalance();
    }


    @Override
    public void setBalance( BigDecimal balance ) {

        accountState.setBalance( balance );
    }


    @Override
    public int getOverdraft() {

        return accountState.getOverdraft();
    }


    @Override
    public String getAccountName() {

        return accountState.getAccountName();
    }


    /**
     * Describes the current account state.  The State hierarchy is sealed, so this
     * pattern-matching switch is exhaustive without a default branch.
     *
     * @return a one line description of the current account state
     */
    private String describeAccountState() {

        return switch ( accountState ) {

            case StarterAccountState starter ->
                    "Starter account: no overdraft, for salaries below " +
                            SalaryGrade.STANDARD_ACCOUNT.getSalary();

            case StandardAccountState standard ->
                    "Standard account: for salaries from " +
                            SalaryGrade.STANDARD_ACCOUNT.getSalary() + " to below " +
                            SalaryGrade.PREMIERE_ACCOUNT.getSalary();

            case PremiereAccountState premiere ->
                    "Premiere account: for salaries of " +
                            SalaryGrade.PREMIERE_ACCOUNT.getSalary() + " and above";
        };
    }


    private void appendStatus( StringBuilder report ) {

        report.append( "===============================================\n" );
        report.append( accountState ).append( '\n' );
        report.append( describeAccountState() ).append( '\n' );
    }


    /**
     * Runs the example.  Note the accountState.toString() method is in the AbstractAccountState
     * which the state objects extend.  The toString() method reports the state of the object.
     * <p>
     * Notice that the setSalary(..) method in this class delegates to the current state object
     * and calls the state object's setSalary(..) method.  The setSalary(..) method of the state
     * object has the ability to switch the current bank account state object to another one:
     * the report shows the account being upgraded from Starter to Standard to Premiere as the
     * salary rises, and downgraded again as it falls.
     *
     * @return a report of the account state after each salary or balance change
     */
    public String runExample() {

        var report = new StringBuilder();

        setBalance( new BigDecimal( "500.00" ) );
        appendStatus( report );

        // below the Standard Account threshold: stays a Starter Account
        setSalary( 4000 );
        setBalance( new BigDecimal( "1000.00" ) );
        appendStatus( report );

        // at or above the Standard Account threshold: upgraded to a Standard Account
        setSalary( 7200 );
        setBalance( new BigDecimal( "3000.00" ) );
        appendStatus( report );

        // at or above the Premiere Account threshold: upgraded to a Premiere Account
        setSalary( 82000 );
        setBalance( new BigDecimal( "7000.00" ) );
        appendStatus( report );

        // back below the Premiere Account threshold: downgraded to a Standard Account
        setSalary( 20000 );
        appendStatus( report );

        // back below the Standard Account threshold: downgraded to a Starter Account
        setSalary( 5000 );
        appendStatus( report );

        report.append( "===============================================\n" );
        return report.toString();
    }


    /**
     * Runs the example and prints the report of account state transitions.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        StateApplication application = new StateApplication();
        System.out.println( application.runExample() );
    }
}
