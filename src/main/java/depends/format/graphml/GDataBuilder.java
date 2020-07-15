package depends.format.graphml;

import java.util.ArrayList;
import java.util.Collection;

import depends.format.FileAttributes;
import depends.matrix.core.DependencyMatrix;
import depends.matrix.core.DependencyPair;
import depends.matrix.core.DependencyValue;

public class GDataBuilder {
    public GRoot build(DependencyMatrix matrix, FileAttributes attribute) {
        ArrayList<GNode> nodes = buildNodes(matrix.getNodes());
        ArrayList<GEdge> edges = buildEdges(matrix.getDependencyPairs());

        ArrayList<GKey> keys = new ArrayList<GKey>();
        keys.add(new GKey("d0", GKeyTarget.NODE, "Name", GKeyType.STRING));
        keys.add(new GKey("d1", GKeyTarget.NODE, "SourceUnit", GKeyType.STRING));
        keys.add(new GKey("d2", GKeyTarget.EDGE, "Relationship", GKeyType.STRING));
        keys.add(new GKey("d3", GKeyTarget.EDGE, "Weight", GKeyType.DOUBLE));

        ArrayList<GGraph> graphs = new ArrayList<GGraph>();
        graphs.add(new GGraph("g0", GEdgeDirection.DIRECTED, nodes, edges));

        return new GRoot(keys, graphs);
    }

    private ArrayList<GNode> buildNodes(ArrayList<String> names) {
        ArrayList<GNode> nodes = new ArrayList<GNode>();

        for (int i = 0; i < names.size(); i++) {
            GNode node = new GNode();
            node.setId(getNodeId(i));

            ArrayList<GData> data = new ArrayList<GData>();
            data.add(new GData("d0", names.get(i)));
            data.add(new GData("d1", "File"));

            node.setData(data);
            nodes.add(node);
        }

        return nodes;
    }

    private ArrayList<GEdge> buildEdges(Collection<DependencyPair> pairs) {
        ArrayList<GEdge> edges = new ArrayList<GEdge>();

        for (DependencyPair pair : pairs) {
            String source = getNodeId(pair.getFrom());
            String target = getNodeId(pair.getTo());

            for (DependencyValue dependency : pair.getDependencies()) {
                ArrayList<GData> data = new ArrayList<GData>();
                data.add(new GData("d2", dependency.getType()));
                data.add(new GData("d3", "" + dependency.getWeight()));

                edges.add(new GEdge(source, target, data));
            }
        }

        return edges;
    }

    private String getNodeId(int index) {
        return "n" + index;
    }
}