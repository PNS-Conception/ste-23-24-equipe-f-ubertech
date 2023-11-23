package sophiatech;

import java.time.LocalTime;

public interface RestaurantSubject {
    public boolean checkAvailableSlot(LocalTime borne_inf, LocalTime borne_sup);
}
