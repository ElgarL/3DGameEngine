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

package com.base.engine.rendering;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.base.engine.components.BaseLight;
import com.base.engine.components.Camera;
import com.base.engine.core.GameObject;
import com.base.engine.core.Transform;
import com.base.engine.core.math.Vector3f;
import com.base.engine.rendering.resourceManagement.MappedValues;
import static org.lwjgl.opengl.GL11.*;
public class RenderingEngine extends MappedValues {
	private final HashMap<String, Integer> samplerMap;
	private final ArrayList<BaseLight> lights;
	private BaseLight activeLight;

	private final Shader forwardAmbient;
	private Camera mainCamera;

	public RenderingEngine() {
		super();
		lights = new ArrayList<BaseLight>();
		samplerMap = new HashMap<String, Integer>();
		samplerMap.put("diffuse", 0);
		samplerMap.put("normalMap", 1);
		samplerMap.put("dispMap", 2);

		addVector3f("ambient", new Vector3f(0.1f, 0.1f, 0.1f));

		forwardAmbient = new Shader("forward-ambient");

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		GL11.glFrontFace(GL11.GL_CW);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		//
		// glEnable(GL_DEPTH_CLAMP);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public static void lights() {


        // Luces
        ByteBuffer temp = ByteBuffer.allocateDirect(16);
        temp.order(ByteOrder.nativeOrder());
        
        glLight(GL_LIGHT0, GL_AMBIENT, (FloatBuffer) temp.asFloatBuffer().put(new float[] {
                0.20f, 0.2f, 0.20f, 1.0f }).flip());

        glLight(GL_LIGHT0, GL_DIFFUSE, (FloatBuffer) temp.asFloatBuffer().put(new float[] {
                0.5f, 0.5f, 0.5f, 1.0f }).flip());

        glLight(GL_LIGHT0, GL_SPECULAR, (FloatBuffer) temp.asFloatBuffer().put(new float[] {
                1.0f, 1.0f, 1.0f, 1.0f }).flip());

        glLight(GL_LIGHT0, GL_POSITION, (FloatBuffer) temp.asFloatBuffer().put(new float[] {
                0.0f, 50.0f, 0.0f, 1.0f }).flip());

        glLight(GL_LIGHT0, GL_CONSTANT_ATTENUATION, (FloatBuffer) temp.asFloatBuffer().put(new float[] {
                0.9f, 0.0f, 0.0f, 0.0f }).flip());

        glEnable(GL_LIGHT0);
        glEnable(GL_LIGHTING);

    }
	
	public void updateUniformStruct(final Transform transform, final Material material, final Shader shader, final String uniformName, final String uniformType) {
		throw new IllegalArgumentException(uniformType + " is not a supported type in RenderingEngine");
	}

	public void render(final GameObject object) throws IllegalStateException {
		if (getMainCamera() == null) {
			System.err.println("Error! Main camera not found. This is very very big bug, and game will crash.");
			throw new IllegalStateException("Camera was not attached to the RenderingEngine");
		}
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		object.renderAll(forwardAmbient, this);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
		GL11.glDepthMask(false);
		GL11.glDepthFunc(GL11.GL_EQUAL);
		
		lights();
		
		for (final BaseLight light : lights) {
			activeLight = light;
			object.renderAll(light.getShader(), this);
		}

		GL11.glDepthFunc(GL11.GL_LESS);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public static String getOpenGLVersion() {
		return GL11.glGetString(GL11.GL_VERSION);
	}

	public void addLight(final BaseLight light) {
		lights.add(light);
	}

	public void addCamera(final Camera camera) {
		mainCamera = camera;
	}

	public int getSamplerSlot(final String samplerName) {
		return samplerMap.get(samplerName);
	}

	public BaseLight getActiveLight() {
		return activeLight;
	}

	public Camera getMainCamera() {
		return mainCamera;
	}

	public void setMainCamera(final Camera mainCamera) {
		this.mainCamera = mainCamera;
	}
}
