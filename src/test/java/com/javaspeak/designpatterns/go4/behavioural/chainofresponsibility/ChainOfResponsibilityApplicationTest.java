package com.javaspeak.designpatterns.go4.behavioural.chainofresponsibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

/**
 * Tests the Chain of Responsibility pattern example: each request is handled by the first
 * approver in the chain whose limit is large enough, and requests exceeding every limit are
 * reported as unhandled.
 *
 * @author John Dickerson - 11 September 2026
 */
public class ChainOfResponsibilityApplicationTest {

    private Approver buildChain() {

        return new TeamLeadApprover( new ManagerApprover( new DirectorApprover( null ) ) );
    }


    @Test
    public void teamLeadHandlesSmallRequest() {

        Optional<String> approval = buildChain().approve( new PurchaseRequest( "laptop", 800 ) );

        assertEquals( Optional.of( "Team Lead approved laptop costing 800" ), approval );
    }


    @Test
    public void managerHandlesMediumRequest() {

        Optional<String> approval =
                buildChain().approve( new PurchaseRequest( "team offsite", 7_500 ) );

        assertEquals( Optional.of( "Manager approved team offsite costing 7500" ), approval );
    }


    @Test
    public void directorHandlesLargeRequest() {

        Optional<String> approval =
                buildChain().approve( new PurchaseRequest( "server cluster", 60_000 ) );

        assertEquals( Optional.of( "Director approved server cluster costing 60000" ), approval );
    }


    @Test
    public void requestExceedingEveryLimitIsUnhandled() {

        Optional<String> approval =
                buildChain().approve( new PurchaseRequest( "office building", 750_000 ) );

        assertEquals( Optional.empty(), approval );
    }


    @Test
    public void runExampleReportsEveryRequest() {

        String report = new ChainOfResponsibilityApplication().runExample();

        assertTrue( report.contains( "Team Lead approved laptop costing 800" ) );
        assertTrue( report.contains( "Manager approved team offsite costing 7500" ) );
        assertTrue( report.contains( "Director approved server cluster costing 60000" ) );
        assertTrue( report.contains( "Nobody could approve office building costing 750000" ) );
    }
}
