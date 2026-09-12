/**
 * Gang of Four State pattern.
 * <p>
 * Text book description:
 * <p>
 * "State: Alter an object's behaviour when its state changes. Allow an object to alter its
 * behaviour when its internal state changes. The object will appear to change its class."
 * <p>
 * In this example a bank account is modelled whose account type depends on the annual salary of
 * the account holder.  The StateContext
 * ({@link com.javaspeak.designpatterns.go4.behavioural.state.StateApplication}) delegates its
 * calls to the current {@link com.javaspeak.designpatterns.go4.behavioural.state.State} object.
 * The sealed State hierarchy has three implementations, all extending
 * {@link com.javaspeak.designpatterns.go4.behavioural.state.AbstractAccountState}: Starter,
 * Standard and Premiere.  A salary change may make the current state object swap itself for
 * another one, upgrading or downgrading the account at the thresholds defined in
 * {@link com.javaspeak.designpatterns.go4.behavioural.state.SalaryGrade}.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.behavioural.state;
