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

import com.base.engine.core.CoreEngine;
import com.base.engine.core.GameObject;
import com.base.engine.core.Transform;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

public abstract class GameComponent {
	private GameObject m_parent;

	public void Input(final float delta) {
	}

	public void Update(final float delta) {
	}

	public void Render(final Shader shader, final RenderingEngine renderingEngine) {
	}

	public void SetParent(final GameObject parent) {
		m_parent = parent;
	}

	public Transform GetTransform() {
		return m_parent.GetTransform();
	}

	public void AddToEngine(final CoreEngine engine) {
	}
}
