package depends.format.graphml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum GKeyTarget {
    @XmlEnumValue("node")
    NODE,

    @XmlEnumValue("edge")
    EDGE
}