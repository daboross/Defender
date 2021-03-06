package net.daboross.defender;

import java.io.File;
import java.util.concurrent.TimeUnit;
import net.daboross.dxml.DXMLException;
import static net.daboross.dxml.DXMLHelper.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author daboross
 */
public class DefenderData {

    public static void main(String[] args) throws DXMLException {
        File saveFile = new File("save.xml");
        Document doc = newDocument();

        Element root = doc.createElement("defenderdata");
        doc.appendChild(root);

        Element saves = doc.createElement("saves");
        root.appendChild(saves);

        Element save = doc.createElement("save" + TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis()));
        saves.appendChild(save);
        for (int i = 0; i < 20; i++) {
            Attr a = doc.createAttribute("Atribute" + i);
            a.setValue(String.valueOf(i * 20));
            save.setAttributeNode(a);
        }
        writeXML(doc, saveFile);
        Document read = readDocument(saveFile);
        Node firstChild = read.getFirstChild();
        NodeList nl = firstChild.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node current = nl.item(i);
            System.out.println("name:" + current.getNodeName());
            System.out.println("val:" + current.getNodeValue());
            System.out.println("prefix:" + current.getPrefix());
            System.out.println("type:" + current.getNodeType());
            System.out.println("local:" + current.getLocalName());
        }
    }
}
