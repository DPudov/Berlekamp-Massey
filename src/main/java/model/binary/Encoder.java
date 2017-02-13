package model.binary;

import model.BMAlgorithm;
import model.polynomials.Polynomial;
import model.polynomials.PolynomialStorage;
import model.utils.BytesUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Class for encoding file with B. - Massey algorithm.
 */
public class Encoder {
    private static final Logger logger = Logger.getLogger(Encoder.class.getName());

    public Encoder() {
    }

    /**
     * 1. Get bytes stream of file
     * 2. Use Berlekamp-Massey
     * 3. Create polynomial with feedback made by BMAlgorithm.
     * 4. Set initial state. It's the end of the buffer with length equal to feedback length.
     **/
    public PolynomialStorage encode(String filePath) throws IOException {
        //open stream
        InputStream is = new BufferedInputStream(new FileInputStream(filePath));
        //init variables
        PolynomialStorage result = new PolynomialStorage();
        result.setField();
        BMAlgorithm algorithm;
        //read data

        while (is.available() != 0) {
            byte[] buffer = new byte[256];

            int b = is.read(buffer);
            if (b < 256) {
                algorithm = new BMAlgorithm(Arrays.copyOfRange(buffer, 0, b));
                byte[] feedback = BytesUtil.packArray(algorithm.forBinaryField());
                Polynomial p = new Polynomial(feedback, b, algorithm.getLinearSpan());
                p.setInitState(Arrays.copyOfRange(buffer, buffer.length - feedback.length, buffer.length));
            } else if (b != -1) {
                algorithm = new BMAlgorithm(buffer);
                byte[] feedback = BytesUtil.packArray(algorithm.forBinaryField());
                logger.info("Feedback packed is " + Arrays.toString(feedback));
                Polynomial p = new Polynomial(feedback,
                        b,
                        algorithm.getLinearSpan());
                p.setInitState(Arrays.copyOfRange(buffer, buffer.length - feedback.length, buffer.length));
                result.add(p);
            }

        }
        is.close();
        return result;
    }

}
