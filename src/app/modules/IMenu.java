package app.modules;

import java.util.ArrayList;

public interface IMenu extends IOption{
    public String getIconPath();
    
    public ArrayList<IOption> getOptionsList();
    //public void initialize();
}
