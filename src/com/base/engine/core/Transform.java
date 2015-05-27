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

import com.base.engine.core.math.Matrix4f;
import com.base.engine.core.math.Quaternion;
import com.base.engine.core.math.Vector3f;

public class Transform {
	private Transform parent;
	private Matrix4f parentMatrix;

	private Vector3f pos;
	private Quaternion rot;
	private Vector3f scale;

	private Vector3f oldPos;
	private Quaternion oldRot;
	private Vector3f oldScale;

	public Transform() {
		pos = new Vector3f(0, 0, 0);
		rot = new Quaternion(0, 0, 0, 1);
		scale = new Vector3f(1, 1, 1);

		parentMatrix = new Matrix4f().initIdentity();
	}

	public void update() {
		if (oldPos != null) {
			oldPos.set(pos);
			oldRot.set(rot);
			oldScale.set(scale);
		} else {
			oldPos = new Vector3f(0, 0, 0).set(pos).add(1.0f);
			oldRot = new Quaternion(0, 0, 0, 0).set(rot).mul(0.5f);
			oldScale = new Vector3f(0, 0, 0).set(scale).add(1.0f);
		}
	}

	public void rotate(final Vector3f axis, final float angle) {
		rot = new Quaternion(axis, angle).mul(rot).normalized();
	}

	public void lookAt(final Vector3f point, final Vector3f up) {
		rot = getLookAtRotation(point, up);
	}

	public Quaternion getLookAtRotation(final Vector3f point, final Vector3f up) {
		return new Quaternion(new Matrix4f().initRotation(point.sub(pos).normalized(), up));
	}

	public boolean hasChanged() {
		if (parent != null && parent.hasChanged()) {
			return true;
		}

		if (!pos.equals(oldPos)) {
			return true;
		}

		if (!rot.equals(oldRot)) {
			return true;
		}

		if (!scale.equals(oldScale)) {
			return true;
		}

		return false;
	}

	public Matrix4f getTransformation() {
		final Matrix4f translationMatrix = new Matrix4f().initTranslation(pos.getX(), pos.getY(), pos.getZ());
		final Matrix4f rotationMatrix = rot.toRotationMatrix();
		final Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());

		return getParentMatrix().mul(translationMatrix.mul(rotationMatrix.mul(scaleMatrix)));
	}

	private Matrix4f getParentMatrix() {
		if (parent != null && parent.hasChanged()) {
			parentMatrix = parent.getTransformation();
		}

		return parentMatrix;
	}

	public void setParent(final Transform parent) {
		this.parent = parent;
	}

	public Vector3f getTransformedPos() {
		return getParentMatrix().transform(pos);
	}

	public Quaternion getTransformedRot() {
		Quaternion parentRotation = new Quaternion(0, 0, 0, 1);

		if (parent != null) {
			parentRotation = parent.getTransformedRot();
		}

		return parentRotation.mul(rot);
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(final Vector3f pos) {
		this.pos = pos;
	}

	public Quaternion getRot() {
		return rot;
	}

	public void setRot(final Quaternion rotation) {
		rot = rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(final Vector3f scale) {
		this.scale = scale;
	}
}
