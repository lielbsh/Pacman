package com.example.PacMan;

public class Ghost extends Figure {
    @Override
    public  boolean move(){
        return false;
    };
    @Override
    public boolean die(){
        return false;
    };
}
