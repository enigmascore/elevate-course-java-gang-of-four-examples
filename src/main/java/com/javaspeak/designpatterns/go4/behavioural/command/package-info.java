/**
 * Gang of Four Command pattern.
 * <p>
 * Text book description:
 * <p>
 * "Command: Encapsulate a command request as an object. Encapsulate a request as an object,
 * thereby letting you parameterize clients with different requests, queue or log requests,
 * and support undoable operations."
 * <p>
 * In this example a
 * {@link com.javaspeak.designpatterns.go4.behavioural.command.RequestProcessor} looks up the
 * {@link com.javaspeak.designpatterns.go4.behavioural.command.Command} mapped to the
 * {@link com.javaspeak.designpatterns.go4.behavioural.command.RequestType} of each
 * {@link com.javaspeak.designpatterns.go4.behavioural.command.Request} and executes it.  The
 * command returns its result through the
 * {@link com.javaspeak.designpatterns.go4.behavioural.command.Response} callback.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.behavioural.command;
