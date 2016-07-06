package com.andreakim.artsupplies;

import dalvik.annotation.TestTargetClass;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by andreakim on 7/6/16.
 */
public class GettersUnitTest {


    @Test
    public void gettersTest() {
        ProductItem paintbrush = new ProductItem();
        assertNull(paintbrush.mPaintbrush);

    }



}
