
package com.ax.bedcon.entity;

public class Hipster
{
    public enum JeansType
    {
        SKINNY, SUPERSKINNY;
    }

    private int id;
    private String name;
    private JeansType jeans;
    private boolean hornRimmedGlasses;

    private String imagePath = "";

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public JeansType getJeans()
    {
        return jeans;
    }

    public boolean isHornRimmedGlasses()
    {
        return hornRimmedGlasses;
    }

    public void setImagePath(final String imagePath)
    {
        this.imagePath = imagePath;
    }

    public String getImagePath()
    {
        return imagePath;
    }

}
