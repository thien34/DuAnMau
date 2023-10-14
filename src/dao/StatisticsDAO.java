package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

public class StatisticsDAO {

    String NGUOI_HOC_SQL = "{call sp_ThongKeNguoiHoc}";
    String BANG_DIEM_SQL = "{call sp_BangDiem (?) }";
    String TONG_HOP_DIEM_SQL = "{call sp_TongHopDiem}";
    String DOANH_THU_SQL = "{call sp_DoanhThu(?)}";

    public List<Object[]> nguoiHoc() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(NGUOI_HOC_SQL);
                while (rs.next()) {
                    Object[] model = {
                        rs.getInt("Nam"),
                        rs.getInt("SoLuong"),
                        rs.getString("MIN"),
                        rs.getString("MAX")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private String xepLoai(Double diem) {
        String xepLoai;
        if (diem < 0) {
            xepLoai = "Chưa Nhập";
        } else if (diem < 3) {
            xepLoai = "Kém";
        } else if (diem < 5) {
            xepLoai = "Trung Bình";
        } else if (diem < 7) {
            xepLoai = "Khá";
        } else if (diem < 9) {
            xepLoai = "Giỏi";
        } else {
            xepLoai = "Xuất sắc";
        }
        return xepLoai;
    }

    public List<Object[]> bangDiem(String IDCourse) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(BANG_DIEM_SQL, IDCourse);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        xepLoai(rs.getDouble(3))
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Object[]> tongDiem() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(TONG_HOP_DIEM_SQL);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("ChuyenDe"),
                        rs.getInt("TongHV"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("TrungBinh")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Object[]> doanhThu(int year) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(DOANH_THU_SQL, year);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("ChuyenDe"),
                        rs.getInt("SoKhoa"),
                        rs.getInt("SoHV"),
                        rs.getDouble("DoanhThu"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("TrungBinh")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
