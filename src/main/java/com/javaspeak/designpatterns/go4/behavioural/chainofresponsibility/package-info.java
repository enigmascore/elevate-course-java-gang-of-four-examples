/**
 * Gang of Four Chain of Responsibility pattern.
 * <p>
 * Text book description:
 * <p>
 * "Chain of Responsibility: A way of passing a request between a chain of objects. Avoid
 * coupling the sender of a request to its receiver by giving more than one object a chance to
 * handle the request. Chain the receiving objects and pass the request along the chain until an
 * object handles it."
 * <p>
 * In this example a
 * {@link com.javaspeak.designpatterns.go4.behavioural.chainofresponsibility.PurchaseRequest}
 * is passed along a chain of
 * {@link com.javaspeak.designpatterns.go4.behavioural.chainofresponsibility.Approver}s
 * (Team Lead, then Manager, then Director) until an approver with a large enough approval
 * limit handles it.  A request which exceeds every limit falls off the end of the chain and
 * is reported as unhandled.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.behavioural.chainofresponsibility;
