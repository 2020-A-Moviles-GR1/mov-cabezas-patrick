import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuMarcas {
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
    private JButton eliminarButton;
    private JButton menuModelosButton;
    private JButton agregarButton;
    private JButton mostrarMarcaButton;
    private JTextArea textArea1;
    private JButton mostrarDatosButton;
    ArrayList marcas = new ArrayList<Marca>();
    ArrayList modelos = new ArrayList<Modelo>();

    public MenuMarcas() {
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
                Marca marca = new Marca(
                        txt_nombre_marca.getText()
                        ,txt_pais_marca.getText()
                        ,Integer.parseInt( txt_anio_marca.getText())
                        ,sucursalLocalCheckBox.isSelected()
                        ,Float.parseFloat( txt_valor_marca.getText())
                );
                AppKt.agragaMarca(marcas,marca);
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
                AppKt.eliminarMarca(marcas,modelos,txt_nombre_marca.getText());
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
    public void setListas(){
        AppKt.leerMarcas(this.marcas);
        AppKt.leerModelos(this.modelos);
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
}


