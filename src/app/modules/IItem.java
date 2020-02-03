package app.modules;

import javafx.scene.Parent;

public interface IItem {
    public Parent getItem();
    public boolean isVisible();
    default public void onShowDo()
    {
        //NOTHING
    };
}
