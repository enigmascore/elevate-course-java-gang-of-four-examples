package com.javaspeak.designpatterns.go4.behavioural.chainofresponsibility;

/**
 * The second Approver in the chain.  A manager can approve purchases costing up to 10,000;
 * anything larger is passed along the chain.
 *
 * @author John Dickerson - 11 September 2026
 */
public final class ManagerApprover extends Approver {

    private static final int APPROVAL_LIMIT = 10_000;

    /**
     * Constructor.
     *
     * @param next
     *      The next approver in the chain, or null if this approver is the end of the chain
     */
    public ManagerApprover( Approver next ) {

        super( "Manager", next );
    }


    @Override
    protected int getApprovalLimit() {

        return APPROVAL_LIMIT;
    }
}
