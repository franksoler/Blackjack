package swing;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;
/**
 * The Rank class defines the valid card ranks for Blackjack.
 * @author  Tony Sintes STYOOP
 */
public enum Rank {

    TWO   ( 2, "2" ),
    THREE ( 3, "3" ),
    FOUR  ( 4, "4" ),
    FIVE  ( 5, "5" ),
    SIX   ( 6, "6" ),
    SEVEN ( 7, "7" ),
    EIGHT ( 8, "8" ),
    NINE  ( 9, "9" ),
    TEN   ( 10, "10" ),
    JACK  ( 10, "J" ),
    QUEEN ( 10, "Q" ),
    KING  ( 10, "K" ),
    ACE   ( 11, "A" );

    // provide an unmodifiable list to loop over
    public static final List<Rank> RANKS =
            Collections.unmodifiableList( Arrays.asList( values() ) );

    private final int    rank;
    private final String display;

    Rank( int rank, String display ) {
        this.rank = rank;
        this.display = display;
    }

    public int getRank() {
        return rank;
    }

    public String toString() {
        return display;
    }
}