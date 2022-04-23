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
    private final int _DEFAULT_VALUE = 0; // Default value of 0
    
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
    
    /**
     * Checking if it is available to remove a flight from the array without leaving "holes"
     * Returns True if available, else, false if not
     * @param f
     */
    public boolean removeFlight(Flight f){
        for (int i=_DEFAULT_VALUE; i<_noOfFlights; i++) {
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
    
    /**
     *Creating a String format of the flights availble
     *Returns the a string of the available flights today
     */
    public String toString () {
        
        if (_noOfFlights == _DEFAULT_VALUE) {
            return null;
        }
        String output = "The Flight from " + _city + " today are";
        for (int i=_DEFAULT_VALUE; i<_noOfFlights; i++) {
            output += "\n" + _flightsSchedule[i].toString();
        }
        return output;
    } // End of toString method
    
    /**
     * Retrives a certain city and checks which is the first of the day flight to depart from that same city
     * Returns the time of the departure
     * @param place
     */
    public Time1 firstFlightFromOrigin (String place) {
 
        Flight first = _flightsSchedule[_DEFAULT_VALUE];

        for (int i=1; i<_noOfFlights; i++) {
            if (_flightsSchedule[i].getDeparture().before(first.getDeparture())) {
                first = _flightsSchedule[i];

            }

        }
        return new Time1(first.getDeparture());

    } // End of firstFlightFromOrigin method
    
    /**
     * Checks for how many full flights there are on that day
     * Returns an int of the amount of flights
     */
    public int howManyFullFlights() {

        int count = _DEFAULT_VALUE;
        
        for (int i=_DEFAULT_VALUE; i<_noOfFlights; i++){
            if (_flightsSchedule[i].getIsFull() == true) {
                count++;
            }
        }
        return count; // Returns the amount of full flights 
    } // End of howManyFullFlights
    
    /**
     * Retrieves the amount of flights between two cities, for example Tel aviv -> Madrid for that day
     * Returns an int of the amount of flights
     * @param city
     */
    public int howManyFlightsBetween (String city) {
        int count = _DEFAULT_VALUE;
        for (int i=1; i<_noOfFlights; i++) {
            if(_flightsSchedule[i].getDestination().equals(city)) {
                count++;
            } 
        }       
        return count; // Returns the amount of flights between cities
    } // End of howManyFlightsBetween
    
    /**
     * Retrieves the most popular city in the schedule
     * Returns the city which is the most popular
     */
    public String mostPopularDestination() {
        String count = "";
        String popDest = _flightsSchedule[0].getDestination();

        for (int i=0; i<_noOfFlights; i++) {
            if (_noOfFlights == _DEFAULT_VALUE) {
                return null;
            }
        }

        for (int i=1; i<_noOfFlights; i++) {
            if (popDest.equals(_flightsSchedule[i].getDestination())){
                count = popDest;
            }

        }
        return popDest;
    } // End of mostPopularDestination
    
    /**
     * Retrieves the most expensive ticket from all the flights for that day
     * Returns the most expensive flight
     */
    public Flight mostExpensiveTicket() {
        Flight price = _flightsSchedule[_DEFAULT_VALUE];
        if (_noOfFlights == _MIN_FLIGHTS) {
            return null;
        }

        for (int i=1; i<_noOfFlights; i++) {
            if((_flightsSchedule[i].getPrice()) > (price.getPrice())) {
                price = _flightsSchedule[i];
            } // End of if statement
        } // End of the for loop
        return new Flight(price);
    } // End of mostExpensiveTicket method
    
    /**
     * Retrieves the longest flight of the day out of all the flights
     * Returns the longest flight
     */
    public Flight longestFlight() {
        if (_noOfFlights == _DEFAULT_VALUE){
            return null;
        }
        
        Flight longestTime = _flightsSchedule[_DEFAULT_VALUE];
        for (int i=1; i<_noOfFlights; i++) {
            if (longestTime.getFlightDuration() < _flightsSchedule[i].getFlightDuration()){
                longestTime = _flightsSchedule[i];
            } // If loop to check if the next flight is the longest
        }
        return new Flight(longestTime);
    } // End of longestFlight method
} // End of Airport class



