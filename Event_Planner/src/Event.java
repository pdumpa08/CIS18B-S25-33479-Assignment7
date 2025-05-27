import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Event {
    private final StringProperty eventTitle;
    private final StringProperty eventDesc;
    private final ObjectProperty<LocalDate> eventDate;

    public Event(String eventTitle, String eventDesc, LocalDate eventDate) {
        this.eventTitle = new SimpleStringProperty(eventTitle);
        this.eventDesc = new SimpleStringProperty(eventDesc);
        this.eventDate = new SimpleObjectProperty<>(eventDate);
    }

    // getters and setters
    public String getEventTitle() {
        return eventTitle.get();
    }
    public void setEventTitle(String eventTitle) {
        this.eventTitle.set(eventTitle);
    }

    public String getEventDesc() {
        return eventDesc.get();
    }
    public void setEventDesc(String eventDesc) {
        this.eventDesc.set(eventDesc);
    }

    public LocalDate getEventDate() {
        return eventDate.get();
    }
    public void setEventDate(LocalDate eventDate) {
        this.eventDate.set(eventDate);
    }
    
}
