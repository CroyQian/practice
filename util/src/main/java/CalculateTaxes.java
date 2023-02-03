import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author Croy Qian
 * @createDate 2022/6/24
 * @Description TODO
 */
public class CalculateTaxes {
    public static double calculateTaxes2(double charge, double rate) {
        double taxes = (charge / (1 + rate)) * rate;
        return new BigDecimal(taxes).setScale(4, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static void main(String[] args) {
        double amountYuan = new BigDecimal((double) Math.abs(75590004) / 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        //这里计算保留了4位小数，且下整
        double taxAmountYuan = calculateTaxes2(amountYuan, 0d);
        double notTaxAmountYuan =  new BigDecimal(amountYuan - taxAmountYuan).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        System.out.println(decimalFormat.format(taxAmountYuan));
        System.out.println(decimalFormat.format(amountYuan));
        System.out.println(decimalFormat.format(notTaxAmountYuan));
//        System.out.println(decimalFormat.format(amountYuan - taxAmountYuan));
//        System.out.println(decimalFormat.format(new BigDecimal(94005.28d).doubleValue()));
        System.out.println(decimalFormat.format(new BigDecimal(755900.04d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
        System.out.println((long)(calculateTaxes2(3000d, 0.06d) + 0.5));

        String a = "0999-90-09";
        System.out.println(a.matches("^[0-9]*$"));
        System.out.println(a.matches("^[1-9]([0-9]{3})-[0-9]{2}-[0-9]{2}"));
        System.out.println(a.matches("[a-zA-Z]{4}"));
    }
}
