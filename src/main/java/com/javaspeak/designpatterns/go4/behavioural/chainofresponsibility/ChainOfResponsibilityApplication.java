package com.javaspeak.designpatterns.go4.behavioural.chainofresponsibility;

import java.util.List;

/**
 * Text book description:
 * <p>
 * "Chain of Responsibility: A way of passing a request between a chain of objects. Avoid
 * coupling the sender of a request to its receiver by giving more than one object a chance to
 * handle the request. Chain the receiving objects and pass the request along the chain until an
 * object handles it."
 * <p>
 * This example uses the Chain of Responsibility pattern.
 * <p>
 * The example models purchase approval in a company.  The Handler role is played by
 * {@link Approver}: each Approver holds a link to the next Approver in the chain and an
 * approval limit.  When an Approver receives a {@link PurchaseRequest} it either approves it
 * (if the amount is within its limit) or passes the request along the chain to the next
 * Approver.
 * <p>
 * The chain built here is Team Lead (limit 1,000), then Manager (limit 10,000), then Director
 * (limit 100,000).  The sender only ever talks to the head of the chain - it neither knows nor
 * cares which Approver ends up handling each request.
 * <p>
 * Four requests are sent down the chain: a laptop for 800 (handled by the Team Lead), a team
 * offsite for 7,500 (handled by the Manager), a server cluster for 60,000 (handled by the
 * Director) and an office building for 750,000 which exceeds every limit, so it falls off the
 * end of the chain unhandled and is reported as such.
 * <p>
 * A well known example of this pattern is the Servlet Filter chain in Servlet engines, where
 * each Filter works on the request and then passes it along the chain.
 *
 * @author John Dickerson - 21 February 2020
 */
public class ChainOfResponsibilityApplication {

    /**
     * Default constructor.
     */
    public ChainOfResponsibilityApplication() {

    }


    /**
     * Runs the example: builds the approval chain, sends four purchase requests down it and
     * reports which approver handled which request.
     *
     * @return a report of who approved (or could not approve) each request
     */
    public String runExample() {

        // Build the chain: Team Lead -> Manager -> Director
        Approver approvalChain =
                new TeamLeadApprover( new ManagerApprover( new DirectorApprover( null ) ) );

        List<PurchaseRequest> purchaseRequests = List.of(
                new PurchaseRequest( "laptop", 800 ),
                new PurchaseRequest( "team offsite", 7_500 ),
                new PurchaseRequest( "server cluster", 60_000 ),
                new PurchaseRequest( "office building", 750_000 ) );

        var report = new StringBuilder();

        for ( PurchaseRequest purchaseRequest : purchaseRequests ) {

            // The sender only talks to the head of the chain; the request travels along the
            // chain until an Approver with a large enough limit handles it
            report.append(
                    approvalChain.approve( purchaseRequest ).orElse(
                            "Nobody could approve " + purchaseRequest.description() +
                                    " costing " + purchaseRequest.amount() ) )
                    .append( '\n' );
        }

        return report.toString();
    }


    /**
     * Runs the example and prints its report.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        ChainOfResponsibilityApplication application = new ChainOfResponsibilityApplication();
        System.out.println( application.runExample() );
    }
}
