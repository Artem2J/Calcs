/**
 * Created by 2jart on 30.11.2016.
 */
public class Calc {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Calc(String code, String name, int namber) {
        this.code = code;

        this.name = name;
        this.namber = namber;
    }
    private String code;
    private String name;

    public int getNamber() {
        return namber;
    }

    public void setNamber(int namber) {
        this.namber = namber;
    }

    private int namber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
