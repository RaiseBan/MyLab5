/**
 * The ParserXml class is responsible for parsing worker data from an XML file.
 */
package support;

import data.*;
import exceptions.WrongArgumentsException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ParserXml {
    String file;

    /**
     * Constructs a new ParserXml object with the specified XML file path.
     *
     * @param file the file path of the XML file to be parsed
     */
    public ParserXml(String file) {
        this.file = file;
    }

    /**
     * Parses the worker data from the XML file and returns a list of Worker objects.
     *
     * @return a list of Worker objects parsed from the XML file
     */
    public List<Worker> parseWorkersFromXML() {
        File xmlFile = new File(this.file);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("worker");
            List<Worker> workerList = new ArrayList<Worker>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                workerList.add(getWorker(nodeList.item(i)));
            }
            return workerList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Returns a Worker object parsed from the specified XML node.
     *
     * @param node the XML node to parse the Worker object from
     * @return a Worker object parsed from the specified XML node
     */
    private Worker getWorker(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String name = getTagValue("name", element);
            Coordinates coordinates = getCoordinates(element);
            Double salary = Double.parseDouble(getTagValue("salary", element));
            Position position = Position.valueOf(getTagValue("position", element).toUpperCase());
            Status status = Status.valueOf(getTagValue("status", element).toUpperCase());
            Person person = getPerson(element);
            return new Worker(name, coordinates, salary, position, status, person);
        }
        return null;


    }

    /**
     * Returns a Coordinates object parsed from the specified XML element.
     *
     * @param element the XML element to parse the Coordinates object from
     * @return a Coordinates object parsed from the specified XML element
     */

    private Coordinates getCoordinates(Element element) {
        Element coordinatesElement = (Element) element.getElementsByTagName("coordinates").item(0);
        int x = Integer.parseInt(getTagValue("x", coordinatesElement));
        int y = Integer.parseInt(getTagValue("y", coordinatesElement));
        return new Coordinates(x, y);
    }

    /**
     * Returns a Person object parsed from the specified XML element.
     *
     * @param element the XML element to parse the Person object from
     * @return a Person object parsed from the specified XML element
     */

    private Person getPerson(Element element) {

        Element personElement = (Element) element.getElementsByTagName("person").item(0);
        LocalDateTime birthday = getLocalDateTime(getTagValue("birthday", personElement));
        long height = Long.parseLong(getTagValue("height", personElement));
        String passportID = getTagValue("passportID", personElement);
        Location location = getLocation(personElement);
        return new Person(birthday, height, passportID, location);
    }

    /**

     Parses a location from an XML element.
     @param element the XML element containing the location
     @return a Location object parsed from the XML element
     */
    private Location getLocation(Element element) {
        Element locationElement = (Element) element.getElementsByTagName("location").item(0);
        String name = getTagValue("name", locationElement);
        float x = Float.parseFloat(getTagValue("x", locationElement));
        long y = Long.parseLong(getTagValue("y", locationElement));
        int z = Integer.parseInt(getTagValue("z", locationElement));
        return new Location(x, y, z, name);
    }

    /**
     * Retrieves the value of an XML element tag.
     *
     * @param tag the name of the XML tag
     * @param element the XML element containing the tag
     * @return the value of the tag
     */
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

    /**
     * Parses a date string into a LocalDateTime object.
     *
     * @param dateStr the date string to be parsed
     * @return a LocalDateTime object representing the parsed date and time, or null if the date string is in an incorrect format
     */

    public LocalDateTime getLocalDateTime(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("ru", "Ru"));
            LocalDateTime bd = LocalDate.parse(dateStr, formatter).atStartOfDay();
            if (bd.isAfter(LocalDate.now().atStartOfDay())) throw new WrongArgumentsException();
            return bd;
        }catch (WrongArgumentsException e){
            Console.err(e.getMessage());
            return null;
        } catch (DateTimeParseException e) {
            Console.err("Неверный формат даты: " + dateStr);
            return null;
        }
    }
}
