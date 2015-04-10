package com.toolbartest;

/**
 * Created by uspurbr on 4/10/15.
 */
public class ListObject {
    public ListObject(String title, int imageId){
        this.title = title;
        this.imageId = imageId;
    }
    public ListObject(String title){
        this.title = title;
    }
    int imageId;
    String title;
}
