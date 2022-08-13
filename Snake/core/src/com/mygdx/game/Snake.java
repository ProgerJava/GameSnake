package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Snake {

    private float speed = 2.5f;
    public int count = 0;
    String route;
    Vector2 vector2Part;
    Texture textureHead = new Texture("head1.png");

    public Snake(String route, Vector2 vector2Part) {
        this.route = route;
        this.vector2Part = vector2Part;
    }


    public void renderManyPart (SpriteBatch spriteBatch, Texture texture, ArrayList <Snake> listPart) {
        for (int i = 0; i < listPart.size(); i++) {
            if (i>0) {
                spriteBatch.draw(texture, listPart.get(i).vector2Part.x, listPart.get(i).vector2Part.y);
            } else {
                spriteBatch.draw(textureHead, listPart.get(i).vector2Part.x, listPart.get(i).vector2Part.y);
            }
        }
    }

    public void update (ArrayList <Snake> listVector) {

        for (int i = 0; i < listVector.size(); i++) {
            String str = listVector.get(i).route;
            switch (str) {
                case "PAGE_UP":
                    if (i == 0) {
                        listVector.get(i).vector2Part.y += speed;
                    } else {
                        listVector.get(i).vector2Part.y += speed;
                        if (listVector.get(i).vector2Part.y>=listVector.get(i-1).vector2Part.y) {
                            listVector.get(i).vector2Part.y = listVector.get(i-1).vector2Part.y;
                            listVector.get(i).route = listVector.get(i - 1).route;
                        }
                    }
                    break;
                case "PAGE_DOWN":
                    if (i == 0) {
                        listVector.get(i).vector2Part.y -= speed;
                    } else {
                        listVector.get(i).vector2Part.y -= speed;
                        if (listVector.get(i).vector2Part.y <=listVector.get(i-1).vector2Part.y) {
                            listVector.get(i).vector2Part.y = listVector.get(i-1).vector2Part.y;
                            listVector.get(i).route = listVector.get(i - 1).route;
                        }
                    }
                    break;
                case "HOME":
                    if (i == 0) {
                        listVector.get(i).vector2Part.x -= speed;
                    } else {
                        listVector.get(i).vector2Part.x -= speed;
                        if (listVector.get(i).vector2Part.x<=listVector.get(i-1).vector2Part.x) {
                            listVector.get(i).vector2Part.x = listVector.get(i-1).vector2Part.x;
                            listVector.get(i).route = listVector.get(i - 1).route;
                        }
                    }
                    break;
                case "END":
                    if (i == 0) {
                        listVector.get(i).vector2Part.x += speed;
                    } else {
                        listVector.get(i).vector2Part.x += speed;
                        if (listVector.get(i).vector2Part.x>=listVector.get(i-1).vector2Part.x) {
                            listVector.get(i).route = listVector.get(i - 1).route;
                        }
                    }
                    break;
            }
        }
    }
    public void upSnake(ArrayList <Snake> snakeArrayList) {
        count++;
        String snakeRoute = snakeArrayList.get(snakeArrayList.size() - 1).route;
        if (snakeArrayList.size() == 1) {
            if (snakeRoute.equals("PAGE_UP")) {
                snakeArrayList.add(new Snake(snakeRoute, new Vector2(snakeArrayList.get(0).vector2Part.x, snakeArrayList.get(0).vector2Part.y - 100)));
            }
            if (snakeRoute.equals("PAGE_DOWN")) {
                snakeArrayList.add(new Snake(snakeRoute, new Vector2(snakeArrayList.get(0).vector2Part.x, snakeArrayList.get(0).vector2Part.y + 100)));
            }
            if (snakeRoute.equals("HOME")) {
                snakeArrayList.add(new Snake(snakeRoute, new Vector2(snakeArrayList.get(0).vector2Part.x + 100, snakeArrayList.get(0).vector2Part.y)));
            }
            if (snakeRoute.equals("END")) {
                snakeArrayList.add(new Snake(snakeRoute, new Vector2(snakeArrayList.get(0).vector2Part.x - 100, snakeArrayList.get(0).vector2Part.y)));
            }
        }
        else {
            if (snakeRoute.equals("PAGE_UP")) {
                snakeArrayList.add(new Snake(snakeRoute, new Vector2(snakeArrayList.get(snakeArrayList.size()-1).vector2Part.x, snakeArrayList.get(snakeArrayList.size()-1).vector2Part.y - 100)));
            }
            if (snakeRoute.equals("PAGE_DOWN")) {
                snakeArrayList.add(new Snake(snakeRoute, new Vector2(snakeArrayList.get(snakeArrayList.size()-1).vector2Part.x, snakeArrayList.get(snakeArrayList.size()-1).vector2Part.y + 100)));
            }
            if (snakeRoute.equals("HOME")) {
                snakeArrayList.add(new Snake(snakeRoute, new Vector2(snakeArrayList.get(snakeArrayList.size()-1).vector2Part.x + 100, snakeArrayList.get(snakeArrayList.size()-1).vector2Part.y)));
            }
            if (snakeRoute.equals("END")) {
                snakeArrayList.add(new Snake(snakeRoute, new Vector2(snakeArrayList.get(snakeArrayList.size()-1).vector2Part.x - 100, snakeArrayList.get(snakeArrayList.size()-1).vector2Part.y)));
            }
        }
    }
}
