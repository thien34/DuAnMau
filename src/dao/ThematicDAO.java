package dao;

import entity.Thematic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

public class ThematicDAO implements MethodDAO<Thematic, String> {

    String GET_ALL = "Select * From Thematic";
    String GET_BY_ID = "Select * From Thematic Where ID = ?";
    String INSERT = "Insert Thematic(ID, ThematicName, Tuition, Time, Image, Description) VALUES (?, ?, ?, ?, ?, ?)";
    String UPDATE = "Update Thematic Set ThematicName = ?, Tuition = ?, Time = ?, Image = ?, Description = ? WHERE ID = ?";
    String DELETE = "Delete Thematic WHERE ID = ?";
    String SEARCH = "Select * From Thematic Where ThematicName = ?";

    @Override
    public List<Thematic> getAll() {
        return selectBySQL(GET_ALL);
    }

    @Override
    public Thematic getByID(String id) {
        List<Thematic> list = this.selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void add(Thematic o) {
        JdbcHelper.executeUpdate(INSERT,
                o.getId(),
                o.getThematicName(),
                o.getTuition(),
                o.getTime(),
                o.getImage(),
                o.getDescription()
        );
    }

    @Override
    public void update(Thematic o) {
        JdbcHelper.executeUpdate(UPDATE,
                o.getThematicName(),
                o.getTuition(),
                o.getTime(),
                o.getImage(),
                o.getDescription(),
                o.getId()
        );
    }

    @Override
    public void remove(String id) {
        JdbcHelper.executeUpdate(DELETE, id);
    }

    @Override
    public List<Thematic> selectBySQL(String sql, Object... args) {
        List<Thematic> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Thematic thematic = Thematic.builder()
                        .id(rs.getString(1))
                        .thematicName(rs.getString(2))
                        .tuition(rs.getDouble(3))
                        .time(rs.getDouble(4))
                        .image(rs.getString(5))
                        .description(rs.getString(6))
                        .build();
                list.add(thematic);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Thematic getByName(String name) {

        List<Thematic> list = this.selectBySQL(SEARCH, name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
