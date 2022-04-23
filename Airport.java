/**
 * The program shows us the list of departures in an aiport
 *
 * @author Kiril shamis
 * @2022b
 */


public class Airport
{
    private Flight [] _flightsSchedule; // Creates an array from the class of flight
    private int _noOfFlights; // Number of flights per day 
    private String _city; // The city of the airport
    private final int _MAX_FLIGHTS = 200; // Final amount of flights per day
    private final int _MIN_FLIGHTS = 0; // Minimun amount of flights per day

    /**
     * Constructor
     * @param _city
     * @param _flightsSchedule
     * @param _noOfFlights
     */
    public Airport(String _city, Flight[] _flightsSchedule, int _noOfFlights) {
        this._city = _city; 
        this._noOfFlights = _MIN_FLIGHTS; // Initializing the number of flights to 0
        this._flightsSchedule = new Flight[_MAX_FLIGHTS]; // Initializing the array to be 200 

    } // End of constructor

    /**
     * Checking if it is available to add a flight to the schedule
     * returns true if availble, false if not
     * @param f
     */
    public boolean addFlight(Flight f) {
        if (_noOfFlights == _MAX_FLIGHTS) {
            return false;

        } // If number of flights in the array is at max, returns false
        _flightsSchedule[_noOfFlights++] = new Flight(f);
        return true;

    } // if it is available to add a flight, it adds it to the array

    public boolean removeFlight(Flight f){
        for (int i=0; i<_noOfFlights; i++) {
            if (_flightsSchedule[i].equals(f)) {
                for(int j=i; j<_noOfFlights; j++)  { 
                    _flightsSchedule[j] = _flightsSchedule[j+1];
                } // Moves the array one step back to prevent holes
            _flightsSchedule[_noOfFlights-1] = null;
            _noOfFlights--;
            return true;
            } // End of if statement
        } // End of for loop
        return false;
    }
    
    public String toString () {
        String allFlights = "The flights from " + _city + "today: ";
        if (_noOfFlights == _MIN_FLIGHTS) {
            return null;
        }

        for (int i = _MIN_FLIGHTS; i <= _noOfFlights; i++) {
            allFlights += _flightsSchedule[i].toString() + "\n";
        }
        return allFlights;
    } // End of toString method

    public Time1 firstFlightFromOrigin (String place) {
        this._city = place;
        
        
        if (_noOfFlights == _MIN_FLIGHTS && (place != _city)) {
            return null;
        }
        Flight first = _flightsSchedule[0];
        
        for (int i=1; i<_MAX_FLIGHTS; i++) {
            if (_flightsSchedule[i].getDeparture().before(first.getDeparture())) {
                first = _flightsSchedule[i];
                
            }
        
        }
        return new Time1(first.getDeparture());
        
    } // End of firstFlightFromOrigin method
    
    public int howManyFullFlights() {
        
        int count = 0;
        for (int i=0; i<_MAX_FLIGHTS; i++) {
            if (_flightsSchedule[i].getIsFull() == true) {
                count ++;
            }
            
        }
        return count; // Returns the amount of full flights 
    } // End of howManyFullFlights
    
    public int howManyFlightsBetween (String city) {
         String origin = this._city;
         String dest = city;
         int count = 0;
         while (this._city != city) {
            for (int i=1; i<_noOfFlights; i++) {
                if (_flightsSchedule[i].getDestination().equals(city)) {
                    count++;
                } 
            }
            }
         return count; // Returns the amount of flights between cities
         
    } // End of howManyFlightsBetween
    
    public String mostPopularDestination() {
        
    }
    
    public Flight mostExpensiveTicket() {
        
    }
    
    public Flight longestFlight() {
        
    }
}
    
/**public String toString() {

}

public int howManyFullFlights()  {

}**/

