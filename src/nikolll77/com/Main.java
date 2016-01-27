package nikolll77.com;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends DefaultHandler {

    String s;
    String train_info;
    String curr_elem;
    Date tmpTime,timeFrom,timeTo;
    SimpleDateFormat timeParser;

    @Override
    public void startDocument() throws SAXException {
        System.out.print("=========");
        timeParser = new SimpleDateFormat("HH:mm");
        try {
            timeFrom = timeParser.parse("15:00");
            timeTo = timeParser.parse("19:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.print("=========");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        curr_elem=localName;
        if (curr_elem.equals("train")) train_info="";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       s=new String(ch,start,length);
       train_info+=s;

       if (curr_elem.equals("departure")) {
            try {
                //Вопрос:Почему здесь выполняется код больше чем 1 раз? (выкрутился проверкой длинны строки)
                if (s.length()==5) tmpTime = timeParser.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("train")) {
            if (tmpTime.after(timeFrom)&&(tmpTime.before(timeTo)))
            System.out.print(train_info);
        }

    }

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException, JAXBException {

        //--------------Добавляем новые паровозы с помощью JAXB-------------------------

        File file=new File("e:\\trains.xml");
        JAXBContext jaxbContext= JAXBContext.newInstance(Trains.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Trains trains = (Trains) unmarshaller.unmarshal(file);

        trains.add(new Train(3,"NewYork","London","15.02.2016","16:20"));
        trains.add(new Train(4,"Paris","Praha","19.02.2016","20:40"));

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(trains,file);

        //------------Тянем поезда с помощью SAX ----------------------------
        // Вопрос: Можно ли добавлять в XML данные с помощью SAX?
        String trainXmlFile="file:///e:/trains.xml";

        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();

        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(new Main());
        xmlReader.parse(trainXmlFile);

    }
}
