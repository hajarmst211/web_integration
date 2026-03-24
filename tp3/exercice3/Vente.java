import java.io.Serializable;
import java.util.Date;

public class Vente implements Serializable {
    public int saleId;
    public String product, region;
    public double amount;
    public Date date;

    public Vente(int saleId, String product, String region, double amount, Date date) {
        this.saleId = saleId; this.product = product; this.region = region;
        this.amount = amount; this.date = date;
    }
}