package se.ecutb.cai;

import se.ecutb.cai.data.CityDaoJDBC;
import se.ecutb.cai.data.DataBase;
import se.ecutb.cai.model.City;

import java.sql.SQLException;

public class Run {

    public static void main(String[] args) throws SQLException {
        DataBase.getConnection();
        CityDaoJDBC dao = new CityDaoJDBC();

    }
}
