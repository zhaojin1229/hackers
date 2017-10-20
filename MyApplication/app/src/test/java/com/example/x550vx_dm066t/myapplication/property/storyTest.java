package com.example.x550vx_dm066t.myapplication.property;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by x550vx-dm066t on 20/10/2017.
 */
public class storyTest {
    story s= new story("id", "title","by", "type","kids","score");

    @Test
    public void get_score() throws Exception {
        Assert.assertEquals(s.get_score(),"score");
    }

    @Test
    public void set_score() throws Exception {
        s.set_score("score1");
        Assert.assertEquals(s.get_score(),"score1");
    }

    @Test
    public void getID() throws Exception {
        Assert.assertEquals(s.getID(),"id");
    }

    @Test
    public void setID() throws Exception {
        s.setID("id1");
        Assert.assertEquals(s.getID(),"id1");
    }

    @Test
    public void getTitle() throws Exception {

    }

    @Test
    public void seTitle() throws Exception {

    }

    @Test
    public void getBy() throws Exception {

    }

    @Test
    public void setBy() throws Exception {

    }

    @Test
    public void getType() throws Exception {

    }

    @Test
    public void setType() throws Exception {

    }

    @Test
    public void getKids() throws Exception {

    }

    @Test
    public void setKids() throws Exception {

    }

}