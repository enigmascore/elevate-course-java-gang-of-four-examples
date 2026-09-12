package com.javaspeak.designpatterns.go4.behavioural.chainofresponsibility;

/**
 * A request for a purchase to be approved.  The request is passed along the chain of
 * {@link Approver}s until one of them has a large enough approval limit to handle it.
 *
 * @param description what the purchase is for, for example "laptop"
 * @param amount the cost of the purchase in whole currency units
 *
 * @author John Dickerson - 11 September 2026
 */
public record PurchaseRequest( String description, int amount ) {
}
