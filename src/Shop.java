import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 2jart on 01.12.2016.
 */
public class Shop {
    public Shop(String name) {
        this.name = name;
    }

    private String name;

    public Map<Calc, Integer> getSaleCalcs() {
        return saleCalcs;
    }

    private Map<Calc, Integer> saleCalcs = new HashMap<>();

    public void addSale(String code, int count){
        Calc calcForSale = CalcsEntry.getCalcs().get(code);
        if (saleCalcs.containsKey(calcForSale)) count += saleCalcs.get(calcForSale);
        saleCalcs.put(calcForSale, count);
    }
}
