package ed.av.rpg.wiki.redactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NodeRed {

    private String name;
    private String description;
    private NodeRed parent;
    private final List<NodeRed> children = new ArrayList<>();

    public static NodeRed createRoot() {
        return new NodeRed("World", "All existing things in the universe including non-material.", null);
    }

    public static NodeRed createNode(String name, String description, NodeRed parent) {
        return new NodeRed(name, description, parent);
    }

    public static NodeRed createSingleNode(String name, String description) {
        return new NodeRed(name, description, null);
    }

    private NodeRed(String name, String description, NodeRed parent) {
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

    public void addChild(String name, String description) {
        children.add(new NodeRed(name, description, this));
    }

    public void addChild(NodeRed child) {
        children.add(child);
        child.setParent(this);
    }

    public List<NodeRed> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return name;
    }

    public void deleteChild(NodeRed child) {
        children.remove(child);
    }

    public NodeRed getParent() {
        return Objects.requireNonNullElse(parent, this);
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setParent(NodeRed parent) {
        this.parent = parent;
    }
}
