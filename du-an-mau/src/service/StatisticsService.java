package service;

import dao.StatisticsDAO;
import java.util.List;

public class StatisticsService {

    public List<Object[]> nguoiHoc() {
        return new StatisticsDAO().nguoiHoc();
    }

    public List<Object[]> bangDiem(String IDCourse) {
        return new StatisticsDAO().bangDiem(IDCourse);
    }

    public List<Object[]> tongDiem() {
        return new StatisticsDAO().tongDiem();
    }

    public List<Object[]> doanhThu(Integer year) {
        return new StatisticsDAO().doanhThu(year);
    }
}
