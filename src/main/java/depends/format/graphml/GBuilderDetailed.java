package depends.format.graphml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import depends.format.FileAttributes;
import depends.matrix.core.DependencyDetail;
import depends.matrix.core.DependencyMatrix;
import depends.matrix.core.DependencyPair;
import depends.matrix.core.DependencyValue;

public class GBuilderDetailed extends GBuilder {
    private ArrayList<GNode> nodes;
    private ArrayList<GEdge> edges;
    private Map<String, String> nodeIds;
    private int currNodeId;

    public GBuilderDetailed() {
        nodes = new ArrayList<GNode>();
        edges = new ArrayList<GEdge>();
        nodeIds = new HashMap<String, String>();
        currNodeId = 0;
    }

    public GDepObject build(DependencyMatrix matrix, FileAttributes attribute) {
        buildNodes(matrix.getDependencyPairs());
        buildEdges(matrix.getDependencyPairs());

        ArrayList<GKey> keys = new ArrayList<GKey>();
        keys.add(new GKey("d0", GKeyTarget.NODE, "Name", GKeyType.STRING));
        keys.add(new GKey("d1", GKeyTarget.NODE, "SourceUnit", GKeyType.STRING));
        keys.add(new GKey("d2", GKeyTarget.EDGE, "Relationship", GKeyType.STRING));
        keys.add(new GKey("d3", GKeyTarget.EDGE, "Weight", GKeyType.DOUBLE));

        ArrayList<GGraph> graphs = new ArrayList<GGraph>();
        graphs.add(new GGraph("g0", GEdgeDirection.DIRECTED, nodes, edges));

        return new GDepObject(keys, graphs);
    }

    private void buildNodes(Collection<DependencyPair> pairs) {
        for (DependencyPair pair : pairs) {
            for (DependencyValue dependency : pair.getDependencies()) {
                for (DependencyDetail detail : dependency.getDetails()) {
                    createNode(detail.getSrc(), detail.getSrcType());
                    createNode(detail.getDest(), detail.getDestType());
                }
            }
        }
    }

    private void buildEdges(Collection<DependencyPair> pairs) {
        for (DependencyPair pair : pairs) {
            for (DependencyValue dependency : pair.getDependencies()) {
                for (DependencyDetail detail : dependency.getDetails()) {
                    createEdge(detail.getSrc(), detail.getDest(), dependency.getType());
                }
            }
        }
    }

    private void createNode(String name, String type) {
        if (!nodeIds.containsKey(name)) {
            nodeIds.put(name, getNextNodeId());
        }

        ArrayList<GData> data = new ArrayList<GData>();
        data.add(new GData("d0", name));
        data.add(new GData("d1", type));
        nodes.add(new GNode(nodeIds.get(name), data));
    }

    private void createEdge(String srcName, String destName, String relationship) {
        ArrayList<GData> data = new ArrayList<GData>();
        data.add(new GData("d2", relationship));
        data.add(new GData("d3", "1"));
        edges.add(new GEdge(nodeIds.get(srcName), nodeIds.get(destName), data));
    }

    private String getNextNodeId() {
        return "n" + currNodeId++;
    }
}