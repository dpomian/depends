package depends.format.graphml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "source", "target", "data" })
public class GEdge {
    private String source;
    private String target;
    private ArrayList<GData> data;

    public GEdge() {
    }

    public GEdge(String source, String target, ArrayList<GData> data) {
        this.source = source;
        this.target = target;
        this.data = data;
    }

    public String getSource() {
        return source;
    }

    @XmlAttribute(name = "source")
    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    @XmlAttribute(name = "target")
    public void setTarget(String target) {
        this.target = target;
    }

    public ArrayList<GData> getData() {
        return data;
    }

    @XmlElement(name = "data")
    public void setData(ArrayList<GData> data) {
        this.data = data;
    }
}