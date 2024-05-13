package com.services.db.models;

import javax.persistence.Entity;
import java.util.Arrays;

@Entity
public class Answers {
    int y, n;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }


    @Override
    public String toString() {
        return "Answers{" +
                "y=" + y +
                ", n=" + n +
                '}';
    }
}
