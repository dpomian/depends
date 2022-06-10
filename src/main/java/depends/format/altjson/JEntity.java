package depends.format.altjson;

public class JEntity {
    private int id;
    private Integer parentId;
    private String name;
    private String type;
    private int line;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLine() {
        return line;
    }

    public void setLine(Integer line) {
        if (line == null) {
            this.line = 0;
            return;
        }

        this.line = line;
    }
}
