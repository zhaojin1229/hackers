package com.example.x550vx_dm066t.myapplication.controller.DB_controller;

import android.database.Cursor;

import com.example.x550vx_dm066t.myapplication.property.story;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by x550vx-dm066t on 21/10/2017.
 */
public class news_DB_controllerTest {
    news_DB_controller controller;
    @Before
    public void setUp() throws Exception {
        controller = Mockito.mock(news_DB_controller.class);
    }

    @Test
    public void addStory() throws Exception {
        story s = new story();
        controller.addStory(s);
        Mockito.verify(controller, Mockito.times(1)).addStory(s);
    }

    @Test
    public void deleteStory() throws Exception {
        controller.deleteStory();
        Mockito.verify(controller, Mockito.times(1)).deleteStory();
    }

    @Test
    public void getStory() throws Exception {
        controller.getStory();
        Mockito.verify(controller, Mockito.times(1)).getStory();
    }

}