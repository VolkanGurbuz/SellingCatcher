package Model;

import java.util.Date;

public class DateModel {

    private String dateId;
    private Date date;

    public DateModel(String dateId, Date date) {
        this.dateId = dateId;
        this.date = date;
    }

    public String getDateId() {
        return dateId;
    }

    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

    @Override
    public String toString() {
        return "DateModel{" +
                "dateId='" + dateId + '\'' +
                ", date=" + date +
                '}';
    }
}
