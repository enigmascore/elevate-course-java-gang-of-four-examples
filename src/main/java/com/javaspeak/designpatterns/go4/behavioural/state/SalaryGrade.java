package com.javaspeak.designpatterns.go4.behavioural.state;

/**
 * Shows what the minimum annual salary is for the different account types.
 *
 * @author John Dickerson - 22 February 2020
 */
public enum SalaryGrade {

    /** Minimum annual salary for a Starter Account. */
    STARTER_ACCOUNT( 0 ),

    /** Minimum annual salary for a Standard Account. */
    STANDARD_ACCOUNT( 7000 ),

    /** Minimum annual salary for a Premiere Account. */
    PREMIERE_ACCOUNT( 80000 );

    private final int salary;

    SalaryGrade( int salary ) {

        this.salary = salary;
    }


    /**
     * Returns the minimum annual salary for this account type.
     *
     * @return the minimum annual salary
     */
    public int getSalary() {

        return salary;
    }
}
