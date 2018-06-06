package src;

import Util.ResultType;

public interface LeptJson {
    ResultType leptParse(LeptValue leptValue, String json);
}
