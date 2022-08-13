package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.runtime.Threads;
import sun.jvm.hotspot.utilities.BitMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainSnake extends ApplicationAdapter {
	SpriteBatch batch;
	Vector2 vectorField, vectorApple;
	Texture textureField, textureApple, textureRestart, texturePart;
	Snake snake;
	Apples apples;
	public boolean isPlay;
	ArrayList<Snake> snakeArrayList = new ArrayList<>();
	BitmapFont bitmapFont;


	@Override
	public void create() {
		batch = new SpriteBatch();
		textureField = new Texture("play.png");
		textureApple = new Texture("apple.png");
		textureRestart = new Texture("RestartBtn.png");
		texturePart = new Texture("fragment.png");
		vectorField = new Vector2(0, 0);
		vectorApple = new Vector2(300, 400);
		snake = new Snake(String.valueOf(DirectionSnake.PAGE_UP), new Vector2(500, 500));
		snakeArrayList.add(snake);
		apples = new Apples(textureApple, vectorApple);
		isPlay = true;
		bitmapFont = new BitmapFont();

	}

	@Override
	public void render() {
		batch.begin();
		batch.draw(textureField, vectorField.x, vectorField.y);
		//Проверка змеи на жизнь...
		alive();
		//Отрисовка счетчика:
		bitmapFont.getData().setScale(1.5f, 1.5f);
		bitmapFont.getColor().set(Color.RED);
		bitmapFont.draw(batch, "Score: " + snake.count, 10, 780);
		if (isPlay) {
			motion();
			update();
			taste();
			apples.render(batch);
			snake.renderManyPart(batch, texturePart, snakeArrayList);
		} else {
			batch.draw(textureRestart, 200, 300);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			if (!isPlay) {
				apples.vectorApple.x = apples.listWithRandomPositionX.get((int) (Math.random() * 8));
				apples.vectorApple.y = apples.listWithRandomPositionY.get((int) (Math.random() * 8));
				snakeArrayList.clear();
				snake = new Snake(String.valueOf(DirectionSnake.PAGE_UP), new Vector2(500, 500));
				snakeArrayList.add(snake);
				isPlay = true;
				snake.count = 0;
			}
		}
		batch.end();
	}

	private void taste() {
		for (int i = 0; i < snakeArrayList.size(); i++) {
			if (snakeArrayList.get(i).vector2Part.x + 100 > apples.vectorApple.x && snakeArrayList.get(i).vector2Part.x < apples.vectorApple.x + 100 &&
					snakeArrayList.get(i).vector2Part.y + 100 > apples.vectorApple.y && snakeArrayList.get(i).vector2Part.y < apples.vectorApple.y + 100) {
				snake.upSnake(snakeArrayList);
				vectorApple.x = apples.listWithRandomPositionX.get((int) (Math.random() * 8));
				vectorApple.y = apples.listWithRandomPositionY.get((int) (Math.random() * 8));
			}
		}
	}

	public void update() {
		snake.update(snakeArrayList);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	public void alive () {
		if (snakeArrayList.get(0).vector2Part.x < 0) {
			isPlay = false;
		}
		if (snakeArrayList.get(0).vector2Part.x+100 > 800) {
			isPlay = false;
		}
		if (snakeArrayList.get(0).vector2Part.y+100 > 800) {
			isPlay = false;
		}if (snakeArrayList.get(0).vector2Part.y < 0) {
			isPlay = false;
		}
		if (snakeArrayList.size() > 2) {
			for (int i = 2; i < snakeArrayList.size(); i++) {
				if (snakeArrayList.get(0).vector2Part.x + 100 > snakeArrayList.get(i).vector2Part.x && snakeArrayList.get(0).vector2Part.x < snakeArrayList.get(i).vector2Part.x + 100 &&
						snakeArrayList.get(0).vector2Part.y + 100 > snakeArrayList.get(i).vector2Part.y && snakeArrayList.get(0).vector2Part.y < snakeArrayList.get(i).vector2Part.y + 100) {
					isPlay = false;
				}
			}
		}
	}
	public void motion () {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			if (snake.route.equals(DirectionSnake.PAGE_DOWN.toString()) && snakeArrayList.size() > 1) {
				isPlay = false;
			}
			snake.route = String.valueOf(DirectionSnake.PAGE_UP);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (snake.route.equals(DirectionSnake.PAGE_UP.toString()) && snakeArrayList.size() > 1) {
				isPlay = false;
			}
			snake.route = String.valueOf(DirectionSnake.PAGE_DOWN);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (snake.route.equals(DirectionSnake.END.toString()) && snakeArrayList.size() > 1) {
				isPlay = false;
			}
			snake.route = String.valueOf(DirectionSnake.HOME);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			if (snake.route.equals(DirectionSnake.HOME.toString()) && snakeArrayList.size() > 1) {
				isPlay = false;
			}
			snake.route = String.valueOf(DirectionSnake.END);
		}
	}
}
