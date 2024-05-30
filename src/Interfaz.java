import Cars.Car;
import Cars.Pickup;
import Cars.Sedan;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.Map;

public class Interfaz extends JFrame{
    private JPanel rocketPanel;

    private JComboBox MarcaCB;
    private JComboBox ModeloCB;
    private JTextField matriculaTF;
    private JTextField AñoTF;

    private JLabel Colorcolor;
    private JTextField nombreTF;
    private JTextField apellidoTF;
    private JTextField cedulaTF;
    private JTextField direccionTF;
    private JTextField telefonoTF;

    private JButton elegirFotoButton;

    private JTable carTable;
    private JButton editarButton;
    private JButton crearBtn;
    private JButton borrarButton;
    private JLabel Foto;

    private Car selectedCar;

    public Interfaz () {
        setContentPane(rocketPanel);
        setVisible(true);
        setSize(1000,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Car.Owner owner = new Car.Owner(12123123, "Pepe", "Hern", "0414-4249066", "Albaquerque");
        Sedan corsa = new Sedan(owner, "Corsa", "Toyota","ASD123",2018, "#0f0f0f", 1000);
        Pickup luv = new Pickup(owner, "Explorer", "Ford","ASD123",2018, "#0f0f0f", 1000);
        Sedan spark = new Sedan(owner, "Mustang", "Ford","ASD123",2018, "#0f0f0f", 100000);

        Car.carList.add(corsa);
        Car.carList.add(luv);
        Car.carList.add(spark);

        selectedCar = Car.carList.getFirst();

        createColumns();
        showCar();

        carTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int c = carTable.rowAtPoint(e.getPoint());

                selectedCar = Car.carList.get(c);
                showCar();
            }
        });

        MarcaCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateModels();
            }
        });
        elegirFotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String Ruta = "";
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG & GIF","jpg", "png", "gif");
                jFileChooser.setFileFilter(filtrado);

                int respuesta = jFileChooser.showOpenDialog(jFileChooser);

                if (respuesta == JFileChooser.APPROVE_OPTION) {
                    Ruta = jFileChooser.getSelectedFile().getPath();

                    Image mImagen = new ImageIcon(Ruta).getImage();
                    ImageIcon mIcono = new ImageIcon(mImagen.getScaledInstance(340,170, Image.SCALE_SMOOTH));
                    Foto.setIcon(mIcono);

                }
            }
        });
    }

    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
    }

    public void createColumns() {

        String[] columnNames = {"Marca", "Modelo", "Placa","Año", "Dueño"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4;
            }
        };
        carTable.setModel(model);

        TableColumnModel columnModel = carTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(150);

        Car.carList.forEach((car  -> {
            model.addRow(car.getColumn());
        }));

    }

    public void showCar () {

        MarcaCB.setSelectedItem(selectedCar.brand);

        updateModels();
        ModeloCB.setSelectedItem(selectedCar.model);

        matriculaTF.setText(selectedCar.model);
        AñoTF.setText(String.valueOf(selectedCar.year));

        nombreTF.setText(selectedCar.owner.first_name);
        apellidoTF.setText(selectedCar.owner.last_name);
        cedulaTF.setText(String.valueOf(selectedCar.owner.ci));
        telefonoTF.setText(selectedCar.owner.phone);
        direccionTF.setText(selectedCar.owner.address);
    }

    public void updateModels () {
        ModeloCB.removeAllItems();

        String brand = (String) MarcaCB.getSelectedItem();

        String[] Ford = {"Fiesta", "Mustang", "Explorer"};
        String[] Toyota = {"Corsa", "Fortuner", "eie"};
        String[] Dodge = {"umm", "eia", "eie"};


        Map<String, String[]> map =
                Map.of(
                        "Ford", Ford,
                        "Toyota", Toyota,
                        "Dodge", Dodge
                );

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(map.get(brand));

        ModeloCB.setModel(model);
    }




}
