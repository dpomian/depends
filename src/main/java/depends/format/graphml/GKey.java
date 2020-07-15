package depends.format.graphml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id", "target", "name", "type" })
public class GKey {
    private String id;
    private GKeyTarget target;
    private String name;
    private GKeyType type;

    public GKey() {
    }

    public GKey(String id, GKeyTarget target, String name, GKeyType type) {
        this.id = id;
        this.target = target;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public GKeyTarget getTarget() {
        return target;
    }

    @XmlAttribute(name = "for")
    public void setTarget(GKeyTarget target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "attr.name")
    public void setName(String name) {
        this.name = name;
    }

    public GKeyType getType() {
        return type;
    }

    @XmlAttribute(name = "attr.type")
    public void setType(GKeyType type) {
        this.type = type;
    }
}