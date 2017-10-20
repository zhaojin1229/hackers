package com.example.x550vx_dm066t.myapplication.property;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by x550vx-dm066t on 20/10/2017.
 */
public class commentsTest {
    comments c= new comments("id", "by","kids","text","time","type");

    @Test
    public void setBy() throws Exception {
        c.setBy("Z");
        Assert.assertEquals(c.getBy(), "Z");
    }

    @Test
    public void setKids() throws Exception {
        c.setKids("123131141");
        Assert.assertEquals(c.getKids(), "123131141");
    }

    @Test
    public void setText() throws Exception {
        c.setText("comments asd");
        String value = c.getText();
        Assert.assertEquals(value, "comments asd");
    }

    @Test
    public void setTime() throws Exception {
        c.setTime("123123");
        String value = c.getTime();
        Assert.assertEquals(value, "123123");
    }

    @Test
    public void setType() throws Exception {
        c.setType("comments");
        String value = c.getType();
        Assert.assertEquals(value, "comments");
    }

    @Test
    public void getId() throws Exception {
        c.setId("123131");
        String value = c.getId();
        Assert.assertEquals(value, "123131");
    }

    @Test
    public void getBy() throws Exception {
        c.setBy("zxc");
        String value = c.getBy();
        Assert.assertEquals(value, "zxc");
    }

    @Test
    public void getKids() throws Exception {
        c.setKids("12313");
        String value = c.getKids();
        Assert.assertEquals(value, "12313");
    }

    @Test
    public void getText() throws Exception {
        c.setText("content");
        String value = c.getText();
        Assert.assertEquals(value, "content");
    }

    @Test
    public void getTime() throws Exception {
        c.setTime("12312");
        String value = c.getTime();
        Assert.assertEquals(value, "12312");
    }

    @Test
    public void getType() throws Exception {
        c.setType("comments");
        String value = c.getType();
        Assert.assertEquals(value, "comments");
    }

}