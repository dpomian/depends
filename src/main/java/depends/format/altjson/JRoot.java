package depends.format.altjson;

import java.util.List;

public class JRoot {
    private List<JEntity> entities;
    private List<JDep> dependencies;

    public List<JEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<JEntity> entities) {
        this.entities = entities;
    }

    public List<JDep> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<JDep> dependencies) {
        this.dependencies = dependencies;
    }
}
