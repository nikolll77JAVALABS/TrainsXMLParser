package nikolll77.com;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "train")
public class Train {

    int id;
    String from;
    String to;
    String date;
    String departure;
    public Train(){}

    public Train(int id,String from,String to,String date,String departure)
    {
        this.id=id;
        this.from=from;
        this.to=to;
        this.date=date;
        this.departure=departure;

    }
    @Override
    public String toString(){
        return "["+id+", "+from+", "+to+", "+date+", "+departure+"]";
    }

    public int getId() {
        return id;
    }

    @XmlAttribute(name="id")
    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }
    @XmlElement(name = "from")
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }
    @XmlElement
    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }
    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    public String getDeparture() {
        return departure;
    }
    @XmlElement
    public void setDeparture(String departure) {
        this.departure = departure;
    }
}
