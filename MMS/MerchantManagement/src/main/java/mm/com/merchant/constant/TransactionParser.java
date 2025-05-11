package mm.com.merchant.constant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionParser {
    public static void main(String[] args) {
        String line = "MER1|FNFR|00218923|7997|5089147079006013|0210|220205|220205|00073800|203600521194|102110|707910100077513|36.00|S|00.00|001|00|5|PAYTM|PAYTM|Noida|XXX|IN|00000300012|00000000000|356|59|810|5399|****|000738|POS1";
        
        // Split the line using the pipe delimiter
        String[] fields = line.split("\\|");

        // Check if the number of fields matches the expected count
        if (fields.length != 32) {
            System.err.println("Unexpected number of fields: " + fields.length);
            return;
        }

        // Extract fields
        String merchant_id = fields[0];
        String record_type = fields[1];
        String transaction_id = fields[2];
        String merchant_code = fields[3];
        String card_number = fields[4];
        String expiry_date = fields[5];
        String tran_date = fields[6];
        String settle_Date = fields[7];
        String amount_cents = fields[8];
        String unique_id = fields[9];
        String product_code = fields[10];
        String reference_number = fields[11];
        String tran_amount = fields[12];
        String status = fields[13];
        String discount = fields[14];
        String tran_type = fields[15];
        String flag = fields[16];
        String item_count = fields[17];
        String processor = fields[18];
        String merchant_name = fields[19];
        String location = fields[20];
        String placeholder = fields[21];
        String country_code = fields[22];
        String customer_id = fields[23];
        String zeroplaceholder = fields[24];
        String code1 = fields[25];
        String code2 = fields[26];
        String code3 = fields[27];
        String code4 = fields[28];
        String redacted = fields[29];
        String code5 = fields[30];
        String pos_id=fields[31];

        // Database connection details
        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=atm_db";
        String user = "postgres";
        String password = "postgres";

        // Create SQL insert statement
        String sql = String.format(
            "INSERT INTO mer_pos_transactions(merchant_id,record_type, transaction_id,merchant_code, card_number, expiry_date, tran_date, settle_date, amount_cents, unique_id, product_code, reference_number, tran_amount, status, discount, tran_type, flag, item_count, processor, merchant_name, location, placeholder, country_code, customer_id, zeroplaceholder, code1, code2, code3, code4, redacted, code5,pos_id) " +
            "VALUES ('%s','%s', '%s', '%s', '%s', '%s', '%s', '%s', %s, '%s', '%s', '%s', %s, '%s', %s, '%s', '%s', %s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
            merchant_id,record_type, transaction_id,merchant_code , card_number, expiry_date, tran_date, settle_Date, amount_cents, unique_id, product_code, reference_number, tran_amount, status, discount, tran_type, flag, item_count, processor, merchant_name, location, placeholder, country_code, customer_id, zeroplaceholder, code1, code2, code3, code4, redacted, code5,pos_id);

        // Execute the insert statement
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
             
            stmt.executeUpdate(sql);
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
