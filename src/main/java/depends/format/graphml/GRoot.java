package depends.format.graphml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "graphml")
@XmlType(propOrder = { "keys", "graphs" })
public class GRoot {
    private ArrayList<GKey> keys;
    private ArrayList<GGraph> graphs;

    public GRoot() {
    }

    public GRoot(ArrayList<GKey> keys, ArrayList<GGraph> graphs) {
        this.keys = keys;
        this.graphs = graphs;
    }

    public ArrayList<GKey> getKeys() {
        return keys;
    }

    @XmlElement(name = "key")
    public void setKeys(ArrayList<GKey> keys) {
        this.keys = keys;
    }

    public ArrayList<GGraph> getGraphs() {
        return graphs;
    }

    @XmlElement(name = "graph")
    public void setGraphs(ArrayList<GGraph> graphs) {
        this.graphs = graphs;
    }
}