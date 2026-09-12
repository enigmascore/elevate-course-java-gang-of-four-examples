package com.javaspeak.designpatterns.go4.behavioural.command;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Text book description:
 * <p>
 * "Command: Encapsulate a command request as an object. Encapsulate a request as an object,
 * thereby letting you parameterize clients with different requests, queue or log requests,
 * and support undoable operations."
 * <p>
 * This example uses the Command pattern.
 * <p>
 * The Command pattern can be seen in web frameworks like the Struts framework.  In the Struts
 * framework different urls are mapped to different Commands. A centralised Controller called
 * the Dispatcher Controller delegates to a particular Command object to do some processing when
 * it receives a particular url.  After the Command has finished processing, the request and
 * attributes are forwarded to a JSP servlet which merges HTML and the attributes into the
 * response.
 * <p>
 * This example shows similarity to the Struts framework:
 * <p>
 * A call is made to the processRequest method of the RequestProcessor passing it a map of
 * attributes.
 * <p>
 * The RequestProcessor retrieves the RequestType from the request and executes a command which
 * is mapped to that RequestType.
 * <p>
 * The Command class reads the map of attributes and creates a modified new map of the
 * attributes which it calls the handleResponse(..) method of the Response with. The
 * handleResponse method appends the modified attributes to the report returned by runExample().
 * <p>
 * This example calls the RequestProcessor twice, each time with a different RequestType.
 * <p>
 * The first RequestType is RequestType.CAPITALISE and the RequestProcessor calls the
 * CapitaliseCommand.
 * <p>
 * The second RequestType is RequestType.SORT and the RequestProcessor calls the SortCommand.
 * <p>
 * Both the calls to the RequestProcessor use the same Response lambda and the report returned
 * looks as follows:
 * <pre>
 * ===============================================
 * name : JOHN DICKERSON
 * message : HELLO WORLD!
 * quote : COMMON SENSE IS NOT COMMON
 * ===============================================
 * quote : Common Sense is not Common
 * message : Hello World!
 * name : John Dickerson
 * ===============================================
 * </pre>
 * Notice that the first command capitalised the text and the second command sorted the
 * attribute entries by the attribute values.
 *
 * @author John Dickerson - 21 February 2020
 */
public class CommandApplication {

    /**
     * Default constructor.
     */
    public CommandApplication() {

    }


    /**
     * Runs the example: sends a CAPITALISE request and a SORT request through the
     * RequestProcessor and collects the response attributes of both in a report.
     *
     * @return a report of the response attributes produced by each command
     */
    public String runExample() {

        RequestProcessor requestProcessor = new RequestProcessorImpl();

        var report = new StringBuilder();

        String divider = "===============================================\n";

        // The Response call back appends each response attribute to the report
        Response response = responseAttributes ->
                responseAttributes.forEach( ( key, value ) ->
                        report.append( key ).append( " : " ).append( value ).append( '\n' ) );

        Map<String, String> requestAttributes = new LinkedHashMap<>();
        requestAttributes.put( "name", "John Dickerson" );
        requestAttributes.put( "message", "Hello World!" );
        requestAttributes.put( "quote", "Common Sense is not Common" );

        report.append( divider );

        requestProcessor.processRequest(
                new RequestImpl( RequestType.CAPITALISE, requestAttributes ), response );

        report.append( divider );

        requestProcessor.processRequest(
                new RequestImpl( RequestType.SORT, requestAttributes ), response );

        report.append( divider );

        return report.toString();
    }


    /**
     * Runs the example and prints its report.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        CommandApplication application = new CommandApplication();
        System.out.println( application.runExample() );
    }
}
