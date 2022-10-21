package by.painter.controller;

import by.painter.model.DBConnectorTool;
import by.painter.view.userinterface.Viewable;
import by.painter.view.userinterface.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class AboutItemListener extends CommonController {

    public AboutItemListener(Viewable window) {
        super(window);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sqlRequest = "SELECT s.name, s.surname, u.title, u.city FROM student s JOIN university u on u.id = s.university_id WHERE s.id = 1;";
        String name = "undefined";
        String surname = "undefined";
        String city = "undefined";
        String university = "undefined";
        try {
            DBConnectorTool.connect();
            ResultSet resultSet = DBConnectorTool.executeSQL(sqlRequest);
            while (resultSet.next()) {
                name = resultSet.getString("Name");
                surname = resultSet.getString("Surname");
                city = resultSet.getString("City");
                university = resultSet.getString("Title");
                LOGGER.info("GET from DB:\nName: " + name + " " + surname + "\nUniversity: " + university + ", г." + city);
            }
        } catch (SQLException | NullPointerException exception) {
            LOGGER.error(exception);
        } finally {
            DBConnectorTool.disconnect();
            String text = "\"" + Window.DEFAULT_TITLE_VERSION + "\"\n" +
                    "Учебный проект курса по \"Java\".\n" +
                    "GitHub: https://github.com/AkulovAlexandr/Painter\n" +
                    "Автор: " + name + " " + surname + "\n" +
                    university + ", г." + city + ", 2022г.";
            JOptionPane.showMessageDialog((Component) window, text, "О программе", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
