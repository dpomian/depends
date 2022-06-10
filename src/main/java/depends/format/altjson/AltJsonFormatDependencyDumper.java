package depends.format.altjson;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import depends.entity.Entity;
import depends.entity.FileEntity;
import depends.format.AbstractFormatDependencyDumper;
import depends.matrix.core.DependencyDetail;
import depends.matrix.core.DependencyMatrix;
import depends.matrix.core.DependencyPair;
import depends.matrix.core.DependencyValue;

public class AltJsonFormatDependencyDumper extends AbstractFormatDependencyDumper {
    public AltJsonFormatDependencyDumper(DependencyMatrix dependencyMatrix, String projectName, String outputDir) {
        super(dependencyMatrix, projectName, outputDir);
    }

    @Override
    public String getFormatName() {
        return "alt-json";
    }

    @Override
    public boolean output() {
        Collection<DependencyPair> pairs = matrix.getDependencyPairs();
        toJson(build(pairs), composeFilename() + ".json");
        return true;
    }

    private JRoot build(Collection<DependencyPair> pairs) {
        Set<Integer> history = new HashSet<Integer>();

        List<JEntity> jEntities = new ArrayList<JEntity>();
        List<JDep> jDeps = new ArrayList<JDep>();

        for (DependencyPair pair : pairs) {
            for (DependencyValue dependency : pair.getDependencies()) {
                for (DependencyDetail detail : dependency.getDetails()) {
                    Entity src = detail.getSrcEntity();
                    Entity dest = detail.getDestEntity();

                    String srcFile = detail.getSrcLocation().getFile();
                    String destFile = detail.getDestLocation().getFile();

                    jEntities.addAll(buildEntities(history, src, srcFile));
                    jEntities.addAll(buildEntities(history, dest, destFile));

                    JDep dep = new JDep();
                    dep.setSrc(src.getId());
                    dep.setDest(dest.getId());
                    dep.setType(dependency.getType());
                    dep.setLine(detail.getSrcLocation().getLineNumber());
                    jDeps.add(dep);
                }
            }
        }

        JRoot jRoot = new JRoot();
        jRoot.setEntities(jEntities);
        jRoot.setDependencies(jDeps);
        return jRoot;
    }

    private List<JEntity> buildEntities(Set<Integer> history, Entity entity, String file) {
        List<JEntity> jEntities = new ArrayList<JEntity>();
        Entity current = entity;

        while (true) {
            if (history.contains(current.getId())) {
                break;
            }

            jEntities.add(buildEntity(current, file));
            history.add(current.getId());

            if (current instanceof FileEntity) {
                break;
            }

            current = current.getParent();
        }

        Collections.reverse(jEntities);
        return jEntities;
    }

    private JEntity buildEntity(Entity entity, String file) {
        JEntity jEntity = new JEntity();
        jEntity.setId(entity.getId());
        jEntity.setLine(entity.getLine());

        String type = entity.getClass().getSimpleName();
        type = type.substring(0, type.length() - 6);
        jEntity.setType(type);

        if (entity instanceof FileEntity) {
            jEntity.setName(file);
        } else {
            jEntity.setName(entity.getRawName().getName());
            jEntity.setParentId(entity.getParent().getId());
        }

        return jEntity;
    }

    private void toJson(JRoot root, String jsonFileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFileName), root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
