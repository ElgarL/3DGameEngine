/*
 * Copyright (C) 2014 Benny Bobaganoosh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.base.engine.core;

import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Window;

public class CoreEngine {
	private boolean running;
	private final Game game;
	private RenderingEngine renderingEngine;
	private final int width;
	private final int height;
	private final double frameTime;

	public CoreEngine(final int width, final int height, final double framerate, final Game game) {
		running = false;
		this.game = game;
		this.width = width;
		this.height = height;
		frameTime = 1.0 / framerate;
		game.setEngine(this);
	}

	public void createWindow(final String title) {
		Window.createWindow(width, height, title);
		renderingEngine = new RenderingEngine();
	}

	public void start() {
		if (running) {
			return;
		}

		run();
	}

	public void stop() {
		if (!running) {
			return;
		}

		running = false;
	}

	private void run() {
		running = true;

		int frames = 0;
		double frameCounter = 0;

		game.init();

		double lastTime = Time.getTime();
		double unprocessedTime = 0;

		while (running) {
			boolean render = false;

			final double startTime = Time.getTime();
			final double passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime;
			frameCounter += passedTime;

			while (unprocessedTime > frameTime) {
				render = true;

				unprocessedTime -= frameTime;

				if (Window.isCloseRequested()) {
					stop();
				}

				game.input((float) frameTime);
				Input.update();

				game.update((float) frameTime);

				if (frameCounter >= 1.0) {
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			if (render) {
				game.render(renderingEngine);
				Window.render();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		cleanUp();
	}

	private void cleanUp() {
		Window.dispose();
	}

	public RenderingEngine getRenderingEngine() {
		return renderingEngine;
	}
}
