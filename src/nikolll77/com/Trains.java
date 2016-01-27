package nikolll77.com;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "trains")
public class Trains {

    @XmlElement(name = "train")
    private ArrayList<Train> trains = new ArrayList<>();

    public void add(Train train) {
        trains.add(train);
    }

    @Override
    public String toString() {
        return "<Trains>" +"\n"+
                trains +
                "\n</Trains>";
    }



}
