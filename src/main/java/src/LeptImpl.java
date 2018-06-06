package src;

import Util.LeptContext;
import Util.LeptType;
import Util.ResultType;

import java.util.regex.Pattern;

import static Util.LeptType.*;
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
                // 解析数字的时候 这个地方出现了问题 导致返回值出错

                result =  LEPT_PARSE_ROOT_NOT_SINGULAR;
            }
        }
        return result;

    }

    private static void leptParseWhitespace(LeptContext leptContext){
        char[] json = leptContext.getJson().toCharArray();
        int i;
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
            if (json[i] != ' ' && json[i] != '\t' && json[i] != '\n' && json[i] != '\r') {
                break;
            }
        }
        leptContext.setJson(leptContext.getJson().substring(i));
    }
    private static ResultType leptParse(LeptContext leptContext, LeptValue leptValue, String parseStr, LeptType type){
        char[] json = leptContext.getJson().toCharArray();
        char[] parseChars = parseStr.toCharArray();
        assert json[0] == parseChars[0];

        if(json.length < parseChars.length || !parseStr.substring(1).equals(leptContext.getJson().substring(1,parseStr.length()))){
            return LEPT_PARSE_INVALID_VALUE;
        }
        leptContext.setJson(leptContext.getJson().substring(parseStr.length()));
        leptValue.leptType = type;
        return LEPT_PARSE_OK;
    }

    private static ResultType leptParseNumber(LeptContext leptContext, LeptValue leptValue){
        String json = leptContext.getJson();
        Pattern pattern = Pattern.compile("^-?(0|[1-9][0-9]*)(\\.[0-9]+)?(([eE])([-+])?[0-9]*)?$");
        if(pattern.matcher(json).find()){
            leptValue.setN(Double.parseDouble(json));
            leptValue.setLeptType(LEPT_NUMBER);
            leptContext.setJson(leptContext.getJson().substring(json.length()));
            return LEPT_PARSE_OK;
        }else
            return LEPT_PARSE_INVALID_VALUE;

    }
//    private static ResultType leptParseNull(LeptContext leptContext, src.LeptValue leptValue){
//        char[] json = leptContext.getJson().toCharArray();
//        assert json[0] == 'n';
//        if(!"ull".equals(leptContext.getJson().substring(1,4))){
//            return LEPT_PARSE_INVALID_VALUE;
//        }
//        leptContext.setJson(leptContext.getJson().substring(4));
//        leptValue.leptType = LEPT_NULL;
//        return LEPT_PARSE_OK;
//    }
//    private static ResultType leptParseFalse(LeptContext leptContext, src.LeptValue leptValue){
//        char[] json = leptContext.getJson().toCharArray();
//        assert json[0] == 'f';
//        if(!"alse".equals(leptContext.getJson().substring(1,5))){
//            return LEPT_PARSE_INVALID_VALUE;
//        }
//        leptContext.setJson(leptContext.getJson().substring(5));
//        leptValue.leptType = LEPT_FALSE;
//        return LEPT_PARSE_OK;
//    }
//    private static ResultType leptParseTrue(LeptContext leptContext, src.LeptValue leptValue){
//        char[] json = leptContext.getJson().toCharArray();
//        assert json[0] == 't';
//        if(!"rue".equals(leptContext.getJson().substring(1,4))){
//            return LEPT_PARSE_INVALID_VALUE;
//        }
//        leptContext.setJson(leptContext.getJson().substring(4));
//        leptValue.leptType = LEPT_TRUE;
//        return LEPT_PARSE_OK;
//    }
    private ResultType leptParseValue(LeptContext leptContext, LeptValue leptValue){
        switch (leptContext.getJson().toCharArray()[0]){
            case 'n':
                return leptParse(leptContext, leptValue, "null", LEPT_NULL);
            case 'f':
                return leptParse(leptContext, leptValue, "false", LEPT_FALSE);
            case 't' :
                return leptParse(leptContext, leptValue, "true", LEPT_TRUE);
            case '\0':
                return LEPT_PARSE_EXPECT_VALUE;
            default:   return leptParseNumber(leptContext, leptValue);

        }
    }
}
