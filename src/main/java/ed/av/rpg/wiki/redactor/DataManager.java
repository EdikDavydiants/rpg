package ed.av.rpg.wiki.redactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class DataManager {

    public static void rewriteData(NodeRed root) {
        Repository.deleteAllData();
        Repository.saveData(collectTreeToList(root));
    }

    public static NodeRed takeData() {
        return buildListToTree(Repository.readData());
    }

    public static List<WorldEntity> collectTreeToList(NodeRed root) {
        List<WorldEntity> worldEntities = new ArrayList<>();
        List<NodeRed> nodes = new CopyOnWriteArrayList<>();
        nodes.add(root);
        worldEntities.add(new WorldEntity(1, 0, root.getName(), root.getDescription()));

        while (true) {
            boolean isCompleteFlag = true;
            for (NodeRed node: nodes) {
                if(!node.getChildren().isEmpty()) {
                    isCompleteFlag = false;
                    int id = worldEntities.size();
                    int parentId = nodes.indexOf(node) + 1;
                    for (NodeRed child: node.getChildren()) {
                        nodes.add(child);
                        var name = child.getName();
                        var desc = child.getDescription();
                        worldEntities.add(new WorldEntity(++id, parentId, name, desc));
                    }
                }
                node.getChildren().clear();
            }
            if (isCompleteFlag) { break; }
        }

        return worldEntities;
    }

    public static NodeRed buildListToTree(List<WorldEntity> worldEntities) {

        worldEntities = worldEntities.stream()
                .sorted(Comparator.comparingInt(WorldEntity::getId))
                .collect(Collectors.toCollection(ArrayList::new));

        List<NodeRed> nodes = worldEntities.stream()
                .map(entity -> NodeRed.createSingleNode(entity.getName(), entity.getDescription()))
                .collect(Collectors.toCollection(ArrayList::new));

        final int startSize = worldEntities.size();
        for (int k = 0; k < startSize - 1; k++) {
            int lastIdx = nodes.size() - 1;
            var lastNode = nodes.remove(lastIdx);
            var lastEntity = worldEntities.remove(lastIdx);
            for (int i = worldEntities.size() - 1; i >= 0; i--) {
                if (worldEntities.get(i).getId() == lastEntity.getParentId()) {
                    var parent = nodes.get(i);
                    parent.addChild(lastNode);
                    lastNode.setParent(parent);
                    break;
                }
            }
        }
        return nodes.get(0);
    }
}
