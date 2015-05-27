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

package com.base.engine.core.math;

public class Matrix4f {
	private float[][] m;

	public Matrix4f() {
		m = new float[4][4];
	}

	public Matrix4f initIdentity() {
		m[0][0] = 1;
		m[0][1] = 0;
		m[0][2] = 0;
		m[0][3] = 0;
		m[1][0] = 0;
		m[1][1] = 1;
		m[1][2] = 0;
		m[1][3] = 0;
		m[2][0] = 0;
		m[2][1] = 0;
		m[2][2] = 1;
		m[2][3] = 0;
		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 0;
		m[3][3] = 1;

		return this;
	}

	public Matrix4f initTranslation(final float x, final float y, final float z) {
		m[0][0] = 1;
		m[0][1] = 0;
		m[0][2] = 0;
		m[0][3] = x;
		m[1][0] = 0;
		m[1][1] = 1;
		m[1][2] = 0;
		m[1][3] = y;
		m[2][0] = 0;
		m[2][1] = 0;
		m[2][2] = 1;
		m[2][3] = z;
		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 0;
		m[3][3] = 1;

		return this;
	}

	public Matrix4f initRotation(float x, float y, float z) {
		final Matrix4f rx = new Matrix4f();
		final Matrix4f ry = new Matrix4f();
		final Matrix4f rz = new Matrix4f();

		x = (float) Math.toRadians(x);
		y = (float) Math.toRadians(y);
		z = (float) Math.toRadians(z);

		rz.m[0][0] = (float) Math.cos(z);
		rz.m[0][1] = -(float) Math.sin(z);
		rz.m[0][2] = 0;
		rz.m[0][3] = 0;
		rz.m[1][0] = (float) Math.sin(z);
		rz.m[1][1] = (float) Math.cos(z);
		rz.m[1][2] = 0;
		rz.m[1][3] = 0;
		rz.m[2][0] = 0;
		rz.m[2][1] = 0;
		rz.m[2][2] = 1;
		rz.m[2][3] = 0;
		rz.m[3][0] = 0;
		rz.m[3][1] = 0;
		rz.m[3][2] = 0;
		rz.m[3][3] = 1;

		rx.m[0][0] = 1;
		rx.m[0][1] = 0;
		rx.m[0][2] = 0;
		rx.m[0][3] = 0;
		rx.m[1][0] = 0;
		rx.m[1][1] = (float) Math.cos(x);
		rx.m[1][2] = -(float) Math.sin(x);
		rx.m[1][3] = 0;
		rx.m[2][0] = 0;
		rx.m[2][1] = (float) Math.sin(x);
		rx.m[2][2] = (float) Math.cos(x);
		rx.m[2][3] = 0;
		rx.m[3][0] = 0;
		rx.m[3][1] = 0;
		rx.m[3][2] = 0;
		rx.m[3][3] = 1;

		ry.m[0][0] = (float) Math.cos(y);
		ry.m[0][1] = 0;
		ry.m[0][2] = -(float) Math.sin(y);
		ry.m[0][3] = 0;
		ry.m[1][0] = 0;
		ry.m[1][1] = 1;
		ry.m[1][2] = 0;
		ry.m[1][3] = 0;
		ry.m[2][0] = (float) Math.sin(y);
		ry.m[2][1] = 0;
		ry.m[2][2] = (float) Math.cos(y);
		ry.m[2][3] = 0;
		ry.m[3][0] = 0;
		ry.m[3][1] = 0;
		ry.m[3][2] = 0;
		ry.m[3][3] = 1;

		m = rz.mul(ry.mul(rx)).getM();

		return this;
	}

	public Matrix4f initScale(final float x, final float y, final float z) {
		m[0][0] = x;
		m[0][1] = 0;
		m[0][2] = 0;
		m[0][3] = 0;
		m[1][0] = 0;
		m[1][1] = y;
		m[1][2] = 0;
		m[1][3] = 0;
		m[2][0] = 0;
		m[2][1] = 0;
		m[2][2] = z;
		m[2][3] = 0;
		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 0;
		m[3][3] = 1;

		return this;
	}

	public Matrix4f initPerspective(final float fov, final float aspectRatio, final float zNear, final float zFar) {
		final float tanHalfFOV = (float) Math.tan(fov / 2);
		final float zRange = zNear - zFar;

		m[0][0] = 1.0f / (tanHalfFOV * aspectRatio);
		m[0][1] = 0;
		m[0][2] = 0;
		m[0][3] = 0;
		m[1][0] = 0;
		m[1][1] = 1.0f / tanHalfFOV;
		m[1][2] = 0;
		m[1][3] = 0;
		m[2][0] = 0;
		m[2][1] = 0;
		m[2][2] = (-zNear - zFar) / zRange;
		m[2][3] = 2 * zFar * zNear / zRange;
		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 1;
		m[3][3] = 0;

		return this;
	}

	public Matrix4f initOrthographic(final float left, final float right, final float bottom, final float top, final float near, final float far) {
		final float width = right - left;
		final float height = top - bottom;
		final float depth = far - near;

		m[0][0] = 2 / width;
		m[0][1] = 0;
		m[0][2] = 0;
		m[0][3] = -(right + left) / width;
		m[1][0] = 0;
		m[1][1] = 2 / height;
		m[1][2] = 0;
		m[1][3] = -(top + bottom) / height;
		m[2][0] = 0;
		m[2][1] = 0;
		m[2][2] = -2 / depth;
		m[2][3] = -(far + near) / depth;
		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 0;
		m[3][3] = 1;

		return this;
	}

	public Matrix4f initRotation(final Vector3f forward, final Vector3f up) {
		final Vector3f f = forward.normalized();

		Vector3f r = up.normalized();
		r = r.cross(f);

		final Vector3f u = f.cross(r);

		return initRotation(f, u, r);
	}

	public Matrix4f initRotation(final Vector3f forward, final Vector3f up, final Vector3f right) {
		final Vector3f f = forward;
		final Vector3f r = right;
		final Vector3f u = up;

		m[0][0] = r.getX();
		m[0][1] = r.getY();
		m[0][2] = r.getZ();
		m[0][3] = 0;
		m[1][0] = u.getX();
		m[1][1] = u.getY();
		m[1][2] = u.getZ();
		m[1][3] = 0;
		m[2][0] = f.getX();
		m[2][1] = f.getY();
		m[2][2] = f.getZ();
		m[2][3] = 0;
		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 0;
		m[3][3] = 1;

		return this;
	}

	public Vector3f transform(final Vector3f r) {
		return new Vector3f(m[0][0] * r.getX() + m[0][1] * r.getY() + m[0][2] * r.getZ() + m[0][3], m[1][0] * r.getX() + m[1][1] * r.getY() + m[1][2] * r.getZ() + m[1][3], m[2][0] * r.getX() + m[2][1] * r.getY() + m[2][2] * r.getZ() + m[2][3]);
	}

	public Matrix4f mul(final Matrix4f r) {
		final Matrix4f res = new Matrix4f();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res.set(i, j, m[i][0] * r.get(0, j) + m[i][1] * r.get(1, j) + m[i][2] * r.get(2, j) + m[i][3] * r.get(3, j));
			}
		}

		return res;
	}

	public float[][] getM() {
		final float[][] res = new float[4][4];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res[i][j] = m[i][j];
			}
		}

		return res;
	}

	public float get(final int x, final int y) {
		return m[x][y];
	}

	public void setM(final float[][] m) {
		this.m = m;
	}

	public void set(final int x, final int y, final float value) {
		m[x][y] = value;
	}
}
