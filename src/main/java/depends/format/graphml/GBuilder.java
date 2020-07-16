package depends.format.graphml;

import depends.format.FileAttributes;
import depends.matrix.core.DependencyMatrix;

public abstract class GBuilder {
    public abstract GDepObject build(DependencyMatrix matrix, FileAttributes attribute);
}