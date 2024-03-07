package com.lseg.testframework.utils.db;

import com.lseg.config.TestConfig;
import com.lseg.product.api.rest.data.Page;
import com.lseg.product.api.rest.data.db.TcrData;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DBManager extends TestConfig {

    public static List<Page> fetchTcrCollectionFromDB(String id) throws SQLException {
        List<Page> result = new ArrayList<>();
        String query = "SELECT * FROM CNA.TCR_CORRECTIONS WHERE ID=" + id;
        DBConnection dbInstance = DBConnection.getInstance(DB_URL, DB_USER, DB_PASSWORD);

        log.info("Execute SQL query " + query);
        try (Connection connection = dbInstance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Page page = new Page();
                page.setTradeId(resultSet.getString("TRADE_ID"));
                page.setTradeDate(resultSet.getString("TRADE_DATE"));
                page.setProductCode(resultSet.getString("PRODUCT_CODE"));
                page.setCurrencyPair(resultSet.getString("CURRENCY_PAIR"));
                page.setCounterparty1Name(resultSet.getString("COUNTERPARTY1_NAME"));
                page.setCounterparty2Name(resultSet.getString("COUNTERPARTY2_NAME"));
                page.setRegulatoryRegime(resultSet.getString("REGULATORY_REGIME"));
                page.setCreditParadigm(resultSet.getString("CREDIT_PARADIGM"));
                page.setActionCode(resultSet.getString("ACTION_CODE"));
                page.setStatus(resultSet.getString("STATUS_CODE"));
                page.setCategory(resultSet.getString("CATEGORY_CODE"));
                page.setCategory(resultSet.getString("MODIFIED_BY"));
                page.setTradingRoomClearance(resultSet.getString("TRADING_ROOM_CLEARANCE_CODE"));
                page.setExecutedSize(resultSet.getBigDecimal("EXECUTED_SIZE"));

                result.add(page);

                System.out.println("TRADE_ID:" + resultSet.getString("TRADE_ID") +
                        "\nTRADE_DATE:" + resultSet.getString("TRADE_DATE") +
                        "\nPRODUCT_CODE:" + resultSet.getString("PRODUCT_CODE") +
                        "\nCURRENCY_PAIR:" + resultSet.getString("CURRENCY_PAIR") +
                        "\nCOUNTERPARTY1_NAME:" + resultSet.getString("COUNTERPARTY1_NAME") +
                        "\nCOUNTERPARTY2_NAME:" + resultSet.getString("COUNTERPARTY2_NAME") +
                        "\nREGULATORY_REGIME:" + resultSet.getString("REGULATORY_REGIME") +
                        "\nCREDIT_PARADIGM:" + resultSet.getString("CREDIT_PARADIGM") +
                        "\nACTION_CODE:" + resultSet.getString("ACTION_CODE") +
                        "\nSTATUS_CODE:" + resultSet.getString("STATUS_CODE") +
                        "\nCATEGORY_CODE:" + resultSet.getString("CATEGORY_CODE") +
                        "\nMODIFIED_BY:" + resultSet.getString("MODIFIED_BY") +
                        "\nTRADING_ROOM_CLEARANCE_CODE:" + resultSet.getString("TRADING_ROOM_CLEARANCE_CODE") +
                        "\nEXECUTED_SIZE:" + resultSet.getString("EXECUTED_SIZE"));
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<TcrData> fetchTcrDataFromDB(String id) throws SQLException {
        List<TcrData> result = new ArrayList<>();
        String query = "SELECT * FROM CNA.TCR_DATA WHERE TCR_CORRECTION_ID=" + id;
        DBConnection dbInstance = DBConnection.getInstance(DB_URL, DB_USER, DB_PASSWORD);

        log.info("Execute SQL query " + query);
        try (Connection connection = dbInstance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TcrData tcrData = new TcrData();
                tcrData.setId(resultSet.getInt("ID"));
                tcrData.setTcrCorrectionId(resultSet.getInt("TCR_CORRECTION_ID"));
                tcrData.setTcrCorrectionTypeCode(resultSet.getString("TCR_CORRECTION_TYPE_CODE"));
                tcrData.setTcrCorrectionSubtypeCode(resultSet.getString("TCR_CORRECTION_SUBTYPE_CODE"));
                tcrData.setDataJson(resultSet.getString("DATA_JSON"));
                tcrData.setCreatedBy(resultSet.getString("CREATED_BY"));
                tcrData.setModifiedBy(resultSet.getString("MODIFIED_BY"));
                tcrData.setVersion(resultSet.getInt("VERSION"));

                result.add(tcrData);

                System.out.println("ID:" + resultSet.getString("ID") +
                        "\nTCR_CORRECTION_ID:" + resultSet.getString("TCR_CORRECTION_ID") +
                        "\nTCR_CORRECTION_TYPE_CODE:" + resultSet.getString("TCR_CORRECTION_TYPE_CODE") +
                        "\nTCR_CORRECTION_SUBTYPE_CODE:" + resultSet.getString("TCR_CORRECTION_SUBTYPE_CODE") +
                        "\nDATA_JSON:" + resultSet.getString("DATA_JSON") +
                        "\nCREATED_BY:" + resultSet.getString("CREATED_BY") +
                        "\nMODIFIED_BY:" + resultSet.getString("MODIFIED_BY") +
                        "\nVERSION:" + resultSet.getString("VERSION"));
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
