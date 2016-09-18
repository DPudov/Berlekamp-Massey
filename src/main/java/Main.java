import model.Encoder;
import model.utils.BytesUtil;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by ${DPudov} on 11.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        Date d1 = new Date();
        byte[] bytes = BytesUtil.getFileBytes("C:\\Users\\DPudov\\Git\\ReleaseNotes.html");

        Encoder.getFunctionFeedback(Encoder.useBerlekampMassey(Arrays.copyOfRange(bytes, 0, 255)));
        Date d2 = new Date();
        System.out.println((d2.getTime() - d1.getTime()) + " success");
    }


}
