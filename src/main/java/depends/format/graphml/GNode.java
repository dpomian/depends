package depends.format.graphml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id", "data" })
public class GNode {
    private String id;
    private ArrayList<GData> data;

    public GNode() {
    }

    public GNode(String id, ArrayList<GData> data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<GData> getData() {
        return data;
    }

    @XmlElement(name = "data")
    public void setData(ArrayList<GData> data) {
        this.data = data;
    }
}