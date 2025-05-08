package ed.av.rpg.wiki.redactor;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Redactor extends HBox {

    private NodeRed root;
    private NodeRed currentNode;
    private final ListView<NodeRed> childListView = new ListView<>();
    private final TextArea nameArea = new TextArea();
    private final TextArea descriptionArea = new TextArea();

    public Redactor() {

        getChildren().add(childListView);
        root = NodeRed.createRoot();
        currentNode = root;

        addClickListener();
        addChooseListener();

        var deleteButton = new Button("Delete");
        deleteButton.setOnMouseClicked(event -> deleteSelectedNode());
        getChildren().add(deleteButton);

        var addButton = new Button("Add New Element");
        addButton.setOnMouseClicked(event -> addChild("...", ""));
        getChildren().add(addButton);

        var backButton = new Button("<--");
        backButton.setOnMouseClicked(event -> back());
        getChildren().add(backButton);

        VBox childListVBox = new VBox(childListView, deleteButton, addButton, backButton);


        nameArea.setPrefHeight(30);
        nameArea.setMaxWidth(300);
        nameArea.setFont(new Font(20));
        descriptionArea.setFont(new Font(15));
        descriptionArea.setMaxWidth(400);

        var saveButton = new Button("Save");
        saveButton.setOnMouseClicked(event -> saveEntity());

        setNameAndDescriptionArea(currentNode);

        VBox descriptionVBox = new VBox(nameArea, descriptionArea, saveButton);


        Button downloadButton = new Button("<<-- DOWNLOAD");
        downloadButton.setOnMouseClicked(event -> downloadData());

        Button uploadButton = new Button("SAVE -->>");
        uploadButton.setOnMouseClicked(event -> uploadData());

        VBox dataButtons = new VBox(downloadButton, uploadButton);

        getChildren().addAll(childListVBox, descriptionVBox, dataButtons);
    }

    private void saveEntity() {
        if (getSelectedChild() != null) {
            var newName = nameArea.getText();
            var newDescription = descriptionArea.getText();
            var selectedChild = getSelectedChild();
            selectedChild.setName(newName);
            selectedChild.setDescription(newDescription);
            setChildViewList();
        }
    }

    private void back() {
        currentNode = currentNode.getParent();
        setChildViewList();
        setNameAndDescriptionArea(currentNode);
    }

    private void setChildViewList() {
        childListView.getItems().clear();
        currentNode.getChildren()
                .forEach(node -> childListView.getItems().add(node));
    }

    private void downloadData() {
        root = DataManager.takeData();
        currentNode = root;
        setChildViewList();
    }

    private void uploadData() {
        DataManager.rewriteData(root);
    }

    private NodeRed getSelectedChild() {
        return childListView.getSelectionModel().getSelectedItem();
    }

    public void addChild(String name, String description) {
        currentNode.addChild(name, description);
        setChildViewList();
    }

    private void deleteSelectedNode() {
        currentNode.deleteChild(getSelectedChild());
        setChildViewList();
    }

    private void addClickListener() {
        childListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                currentNode = getSelectedChild();
                setChildViewList();
            }
        });
    }

    private void addChooseListener() {
        childListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setNameAndDescriptionArea(newValue);
            }
        });
    }

    private void setNameAndDescriptionArea(NodeRed node) {
        nameArea.setText(node.getName());
        descriptionArea.setText(node.getDescription());
    }
}
