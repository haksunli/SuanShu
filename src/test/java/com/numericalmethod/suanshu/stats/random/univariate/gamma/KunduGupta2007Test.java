/*
 * Copyright (c) Numerical Method Inc.
 * http://www.numericalmethod.com/
 * 
 * THIS SOFTWARE IS LICENSED, NOT SOLD.
 * 
 * YOU MAY USE THIS SOFTWARE ONLY AS DESCRIBED IN THE LICENSE.
 * IF YOU ARE NOT AWARE OF AND/OR DO NOT AGREE TO THE TERMS OF THE LICENSE,
 * DO NOT USE THIS SOFTWARE.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITH NO WARRANTY WHATSOEVER,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, WITHOUT LIMITATION,
 * ANY WARRANTIES OF ACCURACY, ACCESSIBILITY, COMPLETENESS,
 * FITNESS FOR A PARTICULAR PURPOSE, MERCHANTABILITY, NON-INFRINGEMENT, 
 * TITLE AND USEFULNESS.
 * 
 * IN NO EVENT AND UNDER NO LEGAL THEORY,
 * WHETHER IN ACTION, CONTRACT, NEGLIGENCE, TORT, OR OTHERWISE,
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIMS, DAMAGES OR OTHER LIABILITIES,
 * ARISING AS A RESULT OF USING OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.numericalmethod.suanshu.stats.random.univariate.gamma;

import com.numericalmethod.suanshu.stats.descriptive.moment.Kurtosis;
import com.numericalmethod.suanshu.stats.descriptive.moment.Mean;
import com.numericalmethod.suanshu.stats.descriptive.moment.Skewness;
import com.numericalmethod.suanshu.stats.descriptive.moment.Variance;
import com.numericalmethod.suanshu.stats.distribution.univariate.GammaDistribution;
import com.numericalmethod.suanshu.stats.distribution.univariate.ProbabilityDistribution;
import com.numericalmethod.suanshu.stats.random.univariate.uniform.UniformRng;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Haksun Li
 */
public class KunduGupta2007Test {

    @Test
    public void test_0010() {
        final int size = 1000000;

        final double k = 0.1;
        final double theta = 1;

        KunduGupta2007 rng = new KunduGupta2007(k, theta, new UniformRng());
        rng.seed(1234567895L);

        double[] x = new double[size];
        for (int i = 0; i < size; ++i) {
            x[i] = rng.nextDouble();
            assertTrue(x[i] >= 0);
        }

        Mean mean = new Mean(x);
        Variance var = new Variance(x);
        Skewness skew = new Skewness(x);
        Kurtosis kurtosis = new Kurtosis(x);

        ProbabilityDistribution dist = new GammaDistribution(k, theta);
        assertEquals(dist.mean(), mean.value(), 0.01);
        assertEquals(dist.variance(), var.value(), 0.01);
        assertEquals(dist.skew(), skew.value(), 0.1);
        assertEquals(dist.kurtosis(), kurtosis.value(), 0.4);
    }

    @Test
    public void test_0020() {
        final int size = 2000000;

        final double k = 0.1;
        final double theta = 0.1;

        KunduGupta2007 rng = new KunduGupta2007(k, theta, new UniformRng());
        rng.seed(1234567891L);

        double[] x = new double[size];
        for (int i = 0; i < size; ++i) {
            x[i] = rng.nextDouble();
            assertTrue(x[i] >= 0);
        }

        Mean mean = new Mean(x);
        Variance var = new Variance(x);
        Skewness skew = new Skewness(x);
        Kurtosis kurtosis = new Kurtosis(x);

        ProbabilityDistribution dist = new GammaDistribution(k, theta);
        assertEquals(dist.mean(), mean.value(), 0.01);
        assertEquals(dist.variance(), var.value(), 0.01);
        assertEquals(dist.skew(), skew.value(), 0.1);
        assertEquals(dist.kurtosis(), kurtosis.value(), 2);
    }
}
