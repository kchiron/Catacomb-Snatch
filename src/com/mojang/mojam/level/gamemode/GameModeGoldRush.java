package com.mojang.mojam.level.gamemode;

import java.util.Random;

import com.mojang.mojam.entity.building.SpawnerForBat;
import com.mojang.mojam.level.tile.FloorTile;
import com.mojang.mojam.level.tile.Tile;
import com.mojang.mojam.network.TurnSynchronizer;

public class GameModeGoldRush extends GameMode {
	@Override
	protected void setupPlayerSpawnArea() {
		super.setupPlayerSpawnArea();
		
		Random random = TurnSynchronizer.synchedRandom;		
		for (int i = 0; i < 15; i++) {
			double x = random.nextInt(newLevel.width) * Tile.WIDTH
					+ Tile.WIDTH / 2;
			double y = random.nextInt(newLevel.height) * Tile.HEIGHT
					+ Tile.HEIGHT / 2 - 4;
			final Tile tile = newLevel.getTile((int) (x / Tile.WIDTH),
					(int) (y / Tile.HEIGHT));
			if (tile instanceof FloorTile
					&& SpawnerSurroundingsChecker.isClear(newLevel, x, y)) {
				newLevel.addEntity(new SpawnerForBat(x, y));
			}
		}
	}
	
	@Override
	protected void setTickItems() {
		newLevel.tickItems.add(new RandomSpawner());
	}
	
	@Override
	protected void setVictoryCondition() {
		newLevel.victoryConditions = new GoldRush();
	}
	
	@Override
	protected void setTargetScore() {
		newLevel.TARGET_SCORE = 5000;
	}
}
