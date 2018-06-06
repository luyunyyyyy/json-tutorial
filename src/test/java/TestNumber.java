import org.junit.Test;

import static Util.LeptType.LEPT_NULL;
import static Util.ResultType.LEPT_PARSE_INVALID_VALUE;
import static Util.ResultType.LEPT_PARSE_ROOT_NOT_SINGULAR;
import static Util.TestUtil.TEST_NUMBER;
import static org.junit.Assert.assertEquals;
import static Util.TestUtil.TEST_ERROR;

public class TestNumber {
    @Test
    public void TestNumber() {
        TEST_NUMBER(0.0, "0");
        TEST_NUMBER(0.0, "-0");
        TEST_NUMBER(0.0, "-0.0");
        TEST_NUMBER(1.0, "1");
        TEST_NUMBER(-1.0, "-1");
        TEST_NUMBER(1.5, "1.5");
        TEST_NUMBER(-1.5, "-1.5");
        TEST_NUMBER(3.1416, "3.1416");
        TEST_NUMBER(1E10, "1E10");
        TEST_NUMBER(1e10, "1e10");
        TEST_NUMBER(1E+10, "1E+10");
        TEST_NUMBER(1E-10, "1E-10");
        TEST_NUMBER(-1E10, "-1E10");
        TEST_NUMBER(-1e10, "-1e10");
        TEST_NUMBER(-1E+10, "-1E+10");
        TEST_NUMBER(-1E-10, "-1E-10");
        TEST_NUMBER(1.234E+10, "1.234E+10");
        TEST_NUMBER(1.234E-10, "1.234E-10");
        TEST_NUMBER(0.0, "1e-10000"); /* must underflow */
    }
    @Test
    public void TestError(){
        TEST_ERROR(LEPT_PARSE_INVALID_VALUE, "+0");
        TEST_ERROR(LEPT_PARSE_INVALID_VALUE, "+1");
        TEST_ERROR(LEPT_PARSE_INVALID_VALUE, ".123"); /* at least one digit before '.' */
        TEST_ERROR(LEPT_PARSE_INVALID_VALUE, "1.");   /* at least one digit after '.' */
        TEST_ERROR(LEPT_PARSE_INVALID_VALUE, "INF");
        TEST_ERROR(LEPT_PARSE_INVALID_VALUE, "inf");
        TEST_ERROR(LEPT_PARSE_INVALID_VALUE, "NAN");
        TEST_ERROR(LEPT_PARSE_INVALID_VALUE, "nan");
    }

}
