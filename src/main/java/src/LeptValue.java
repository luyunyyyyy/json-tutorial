package src;

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

    public double getN() {
        assert this.leptType == LeptType.LEPT_NUMBER;
        return n;
//        double lept_get_number(const lept_value* v) {
//            assert(v != NULL && v->type == LEPT_NUMBER);
//            return v->n;
//        }
    }

    public void setN(double n) {
        this.n = n;
    }

    private double n;
    LeptType leptType;

}
