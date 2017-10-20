package com.example.x550vx_dm066t.myapplication.controller.DB_controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

public class DatabaseControllerTest {
        DatabaseController mDbHelper;
        SQLiteDatabase db;
        Context context;

        @Before
        public void setUp() throws Exception {

        }

        @Test
        public void onCreate() throws Exception {
            mDbHelper = Mockito.mock(DatabaseController.class);
            mDbHelper.getReadableDatabase();
            Mockito.verify(mDbHelper,times(1)).getReadableDatabase();
        }

        @Test
        public void onUpgrade() throws Exception {

        }


}