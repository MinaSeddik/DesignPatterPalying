package aware.p2c;

import org.apache.commons.math3.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Fd258LayoutConfig {

    private final static String XML_LAYOUT_CONFIG_NAME = "FD-258_rev-05-15-2017.xml";

    private final static String FIELD_TYPE = "type";
    private final static String FIELD_BOX = "box";
    private final static String FIELD_FONT = "font";
    private final static String FIELD_TYPE_TEXT = "text";
    private final static String FIELD_TYPE_IMAGE = "image";


    private Document document;

    public Fd258LayoutConfig() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(classLoader.getResourceAsStream(XML_LAYOUT_CONFIG_NAME));
            document.getDocumentElement().normalize();

        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }

    }

    public TextFieldConfig getTextFieldConfig(String fieldName) {

        Map<String, Node> nodes = getChildNodesForField(fieldName);
        validateNodeType(nodes.get(FIELD_TYPE), FIELD_TYPE_TEXT, fieldName);

        Box box = handleBoxNodeElement(nodes.get(FIELD_BOX));
        Pair<Font, Color> fontProperties = handleFontAndColorNodeElement(nodes.get(FIELD_FONT));

        return new TextFieldConfig(box, fontProperties.getFirst(), fontProperties.getSecond());
    }

    private Map<String, Node> getChildNodesForField(String fieldName) {
        NodeList nodeList = document.getElementsByTagName(fieldName);
        if (nodeList == null || nodeList.getLength() == 0) {
            throw new RuntimeException("Invalid FD-258 Field Name: " + fieldName);
        }

        Node first = nodeList.item(0);
        NodeList properties = first.getChildNodes();

        Map<String, Node> nodes = IntStream.range(0, properties.getLength())
                .mapToObj(properties::item)
                .filter(n -> n.getNodeType() == Node.ELEMENT_NODE)
                .collect(Collectors.toMap(Node::getNodeName, Function.identity()));
        return nodes;
    }

    private Pair<Font, Color> handleFontAndColorNodeElement(Node fontNode) {
        NodeList childs = fontNode.getChildNodes();
        String[] properties = new String[4];


        IntStream.range(0, childs.getLength())
                .mapToObj(childs::item)
                .filter(n -> n.getNodeType() == Node.ELEMENT_NODE)
                .forEach(n -> {
                    switch (n.getNodeName().trim()) {
                        case "familyName":
                            properties[0] = n.getChildNodes().item(0).getNodeValue().trim();
                            break;
                        case "style":
                            properties[1] = n.getChildNodes().item(0).getNodeValue().trim();
                            break;
                        case "size":
                            properties[2] = n.getChildNodes().item(0).getNodeValue().trim();
                            break;
                        case "color":
                            properties[3] = n.getChildNodes().item(0).getNodeValue().trim();
                            break;
                    }
                });

        int fontStyle = Font.PLAIN;
        switch (properties[1].trim()) {
            case "plain":
                fontStyle = Font.PLAIN;
                break;
            case "bold":
                fontStyle = Font.BOLD;
                break;
            case "italic":
                fontStyle = Font.ITALIC;
                break;
        }

        Font font = new Font(properties[0], fontStyle, new Integer(properties[2]));

        Color color = Color.BLACK;
        try {
            Field field = Class.forName("java.awt.Color").getField(properties[3]);
            color = (Color) field.get(null);
        } catch (Exception e) {
            color = Color.BLACK; // Not defined
        }

        return new Pair<>(font, color);
    }

    private Box handleBoxNodeElement(Node boxNode) {

        NodeList childs = boxNode.getChildNodes();
        String[] coordinates = new String[4];


        IntStream.range(0, childs.getLength())
                .mapToObj(childs::item)
                .filter(n -> n.getNodeType() == Node.ELEMENT_NODE)
                .forEach(n -> {
                    switch (n.getNodeName().trim()) {
                        case "topLeftX":
                            coordinates[0] = n.getChildNodes().item(0).getNodeValue().trim();
                            break;
                        case "topLeftY":
                            coordinates[1] = n.getChildNodes().item(0).getNodeValue().trim();
                            break;
                        case "width":
                            coordinates[2] = n.getChildNodes().item(0).getNodeValue().trim();
                            break;
                        case "height":
                            coordinates[3] = n.getChildNodes().item(0).getNodeValue().trim();
                            break;
                    }
                });


        return new Box(new Integer(coordinates[0]), new Integer(coordinates[1]), new Integer(coordinates[2]), new Integer(coordinates[3]));
    }


    public ImageFieldConfig getImageFieldConfig(FingerPrintPosition fingerPrintPosition) {
        return getImageFieldConfig(fingerPrintPosition.name());
    }

    public ImageFieldConfig getImageFieldConfig(String fieldName) {

        Map<String, Node> nodes = getChildNodesForField(fieldName);
        validateNodeType(nodes.get(FIELD_TYPE), FIELD_TYPE_IMAGE, fieldName);

        Box box = handleBoxNodeElement(nodes.get(FIELD_BOX));

        return new ImageFieldConfig(box);
    }

    private void validateNodeType(Node type, String expectedType, String fieldName) {
        if (!type.getFirstChild().getNodeValue().trim().equals(expectedType)) {
            throw new RuntimeException("Invalid Field type: " + type.getFirstChild().getNodeValue() + " for field " + fieldName);
        }
    }

}
