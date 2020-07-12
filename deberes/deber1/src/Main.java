import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    private JTextField txt_texto;
    private JButton btn_agregar;
    JPanel Panel;
    private JTextField txt_nombre_marca;
    private JTextField txt_pais_marca;
    private JTextField txt_anio_marca;
    private JCheckBox sucursalLocalCheckBox;
    private JTextField txt_valor_marca;
    private JButton actualizarButton;
    private JButton salirButton;
    JTable table1=createTable();
    private JButton eliminarButton;
    private JButton menuModelosButton;
    private JButton agregarButton;
    private JButton mostrarMarcaButton;
    private JTextArea textArea1;
    private JButton mostrarDatosButton;
    ArrayList marcas = new ArrayList<Marca>();
    ArrayList modelos = new ArrayList<Modelo>();


    public void setListas(){
        AppKt.leerMarcas(this.marcas);
        AppKt.leerModelos(this.modelos);
    }

    public Main() {
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppKt.guardaMarcas(marcas);
                AppKt.guardaModelos(modelos);
                System.exit(0);
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agragarMarca();
                actualizarDatosMarcas();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Marca marca = new Marca(
                            txt_nombre_marca.getText()
                            ,txt_pais_marca.getText()
                            ,Integer.parseInt( txt_anio_marca.getText())
                            ,sucursalLocalCheckBox.isSelected()
                            ,Float.parseFloat( txt_valor_marca.getText())
                    );
                    AppKt.actualizarMarcas(marcas,marca);
                    actualizarDatosMarcas();
                }catch (Exception e1){
                    System.out.println("Error");
                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaMarca();
                actualizarDatosMarcas();
            }
        });
        menuModelosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppKt.guardaModelos(modelos);
                AppKt.guardaMarcas(marcas);
                AppKt.abrirMenuModelos();
                AppKt.cerrarMenuMarcas();
            }
        });
        setListas();
        actualizarDatosMarcas();
    }

    public void actualizarDatosMarcas(){
        String texto=AppKt.mostrarMarcas(marcas);
        textArea1.setText(texto);
        txt_nombre_marca.setText("");
        txt_anio_marca.setText("");
        txt_pais_marca.setText("");
        txt_valor_marca.setText("");
        sucursalLocalCheckBox.setSelected(false);
    }

    public JPanel getPanel() {
        return Panel;
    }
    public void agragarMarca(){
        Marca marca = new Marca(
                txt_nombre_marca.getText()
                ,txt_pais_marca.getText()
                ,Integer.parseInt( txt_anio_marca.getText())
                ,sucursalLocalCheckBox.isSelected()
                ,Float.parseFloat( txt_valor_marca.getText())
        );
        AppKt.agragaMarca(marcas,marca);
    }
    public void eliminaMarca(){
        AppKt.eliminarMarca(marcas,modelos,txt_nombre_marca.getText());
    }


    public static JTable createTable(){
        String[] columnNames = {"First Name", "Last Name"};
        Object[][] data = {{"Kathy", "Smith"},{"John", "Doe"}};
        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        return table;
    }


    public class TableExample {
        JFrame f;
        TableExample(){
            f=new JFrame();
            String data[][]={ {"101","Amit","670000"},
                    {"102","Jai","780000"},
                    {"101","Sachin","700000"}};
            String column[]={"ID","NAME","SALARY"};
            JTable jt=new JTable(data,column);
            jt.setBounds(30,40,200,300);
            JScrollPane sp=new JScrollPane(jt);

            f.add(sp);
            f.setSize(300,400);
            f.setVisible(true);
        }
    }
}


