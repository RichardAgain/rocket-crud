import Cars.Car;
import Cars.Pickup;
import Cars.Sedan;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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

    private JTable carTable;
    private JButton editarButton;
    private JButton crearBtn;
    private JButton borrarButton;
    private JLabel precioL;
    private JSpinner diasSpinner;
    private JTextField precioTF;
    private JLabel descuentoLabel;
    private JLabel ivaLabel;
    private JLabel totalVentaLabel;
    private JLabel descAñoLabel;
    private JLabel precioAlquilerLabel;
    private JLabel alquilerLabel;

    private Car selectedCar;
    private int selectedCarIndex;

    public Interfaz () {
        setContentPane(rocketPanel);
        setVisible(true);
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 31, 1);
        diasSpinner.setModel(spinnerModel);

        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(diasSpinner, "#");
        diasSpinner.setEditor(editor);

        Car.Owner owner = new Car.Owner(12029954, "Nicole", "Watterson", "0414-4569374", "213 ELmore");
        Car.Owner owner2 = new Car.Owner(30556338, "Richard", "Watterson", "0414-4300312", "Valencia");
        Car.Owner owner3 = new Car.Owner(30506201, "Red", "Angry Bird", "0414-4249066", "Isla Pajaros");
        Sedan corsa = new Sedan(owner, "Corolla", "Toyota","ASD123",2018, "#0f0f0f", 1000);
        Pickup luv = new Pickup(owner2, "Explorer", "Ford","ASD123",2018, "#0f0f0f", 1000);
        Sedan spark = new Sedan(owner3, "Mustang", "Ford","ASD123",2018, "#0f0f0f", 100000);

        Car.carList.add(corsa);
        Car.carList.add(luv);
        Car.carList.add(spark);

        createColumns();

        selectedCarIndex = 0;
        selectedCar = Car.carList.getFirst();
        showCar();

        carTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int c = carTable.rowAtPoint(e.getPoint());

                selectedCar = Car.carList.get(c);
                selectedCarIndex = c;
                showCar();
            }
        });

        MarcaCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 updateModels();
            }
        });

        diasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                precioAlquilerLabel.setText(String.valueOf(selectedCar.getPrice((int) diasSpinner.getValue())));
            }
        });


        crearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String message = null;
                try {
                    message = validateFields() ? createCar() : "Faltan Validaciones";
                } catch (NoSuchMethodException ex) {
                    throw new RuntimeException(ex);
                } catch (InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                } catch (InstantiationException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, message);

            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = validateFields() ? editCar() : "Faltan Validaciones";
                JOptionPane.showMessageDialog(null, message);
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    removeCar();
                } catch (IndexOutOfBoundsException error) {
                    JOptionPane.showMessageDialog(null, "Nada que borrar");
                } catch (NoSuchElementException error) {
                    JOptionPane.showMessageDialog(null, "Nada que borrar");
                }

            }
        });
    }

    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
    }

    public boolean validateFields() {
        try {
            Double.parseDouble(precioTF.getText());
            Integer.parseInt(cedulaTF.getText());
        } catch (NumberFormatException error){
            return false;
        }

        return
                (
                nombreTF.getText().matches("^[a-zA-Z]*$") &&
                apellidoTF.getText().matches("^[a-zA-Z]*$") &&
                telefonoTF.getText().matches("^\\d{4}-\\d{7}$") &&
                matriculaTF.getText().matches("[A-Z0-9]{6}") &&
                cedulaTF.getText().matches("[0-9]{7,8}") &&
                (Integer.parseInt(AñoTF.getText()) >= 1900 &&
                (Integer.parseInt(AñoTF.getText()) <= 2024 &&
                (Double.parseDouble(precioTF.getText())) >= 1000 &&
                (Double.parseDouble(precioTF.getText())) <= 30000 )));

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

        selectedCar = Car.carList.getLast();
        selectedCarIndex = Car.carList.size() - 1;
    }

    public void showCar () {

        MarcaCB.setSelectedItem(selectedCar.brand);

        updateModels();
        ModeloCB.setSelectedItem(selectedCar.model);

        matriculaTF.setText(selectedCar.plate);
        AñoTF.setText(String.valueOf(selectedCar.year));

        nombreTF.setText(selectedCar.owner.first_name);
        apellidoTF.setText(selectedCar.owner.last_name);
        cedulaTF.setText(String.valueOf(selectedCar.owner.ci));
        telefonoTF.setText(selectedCar.owner.phone);
        direccionTF.setText(selectedCar.owner.address);

        showPrices();
    }

    public String createCar () throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<String> lista_pickup = Arrays.asList(new String[]{ "Explorer", "Fortuner", "Silverado" });

        Car.Owner owner = new Car.Owner(
                Integer.parseInt(cedulaTF.getText()),
                nombreTF.getText(),
                apellidoTF.getText(),
                telefonoTF.getText(),
                direccionTF.getText()
        );

        String key = (lista_pickup.contains(ModeloCB.getSelectedItem())) ? "Pickup" : "Sedan";

        Map<String, Class<? extends Car>> map =
                Map.of(
                        "Sedan", Sedan.class,
                        "Pickup", Pickup.class
                );

        Class<? extends Car> aClass = map.getOrDefault(key, Sedan.class);

        Car car = aClass.getDeclaredConstructor(
                Car.Owner.class, String.class, String.class, String.class, int.class, String.class, double.class)
                .newInstance(owner, (String) MarcaCB.getSelectedItem(), (String) ModeloCB.getSelectedItem(),
                        matriculaTF.getText(), Integer.parseInt(AñoTF.getText()), "color", Double.parseDouble(precioTF.getText()));


        Car.carList.add(car);

        createColumns();

        return "Validado Correctamente";
    }

    public String editCar () {
        Car.carList.get(selectedCarIndex).owner.ci = Integer.parseInt(cedulaTF.getText());
        Car.carList.get(selectedCarIndex).owner.first_name = nombreTF.getText();
        Car.carList.get(selectedCarIndex).owner.last_name = telefonoTF.getText();
        Car.carList.get(selectedCarIndex).owner.phone = direccionTF.getText();
        Car.carList.get(selectedCarIndex).owner.address = direccionTF.getText();

        Car.carList.get(selectedCarIndex).brand = (String) MarcaCB.getSelectedItem();
        Car.carList.get(selectedCarIndex).model = (String) ModeloCB.getSelectedItem();
        Car.carList.get(selectedCarIndex).plate = matriculaTF.getText();
        Car.carList.get(selectedCarIndex).year = Integer.parseInt(AñoTF.getText());
        Car.carList.get(selectedCarIndex).color = "color";
        Car.carList.get(selectedCarIndex).price = 1000.00;

        return "Validado Correctamente";

    }

    public void removeCar () {
        Car.carList.remove(selectedCarIndex);

        createColumns();
        showCar();
    }

    public void showPrices() {
        precioTF.setText(String.valueOf(selectedCar.price));
        descuentoLabel.setText(String.valueOf(selectedCar.getDiscount() * 100) + " %"  );

        ivaLabel.setText(String.valueOf(selectedCar.price * 1.16) + " $");
        descAñoLabel.setText(String.valueOf(((double) (2024 - selectedCar.year) / 200) * 100) + " %");


        alquilerLabel.setText(String.valueOf(selectedCar.price * 0.01) + " $");
        precioAlquilerLabel.setText(String.valueOf(selectedCar.getPrice((int) diasSpinner.getValue())) + " $");
        totalVentaLabel.setText(String.valueOf(selectedCar.getPrice()));

    }

    public void updateModels () {
        ModeloCB.removeAllItems();

        String brand = (String) MarcaCB.getSelectedItem();

        String[] Ford = {"Fiesta", "Mustang", "Explorer"};
        String[] Toyota = {"Corolla", "Fortuner"};
        String[] Chevrolet = {"Optra", "Cruze", "Silverado"};


        Map<String, String[]> map =
                Map.of(
                        "Ford", Ford,
                        "Toyota", Toyota,
                        "Dodge", Chevrolet
                );

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(map.get(brand));

        ModeloCB.setModel(model);

    }




}
