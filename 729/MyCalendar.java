import java.util.Map;
import java.util.TreeMap;
import java.util.NavigableMap;

/**
 * Problem 729. My Calendar I 
 * (Daily Problem for 03AUG2022)
 * 
 * You are implementing a program to use as your calendar. We can add a new 
 * event if adding the event will not cause a double booking.
 *
 * A double booking happens when two events have some non-empty intersection 
 * (i.e., some moment is common to both events.).
 *
 * The event can be represented as a pair of integers start and end that 
 * represents a booking on the half-open interval [start, end), the range of 
 * real numbers x such that start <= x < end.
 *
 * Implement the MyCalendar class:
 * 
 * MyCalendar() Initializes the calendar object.
 * 
 * boolean book(int start, int end) Returns true if the event can be added to 
 * the calendar successfully without causing a double booking. Otherwise, return 
 * false and do not add the event to the calendar.
 */
class MyCalendar
{
    // key = start time for booking
    // value = end time for booking
    private final NavigableMap<Integer, Integer> books = new TreeMap<>();

    public MyCalendar() {}
    
    public boolean book( int start, int end ) 
    {
        // see if the desired booking starts within the range of the booking
        // just prior to the desired booking 
        Map.Entry<Integer, Integer> priorBooking = books.floorEntry( start );
        if ( (priorBooking != null) && (start < priorBooking.getValue()) )
        {
            return false;
        }      
        
        // see if the desired booking starts within the range of the booking 
        // just subsequent to the desired booking
        Map.Entry<Integer, Integer> followingBooking = books.ceilingEntry( start );
        if ( (followingBooking != null) && (followingBooking.getKey() < end) )
        {
            return false;
        }

        // if the routine gets here, then the booking does not interfere with an
        // existing booking
        books.put( start, end );
        return true;
    }
 
    public static void main( String[] argv )
    {
        MyCalendar myCalendar = new MyCalendar();

        assert myCalendar.book( 10, 20 );
        assert ! myCalendar.book( 15, 25 );
        assert myCalendar.book( 20, 30 );
    }
}