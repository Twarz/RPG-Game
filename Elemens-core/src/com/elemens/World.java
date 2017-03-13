package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class World implements Disposable {

	private Hero hero;
	private ArrayList<Solid> solids;
	private ArrayList<Ladder> ladders;
	private Texture background;
	private Texture foreground;

	public World() {
		this.background = new Texture("village_back.png");
		this.foreground = new Texture("village_front.png");
		this.solids = new ArrayList<Solid>();
		this.solids.add(new Solid(0, 0, 2000, 140));
		this.solids.add(new Solid(0, 140, 20, 1000));
		this.solids.add(new Solid(50, 200, 200, 50));

		// Ladder
		this.ladders = new ArrayList<Ladder>();
		this.ladders.add(new Ladder(372, 140, 80, 350));

		this.solids.add(this.ladders.get(0).top);

		// Steps
		for (int i = 0; i < 1000; i++) {
			this.solids.add(new Solid(800 + i * 40, 140 + i * 10, 40, 1));
		}
		this.hero = new Hero(600, 200, 74, 107);
	}

	public void draw(SpriteBatch sb) {
		sb.draw(this.background, 0, 0);
		this.hero.draw(sb);
		sb.draw(this.foreground, 0, 0);
	}

	public void draw(ShapeRenderer sr) {
		this.hero.draw(sr);
		for (Solid s : this.solids)
			s.draw(sr, Color.RED);
		for (Ladder s : this.ladders)
			s.draw(sr);
	}

	public void update() {
		this.hero.update(Gdx.graphics.getDeltaTime());
		this.hero.canClimbDown = false;
		this.hero.canClimbUp = false;
		if (Gdx.input.isKeyPressed(Input.Keys.Z)){
			for (Ladder l : this.ladders) {
				if (this.hero.center.overlaps(l.climbZone)) {
					this.hero.canClimbUp = true;
					break;
				}
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)){
			for (Ladder l : this.ladders) {
				if (this.hero.center.overlaps(l.climbZoneDown)) {
					this.hero.canClimbDown = true;
					break;
				}
			}

		}
		for (Solid s : this.solids) {

			switch (this.hero.isCollidingH(s.body)) {
			case CENTER:
				break;
			case LEFT:
				this.hero.stopH(s.body.x + s.body.width + 1);
				break;
			case RIGHT:
				this.hero.stopH(s.body.x - this.hero.body.width - 1);
				break;
			default:
				break;
			}

			switch (this.hero.isCollidingV(s.body)) {
			case CENTER:
				break;// this.hero.stopV(s.body.y + s.body.height);
			case BOTTOM:
				if (!this.hero.canClimbDown) {
					this.hero.resetJump();
					this.hero.stopV(s.body.y + s.body.height);
				}
				break;
			case TOP:
				if (!this.hero.canClimbUp) {
					this.hero.stopV(s.body.y - this.hero.body.height);
				}
				break;
			default:
				break;
			}
		}

		this.hero.updateInput();

		if (this.hero.body.y < -100) {
			this.hero.setPosition(600, 200);
		}
	}

	@Override
	public void dispose() {
		this.background.dispose();
		this.hero.dispose();
	}

	public Vector2 getCameraPosition() {
		float heroX, heroY, camX, camY;
		heroX = this.hero.getCenterX(); // this.hero.body.x +
		// this.hero.body.width/2
		heroY = this.hero.getCenterY(); // this.hero.body.y +
		// this.hero.body.height/2
		if (heroX <= 800) {
			camX = 800;
		} else if (heroX >= 6880) {
			camX = 6880;
		} else {
			camX = heroX;
		}

		if (heroY <= 450) {
			camY = 450;
		} else if (heroY >= 3870) {
			camY = 3870;
		} else {
			camY = heroY;
		}
		return new Vector2(camX, camY);
	}
}