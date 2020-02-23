package se.ecutb.cai;

import se.ecutb.cai.data.CityDaoJDBC;
import se.ecutb.cai.data.DataBase;
import se.ecutb.cai.model.City;

import java.sql.SQLException;

public class Run {

    public static void main(String[] args) throws SQLException {


        CityDaoJDBC dao = new CityDaoJDBC();

        City city1 = new City("AA", "AAA","AAA", 123);

        //System.out.println(dao.findById(1));
        //System.out.println(dao.findByCode("AFG"));
        //System.out.println(dao.findByName("Breda"));
        //System.out.println(dao.findAll());
        //System.out.println(dao.add(city1));
        //System.out.println(dao.update(city1));
        //System.out.println(dao.delete(city1));


    }
}
