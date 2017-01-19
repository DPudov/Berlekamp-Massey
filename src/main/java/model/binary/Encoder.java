package model.binary;

import model.BMAlgorithm;
import model.polynomials.Polynomial;
import model.polynomials.PolynomialStorage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by ${DPudov} on 13.09.2016.
 */
public class Encoder {
    private final static String LINEAR_SPAN = "Linear span = ";

    public Encoder() {
    }

    /**
     * 1. Get bytes stream of file
     * 2. Use Berlekamp-Massey
     * 3. Write bytes to new file
     **/
    public PolynomialStorage encode(String filePath, int lengthOfBuffer, int field) throws IOException {
        //open stream
        InputStream is = new BufferedInputStream(new FileInputStream(filePath));
        //init variables
        PolynomialStorage result = new PolynomialStorage();
        result.setField(field);
        BMAlgorithm algorithm;
        //read data

        while (is.available() != 0) {
            byte[] buffer = new byte[lengthOfBuffer];
            int b = is.read(buffer);
            if (b != 0) {
                algorithm = new BMAlgorithm(buffer);
                byte[] feedback = algorithm.forBinaryField();
                System.out.println(Arrays.toString(feedback));
                System.out.println(algorithm.getLinearSpan());
                Polynomial p = new Polynomial(feedback,
                        b,
                        algorithm.getLinearSpan());
                p.setInitState(Arrays.copyOfRange(buffer, 0, feedback.length  - 1));
                //p.setInitialState(Arrays.copyOfRange(buffer, 0, ((algorithm.getLinearSpan() + 7) / 8)));
                result.add(p);
            }

        }
        is.close();
        return result;
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
