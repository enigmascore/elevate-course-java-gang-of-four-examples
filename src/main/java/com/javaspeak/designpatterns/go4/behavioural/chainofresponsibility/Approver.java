package com.javaspeak.designpatterns.go4.behavioural.chainofresponsibility;

import java.util.Optional;

/**
 * The Handler role of the Chain of Responsibility pattern.
 * <p>
 * Each Approver holds a link to the next Approver in the chain.  When asked to approve a
 * {@link PurchaseRequest} an Approver either handles it (if the amount is within its approval
 * limit) or passes the request unchanged to the next Approver in the chain.  If the end of the
 * chain is reached without any Approver handling the request, an empty Optional is returned so
 * that the sender knows the request went unhandled.
 * <p>
 * The hierarchy is sealed: the only Approvers are {@link TeamLeadApprover},
 * {@link ManagerApprover} and {@link DirectorApprover}.
 *
 * @author John Dickerson - 11 September 2026
 */
public abstract sealed class Approver
        permits TeamLeadApprover, ManagerApprover, DirectorApprover {

    private final String name;
    private final Approver next;

    /**
     * Constructor.
     *
     * @param name
     *      The name of this approver, used in the approval report
     *
     * @param next
     *      The next approver in the chain, or null if this approver is the end of the chain
     */
    protected Approver( String name, Approver next ) {

        this.name = name;
        this.next = next;
    }


    /**
     * Attempts to approve the request.  If the request amount is within this approver's
     * approval limit the request is handled here; otherwise it is passed along the chain to
     * the next approver.
     *
     * @param purchaseRequest
     *      The purchase request to approve
     *
     * @return a description of who approved the request, or an empty Optional if no approver
     *      in the chain could handle it
     */
    public Optional<String> approve( PurchaseRequest purchaseRequest ) {

        if ( purchaseRequest.amount() <= getApprovalLimit() ) {

            return Optional.of(
                    name + " approved " + purchaseRequest.description() +
                            " costing " + purchaseRequest.amount() );
        }

        if ( next != null ) {

            return next.approve( purchaseRequest );
        }

        return Optional.empty();
    }


    /**
     * The largest amount this approver is allowed to approve.
     *
     * @return the approval limit in whole currency units
     */
    protected abstract int getApprovalLimit();
}
