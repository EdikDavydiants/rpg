package ed.av.rpg.wiki;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private static final String ROOT_PIC_DIR = "categories/root_pic.png";
    private final List<Node> children = new ArrayList<>();
    private CategoryButton categoryButton;
    private InfoArea infoArea;
    private final String categoryButtonPicDir;

    public Node(String categoryButtonPicDir) {
        this.categoryButtonPicDir = categoryButtonPicDir;
    }

    public Node() {
        this.categoryButtonPicDir = null;
    }


    public void addChild(Node node) {
        children.add(node);
    }


    public void createAndSetCategoryButton() {
        if(categoryButtonPicDir == null) {
            throw new RuntimeException("This node is root!");
        }
        categoryButton = new CategoryButton(new Image(categoryButtonPicDir));
    }

    public void createAndSetInfoArea() {
        if (categoryButtonPicDir != null) {
            categoryButton = new CategoryButton(new Image(categoryButtonPicDir));
        } else {
            categoryButton = new CategoryButton(new Image(ROOT_PIC_DIR));
        }

    }

}
