/**
 * Gang of Four Proxy pattern.
 * <p>
 * Text book description:
 * <p>
 * "Proxy: An object representing another object. Provide a surrogate or placeholder for
 * another object to control access to it."
 * <p>
 * In this example the
 * {@link com.javaspeak.designpatterns.go4.structural.proxy.TriangleProxy} proxies the
 * {@link com.javaspeak.designpatterns.go4.structural.proxy.Triangle} subject: it delegates
 * drawing to the subject while counting and logging how many times the
 * {@link com.javaspeak.designpatterns.go4.structural.proxy.Shape#drawShape()} method has been
 * invoked.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.structural.proxy;
