package cn.edu.zut.pojo;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable
{
    public static final long serialVersionUID =-5440372534300871944L;
    private Integer id;
    private String username;
    private String password;
    private  Date create_time;
    private String status;

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getCreate_time()
    {
        return create_time;
    }

    public void setCreate_time(Date create_time)
    {
        this.create_time = create_time;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public User()
    {
    }

    public User(Integer id, String username, String password, Date create_time, String status)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.create_time = create_time;
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", create_time=" + create_time +
                ", status='" + status + '\'' +
                '}';
    }
}
