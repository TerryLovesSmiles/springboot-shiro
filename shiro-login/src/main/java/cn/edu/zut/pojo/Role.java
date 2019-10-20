package cn.edu.zut.pojo;
import	java.io.Serializable;

public class Role implements Serializable

{
    private Integer id;
    private String name;
    private String desc;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public Role()
    {
    }

    public Role(Integer id, String name, String desc)
    {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }
}
