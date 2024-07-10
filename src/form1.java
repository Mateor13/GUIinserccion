import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form1 extends JFrame {
    private JPanel mainPanel;
    private JTextField nm;
    private JButton ok;
    private JTextField ci;
    private JTextField b1;
    private JTextField b2;
    private JLabel Resultado;

    public form1() {
        setTitle("Inserción de Datos");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/clasepoo";
                String usuario = "root";
                String password = "123456";
                Estudiantes est = new Estudiantes();
                try (Connection con = DriverManager.getConnection(url, usuario, password);) {
                    Resultado.setText("Se ha ingresado a la base de datos correctamente");
                    est.setNombre(nm.getText());
                    est.setCedula(ci.getText());
                    est.setNota1(b1.getText());
                    est.setNota2(b2.getText());
                    String querry = "insert into estudiantes(cedula, nombre, b1, b2) values (?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(querry);
                    ps.setString(1, est.getCedula());
                    ps.setString(2, est.getNombre());
                    ps.setString(3, est.getNota1());
                    ps.setString(4, est.getNota2());
                    ps.executeUpdate();
                    Resultado.setText("Datos insertados correctamente");
                } catch (SQLException e1) {
                        e1.printStackTrace();
                        Resultado.setText("Error al insertar los datos");
                }
            }
        });
    }
}

