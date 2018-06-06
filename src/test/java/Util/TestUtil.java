package Util;
import org.junit.Assert;
import src.LeptImpl;
import src.LeptJson;
import src.LeptValue;

import static Util.LeptType.LEPT_NULL;
import static Util.LeptType.LEPT_NUMBER;
import static Util.ResultType.LEPT_PARSE_OK;
import static Util.ResultType.LEPT_PARSE_ROOT_NOT_SINGULAR;

public class TestUtil {
    public static void TEST_ERROR(ResultType type, String json){
        LeptJson leptJson = new LeptImpl();
        LeptValue leptValue = new LeptValue();
        Assert.assertEquals(leptJson.leptParse(leptValue, json), type);
        Assert.assertEquals(leptValue.getLeptType(), LEPT_NULL); // 默认是 null
    }
    public static void TEST_NUMBER(double expect, String json){
        LeptJson leptJson = new LeptImpl();
        LeptValue leptValue = new LeptValue();
        Assert.assertEquals(leptJson.leptParse(leptValue, json), LEPT_PARSE_OK);
        Assert.assertEquals(leptValue.getLeptType(), LEPT_NUMBER);
        Assert.assertEquals(leptValue.getN(), expect, 0.000001);

    }

}
