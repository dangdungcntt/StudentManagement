package BLL;


import DAL.Database;
import DAL.Env;
import DAL.Student;
import PL.MainScreen.ListStudent.ListPanel;

import java.net.ConnectException;
import java.sql.*;
import java.util.HashMap;

/**
 * Created by Nguyá»…n Ä�Äƒng DÅ©ng on 3/6/2017 9:22 PM
 * Project: BaiTapLon
 */
public class StudentBLL {
    private HashMap<Integer, Student> mapStudent;
    private Database database;
    private String TBL_STUDENT = Env.envVar.get("DB_TBL_STUDENT");
    private String TBL_STUDENT_ID = Env.envVar.get("DB_TBL_STUDENT_ID");
    private String TBL_STUDENT_NAME = Env.envVar.get("DB_TBL_STUDENT_NAME");
    private String TBL_STUDENT_CITY = Env.envVar.get("DB_TBL_STUDENT_CITY");
    private String TBL_STUDENT_BIRTH = Env.envVar.get("DB_TBL_STUDENT_BIRTH");
    private String TBL_STUDENT_GENDER = Env.envVar.get("DB_TBL_STUDENT_GENDER");
    private String TBL_STUDENT_MATH = Env.envVar.get("DB_TBL_STUDENT_MATH");
    private String TBL_STUDENT_PHYSICAL = Env.envVar.get("DB_TBL_STUDENT_PHYSICAL");
    private String TBL_STUDENT_CHEMISTRY = Env.envVar.get("DB_TBL_STUDENT_CHEMISTRY");
    private String TBL_CITY = Env.envVar.get("DB_TBL_CITY");
    private String TBL_CITY_ID = Env.envVar.get("DB_TBL_CITY_ID");
    private String TBL_CITY_NAME = Env.envVar.get("DB_TBL_CITY_NAME");
    private String TBL_CITY_NAME_TXT = Env.envVar.get("DB_TBK_CITY_NAME_TXT");

    public StudentBLL() {
        mapStudent = new HashMap<Integer, Student>();
        database = new Database();
    }

    public StudentBLL(HashMap<Integer, Student> mapStudent) {

        this.mapStudent = mapStudent;
    }

    public HashMap<Integer, Student> getMapStudent() {

        return mapStudent;
    }

    public void setMapStudent(HashMap<Integer, Student> mapStudent) {
        this.mapStudent = mapStudent;
    }

    @Override
    public String toString() {
        return "StudentBLL{\n" +
                "mapStudent=\n" + mapStudent +
                "\n}";
    }

    public int countNumberStudent() throws ConnectException {
        try {
            Connection connection = database.connect();
            try {
                int totalRec;
                String countSql = "SELECT COUNT(" + TBL_STUDENT_ID + ") as total FROM " + TBL_STUDENT;
                PreparedStatement countStatement = connection.prepareStatement(countSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet countResultSet = countStatement.executeQuery();
                countResultSet.next();
                totalRec = countResultSet.getInt(1);
                countResultSet.close();
                countStatement.close();
                database.close();
                return totalRec;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ConnectException e) {
            throw new ConnectException();
        }
        return -1;
    }

    public boolean readData(int pageNumber, int numberPerPage) throws ConnectException {
        try {
            ListPanel.TOTAL_PAGE = (int) Math.ceil(countNumberStudent() * 1.0 / ListPanel.NUMBER_PERPAGE);
            ListPanel.CUR_PAGE = pageNumber;
            mapStudent.clear();
            Connection connection = database.connect();
            try {
                String sql = "SELECT * FROM " + TBL_STUDENT + " ORDER BY " + TBL_STUDENT_ID +
                        (Env.envVar.get("TYPE_DB").equalsIgnoreCase("mysql") ?
                                " LIMIT " + ((pageNumber - 1) * numberPerPage) + "," + numberPerPage :
                                " OFFSET " + ((pageNumber - 1) * numberPerPage) + " ROWS FETCH NEXT " + numberPerPage + " ROWS ONLY");
//                System.out.println(sql);
                PreparedStatement pStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultSet = pStatement.executeQuery();
                getFromResultSet(resultSet);
                resultSet.close();
                pStatement.close();
                database.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ConnectException e) {
            throw new ConnectException();
        }
        return mapStudent.size() != 0;
    }

    public HashMap<Integer, Student> getListStudentByCityId(int cityId) {
        HashMap<Integer, Student> mapStudentByCityId = new HashMap<Integer, Student>();
        for (Student student : mapStudent.values()) {
            if (student.getCityId() == cityId) {
                mapStudentByCityId.put(student.getStudentId(), student);
            }
        }
        return mapStudentByCityId;
    }

    public Student getStudentById(int studentId) {
        return mapStudent.get(studentId);
    }

    public int countNumberStudentFiltered(String cityName, int studentId) throws ConnectException {
        try {
            Connection connection = database.connect();
            try {
                int totalRec;
                //COUNT RECORDS
                String countSql = "SELECT COUNT(" + TBL_STUDENT_ID + ") as total FROM " + TBL_STUDENT + "," + TBL_CITY
                        + " WHERE ";
                countSql = createQuery(countSql, cityName, studentId);
//                System.out.println("Count" + countSql);
                PreparedStatement countStatement = connection.prepareStatement(countSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet countResultSet = countStatement.executeQuery();
                countResultSet.next();
                totalRec = countResultSet.getInt(1);
                countResultSet.close();
                countStatement.close();
                database.close();
                return totalRec;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ConnectException e) {
            throw new ConnectException();
        }
        return -1;
    }

    public void filterStudent(String cityName, int studentId, int pageNumber, int numberPerPage) throws ConnectException {
        if (cityName.length() == 0 && studentId < 0) return;
        StringBuilder builder = new StringBuilder("%");
        int leng = cityName.length();
        for (int i = 0; i < leng; i++) {
            builder.append(cityName.charAt(i)).append("%");
        }
        cityName = builder.toString();
//        System.out.println(cityName);
        try {
            mapStudent.clear();
            Connection connection = database.connect();
            try {
                ListPanel.TOTAL_PAGE = (int) Math.ceil(countNumberStudentFiltered(cityName, studentId) * 1.0 / ListPanel.NUMBER_PERPAGE);
                ListPanel.CUR_PAGE = pageNumber;
                String sql = "SELECT * FROM " + TBL_STUDENT + "," + TBL_CITY
                        + " WHERE ";
                sql = createQuery(sql, cityName, studentId);
                sql += " ORDER BY " + TBL_STUDENT_ID + " ASC " +
                        (Env.envVar.get("TYPE_DB").equalsIgnoreCase("mysql") ?
                                " LIMIT " + ((pageNumber - 1) * numberPerPage) + "," + numberPerPage :
                                " OFFSET " + ((pageNumber - 1) * numberPerPage) + " ROWS FETCH NEXT " + numberPerPage + " ROWS ONLY");
//                System.out.println("Sql" + sql);
                PreparedStatement pStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultSet = pStatement.executeQuery();
                getFromResultSet(resultSet);
                resultSet.close();
                pStatement.close();
                database.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ConnectException e) {
            throw new ConnectException("Cannot connect to database");
        }
        //return mapStudent.size() != 0;
    }

    private String createQuery(String sql, String cityName, int studentId) {
        StringBuilder builder = new StringBuilder();
        builder.append(sql);
        builder.append(TBL_CITY_ID)
                .append(" = ")
                .append(TBL_STUDENT_CITY);
        if (cityName.length() > 0) {
            builder.append(" AND (")
                    .append(TBL_CITY_NAME_TXT)
                    .append(" LIKE \'").append(cityName).append("\'")
                    .append(" OR ")
                    .append(TBL_CITY_NAME)
                    .append(" LIKE \'").append(cityName).append("\')");
        }
        if (studentId > -1) {
            builder.append(" AND ").append(TBL_STUDENT_ID)
                    .append(" LIKE \'").append("%").append(studentId).append("%").append("\'");
        }
        return builder.toString();
    }

    private void getFromResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Student student = new Student();
            student.setStudentId(resultSet.getInt(1));
            student.setStudentName(resultSet.getString(2));
            student.setCityId(resultSet.getInt(3));
            student.setBirth(resultSet.getDate(4));
            student.setGender(resultSet.getBoolean(5));
            student.setMath(resultSet.getFloat(6));
            student.setPhysical(resultSet.getFloat(7));
            student.setChemistry(resultSet.getFloat(8));
            mapStudent.put(resultSet.getInt(1), student);
        }
    }

    public boolean add(Student student) throws ConnectException {
        try {
            Connection connection = database.connect();
            try {
                String sql = "INSERT INTO " + TBL_STUDENT + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pStatement = connection.prepareStatement(sql);
                pStatement.setInt(1, student.getStudentId());
                pStatement.setString(2, student.getStudentName());
                pStatement.setInt(3, student.getCityId());
                pStatement.setDate(4, new Date(student.getBirth().getTime()));
                pStatement.setBoolean(5, student.isGender());
                pStatement.setFloat(6, student.getMath());
                pStatement.setFloat(7, student.getPhysical());
                pStatement.setFloat(8, student.getChemistry());
                if (pStatement.executeUpdate() == 1) {
                    pStatement.close();
                    database.close();
                    mapStudent.put(student.getStudentId(), student);
                    return true;
                }
                pStatement.close();
                database.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ConnectException e) {
            e.printStackTrace();
            throw new ConnectException("Cannot connect to database");
        }

        return false;
    }

    public boolean delete(int studentId) throws ConnectException {
        try {
            Connection connection = database.connect();
            try {
                String sql = "DELETE FROM " + TBL_STUDENT + " WHERE " + TBL_STUDENT_ID + " = ?";
                PreparedStatement pStatement = connection.prepareStatement(sql);
                pStatement.setInt(1, studentId);
                if (pStatement.executeUpdate() == 1) {
                    pStatement.close();
                    database.close();
                    mapStudent.remove(studentId);
                    return true;
                }
                pStatement.close();
                database.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ConnectException e) {
            throw new ConnectException("Cannot connect to database");
        }
        return false;
    }

    public boolean update(Student student) throws ConnectException {
        try {
            Connection connection = database.connect();
            try {
                String sql = "UPDATE " + TBL_STUDENT + " SET "
                        + TBL_STUDENT_NAME + " = ?, "
                        + TBL_STUDENT_CITY + " = ?, "
                        + TBL_STUDENT_BIRTH + " = ?, "
                        + TBL_STUDENT_GENDER + " = ?,"
                        + TBL_STUDENT_MATH + " = ?, "
                        + TBL_STUDENT_PHYSICAL + " = ?, "
                        + TBL_STUDENT_CHEMISTRY + " = ? WHERE "
                        + TBL_STUDENT_ID + " = ?";
                PreparedStatement pStatement = connection.prepareStatement(sql);
                pStatement.setString(1, student.getStudentName());
                pStatement.setInt(2, student.getCityId());
                pStatement.setDate(3, new Date(student.getBirth().getTime()));
                pStatement.setBoolean(4, student.isGender());
                pStatement.setFloat(5, student.getMath());
                pStatement.setFloat(6, student.getPhysical());
                pStatement.setFloat(7, student.getChemistry());
                pStatement.setInt(8, student.getStudentId());
//                System.out.println(pStatement.toString());
                if (pStatement.executeUpdate() == 1) {
                    pStatement.close();
                    database.close();
                    mapStudent.replace(student.getStudentId(), student);
                    return true;
                }
                pStatement.close();
                database.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ConnectException e) {
            throw new ConnectException("Cannot connect to database");
        }
        return false;
    }
}