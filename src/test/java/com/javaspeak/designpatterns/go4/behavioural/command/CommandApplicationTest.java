package com.javaspeak.designpatterns.go4.behavioural.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests the Command pattern example: the CAPITALISE request capitalises the attribute values,
 * the SORT request orders the entries by their values, and runExample() reports both.
 *
 * @author John Dickerson - 11 September 2026
 */
public class CommandApplicationTest {

    private Map<String, String> buildRequestAttributes() {

        Map<String, String> requestAttributes = new LinkedHashMap<>();
        requestAttributes.put( "name", "John Dickerson" );
        requestAttributes.put( "message", "Hello World!" );
        requestAttributes.put( "quote", "Common Sense is not Common" );
        return requestAttributes;
    }


    @Test
    public void capitaliseCommandCapitalisesAllValues() {

        Map<String, String> received = new LinkedHashMap<>();

        new RequestProcessorImpl().processRequest(
                new RequestImpl( RequestType.CAPITALISE, buildRequestAttributes() ),
                received::putAll );

        Map<String, String> expected = new LinkedHashMap<>();
        expected.put( "name", "JOHN DICKERSON" );
        expected.put( "message", "HELLO WORLD!" );
        expected.put( "quote", "COMMON SENSE IS NOT COMMON" );

        assertEquals( expected, received );
    }


    @Test
    public void sortCommandOrdersEntriesByValue() {

        Map<String, String> received = new LinkedHashMap<>();

        new RequestProcessorImpl().processRequest(
                new RequestImpl( RequestType.SORT, buildRequestAttributes() ),
                received::putAll );

        assertEquals( List.of( "quote", "message", "name" ), List.copyOf( received.keySet() ) );
    }


    @Test
    public void sortCommandAllowsDuplicateValues() {

        Map<String, String> requestAttributes = new LinkedHashMap<>();
        requestAttributes.put( "b", "same" );
        requestAttributes.put( "a", "same" );

        Map<String, String> received = new LinkedHashMap<>();

        new RequestProcessorImpl().processRequest(
                new RequestImpl( RequestType.SORT, requestAttributes ), received::putAll );

        assertEquals( 2, received.size() );
        assertEquals( "same", received.get( "a" ) );
        assertEquals( "same", received.get( "b" ) );
    }


    @Test
    public void runExampleReportsCapitalisedAndSortedAttributes() {

        String expected = """
                ===============================================
                name : JOHN DICKERSON
                message : HELLO WORLD!
                quote : COMMON SENSE IS NOT COMMON
                ===============================================
                quote : Common Sense is not Common
                message : Hello World!
                name : John Dickerson
                ===============================================
                """;

        assertEquals( expected, new CommandApplication().runExample() );
    }
}
