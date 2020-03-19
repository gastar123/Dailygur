package com.xe72.dailygur.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageModelTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = NullPointerException.class)
    public void loadComment() {
        ImageModel imageModel = new ImageModel(null);
        imageModel.loadComment("");

    }

    @Test
    public void loadAlbum() {
        Assert.assertTrue("test", false);
    }

    @Test
    public void loadImage() {
        assert false;
    }
}