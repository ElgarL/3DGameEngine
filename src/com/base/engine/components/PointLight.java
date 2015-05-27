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

package com.base.engine.components;

import com.base.engine.core.math.Attenuation;
import com.base.engine.core.math.Vector3f;
import com.base.engine.rendering.Shader;

public class PointLight extends BaseLight {
	private static final int COLOR_DEPTH = 256;

	private final Attenuation attenuation;
	private float range;

	public PointLight(final Vector3f color, final float intensity, final Attenuation attenuation) {
		super(color, intensity);
		this.attenuation = attenuation;

		final float a = attenuation.getExponent();
		final float b = attenuation.getLinear();
		final float c = attenuation.getConstant() - PointLight.COLOR_DEPTH * getIntensity() * getColor().max();

		this.range = (float) ((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));

		setShader(new Shader("forward-point"));
	}

	public float getRange() {
		return range;
	}

	public void setRange(final float range) {
		this.range = range;
	}

	public Attenuation getAttenuation() {
		return attenuation;
	}
}
