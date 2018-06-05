import Util.LeptType;

public class LeptValue {
    public LeptValue() {
    }

    public LeptType getLeptType() {
        return leptType;
    }

    public void setLeptType(LeptType leptType) {
        this.leptType = leptType;
    }

    public LeptValue(LeptType leptType) {

        this.leptType = leptType;
    }

    LeptType leptType;

}
