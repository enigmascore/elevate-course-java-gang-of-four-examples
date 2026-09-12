package com.javaspeak.designpatterns.go4.behavioural.strategy;

/**
 * Text book description:
 * <p>
 * "Strategy: Encapsulates an algorithm inside a class. Define a family of algorithms,
 * encapsulate each one, and make them interchangeable.  Strategy lets the algorithm vary
 * independently from clients that use it."
 * <p>
 * The Strategy Pattern allows a strategy for doing something to be swapped with another Strategy
 * at runtime.
 * <p>
 * In this example we have a StrategyContext (StrategyApplication) where we set the Strategy. We
 * then call executeStrategy() which in turn calls the drawShape() method on the underlying
 * Strategy.
 * <p>
 * In this way, when we have a SquareStrategy set we end up drawing a Square as a shape and when
 * we have a TriangleStrategy set we draw a Triangle.
 * <p>
 * As Strategy is a {@code @FunctionalInterface} a lambda or method reference can also serve as a
 * strategy; the named classes SquareStrategy and TriangleStrategy remain the primary teaching
 * vehicle here.
 *
 * @author John Dickerson - 22 February 2020
 */
public class StrategyApplication implements StrategyContext {

    private Strategy strategy;

    /**
     * Creates a StrategyApplication with no strategy set yet.
     */
    public StrategyApplication() {

    }


    @Override
    public void setStrategy( Strategy strategy ) {

        this.strategy = strategy;
    }


    @Override
    public String executeStrategy() {

        return strategy.drawShape();
    }


    /**
     * Runs the example: sets a SquareStrategy and executes it to draw a Square, then swaps in a
     * TriangleStrategy and executes it to draw a Triangle.  Finally a lambda is swapped in to
     * show that any implementation of the functional interface can serve as a strategy.
     *
     * @return the shapes drawn by each strategy in turn
     */
    public String runExample() {

        var report = new StringBuilder();

        setStrategy( new SquareStrategy() );
        report.append( "SquareStrategy draws:\n\n" ).append( executeStrategy() ).append( '\n' );

        setStrategy( new TriangleStrategy() );
        report.append( "TriangleStrategy draws:\n\n" ).append( executeStrategy() ).append( '\n' );

        // Because Strategy is a @FunctionalInterface, a lambda can also be swapped in as a
        // strategy at runtime:
        setStrategy( () -> "- - - - -\n" );
        report.append( "Lambda strategy draws:\n\n" ).append( executeStrategy() );

        return report.toString();
    }


    /**
     * Runs the example from the command line and prints the result.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        StrategyApplication application = new StrategyApplication();
        System.out.println( application.runExample() );
    }
}
