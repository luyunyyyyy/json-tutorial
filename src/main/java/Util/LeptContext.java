package Util;

public class LeptContext {
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    private String json;

    public LeptContext(String json) {
        this.json = json;
    }
}
