package dbutils;

import models.Laptop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Мадина on 22.11.2014.
 */
public class DBUtilLaptop {
    public static String url = "jdbc:derby://localhost:1527/MyDbTest";
    public static String username = "app";
    public static String password = "app";

    public List<Laptop> findAll() {
        List<Laptop> list = new ArrayList<Laptop>();
        String sql = "SELECT * FROM laptop ORDER BY brandname";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(new Laptop(rs.getInt("id"),
                                    rs.getString("brandname"),
                                    rs.getString("processor"),
                                    rs.getDouble("diagonal"),
                                    rs.getString("ram"),
                                    rs.getString("hdd"),
                                    rs.getString("os"),
                                    rs.getString("picture")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }


    public List<Laptop> findByName(String name) {
        List<Laptop> list = new ArrayList<Laptop>();
        String sql = "SELECT * FROM laptop as e " +
                "WHERE UPPER(BRANDNAME) LIKE ? " +
                "ORDER BY BRANDNAME";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public Laptop findById(int id) {
        String sql = "SELECT * FROM laptop WHERE id = ?";
        Laptop laptop = null;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                laptop = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return laptop;
    }

    public Laptop save(Laptop laptop) {
        return laptop.getId() > 0 ? update(laptop) : create(laptop);
    }

    public Laptop create(Laptop laptop) {
        PreparedStatement ps = null;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            ps = connection.prepareStatement("INSERT INTO laptop (id, brandname, processor, diagonal, ram, hdd, os, picture) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    new String[] { "ID" });
            ps.setString(2, laptop.getBrandName());
            ps.setString(3, laptop.getProcessor());
            ps.setDouble(4, laptop.getDiagonal());
            ps.setString(5, laptop.getRam());
            ps.setString(6, laptop.getHDD());
            ps.setString(7, laptop.getOs());
            ps.setString(8, laptop.getPicture());
            ps.setLong(1, System.currentTimeMillis()/1000L);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return laptop;
    }

    public Laptop update(Laptop laptop) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE laptop SET brandname=?, processor=?, diagonal=?, ram=?, hdd=?," +
                            " os=?, picture=? WHERE id=?");
            ps.setString(1, laptop.getBrandName());
            ps.setString(2, laptop.getProcessor());
            ps.setDouble(3, laptop.getDiagonal());
            ps.setString(4, laptop.getRam());
            ps.setString(5, laptop.getHDD());
            ps.setString(6, laptop.getOs());
            ps.setString(7, laptop.getPicture());
            ps.setInt(8, laptop.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return laptop;
    }

    public boolean remove(int id) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM laptop WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected Laptop processRow(ResultSet rs) throws SQLException {
        Laptop laptop = new Laptop();
        laptop.setId(rs.getInt("id"));
        laptop.setBrandName(rs.getString("brandname"));
        laptop.setDiagonal(rs.getDouble("diagonal"));
        laptop.setProcessor(rs.getString("processor"));
        laptop.setRam(rs.getString("ram"));
        laptop.setHDD(rs.getString("hdd"));
        laptop.setOs(rs.getString("os"));
        laptop.setPicture(rs.getString("picture"));
        return laptop;
    }
}
