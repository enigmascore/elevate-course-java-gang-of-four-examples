package com.javaspeak.designpatterns.go4.behavioural.chainofresponsibility;

/**
 * The first Approver in the chain.  A team lead can approve small purchases costing up to
 * 1,000; anything larger is passed along the chain.
 *
 * @author John Dickerson - 11 September 2026
 */
public final class TeamLeadApprover extends Approver {

    private static final int APPROVAL_LIMIT = 1_000;

    /**
     * Constructor.
     *
     * @param next
     *      The next approver in the chain, or null if this approver is the end of the chain
     */
    public TeamLeadApprover( Approver next ) {

        super( "Team Lead", next );
    }


    @Override
    protected int getApprovalLimit() {

        return APPROVAL_LIMIT;
    }
}
