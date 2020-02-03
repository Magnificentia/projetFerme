package app.modules;

import javafx.scene.Node;

import java.util.List;
import java.util.Map;

public interface IController {
    public Map<Node, List<userType>> getNodeRoles();
    default public void onShowDoController()
    {
        //NOTHING
    };
}
