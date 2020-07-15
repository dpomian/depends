package depends.format.graphml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id", "edgeDefault", "nodes", "edges" })
public class GGraph {
    private String id;
    private GEdgeDirection edgeDefault;
    private ArrayList<GNode> nodes;
    private ArrayList<GEdge> edges;

    public GGraph() {
    }

    public GGraph(String id, GEdgeDirection edgeDefault, ArrayList<GNode> nodes, ArrayList<GEdge> edges) {
        this.id = id;
        this.edgeDefault = edgeDefault;
        this.nodes = nodes;
        this.edges = edges;
    }

    public String getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public GEdgeDirection getEdgeDefault() {
        return edgeDefault;
    }

    @XmlAttribute(name = "edgedefault")
    public void setEdgeDefault(GEdgeDirection edgeDefault) {
        this.edgeDefault = edgeDefault;
    }

    public ArrayList<GNode> getNodes() {
        return nodes;
    }

    @XmlElement(name = "node")
    public void setNodes(ArrayList<GNode> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<GEdge> getEdges() {
        return edges;
    }

    @XmlElement(name = "edge")
    public void setEdges(ArrayList<GEdge> edges) {
        this.edges = edges;
    }
}