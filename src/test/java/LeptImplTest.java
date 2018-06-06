import Util.TestUtil;
import org.junit.Test;
import src.LeptImpl;
import src.LeptJson;
import src.LeptValue;

import static Util.LeptType.LEPT_FALSE;
import static Util.LeptType.LEPT_NULL;
import static Util.LeptType.LEPT_TRUE;
import static Util.ResultType.LEPT_PARSE_INVALID_VALUE;
import static Util.ResultType.LEPT_PARSE_OK;
import static Util.ResultType.LEPT_PARSE_ROOT_NOT_SINGULAR;
import static org.junit.Assert.*;

public class LeptImplTest {

    @Test
    public void leptParse() {
        LeptJson leptJson = new LeptImpl();
        LeptValue leptValue = new LeptValue();
        assertEquals(leptJson.leptParse(leptValue, "null x"), LEPT_PARSE_ROOT_NOT_SINGULAR);
        assertEquals(leptValue.getLeptType(), LEPT_NULL);

        assertEquals(leptJson.leptParse(leptValue, "null"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_NULL);
//        assertEquals(leptJson.leptParse(leptValue, "nuull"), LEPT_PARSE_INVALID_VALUE);
//        assertEquals(leptValue.getLeptType(), LEPT_NULL); // 默认是 null
        TestUtil.TEST_ERROR(LEPT_PARSE_INVALID_VALUE, "nuull");
        assertEquals(leptJson.leptParse(leptValue, " null"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_NULL);
        assertEquals(leptJson.leptParse(leptValue, "    null"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_NULL);
        assertEquals(leptJson.leptParse(leptValue, "\nnull"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_NULL);

        assertEquals(leptJson.leptParse(leptValue, "false"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_FALSE);
        assertEquals(leptJson.leptParse(leptValue, "fallse"), LEPT_PARSE_INVALID_VALUE);
        assertEquals(leptValue.getLeptType(), LEPT_NULL); // 默认是 null
        assertEquals(leptJson.leptParse(leptValue, " false"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_FALSE);
        assertEquals(leptJson.leptParse(leptValue, "    false"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_FALSE);
        assertEquals(leptJson.leptParse(leptValue, "\nfalse"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_FALSE);

        assertEquals(leptJson.leptParse(leptValue, "true"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_TRUE);
        assertEquals(leptJson.leptParse(leptValue, "tuure"), LEPT_PARSE_INVALID_VALUE);
        assertEquals(leptValue.getLeptType(), LEPT_NULL); // 默认是 null
        assertEquals(leptJson.leptParse(leptValue, " true"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_TRUE);
        assertEquals(leptJson.leptParse(leptValue, "    true"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_TRUE);
        assertEquals(leptJson.leptParse(leptValue, "\ntrue"), LEPT_PARSE_OK);
        assertEquals(leptValue.getLeptType(), LEPT_TRUE);
    }
}