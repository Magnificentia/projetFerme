package app.modules;

public abstract class Item implements IItem{
    private String valuename="default";
    protected Item()
    {

    }
    protected Item(String name)
    {
        this.valuename=name;
    }

    public String toString()
    {
        return valuename;
    }
}
