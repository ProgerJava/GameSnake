package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Apples {
    Texture apple;
    Vector2 vectorApple;
    ArrayList<Integer> listWithRandomPositionX = initializationListX();
    ArrayList<Integer> listWithRandomPositionY = initializationListY();



    public Apples(Texture apple, Vector2 vectorApple) {
        this.apple = apple;
        this.vectorApple = vectorApple;

    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(apple, vectorApple.x, vectorApple.y);
    }
    public ArrayList <Integer> initializationListX () {
        ArrayList <Integer> list = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < 8; i++) {
            list.add(x);
            x += 100;
        }
        return list;
    }
    public ArrayList <Integer> initializationListY () {
        ArrayList <Integer> list = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < 8; i++) {
            list.add(x);
            x += 100;
        }
        return list;
    }

}
