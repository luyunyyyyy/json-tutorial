import Util.LeptContext;
import Util.ResultType;

import static Util.LeptType.LEPT_FALSE;
import static Util.LeptType.LEPT_NULL;
import static Util.LeptType.LEPT_TRUE;
import static Util.ResultType.*;

public class LeptImpl implements LeptJson{
    @Override
    public ResultType leptParse(LeptValue leptValue, String json) {

        assert leptValue != null;
        LeptContext leptContext = new LeptContext(json);
        leptValue.leptType = LEPT_NULL;
        leptParseWhitespace(leptContext);
        ResultType result = leptParseValue(leptContext, leptValue);
        if(result == LEPT_PARSE_OK){
            leptParseWhitespace(leptContext);
            if(leptContext.getJson().length()!=0){

                result =  LEPT_PARSE_ROOT_NOT_SINGULAR;
            }
        }
        return result;

    }

    private static void leptParseWhitespace(LeptContext leptContext){
        char[] json = leptContext.getJson().toCharArray();
        int i = 0;
//        while(json[i] == ' ' || json[i] == '\t' || json[i] == '\n' || json[i] == '\r'){
//            i ++;
//        }
//        for(Character character : json){
//            if(json[i] == ' ' || json[i] == '\t' || json[i] == '\n' || json[i] == '\r'){
//                i++;
//            }else{
//                return
//            }
//        }
        for(i = 0 ; i < json.length ; i ++){
            if(json[i] == ' ' || json[i] == '\t' || json[i] == '\n' || json[i] == '\r'){

            }else{
                break;
            }
        }
        leptContext.setJson(leptContext.getJson().substring(i));
    }
    private static ResultType leptParseNull(LeptContext leptContext, LeptValue leptValue){
        char[] json = leptContext.getJson().toCharArray();
        assert json[0] == 'n';
        if(!"ull".equals(leptContext.getJson().substring(1,4))){
            return LEPT_PARSE_INVALID_VALUE;
        }
        leptContext.setJson(leptContext.getJson().substring(4));
        leptValue.leptType = LEPT_NULL;
        return LEPT_PARSE_OK;
    }
    private static ResultType leptParseFalse(LeptContext leptContext, LeptValue leptValue){
        char[] json = leptContext.getJson().toCharArray();
        assert json[0] == 'f';
        if(!"alse".equals(leptContext.getJson().substring(1,5))){
            return LEPT_PARSE_INVALID_VALUE;
        }
        leptContext.setJson(leptContext.getJson().substring(5));
        leptValue.leptType = LEPT_FALSE;
        return LEPT_PARSE_OK;
    }
    private static ResultType leptParseTrue(LeptContext leptContext, LeptValue leptValue){
        char[] json = leptContext.getJson().toCharArray();
        assert json[0] == 't';
        if(!"rue".equals(leptContext.getJson().substring(1,4))){
            return LEPT_PARSE_INVALID_VALUE;
        }
        leptContext.setJson(leptContext.getJson().substring(4));
        leptValue.leptType = LEPT_TRUE;
        return LEPT_PARSE_OK;
    }
    private ResultType leptParseValue(LeptContext leptContext, LeptValue leptValue){
        switch (leptContext.getJson().toCharArray()[0]){
            case 'n':
                return leptParseNull(leptContext, leptValue);
            case 'f':
                return leptParseFalse(leptContext, leptValue);
            case 't' :
                return leptParseTrue(leptContext, leptValue);
            case '\0':
                return LEPT_PARSE_EXPECT_VALUE;
            default:
                return LEPT_PARSE_INVALID_VALUE;
        }
    }
}
