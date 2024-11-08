import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dermatologist {
    private String name;
    private Map<String, List<String>> schedule;

    public Dermatologist(String name) {
        this.name = name;
        this.schedule = new HashMap<>();
        setupSchedule();
    }

    private void setupSchedule() {
        // Use ArrayLists so the schedule can be modified (mutable)
        schedule.put("Monday", new ArrayList<>(List.of("10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45")));
        schedule.put("Wednesday", new ArrayList<>(List.of("14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45")));
        schedule.put("Friday", new ArrayList<>(List.of("16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45")));
        schedule.put("Saturday", new ArrayList<>(List.of("09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45")));
    }

    public String getName() {
        return name;
    }

    public Map<String, List<String>> getSchedule() {
        return schedule;
    }

    // Method to check if a time slot is available
    public boolean isTimeSlotAvailable(String day, String time) {
        List<String> availableTimes = schedule.get(day);
        return availableTimes != null && availableTimes.contains(time);
    }

    // Method to book a time slot by removing it from the schedule
    public void bookTimeSlot(String day, String time) {
        List<String> availableTimes = schedule.get(day);
        if (availableTimes != null && availableTimes.contains(time)) {
            availableTimes.remove(time);  // Remove the time slot to mark it as booked
            System.out.println("Time slot " + time + " on " + day + " has been booked.");
        } else {
            System.out.println("Time slot is already booked or invalid.");
        }
    }
}
