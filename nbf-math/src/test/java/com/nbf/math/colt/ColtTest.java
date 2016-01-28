package com.nbf.math.colt;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import org.junit.Test;

/**
 * User: root
 * Date: 1/27/16
 * Time: 11:08 PM
 */
public class ColtTest {

    @Test
    public void matrixMultiplicative(){
        DoubleMatrix2D A = new DenseDoubleMatrix2D(2,3);
        DoubleMatrix2D B = new DenseDoubleMatrix2D(3,1);
        A.assign(new double[][]{{25,20,18},{24,16,27}});
        B.assign(new double[][]{{0.5},{0.2},{0.7}});
        System.out.println(A);
        System.out.println(B);
        DoubleMatrix2D C = Algebra.DEFAULT.mult(A,B);
        System.out.println(C);

    }

}
