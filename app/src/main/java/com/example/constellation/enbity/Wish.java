package com.example.constellation.enbity;

import java.io.Serializable;

public class Wish implements Serializable {
    private String wishcontent;
    private String id;

    public String getWishcontent() {
        return wishcontent;
    }

    public void setWishcontent(String wishcontent) {
        this.wishcontent = wishcontent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Wish{" +
                " wishcontent='" + wishcontent + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

