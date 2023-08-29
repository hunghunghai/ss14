package ra.service;

import ra.model.Product;
import ra.ultil.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IGenericService<Product, Integer> {

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        CallableStatement callSt = null;
        try {
            connection = ConnectDB.getConnection();
            callSt = connection.prepareCall("{call get_Product}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setUrlFile(rs.getString("urlFile"));
                product.setStatus(rs.getBoolean("status"));
                products.add(product);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return products;
    }

    @Override
    public void save(Product product) {
        Connection connection = ConnectDB.getConnection();
        try {
            if (product.getId() == 0) {
                CallableStatement callSt = connection.prepareCall("{call add_product(?,?,?,?,?)}");
                callSt.setString(1, product.getName());
                callSt.setString(2, product.getDescription());
                callSt.setDouble(3, product.getPrice());
                callSt.setInt(4, product.getQuantity());
                callSt.setString(5, product.getUrlFile());
                callSt.executeUpdate();
            } else {
                CallableStatement callSt = connection.prepareCall("{call update_product(?,?,?,?,?,?)}");
                callSt.setLong(1, product.getId());
                callSt.setString(2, product.getName());
                callSt.setString(3, product.getDescription());
                callSt.setDouble(4, product.getPrice());
                callSt.setInt(5, product.getQuantity());
                callSt.setString(6, product.getUrlFile());
                callSt.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
    }

    @Override
    public Product findById(Integer id) {
        Connection connection = ConnectDB.getConnection();
        Product product = new Product();
        try {
           CallableStatement callSt =connection.prepareCall("{call findById(?)}");
           callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setUrlFile(rs.getString("urlFile"));
                product.setStatus(rs.getBoolean("status"));
                return product;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = ConnectDB.getConnection();
        try {
            CallableStatement callSt = connection.prepareCall("{call delete_product(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
    }

    public List<Product> searchByName(String name) {
        Connection connection = ConnectDB.getConnection();
        List<Product> listSearch = new ArrayList<>();
        try {
            CallableStatement callSt = connection.prepareCall("{call searchByName(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setUrlFile(rs.getString("urlFile"));
                product.setStatus(rs.getBoolean("status"));
                listSearch.add(product);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return listSearch;
    }
}
