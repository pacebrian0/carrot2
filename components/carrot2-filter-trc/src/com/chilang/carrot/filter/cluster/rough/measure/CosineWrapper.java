package com.chilang.carrot.filter.cluster.rough.measure;

import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.doublealgo.Statistic;
import cern.jet.math.Functions;

public class CosineWrapper implements  Statistic.VectorVectorFunction  {
    
     private static final cern.jet.math.Functions F = cern.jet.math.Functions.functions;

    /**
     * Calculate cosine coefficient between two vectors :
     *  Sum(x[i] * y[i]) / Sqrt(Sum(x[i]^2) * Sum(y[i]^2))
     * When numerator part (Sum(x[i] * y[i]) is 0, returns 0;
     * @param x
     * @param y
     * @return
     */
    public double apply(DoubleMatrix1D x, DoubleMatrix1D y) {
        double nominator =  x.aggregate(y, Functions.plus, Functions.mult );
        //handle case when a == 0 in a/b
        if (nominator == 0)
            return 0;

        return nominator /
                Math.sqrt(x.aggregate(Functions.plus,Functions.pow(2)) * y.aggregate(Functions.plus, Functions.pow(2)));
    }
}
