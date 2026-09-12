package com.javaspeak.designpatterns.go4.behavioural.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * Tests the State pattern example: the account state object upgrades and downgrades itself at
 * the salary thresholds documented in SalaryGrade, and balance changes never switch the state.
 *
 * @author John Dickerson - 11 September 2026
 */
public class StateApplicationTest {

    @Test
    public void accountUpgradesAtDocumentedThresholds() {

        StateApplication application = new StateApplication();

        assertEquals( "Starter Account", application.getAccountName() );
        assertEquals( 0, application.getOverdraft() );

        // one below the Standard threshold: stays a Starter Account
        application.setSalary( SalaryGrade.STANDARD_ACCOUNT.getSalary() - 1 );
        assertEquals( "Starter Account", application.getAccountName() );

        // exactly the Standard threshold: upgraded to a Standard Account
        application.setSalary( SalaryGrade.STANDARD_ACCOUNT.getSalary() );
        assertEquals( "Standard Account", application.getAccountName() );
        assertEquals( 700, application.getOverdraft() );

        // one below the Premiere threshold: stays a Standard Account
        application.setSalary( SalaryGrade.PREMIERE_ACCOUNT.getSalary() - 1 );
        assertEquals( "Standard Account", application.getAccountName() );

        // exactly the Premiere threshold: upgraded to a Premiere Account
        application.setSalary( SalaryGrade.PREMIERE_ACCOUNT.getSalary() );
        assertEquals( "Premiere Account", application.getAccountName() );
        assertEquals( 5000, application.getOverdraft() );
    }


    @Test
    public void accountDowngradesAtDocumentedThresholds() {

        StateApplication application = new StateApplication();

        application.setSalary( SalaryGrade.PREMIERE_ACCOUNT.getSalary() );
        assertEquals( "Premiere Account", application.getAccountName() );

        // below the Premiere threshold: downgraded to a Standard Account
        application.setSalary( SalaryGrade.PREMIERE_ACCOUNT.getSalary() - 1 );
        assertEquals( "Standard Account", application.getAccountName() );

        // below the Standard threshold: downgraded to a Starter Account
        application.setSalary( SalaryGrade.STANDARD_ACCOUNT.getSalary() - 1 );
        assertEquals( "Starter Account", application.getAccountName() );
    }


    @Test
    public void premiereAccountDowngradesStraightToStarter() {

        StateApplication application = new StateApplication();

        application.setSalary( 90000 );
        assertEquals( "Premiere Account", application.getAccountName() );

        application.setSalary( 1000 );
        assertEquals( "Starter Account", application.getAccountName() );
        assertEquals( 1000, application.getSalary() );
    }


    @Test
    public void balanceIsKeptAcrossTransitionsAndNeverSwitchesState() {

        StateApplication application = new StateApplication();

        application.setBalance( new BigDecimal( "1234.56" ) );
        assertEquals( "Starter Account", application.getAccountName() );

        application.setSalary( 10000 );
        assertEquals( "Standard Account", application.getAccountName() );

        // the new state object carries the balance over
        assertEquals( new BigDecimal( "1234.56" ), application.getBalance() );

        // a large balance alone does not upgrade the account
        application.setBalance( new BigDecimal( "1000000.00" ) );
        assertEquals( "Standard Account", application.getAccountName() );
    }


    @Test
    public void runExampleReportsUpgradesAndDowngrades() {

        String report = new StateApplication().runExample();

        int starter = report.indexOf( "Account Name : Starter Account" );
        int standard = report.indexOf( "Account Name : Standard Account", starter );
        int premiere = report.indexOf( "Account Name : Premiere Account", standard );
        int downgradedStandard = report.indexOf( "Account Name : Standard Account", premiere );
        int downgradedStarter =
                report.indexOf( "Account Name : Starter Account", downgradedStandard );

        assertTrue( starter >= 0 );
        assertTrue( standard > starter );
        assertTrue( premiere > standard );
        assertTrue( downgradedStandard > premiere );
        assertTrue( downgradedStarter > downgradedStandard );

        assertTrue( report.contains( "balance : 7000.00, salary : 82000" ) );
    }
}
