package db.migration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import com.java.exercise.jpay.constants.CountriesStore;
import com.java.exercise.jpay.utils.PhoneNumberValidationUtils;

public class V3__insert_phone_numbers_data extends BaseJavaMigration {
  private final static String SELECT_ALL_CUSTOMERS_QUERY = "select phone from customer";
  private final static String PHONE_NUMBERS_INSERTION_QUERY = "insert into phone_number (`number`, `country_code`, `valid`) values (?, ?, ?)";

  @Override
  public void migrate(Context context) throws Exception {
    Connection connection = context.getConnection();
    ResultSet customersPhones = connection.createStatement().executeQuery(SELECT_ALL_CUSTOMERS_QUERY);
    List<String> phoneNumbersText = new ArrayList<>();
    while (customersPhones.next()) {
      phoneNumbersText.add(customersPhones.getString(1));
    }
    PreparedStatement insertionPreparedStmt = connection.prepareStatement(PHONE_NUMBERS_INSERTION_QUERY);
    phoneNumbersText.forEach(phoneNumber -> {
      Integer countryCode = PhoneNumberValidationUtils.findCountryCode(phoneNumber);
      try {
        insertionPreparedStmt.setString(1, phoneNumber);
        insertionPreparedStmt.setInt(2, countryCode);
        insertionPreparedStmt.setBoolean(3, Pattern.matches(CountriesStore.COUNTRIES_STATIC_INFO.get(countryCode).getValidationRegex(), phoneNumber));
        insertionPreparedStmt.addBatch();
      } catch (SQLException e) {
        Logger.getGlobal().log(Level.WARNING, "Error while setting phone numbers statement parameters.");
      }

    });
    insertionPreparedStmt.executeBatch();
  }

}
