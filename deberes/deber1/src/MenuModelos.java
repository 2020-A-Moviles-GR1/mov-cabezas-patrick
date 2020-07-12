import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuModelos {
    private JCheckBox disponibleCheckBox;
    private JTextField txt_nombre_modelo;
    private JTextField txt_anio_modelo;
    private JTextField txt_precio_modelo;
    private JTextField txt_nombre_marca;
    private JButton agregarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JTextArea txt_mostrar;
    private JButton salirButton;
    JPanel panel;
    ArrayList marcas = new ArrayList<Marca>();
    ArrayList modelos = new ArrayList<Modelo>();

    public MenuModelos() {
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Modelo modelo = new Modelo(
                        txt_nombre_modelo.getText(),
                        Integer.parseInt( txt_anio_modelo.getText()),
                        Float.parseFloat(txt_precio_modelo.getText()),
                        disponibleCheckBox.isSelected(),
                        txt_nombre_marca.getText()
                );
                AppKt.agregarModelos(marcas,modelos,modelo);
                actualizarDatosModelos();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppKt.guardaModelos(modelos);
                AppKt.guardaMarcas(marcas);
                AppKt.abrirMenuMarcas();
                AppKt.cerrarMenuModelos();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Modelo modelo = new Modelo(
                        txt_nombre_modelo.getText(),
                        Integer.parseInt( txt_anio_modelo.getText()),
                        Float.parseFloat(txt_precio_modelo.getText()),
                        disponibleCheckBox.isSelected(),
                        txt_nombre_marca.getText()
                );
                AppKt.actualizarModelos(modelos,modelo);
                actualizarDatosModelos();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppKt.eliminarModelos(modelos,txt_nombre_modelo.getText());
                actualizarDatosModelos();
            }
        });
        setListas();
        actualizarDatosModelos();
    }
    public void setListas(){
        AppKt.leerMarcas(this.marcas);
        AppKt.leerModelos(this.modelos);
    }
    public void actualizarDatosModelos() {
        String texto = AppKt.mostrarModelos(modelos);
        txt_mostrar.setText(texto);
        txt_nombre_marca.setText("");
        txt_nombre_modelo.setText("");
        txt_anio_modelo.setText("");
        txt_precio_modelo.setText("");
        disponibleCheckBox.setSelected(false);
    }
}
