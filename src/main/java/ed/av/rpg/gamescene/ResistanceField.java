package ed.av.rpg.gamescene;

import ed.av.rpg.linearalgebra.Point2Df;

import static ed.av.rpg.gamescene.HexNet.HexNetIteration.iterateIJ;


public class ResistanceField {

    private final float[][] scalarField;
    private final Point2Df[][] vectorField;

    public ResistanceField(int wSize, int hSize) {

        scalarField = new float[wSize][hSize];
        vectorField = new Point2Df[wSize][hSize];
        iterateIJ(wSize, hSize, (i, j) -> vectorField[i][j] = new Point2Df());
    }
}
