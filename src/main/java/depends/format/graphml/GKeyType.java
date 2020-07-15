package depends.format.graphml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum GKeyType {
    @XmlEnumValue("boolean")
    BOOLEAN,

    @XmlEnumValue("int")
    INT,

    @XmlEnumValue("long")
    LONG,

    @XmlEnumValue("float")
    FLOAT,

    @XmlEnumValue("double")
    DOUBLE,
    
    @XmlEnumValue("string")
    STRING
}