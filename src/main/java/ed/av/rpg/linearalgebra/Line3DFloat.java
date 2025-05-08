package ed.av.rpg.linearalgebra;

/**
 * Line with parallel vector and some point on the one.
 */
public class Line3DFloat {

	public Vector3DFloat r0; // Any point on the line
	public Vector3DFloat a;  // Any parallel vector
	

	Line3DFloat(Vector3DFloat r0, Vector3DFloat a) {
		this.r0 = r0;
		this.a = a;
	}

	/**
	 * Returns array of coefficients in equation system.
	 * a00 * x + a01 * y + a02 * z = a03,
	 * a10 * x + a11 * y + a12 * z = a13.
	 */
	public float[][] getLineEquations() {
		
		float[][] equations = new float[2][4];

		equations[0][0] = a.z;
		equations[0][1] = 0;
		equations[0][2] = -a.x;
		equations[0][3] = r0.x * a.z - r0.z * a.x;

		equations[1][0] = 0;
		equations[1][1] = a.z;
		equations[1][2] = -a.y;
		equations[1][3] = r0.y * a.z - r0.z * a.y;
		
		return equations;
	}
}
