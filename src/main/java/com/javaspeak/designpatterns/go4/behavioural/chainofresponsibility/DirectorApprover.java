package com.javaspeak.designpatterns.go4.behavioural.chainofresponsibility;

/**
 * The last Approver in the chain.  A director can approve purchases costing up to 100,000.
 * If even the director cannot approve the purchase the request goes unhandled and the caller
 * is told so.
 *
 * @author John Dickerson - 11 September 2026
 */
public final class DirectorApprover extends Approver {

    private static final int APPROVAL_LIMIT = 100_000;

    /**
     * Constructor.  The director is usually the end of the chain, in which case null is passed
     * as the next approver.
     *
     * @param next
     *      The next approver in the chain, or null if this approver is the end of the chain
     */
    public DirectorApprover( Approver next ) {

        super( "Director", next );
    }


    @Override
    protected int getApprovalLimit() {

        return APPROVAL_LIMIT;
    }
}
