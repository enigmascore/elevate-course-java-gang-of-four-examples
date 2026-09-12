package com.javaspeak.designpatterns.go4.behavioural.memento;

/**
 * This class describes document editing functionality such as saving the document text, font
 * family and font size.
 * <p>
 * This class implements the Document interface which extends the Originator interface with its
 * getMemento() and restoreFromMemento(..) methods.
 * <p>
 * When the getMemento() method is called on the Document the Document returns a snapshot of its
 * state (document text, font family and font size) in a DocumentMemento record.
 * <p>
 * When restoreFromMemento(..) is called on the Document the Document will roll back its state
 * to the state encapsulated in the DocumentMemento record.
 *
 * @author John Dickerson - 22 February 2020
 */
public class DocumentImpl implements Document {

    private String documentText;
    private String fontFamily;
    private int fontSize;

    /**
     * Creates a DocumentImpl with no text, font family or font size set yet.
     */
    public DocumentImpl() {

    }


    /**
     * Nested record used to create a Memento saving the current state of the document.  Being a
     * record it is immutable: its state cannot be modified after it has been created.  It is the
     * only permitted implementation of the sealed Memento interface.
     *
     * @param documentText
     *      the document text at the time the snapshot was taken
     *
     * @param fontFamily
     *      the font family at the time the snapshot was taken
     *
     * @param fontSize
     *      the font size at the time the snapshot was taken
     */
    record DocumentMemento(
            String documentText, String fontFamily, int fontSize ) implements Memento {
    }

    // START Document interface methods ===========================================================

    @Override
    public void saveDocumentText( String documentText ) {

        this.documentText = documentText;
    }


    @Override
    public String getDocumentText() {

        return documentText;
    }


    @Override
    public void saveFontFamily( String fontFamily ) {

        this.fontFamily = fontFamily;
    }


    @Override
    public String getFontFamily() {

        return fontFamily;
    }


    @Override
    public void saveFontSize( int fontSize ) {

        this.fontSize = fontSize;
    }


    @Override
    public int getFontSize() {

        return fontSize;
    }

    // END Document interface methods =============================================================

    // START Originator interface methods =========================================================

    @Override
    public Memento getMemento() {

        return new DocumentMemento( documentText, fontFamily, fontSize );
    }


    @Override
    public void restoreFromMemento( Memento memento ) {

        // Memento is sealed, so this record-pattern switch is exhaustive: no default branch and
        // no unguarded cast is needed.
        switch ( memento ) {

            case DocumentMemento( String text, String family, int size ) -> {

                this.documentText = text;
                this.fontFamily = family;
                this.fontSize = size;
            }
        }
    }

    // END Originator interface methods ===========================================================
}
