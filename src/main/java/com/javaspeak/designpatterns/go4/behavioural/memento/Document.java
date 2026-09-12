package com.javaspeak.designpatterns.go4.behavioural.memento;

/**
 * This interface describes document editing functionality such as saving the document text, font
 * family and font size.
 * <p>
 * This interface extends the Originator interface which has additional methods like:
 * <pre>
 * {@code
 * Memento getMemento();
 * void restoreFromMemento( Memento memento );
 * }
 * </pre>
 * The Document interface is implemented by the Originator.  When the getMemento() method is
 * called on the Document the Document returns a snapshot of its state in a DocumentMemento
 * object.
 * <p>
 * When restoreFromMemento(..) is called on the Document the Document will roll back its state
 * to the state encapsulated in the DocumentMemento object.
 *
 * @author John Dickerson - 22 February 2020
 */
public interface Document extends Originator {

    /**
     * Saves the document text.
     *
     * @param documentText
     *      the text of the document
     */
    void saveDocumentText( String documentText );


    /**
     * Returns the current document text.
     *
     * @return the current document text
     */
    String getDocumentText();


    /**
     * Saves the font family used to render the document.
     *
     * @param fontFamily
     *      the font family, e.g. "arial"
     */
    void saveFontFamily( String fontFamily );


    /**
     * Returns the current font family.
     *
     * @return the current font family
     */
    String getFontFamily();


    /**
     * Saves the font size used to render the document.
     *
     * @param fontSize
     *      the font size in points
     */
    void saveFontSize( int fontSize );


    /**
     * Returns the current font size.
     *
     * @return the current font size in points
     */
    int getFontSize();
}
