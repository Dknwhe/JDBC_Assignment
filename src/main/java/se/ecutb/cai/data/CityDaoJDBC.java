package se.ecutb.cai.data;

import se.ecutb.cai.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static se.ecutb.cai.data.DataBase.getConnection;

public class CityDaoJDBC implements CityDao {




    private City create_CityFromResultSet(ResultSet resultSet) throws SQLException {
        return new City(
                resultSet.getInt("ID"),
                resultSet.getString("Name"),
                resultSet.getString("CountryCode"),
                resultSet.getString("District"),
                resultSet.getInt("Population"));
    }

    private static final String FIND_BY_ID = "SELECT * FROM city WHERE ID = ? ";

    @Override
    public City findById(int id) {
       City city = null;
       try(Connection connection = getConnection();
            PreparedStatement statement = create_FindById(connection,id);
            ResultSet resultSet = statement.executeQuery()) {
           while (resultSet.next()) {
               city = create_CityFromResultSet(resultSet);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return city;
    }

    private PreparedStatement create_FindById(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
        preparedStatement.setInt(1,id);
        return preparedStatement;
    }



    private static final String FIND_BY_COUNTRY_CODE = "SELECT * FROM city WHERE CountryCode = ?";

    @Override
    public List<City> findByCode(String code) {
        List<City> cityList = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = create_FindByCode(connection, code);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                cityList.add(create_CityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    private PreparedStatement create_FindByCode(Connection connection, String code) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_COUNTRY_CODE);
        preparedStatement.setString(1,code);
        return preparedStatement;
    }


    private static final String FIND_BY_NAME = "SELECT * FROM city WHERE Name = ?";

    @Override
    public List<City> findByName(String name) {
        List<City> cityList = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = create_FindByName(connection, name);
            ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                cityList.add(create_CityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    private PreparedStatement create_FindByName(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
        preparedStatement.setString(1,name);
        return preparedStatement;
    }


    private static final String FIND_BY_ALL = "SELECT * FROM city";

    @Override
    public List<City> findAll() {
        List<City> cityList = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = create_FindAll(connection);
            ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                cityList.add(create_CityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    private PreparedStatement create_FindAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ALL);
        return preparedStatement;
    }


    private static final String INSERT = "INSERT INTO city(Name,CountryCode,District,Population) VALUES(?,?,?,?)";

    @Override
    public City add(City city) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                city = new City(resultSet.getInt(1), city.getName(), city.getCountryCode(), city.getDistrict(), city.getPopulation());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if (resultSet !=null) {
                    resultSet.close();
                }
                if (preparedStatement !=null) {
                    preparedStatement.close();
                }
                if (connection !=null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return city;
    }

    private static final String UPDATE_CITY = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";

    @Override
    public City update(City city) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CITY))
        {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getCityId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }


    private static final String DELETE_CITY = "DELETE FROM city WHERE ID = ?";

    @Override
    public int delete(City city) {
        int delete = 0;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITY)) {
            preparedStatement.setInt(1, city.getCityId());
            delete = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return delete;
    }
}
