import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dermatologist {
    private String name;
    private Map<String, List<String>> schedule;
    private Map<String, Map<String, Boolean>> bookings; // A map to track booked time slots for each day

    public Dermatologist(String name) {
        this.name = name;
        this.schedule = new HashMap<>();
        this.bookings = new HashMap<>();
        setupSchedule();
        setupBookings();
    }

    // Setup the schedule with available times for each day
    private void setupSchedule() {
        schedule.put("Monday", List.of("10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45"));
        schedule.put("Wednesday", List.of("14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45"));
        schedule.put("Friday", List.of("16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45"));
        schedule.put("Saturday", List.of("09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45"));
    }

    // Initialize the booking map to track availability of time slots
    private void setupBookings() {
        for (String day : schedule.keySet()) {
            Map<String, Boolean> dayBookings = new HashMap<>();
            for (String time : schedule.get(day)) {
                dayBookings.put(time, false); // false means the time slot is available
            }
            bookings.put(day, dayBookings);
        }
    }

    public String getName() {
        return name;
    }

    // Returns the dermatologist's schedule (days and available times)
    public Map<String, List<String>> getSchedule() {
        return schedule;
    }

    // Checks if the specified time slot is available for the given day
    public boolean isTimeSlotAvailable(String day, String time) {
        // Check if the day exists in the schedule and if the time slot is available
        if (bookings.containsKey(day) && bookings.get(day).containsKey(time)) {
            return !bookings.get(day).get(time); // false means not booked, so available
        }
        return false; // If the day or time is not in the schedule, it's considered unavailable
    }

    
    public boolean bookTimeSlot(String day, String time) {
       
        if (isTimeSlotAvailable(day, time)) {
            bookings.get(day).put(time, true);
            return true;
        }
        return false; 
    }

   
    public void cancelBooking(String day, String time) {
        if (bookings.containsKey(day) && bookings.get(day).containsKey(time)) {
            bookings.get(day).put(time, false); // Set the time slot to available
        }
    }
}
