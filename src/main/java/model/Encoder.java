package model;

import model.utils.BytesUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by ${DPudov} on 13.09.2016.
 */
public class Encoder {
    private static Encoder ourInstance = new Encoder();
    private final static String LINEAR_SPAN = "Linear span = ";

    public static Encoder getInstance() {
        return ourInstance;
    }

    private Encoder() {
    }

    /**
     * 1. Get bytes stream of file
     * 2. Use Berlekamp-Massey
     * 3. Write bytes to new file
     **/
    public void encode(String filePath, short lengthOfPolynomial) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filePath));
        BMAlgorithm algorithm;
        while (is.available() != 0) {
            byte[] buffer = new byte[lengthOfPolynomial];
            algorithm = new BMAlgorithm(buffer);
            is.read(buffer, 0, lengthOfPolynomial - 1);
            byte[] polynom = BytesUtil.createThePolynom(algorithm.useBerlekampMassey());
            Polynomial p = new Polynomial(polynom, lengthOfPolynomial, algorithm.getLinearSpan());
            p.setInitialState(Arrays.copyOfRange(buffer, 0, algorithm.getLinearSpan() - 1));
            PolynomialStorage.getInstance().add(p);
        }
        is.close();

    }

    public static String getFunctionFeedback(byte[] feedbackArray) {
        int j = 0;
        StringBuilder builder = new StringBuilder();
        for (byte b : feedbackArray) {
            if (j < feedbackArray.length - 1) {
                if (b == 1) {
                    System.out.print(b + "x^(" + j + ") + ");
                    builder.append(b).append("x^(").append(j).append(") + ");
                }
            } else {
                if (b == 1) {
                    System.out.print(b + "x^(" + j + ")");
                    builder.append(b).append("x^(").append(j).append(")");
                }
            }
            j++;
        }
        System.out.println();
        return builder.toString();
    }
}
