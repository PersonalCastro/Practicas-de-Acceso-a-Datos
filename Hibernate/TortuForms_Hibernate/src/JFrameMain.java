
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultListModel;
import tortuforms_hibernate.Refugio;
import tortuforms.RefugioController;
import tortuforms_hibernate.Grupoespecialista;
import tortuforms.GrupoEspecialistaController;
import tortuforms_hibernate.Cuidador;
import tortuforms.CuidadorController;
import tortuforms_hibernate.Tortuga;
import tortuforms.TortugaController;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PersonalCastro
 */
public class JFrameMain extends javax.swing.JFrame {

    //Refujio
    private ArrayList<Refugio> listaRefugios;
    private RefugioController controladorDeRefugios;
    private boolean add_refugio;
    private boolean eliminar_refugio;
    private boolean modificar_refugio;
    
    //Grupo de Especialistas
    private ArrayList<Grupoespecialista> listaGruposEspecialistas;
    private GrupoEspecialistaController controladorDeGruposEspecialistas;
    private boolean add_grupoEspecialista;
    private boolean eliminar_grupoEspecialista;
    private boolean modificar_grupoEspecialista;   
    
    //Cuidadores
    private ArrayList<Cuidador> listaCuidadores;
    private CuidadorController controladorDeCuidadores;
    private boolean add_cuidador;
    private boolean eliminar_cuidador;
    private boolean modificar_cuidador;   
    
    //Tortugas
    private ArrayList<Tortuga> listaTortugas;
    private TortugaController controladorDeTortugas;
    private boolean add_tortuga;
    private boolean eliminar_tortuga;
    private boolean modificar_tortuga;
    
    
    public static Refugio none_refugio;
    public static Grupoespecialista none_grupoespecialista;
    public static Cuidador none_cuidador;

    
    
    /**
     * Creates new form JFrameMain
     */
    public JFrameMain() {

        listaRefugios = new ArrayList();
        controladorDeRefugios = new RefugioController();
        listaGruposEspecialistas = new ArrayList();
        controladorDeGruposEspecialistas = new GrupoEspecialistaController();
        listaCuidadores = new ArrayList();
        controladorDeCuidadores = new CuidadorController();
        listaTortugas = new ArrayList();
        controladorDeTortugas = new TortugaController();
        
        none_refugio = new Refugio();
        none_grupoespecialista = new Grupoespecialista();
        none_cuidador = new Cuidador();
                
        initComponents();
        setfalseAdd_eliminar_modificar();
        
        
        initDataComponents();        
    }
    
    public void initDataComponents(){
    
        
        this.controladorDeRefugios.getRefugios(this.listaRefugios);
        this.controladorDeGruposEspecialistas.getGruposEspecialistas(this.listaGruposEspecialistas);
        this.controladorDeCuidadores.getCuidadores(this.listaCuidadores);
        this.controladorDeTortugas.getTortugas(this.listaTortugas);
        
        
        printListRefugios();
        printListGruposEspecialistas();
        printListCuidadores();
        printListTortugas();        
    }
    
    private void printListRefugios(){
        if(!this.listaRefugios.isEmpty()){
            this.limpiarJlistRefugios();
            DefaultListModel lista = (DefaultListModel) this.jListRefugios.getModel();
            
            
            for (Refugio aux: this.listaRefugios){
                
                this.jListRefugios.setCellRenderer(new ListWithImage("img/Refugio.png"));

                lista.addElement(aux.getNombre());
            }
            RefugioController.printArrayRefugios(this.listaRefugios);

        }else{
            this.limpiarJlistRefugios();
        }
    }
    
    private void printListGruposEspecialistas(){
        if(!this.listaGruposEspecialistas.isEmpty()){
            this.limpiarJlistGruposEspecialistas();
            DefaultListModel lista = (DefaultListModel) this.jListGrupoEspecialista.getModel();
            
            
            for (Grupoespecialista aux: this.listaGruposEspecialistas){
                
                this.jListGrupoEspecialista.setCellRenderer(new ListWithImage("img/Especialistas.png"));

                lista.addElement(aux.getEspecialidad());
            }
            
            GrupoEspecialistaController.printArrayGruposEspecialistas(this.listaGruposEspecialistas);

        }else{
            this.limpiarJlistGruposEspecialistas();
        }
    }
    
    private void printListCuidadores(){
        if(!this.listaCuidadores.isEmpty()){
            this.limpiarJlistCuidadores();
            DefaultListModel lista = (DefaultListModel) this.jListCuidadores.getModel();
                       
            Random aleatorio = new Random();
            int fotoAleatoria = 0;
            
            for (Cuidador aux: this.listaCuidadores){
                
                fotoAleatoria = aleatorio.nextInt(4);
                
                
                if(fotoAleatoria == 0){
                    this.jListCuidadores.setCellRenderer(new ListWithImage("img/Cuidador1.png"));
                }else if(fotoAleatoria == 1){
                    this.jListCuidadores.setCellRenderer(new ListWithImage("img/Cuidador2.png"));
                }else if(fotoAleatoria == 2){
                    this.jListCuidadores.setCellRenderer(new ListWithImage("img/Cuidador3.png"));
                }else if(fotoAleatoria == 3){
                    this.jListCuidadores.setCellRenderer(new ListWithImage("img/Cuidador4.png"));
                }
                

                lista.addElement(aux.getNombre());
            }
            CuidadorController.printArrayCuidadores(this.listaCuidadores);
        }else{
            this.limpiarJlistCuidadores();
        }
    }
    
    private void printListTortugas(){
        if(!this.listaTortugas.isEmpty()){
            this.limpiarJlistTortugas();
            DefaultListModel lista = (DefaultListModel) this.jListTortugas.getModel();
            
            
            for (Tortuga aux: this.listaTortugas){
                
                jListTortugas.setCellRenderer(new ListWithImage("img/tortoise.png"));

                lista.addElement(aux.getApodo());
            }
            TortugaController.printArrayTortugas(this.listaTortugas);
        }else{
            this.limpiarJlistTortugas();
        }
    }

    private void limpiarJlistRefugios(){
        DefaultListModel model = new DefaultListModel();
        this.jListRefugios.setModel(model);
    }
    
    private void limpiarJlistGruposEspecialistas(){
        DefaultListModel model = new DefaultListModel();
        this.jListGrupoEspecialista.setModel(model);
    }
    
    private void limpiarJlistCuidadores(){
        DefaultListModel model = new DefaultListModel();
        this.jListCuidadores.setModel(model);
    }
    
    private void limpiarJlistTortugas(){
        DefaultListModel model = new DefaultListModel();
        this.jListTortugas.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelRefugio = new javax.swing.JPanel();
        jTextFieldSucursales_refugio = new javax.swing.JTextField();
        jTextFieldNombre_refugio = new javax.swing.JTextField();
        jTextFieldCiudad_refugio = new javax.swing.JTextField();
        jLabelSucursales_refugio = new javax.swing.JLabel();
        jLabelNombre_refugio = new javax.swing.JLabel();
        jLabelCiudad_refugio = new javax.swing.JLabel();
        jComboBoxAbierto_refugio = new javax.swing.JComboBox<>();
        jLabelAbierto_refugio = new javax.swing.JLabel();
        jButtonAdd_refugio = new javax.swing.JButton();
        jButtonEliminar_refugio = new javax.swing.JButton();
        jButtonModificar_refugio = new javax.swing.JButton();
        jButtonGuardar_refugio = new javax.swing.JButton();
        jButtonCancelar_refugio = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListRefugios = new javax.swing.JList<>();
        jPanelGrupoEspecialistas = new javax.swing.JPanel();
        jLabelExpedicion_grupoEspecialista = new javax.swing.JLabel();
        jButtonAdd_grupoEspecialista = new javax.swing.JButton();
        jButtonEliminar_grupoEspecialista = new javax.swing.JButton();
        jButtonModificar_grupoEspecialista = new javax.swing.JButton();
        jTextFieldInformes_grupoEspecialista = new javax.swing.JTextField();
        jButtonGuardar_grupoEspecialista = new javax.swing.JButton();
        jTextFieldEspecialidad_grupoEspecialista = new javax.swing.JTextField();
        jButtonCancelar_grupoEspecialista = new javax.swing.JButton();
        jTextFieldCapacidad_grupoEspecialista = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListGrupoEspecialista = new javax.swing.JList<>();
        jLabelInformes_grupoEspecialista = new javax.swing.JLabel();
        jLabelEspecialidad_grupoEspecialista = new javax.swing.JLabel();
        jLabelCapacidad_grupoEspecialista = new javax.swing.JLabel();
        jComboBoxExpedicion_grupoEspecialista = new javax.swing.JComboBox<>();
        jComboBoxIdRefenciaRefugio_grupoEspecialista = new javax.swing.JComboBox<>();
        jLabelIdRefenciaRefugio_grupoEspecialista = new javax.swing.JLabel();
        jPanelCuidador = new javax.swing.JPanel();
        jLabelJefe_cuidador = new javax.swing.JLabel();
        jButtonAdd_cuidador = new javax.swing.JButton();
        jButtonEliminar_cuidador = new javax.swing.JButton();
        jButtonModificar_cuidador = new javax.swing.JButton();
        jTextFieldDni_cuidador = new javax.swing.JTextField();
        jButtonGuardar_cuidador = new javax.swing.JButton();
        jTextFieldNombre_cuidador = new javax.swing.JTextField();
        jButtonCancelar_cuidador = new javax.swing.JButton();
        jTextFieldEdad_cuidador = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListCuidadores = new javax.swing.JList<>();
        jLabelDni_cuidador = new javax.swing.JLabel();
        jLabelNombre_cuidador = new javax.swing.JLabel();
        jLabelEdad_cuidador = new javax.swing.JLabel();
        jComboBoxJefe_cuidador = new javax.swing.JComboBox<>();
        jComboBoxIdRefenciaGrupoEspecialista_Cuidador = new javax.swing.JComboBox<>();
        jLabelIdRefenciaGrupoEspecialista_Cuidador = new javax.swing.JLabel();
        jPanelTortugas = new javax.swing.JPanel();
        jLabelHiberna_tortuga = new javax.swing.JLabel();
        jButtonAdd_tortuga = new javax.swing.JButton();
        jButtonEliminar_tortuga = new javax.swing.JButton();
        jButtonModificar_tortuga = new javax.swing.JButton();
        jButtonGuardar_tortuga = new javax.swing.JButton();
        jButtonCancelar_tortuga = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListTortugas = new javax.swing.JList<>();
        jTextFieldEdad_tortuga = new javax.swing.JTextField();
        jTextFieldApodo_tortuga = new javax.swing.JTextField();
        jTextFieldPeso_tortuga = new javax.swing.JTextField();
        jLabelEdad_tortuga = new javax.swing.JLabel();
        jLabelApodo_tortuga = new javax.swing.JLabel();
        jLabelPeso_tortuga = new javax.swing.JLabel();
        jComboBoxHiberna_tortuga = new javax.swing.JComboBox<>();
        jComboBoxIdRefenciaCuidador_Tortuga = new javax.swing.JComboBox<>();
        jLabelIdRefenciaCuidador_Tortuga = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setName("Grupos"); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jTextFieldSucursales_refugio.setEditable(false);

        jTextFieldNombre_refugio.setEditable(false);

        jTextFieldCiudad_refugio.setEditable(false);
        jTextFieldCiudad_refugio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCiudad_refugioActionPerformed(evt);
            }
        });

        jLabelSucursales_refugio.setText("Sucursales :");

        jLabelNombre_refugio.setText("Nombre :");

        jLabelCiudad_refugio.setText("Ciudad :");

        jComboBoxAbierto_refugio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Abierto", "Cerrado" }));
        jComboBoxAbierto_refugio.setEnabled(false);

        jLabelAbierto_refugio.setText("Abierto :");

        jButtonAdd_refugio.setText("Añadir");
        jButtonAdd_refugio.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonAdd_refugio.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonAdd_refugio.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonAdd_refugio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdd_refugioActionPerformed(evt);
            }
        });

        jButtonEliminar_refugio.setText("Eliminar");
        jButtonEliminar_refugio.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_refugio.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_refugio.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_refugio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar_refugioActionPerformed(evt);
            }
        });

        jButtonModificar_refugio.setText("Modificar");
        jButtonModificar_refugio.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonModificar_refugio.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonModificar_refugio.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonModificar_refugio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificar_refugioActionPerformed(evt);
            }
        });

        jButtonGuardar_refugio.setText("Guardar");
        jButtonGuardar_refugio.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_refugio.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_refugio.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_refugio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardar_refugioActionPerformed(evt);
            }
        });

        jButtonCancelar_refugio.setText("Cancelar");
        jButtonCancelar_refugio.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_refugio.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_refugio.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_refugio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelar_refugioActionPerformed(evt);
            }
        });

        jListRefugios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListRefugiosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListRefugios);

        javax.swing.GroupLayout jPanelRefugioLayout = new javax.swing.GroupLayout(jPanelRefugio);
        jPanelRefugio.setLayout(jPanelRefugioLayout);
        jPanelRefugioLayout.setHorizontalGroup(
            jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRefugioLayout.createSequentialGroup()
                .addGroup(jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRefugioLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButtonGuardar_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelar_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRefugioLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelRefugioLayout.createSequentialGroup()
                                .addGroup(jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelCiudad_refugio)
                                    .addComponent(jLabelAbierto_refugio)
                                    .addComponent(jLabelNombre_refugio)
                                    .addComponent(jLabelSucursales_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldCiudad_refugio, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldSucursales_refugio, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNombre_refugio, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxAbierto_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtonAdd_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonEliminar_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonModificar_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanelRefugioLayout.setVerticalGroup(
            jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRefugioLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRefugioLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSucursales_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSucursales_refugio))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNombre_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNombre_refugio))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCiudad_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCiudad_refugio))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAbierto_refugio)
                            .addComponent(jComboBoxAbierto_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelRefugioLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButtonAdd_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModificar_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanelRefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelar_refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jTabbedPane1.addTab("Refujios", jPanelRefugio);

        jLabelExpedicion_grupoEspecialista.setText("Expedición :");

        jButtonAdd_grupoEspecialista.setText("Añadir");
        jButtonAdd_grupoEspecialista.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonAdd_grupoEspecialista.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonAdd_grupoEspecialista.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonAdd_grupoEspecialista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdd_grupoEspecialistaActionPerformed(evt);
            }
        });

        jButtonEliminar_grupoEspecialista.setText("Eliminar");
        jButtonEliminar_grupoEspecialista.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_grupoEspecialista.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_grupoEspecialista.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_grupoEspecialista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar_grupoEspecialistaActionPerformed(evt);
            }
        });

        jButtonModificar_grupoEspecialista.setText("Modificar");
        jButtonModificar_grupoEspecialista.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonModificar_grupoEspecialista.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonModificar_grupoEspecialista.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonModificar_grupoEspecialista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificar_grupoEspecialistaActionPerformed(evt);
            }
        });

        jTextFieldInformes_grupoEspecialista.setEditable(false);

        jButtonGuardar_grupoEspecialista.setText("Guardar");
        jButtonGuardar_grupoEspecialista.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_grupoEspecialista.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_grupoEspecialista.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_grupoEspecialista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardar_grupoEspecialistaActionPerformed(evt);
            }
        });

        jTextFieldEspecialidad_grupoEspecialista.setEditable(false);

        jButtonCancelar_grupoEspecialista.setText("Cancelar");
        jButtonCancelar_grupoEspecialista.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_grupoEspecialista.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_grupoEspecialista.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_grupoEspecialista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelar_grupoEspecialistaActionPerformed(evt);
            }
        });

        jTextFieldCapacidad_grupoEspecialista.setEditable(false);
        jTextFieldCapacidad_grupoEspecialista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCapacidad_grupoEspecialistaActionPerformed(evt);
            }
        });

        jListGrupoEspecialista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListGrupoEspecialistaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jListGrupoEspecialista);

        jLabelInformes_grupoEspecialista.setText("Informes :");

        jLabelEspecialidad_grupoEspecialista.setText("Especialidad :");

        jLabelCapacidad_grupoEspecialista.setText("Capacidad :");

        jComboBoxExpedicion_grupoEspecialista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "De expedición", "En el refugio" }));
        jComboBoxExpedicion_grupoEspecialista.setEnabled(false);

        jComboBoxIdRefenciaRefugio_grupoEspecialista.setEnabled(false);

        jLabelIdRefenciaRefugio_grupoEspecialista.setText("Refugio :");

        javax.swing.GroupLayout jPanelGrupoEspecialistasLayout = new javax.swing.GroupLayout(jPanelGrupoEspecialistas);
        jPanelGrupoEspecialistas.setLayout(jPanelGrupoEspecialistasLayout);
        jPanelGrupoEspecialistasLayout.setHorizontalGroup(
            jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGrupoEspecialistasLayout.createSequentialGroup()
                .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelGrupoEspecialistasLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButtonGuardar_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelar_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelGrupoEspecialistasLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelGrupoEspecialistasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelGrupoEspecialistasLayout.createSequentialGroup()
                                .addComponent(jLabelIdRefenciaRefugio_grupoEspecialista)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxIdRefenciaRefugio_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelGrupoEspecialistasLayout.createSequentialGroup()
                                .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCapacidad_grupoEspecialista, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelExpedicion_grupoEspecialista, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelEspecialidad_grupoEspecialista, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelInformes_grupoEspecialista, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldCapacidad_grupoEspecialista, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldInformes_grupoEspecialista, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldEspecialidad_grupoEspecialista, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxExpedicion_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonAdd_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEliminar_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonModificar_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanelGrupoEspecialistasLayout.setVerticalGroup(
            jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGrupoEspecialistasLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGrupoEspecialistasLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButtonAdd_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModificar_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelGrupoEspecialistasLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldInformes_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelInformes_grupoEspecialista))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldEspecialidad_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEspecialidad_grupoEspecialista))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCapacidad_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCapacidad_grupoEspecialista))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelExpedicion_grupoEspecialista)
                            .addComponent(jComboBoxExpedicion_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelIdRefenciaRefugio_grupoEspecialista)
                            .addComponent(jComboBoxIdRefenciaRefugio_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanelGrupoEspecialistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelar_grupoEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jTabbedPane1.addTab("Grupos Especialistas", jPanelGrupoEspecialistas);

        jLabelJefe_cuidador.setText("Jefe :");

        jButtonAdd_cuidador.setText("Añadir");
        jButtonAdd_cuidador.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonAdd_cuidador.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonAdd_cuidador.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonAdd_cuidador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdd_cuidadorActionPerformed(evt);
            }
        });

        jButtonEliminar_cuidador.setText("Eliminar");
        jButtonEliminar_cuidador.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_cuidador.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_cuidador.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_cuidador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar_cuidadorActionPerformed(evt);
            }
        });

        jButtonModificar_cuidador.setText("Modificar");
        jButtonModificar_cuidador.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonModificar_cuidador.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonModificar_cuidador.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonModificar_cuidador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificar_cuidadorActionPerformed(evt);
            }
        });

        jTextFieldDni_cuidador.setEditable(false);

        jButtonGuardar_cuidador.setText("Guardar");
        jButtonGuardar_cuidador.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_cuidador.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_cuidador.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_cuidador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardar_cuidadorActionPerformed(evt);
            }
        });

        jTextFieldNombre_cuidador.setEditable(false);

        jButtonCancelar_cuidador.setText("Cancelar");
        jButtonCancelar_cuidador.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_cuidador.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_cuidador.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_cuidador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelar_cuidadorActionPerformed(evt);
            }
        });

        jTextFieldEdad_cuidador.setEditable(false);
        jTextFieldEdad_cuidador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEdad_cuidadorActionPerformed(evt);
            }
        });

        jListCuidadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListCuidadoresMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jListCuidadores);

        jLabelDni_cuidador.setText("Dni :");

        jLabelNombre_cuidador.setText("Nombre :");

        jLabelEdad_cuidador.setText("Edad :");

        jComboBoxJefe_cuidador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Jefe", "Trabajador" }));
        jComboBoxJefe_cuidador.setEnabled(false);

        jComboBoxIdRefenciaGrupoEspecialista_Cuidador.setEnabled(false);

        jLabelIdRefenciaGrupoEspecialista_Cuidador.setText("Especialidad :");

        javax.swing.GroupLayout jPanelCuidadorLayout = new javax.swing.GroupLayout(jPanelCuidador);
        jPanelCuidador.setLayout(jPanelCuidadorLayout);
        jPanelCuidadorLayout.setHorizontalGroup(
            jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCuidadorLayout.createSequentialGroup()
                .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCuidadorLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButtonGuardar_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelar_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCuidadorLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelCuidadorLayout.createSequentialGroup()
                                .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelCuidadorLayout.createSequentialGroup()
                                        .addComponent(jLabelIdRefenciaGrupoEspecialista_Cuidador)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBoxIdRefenciaGrupoEspecialista_Cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelCuidadorLayout.createSequentialGroup()
                                        .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelEdad_cuidador, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelJefe_cuidador, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelNombre_cuidador, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelDni_cuidador, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextFieldEdad_cuidador, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldDni_cuidador, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldNombre_cuidador, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBoxJefe_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtonAdd_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonEliminar_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonModificar_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanelCuidadorLayout.setVerticalGroup(
            jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCuidadorLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCuidadorLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButtonAdd_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModificar_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelCuidadorLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDni_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDni_cuidador))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNombre_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNombre_cuidador))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldEdad_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEdad_cuidador))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelJefe_cuidador)
                            .addComponent(jComboBoxJefe_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxIdRefenciaGrupoEspecialista_Cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIdRefenciaGrupoEspecialista_Cuidador))
                .addGap(30, 30, 30)
                .addGroup(jPanelCuidadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelar_cuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jTabbedPane1.addTab("Cuidadores", jPanelCuidador);

        jLabelHiberna_tortuga.setText("Hiberna :");

        jButtonAdd_tortuga.setText("Añadir");
        jButtonAdd_tortuga.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonAdd_tortuga.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonAdd_tortuga.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonAdd_tortuga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdd_tortugaActionPerformed(evt);
            }
        });

        jButtonEliminar_tortuga.setText("Eliminar");
        jButtonEliminar_tortuga.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_tortuga.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_tortuga.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonEliminar_tortuga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar_tortugaActionPerformed(evt);
            }
        });

        jButtonModificar_tortuga.setText("Modificar");
        jButtonModificar_tortuga.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonModificar_tortuga.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonModificar_tortuga.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonModificar_tortuga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificar_tortugaActionPerformed(evt);
            }
        });

        jButtonGuardar_tortuga.setText("Guardar");
        jButtonGuardar_tortuga.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_tortuga.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_tortuga.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonGuardar_tortuga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardar_tortugaActionPerformed(evt);
            }
        });

        jButtonCancelar_tortuga.setText("Cancelar");
        jButtonCancelar_tortuga.setMaximumSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_tortuga.setMinimumSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_tortuga.setPreferredSize(new java.awt.Dimension(85, 32));
        jButtonCancelar_tortuga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelar_tortugaActionPerformed(evt);
            }
        });

        jListTortugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListTortugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListTortugas);

        jTextFieldEdad_tortuga.setEditable(false);

        jTextFieldApodo_tortuga.setEditable(false);

        jTextFieldPeso_tortuga.setEditable(false);
        jTextFieldPeso_tortuga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPeso_tortugaActionPerformed(evt);
            }
        });

        jLabelEdad_tortuga.setText("Edad :");

        jLabelApodo_tortuga.setText("Apodo :");

        jLabelPeso_tortuga.setText("Peso(kg) :");

        jComboBoxHiberna_tortuga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Hiberna", "No hiberna" }));
        jComboBoxHiberna_tortuga.setEnabled(false);

        jComboBoxIdRefenciaCuidador_Tortuga.setEnabled(false);

        jLabelIdRefenciaCuidador_Tortuga.setText("Cuidador :");

        javax.swing.GroupLayout jPanelTortugasLayout = new javax.swing.GroupLayout(jPanelTortugas);
        jPanelTortugas.setLayout(jPanelTortugasLayout);
        jPanelTortugasLayout.setHorizontalGroup(
            jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTortugasLayout.createSequentialGroup()
                .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTortugasLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTortugasLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButtonGuardar_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelar_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanelTortugasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelTortugasLayout.createSequentialGroup()
                        .addComponent(jLabelIdRefenciaCuidador_Tortuga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxIdRefenciaCuidador_Tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTortugasLayout.createSequentialGroup()
                        .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPeso_tortuga, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelHiberna_tortuga, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelApodo_tortuga, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelEdad_tortuga, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldPeso_tortuga, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEdad_tortuga, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldApodo_tortuga, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxHiberna_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonAdd_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModificar_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        jPanelTortugasLayout.setVerticalGroup(
            jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTortugasLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTortugasLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButtonAdd_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModificar_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTortugasLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldEdad_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEdad_tortuga))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldApodo_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelApodo_tortuga))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPeso_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPeso_tortuga))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelHiberna_tortuga)
                            .addComponent(jComboBoxHiberna_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxIdRefenciaCuidador_Tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIdRefenciaCuidador_Tortuga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanelTortugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelar_tortuga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jTabbedPane1.addTab("Tortugas", jPanelTortugas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     
    
        /*  BOTONES DE TORTUGA    */
    
    
    
    private void jTextFieldPeso_tortugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPeso_tortugaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPeso_tortugaActionPerformed

    private void jButtonAdd_tortugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdd_tortugaActionPerformed

        if(!this.add_tortuga){
            this.setfalseAdd_eliminar_modificar();
            this.add_tortuga = true;
            this.jButtonAdd_tortuga.setBackground(new java.awt.Color(70, 200, 74));

            this.editableJtextFields_tortuga(true);
            this.setJtextFieldsClean_tortuga();
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_tortuga(false);
            this.setJtextFieldsClean_tortuga();
        }

        
    }//GEN-LAST:event_jButtonAdd_tortugaActionPerformed

    private void jButtonCancelar_tortugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelar_tortugaActionPerformed

        this.editableJtextFields_tortuga(false);
        this.setJtextFieldsClean_tortuga();

        this.setfalseAdd_eliminar_modificar();
    }//GEN-LAST:event_jButtonCancelar_tortugaActionPerformed

    private void jButtonGuardar_tortugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardar_tortugaActionPerformed

        this.editableJtextFields_tortuga(false);
        
        if(this.add_tortuga){
            
            if(!this.jTextFieldEdad_tortuga.getText().equals("") && 
                    !this.jTextFieldApodo_tortuga.getText().equals("") && 
                    !this.jTextFieldPeso_tortuga.getText().equals("") && 
                    !(this.jComboBoxHiberna_tortuga.getSelectedIndex() == 0)){
                
                boolean auxHiberna = false;
                if (this.jComboBoxHiberna_tortuga.getSelectedIndex() == 1){
                    auxHiberna = true;
                }
                
                
                this.listaTortugas.add(new Tortuga(
                        Integer.valueOf(this.controladorDeTortugas.generateId()), 
                        this.jTextFieldApodo_tortuga.getText(), 
                        Long.valueOf(this.jTextFieldPeso_tortuga.getText()), 
                        auxHiberna,
                        Integer.valueOf(this.jTextFieldEdad_tortuga.getText()),
                        this.getIdReferenceCuidador_Tortuga(this.jComboBoxIdRefenciaCuidador_Tortuga.getSelectedIndex()))
                        );
                
                printListTortugas();
                this.controladorDeTortugas.persistenciaDeDatos(this.listaTortugas, this.listaTortugas.size()-1,1 );
            }


            
        }else if(this.eliminar_tortuga){
           
            if(!this.jListTortugas.isSelectionEmpty()){
                int selectedIndex = this.jListTortugas.getSelectedIndex();

                this.controladorDeTortugas.persistenciaDeDatos(this.listaTortugas, selectedIndex, 3);
                this.listaTortugas.remove(selectedIndex);
                printListTortugas();

            }
            
        }else if(this.modificar_tortuga){
            
            if(!this.jListTortugas.isSelectionEmpty()){
                
                int selectedIndex = this.jListTortugas.getSelectedIndex();
                this.listaTortugas.get(selectedIndex).setEdad(Integer.valueOf(this.jTextFieldEdad_tortuga.getText()) );
                this.listaTortugas.get(selectedIndex).setApodo(this.jTextFieldApodo_tortuga.getText());
                this.listaTortugas.get(selectedIndex).setPeso(Long.valueOf(this.jTextFieldPeso_tortuga.getText()));
                this.listaTortugas.get(selectedIndex).setCuidador(this.getIdReferenceCuidador_Tortuga(this.jComboBoxIdRefenciaCuidador_Tortuga.getSelectedIndex()));
                if(this.jComboBoxHiberna_tortuga.getSelectedIndex() == 1){
                    this.listaTortugas.get(selectedIndex).setHiberna(true);
                }else if (this.jComboBoxHiberna_tortuga.getSelectedIndex() == 2 ){
                    this.listaTortugas.get(selectedIndex).setHiberna(false);
                }

                printListTortugas();
                this.controladorDeTortugas.persistenciaDeDatos(this.listaTortugas, selectedIndex, 2);
            }

        }
        
        
        this.setJtextFieldsClean_tortuga();
        this.setfalseAdd_eliminar_modificar();
    }//GEN-LAST:event_jButtonGuardar_tortugaActionPerformed

    private void jButtonModificar_tortugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificar_tortugaActionPerformed

        if(!this.modificar_tortuga){
            this.setfalseAdd_eliminar_modificar();
            this.modificar_tortuga = true;
            this.jButtonModificar_tortuga.setBackground(new java.awt.Color(70, 200, 74));
            this.editableJtextFields_tortuga(true);

            if(!this.jListTortugas.isSelectionEmpty()){
                mostrarDatosTortugaSeleccionada();
            }
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_tortuga(false);
        }

        
    }//GEN-LAST:event_jButtonModificar_tortugaActionPerformed

    private void jButtonEliminar_tortugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar_tortugaActionPerformed

        if(!this.eliminar_tortuga){
            this.setfalseAdd_eliminar_modificar();
            this.eliminar_tortuga = true;
            this.jButtonEliminar_tortuga.setBackground(new java.awt.Color(70, 200, 74));

            this.editableJtextFields_tortuga(false);
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_tortuga(false);
        }

    }//GEN-LAST:event_jButtonEliminar_tortugaActionPerformed

    private void jListTortugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListTortugasMouseClicked
        
        mostrarDatosTortugaSeleccionada();
    }//GEN-LAST:event_jListTortugasMouseClicked

    private void jTextFieldCiudad_refugioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCiudad_refugioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCiudad_refugioActionPerformed

    
    
        /*  BOTONES DE REFUGIO    */
    
    
    
    private void jButtonAdd_refugioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdd_refugioActionPerformed

        if(!this.add_refugio){
            this.setfalseAdd_eliminar_modificar();
            this.add_refugio = true;
            this.jButtonAdd_refugio.setBackground(new java.awt.Color(70, 200, 74));

            this.editableJtextFields_refugio(true);
            this.setJtextFieldsClean_refugio();
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_refugio(false);
            this.setJtextFieldsClean_refugio();
        }

    }//GEN-LAST:event_jButtonAdd_refugioActionPerformed

    private void jButtonEliminar_refugioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar_refugioActionPerformed

        if(!this.eliminar_refugio){
            this.setfalseAdd_eliminar_modificar();
            this.eliminar_refugio = true;
            this.jButtonEliminar_refugio.setBackground(new java.awt.Color(70, 200, 74));

            this.editableJtextFields_refugio(false);
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_refugio(false);
        }

    }//GEN-LAST:event_jButtonEliminar_refugioActionPerformed

    private void jButtonModificar_refugioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificar_refugioActionPerformed

        if(!this.modificar_refugio){
            this.setfalseAdd_eliminar_modificar();
            this.modificar_refugio = true;
            this.jButtonModificar_refugio.setBackground(new java.awt.Color(70, 200, 74));
            this.editableJtextFields_refugio(true);

            if(!this.jListRefugios.isSelectionEmpty()){
                mostrarDatosRefugioSeleccionado();
            }
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_refugio(false);
        }

    }//GEN-LAST:event_jButtonModificar_refugioActionPerformed

    private void jButtonGuardar_refugioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardar_refugioActionPerformed


        this.editableJtextFields_refugio(false);
        
        if(this.add_refugio){
            
            if(!this.jTextFieldNombre_refugio.getText().equals("") && 
                    !this.jTextFieldSucursales_refugio.getText().equals("") && 
                    !this.jTextFieldCiudad_refugio.getText().equals("") && 
                    !(this.jComboBoxAbierto_refugio.getSelectedIndex() == 0)){
                
                boolean auxAbierto = false;
                if (this.jComboBoxAbierto_refugio.getSelectedIndex() == 1){
                    auxAbierto = true;
                }
                
                
                this.listaRefugios.add(new Refugio(
                        Integer.valueOf(this.controladorDeRefugios.generateId()), 
                        this.jTextFieldNombre_refugio.getText(),
                        this.jTextFieldCiudad_refugio.getText(), 
                        auxAbierto,
                        Integer.valueOf(this.jTextFieldSucursales_refugio.getText()))
                        );
                
                printListRefugios();
                this.controladorDeRefugios.persistenciaDeDatos(this.listaRefugios, this.listaRefugios.size() -1, 1);
                
            }


            
        }else if(this.eliminar_refugio){
           
            if(!this.jListRefugios.isSelectionEmpty()){
                int selectedIndex = this.jListRefugios.getSelectedIndex();

                this.controladorDeGruposEspecialistas.persistenciaDeDatos(this.listaGruposEspecialistas, this.listaRefugios.get(selectedIndex).getId(), 4);
                this.controladorDeRefugios.persistenciaDeDatos(this.listaRefugios, selectedIndex, 3);
                
                this.eliminarReferenciasRefugio(this.listaRefugios.get(selectedIndex).getId());
                this.listaRefugios.remove(selectedIndex);
                printListRefugios();
            }
            
        }else if(this.modificar_refugio){
            
            if(!this.jListRefugios.isSelectionEmpty()){
                
                int selectedIndex = this.jListRefugios.getSelectedIndex();
                this.listaRefugios.get(selectedIndex).setSucursales(Integer.valueOf(this.jTextFieldSucursales_refugio.getText()) );
                this.listaRefugios.get(selectedIndex).setNombre(this.jTextFieldNombre_refugio.getText());
                this.listaRefugios.get(selectedIndex).setCiudad(this.jTextFieldCiudad_refugio.getText());
                if(this.jComboBoxAbierto_refugio.getSelectedIndex() == 1){
                    this.listaRefugios.get(selectedIndex).setAbierto(true);
                }else if (this.jComboBoxAbierto_refugio.getSelectedIndex() == 2 ){
                    this.listaRefugios.get(selectedIndex).setAbierto(false);
                }

                printListRefugios();
                this.controladorDeRefugios.persistenciaDeDatos(this.listaRefugios, selectedIndex, 2);
            }

        }
        
        
        this.setJtextFieldsClean_refugio();
        this.setfalseAdd_eliminar_modificar();
    }//GEN-LAST:event_jButtonGuardar_refugioActionPerformed

    private void jButtonCancelar_refugioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelar_refugioActionPerformed

        this.editableJtextFields_refugio(false);
        this.setJtextFieldsClean_refugio();

        this.setfalseAdd_eliminar_modificar();

    }//GEN-LAST:event_jButtonCancelar_refugioActionPerformed

    private void jListRefugiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListRefugiosMouseClicked
        mostrarDatosRefugioSeleccionado();
    }//GEN-LAST:event_jListRefugiosMouseClicked

    
    
        /*  BOTONES DE GRUPO ESPECIALISTAS  */
    
    
  
    private void jButtonAdd_grupoEspecialistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdd_grupoEspecialistaActionPerformed

        if(!this.add_grupoEspecialista){
            this.setfalseAdd_eliminar_modificar();
            this.add_grupoEspecialista = true;
            this.jButtonAdd_grupoEspecialista.setBackground(new java.awt.Color(70, 200, 74));

            this.editableJtextFields_grupoEspecialista(true);
            this.setJtextFieldsClean_grupoEspecialista();
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_grupoEspecialista(false);
            this.setJtextFieldsClean_grupoEspecialista();
        }
        
    }//GEN-LAST:event_jButtonAdd_grupoEspecialistaActionPerformed

    private void jButtonEliminar_grupoEspecialistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar_grupoEspecialistaActionPerformed

        if(!this.eliminar_grupoEspecialista){
            this.setfalseAdd_eliminar_modificar();
            this.eliminar_grupoEspecialista = true;
            this.jButtonEliminar_grupoEspecialista.setBackground(new java.awt.Color(70, 200, 74));

            this.editableJtextFields_grupoEspecialista(false);
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_grupoEspecialista(false);
        }
        
    }//GEN-LAST:event_jButtonEliminar_grupoEspecialistaActionPerformed

    private void jButtonModificar_grupoEspecialistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificar_grupoEspecialistaActionPerformed

        if(!this.modificar_grupoEspecialista){
            this.setfalseAdd_eliminar_modificar();
            this.modificar_grupoEspecialista = true;
            this.jButtonModificar_grupoEspecialista.setBackground(new java.awt.Color(70, 200, 74));
            this.editableJtextFields_grupoEspecialista(true);

            if(!this.jListGrupoEspecialista.isSelectionEmpty()){
                mostrarDatosGrupoEspecialistaSeleccionado();
            }
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_grupoEspecialista(false);
        }

    }//GEN-LAST:event_jButtonModificar_grupoEspecialistaActionPerformed

    private void jButtonGuardar_grupoEspecialistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardar_grupoEspecialistaActionPerformed

        this.editableJtextFields_grupoEspecialista(false);
        
        if(this.add_grupoEspecialista){
            
            if(!this.jTextFieldInformes_grupoEspecialista.getText().equals("") && 
                    !this.jTextFieldEspecialidad_grupoEspecialista.getText().equals("") && 
                    !this.jTextFieldCapacidad_grupoEspecialista.getText().equals("") && 
                    !(this.jComboBoxExpedicion_grupoEspecialista.getSelectedIndex() == 0)){
                
                boolean auxExpedicion = false;
                if (this.jComboBoxExpedicion_grupoEspecialista.getSelectedIndex() == 1){
                    auxExpedicion = true;
                }
                
                
                this.listaGruposEspecialistas.add(new Grupoespecialista(
                        Integer.valueOf(this.controladorDeGruposEspecialistas.generateId()), 
                        this.jTextFieldEspecialidad_grupoEspecialista.getText(),
                        Integer.valueOf(this.jTextFieldCapacidad_grupoEspecialista.getText()), 
                        auxExpedicion,
                        Integer.valueOf(this.jTextFieldInformes_grupoEspecialista.getText()),
                        this.getIdReferenceRefugio_GrupoEspecialista(this.jComboBoxIdRefenciaRefugio_grupoEspecialista.getSelectedIndex()))
                        );
                
                printListGruposEspecialistas();
                this.controladorDeGruposEspecialistas.persistenciaDeDatos(this.listaGruposEspecialistas, this.listaGruposEspecialistas.size() -1, 1);
            }


            
        }else if(this.eliminar_grupoEspecialista){
           
            if(!this.jListGrupoEspecialista.isSelectionEmpty()){
                int selectedIndex = this.jListGrupoEspecialista.getSelectedIndex();

              this.controladorDeCuidadores.persistenciaDeDatos(this.listaCuidadores,this.listaGruposEspecialistas.get(selectedIndex).getId(), 4);
                this.controladorDeGruposEspecialistas.persistenciaDeDatos(this.listaGruposEspecialistas, selectedIndex, 3);
                
                this.eliminarReferenciasGrupoEspecialista(this.listaGruposEspecialistas.get(selectedIndex).getId());
                this.listaGruposEspecialistas.remove(selectedIndex);
                printListGruposEspecialistas();

            }
            
        }else if(this.modificar_grupoEspecialista){
            
            if(!this.jListGrupoEspecialista.isSelectionEmpty()){
                
                int selectedIndex = this.jListGrupoEspecialista.getSelectedIndex();
                this.listaGruposEspecialistas.get(selectedIndex).setCapacidad(Integer.valueOf(this.jTextFieldCapacidad_grupoEspecialista.getText()) );
                this.listaGruposEspecialistas.get(selectedIndex).setEspecialidad(this.jTextFieldEspecialidad_grupoEspecialista.getText());
                this.listaGruposEspecialistas.get(selectedIndex).setInformes(Integer.valueOf(this.jTextFieldInformes_grupoEspecialista.getText()) );
                this.listaGruposEspecialistas.get(selectedIndex).setRefugio(this.getIdReferenceRefugio_GrupoEspecialista(this.jComboBoxIdRefenciaRefugio_grupoEspecialista.getSelectedIndex()));
                if(this.jComboBoxExpedicion_grupoEspecialista.getSelectedIndex() == 1){
                    this.listaGruposEspecialistas.get(selectedIndex).setExpedicion(true);
                }else if (this.jComboBoxExpedicion_grupoEspecialista.getSelectedIndex() == 2 ){
                    this.listaGruposEspecialistas.get(selectedIndex).setExpedicion(false);
                }

                printListGruposEspecialistas();
                this.controladorDeGruposEspecialistas.persistenciaDeDatos(this.listaGruposEspecialistas, selectedIndex, 2);
            }
        }
                
        
        this.setJtextFieldsClean_grupoEspecialista();
        this.setfalseAdd_eliminar_modificar();
    }//GEN-LAST:event_jButtonGuardar_grupoEspecialistaActionPerformed

    private void jButtonCancelar_grupoEspecialistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelar_grupoEspecialistaActionPerformed

        this.editableJtextFields_grupoEspecialista(false);
        this.setJtextFieldsClean_grupoEspecialista();

        this.setfalseAdd_eliminar_modificar();
        
    }//GEN-LAST:event_jButtonCancelar_grupoEspecialistaActionPerformed

    private void jTextFieldCapacidad_grupoEspecialistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCapacidad_grupoEspecialistaActionPerformed
    }//GEN-LAST:event_jTextFieldCapacidad_grupoEspecialistaActionPerformed

    private void jListGrupoEspecialistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListGrupoEspecialistaMouseClicked
        mostrarDatosGrupoEspecialistaSeleccionado();
    }//GEN-LAST:event_jListGrupoEspecialistaMouseClicked

    
    
        /*  BOTONES DE CUIDADORES  */
    
    
 
    private void jButtonAdd_cuidadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdd_cuidadorActionPerformed

        if(!this.add_cuidador){
            this.setfalseAdd_eliminar_modificar();
            this.add_cuidador = true;
            this.jButtonAdd_cuidador.setBackground(new java.awt.Color(70, 200, 74));

            this.editableJtextFields_cuidador(true);
            this.setJtextFieldsClean_cuidador();
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_cuidador(false);
            this.setJtextFieldsClean_cuidador();
        }

    }//GEN-LAST:event_jButtonAdd_cuidadorActionPerformed

    private void jButtonEliminar_cuidadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar_cuidadorActionPerformed

        if(!this.eliminar_cuidador){
            this.setfalseAdd_eliminar_modificar();
            this.eliminar_cuidador = true;
            this.jButtonEliminar_cuidador.setBackground(new java.awt.Color(70, 200, 74));

            this.editableJtextFields_cuidador(false);
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_cuidador(false);
        }
        
    }//GEN-LAST:event_jButtonEliminar_cuidadorActionPerformed

    private void jButtonModificar_cuidadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificar_cuidadorActionPerformed

        if(!this.modificar_cuidador){
            this.setfalseAdd_eliminar_modificar();
            this.modificar_cuidador = true;
            this.jButtonModificar_cuidador.setBackground(new java.awt.Color(70, 200, 74));
            this.editableJtextFields_cuidador(true);

            if(!this.jListCuidadores.isSelectionEmpty()){
                mostrarDatosCuidadorSeleccionado();
            }
        }else{
            this.setfalseAdd_eliminar_modificar();
            this.editableJtextFields_cuidador(false);
        }

    }//GEN-LAST:event_jButtonModificar_cuidadorActionPerformed

    private void jButtonGuardar_cuidadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardar_cuidadorActionPerformed



        this.editableJtextFields_cuidador(false);
        
        if(this.add_cuidador){
            
            if(!this.jTextFieldNombre_cuidador.getText().equals("") && 
                    !this.jTextFieldEdad_cuidador.getText().equals("") && 
                    !this.jTextFieldDni_cuidador.getText().equals("") && 
                    !(this.jComboBoxJefe_cuidador.getSelectedIndex() == 0)){
                
                boolean auxJefe = false;
                if (this.jComboBoxJefe_cuidador.getSelectedIndex() == 1){
                    auxJefe = true;
                }
                
                
                this.listaCuidadores.add(new Cuidador(
                        Integer.valueOf(this.controladorDeCuidadores.generateId()), 
                        this.jTextFieldNombre_cuidador.getText(),
                        Integer.valueOf(this.jTextFieldEdad_cuidador.getText()), 
                        auxJefe,
                        this.jTextFieldDni_cuidador.getText(),
                        this.getIdReferenceGrupoEspecialista_Cuidador(this.jComboBoxIdRefenciaGrupoEspecialista_Cuidador.getSelectedIndex()))
                        );
                
                printListCuidadores();
                this.controladorDeCuidadores.persistenciaDeDatos(this.listaCuidadores, this.listaCuidadores.size() -1, 1);
            }


            
        }else if(this.eliminar_cuidador){
           
            if(!this.jListCuidadores.isSelectionEmpty()){
                int selectedIndex = this.jListCuidadores.getSelectedIndex();


                this.controladorDeTortugas.persistenciaDeDatos(this.listaTortugas, this.listaCuidadores.get(selectedIndex).getId(), 4);
                this.controladorDeCuidadores.persistenciaDeDatos(this.listaCuidadores, selectedIndex, 3);
                
                
                this.eliminarReferenciasCuidador(this.listaCuidadores.get(selectedIndex).getId());
                this.listaCuidadores.remove(selectedIndex);
                printListCuidadores();

            }
            
        }else if(this.modificar_cuidador){
            
            if(!this.jListCuidadores.isSelectionEmpty()){
                
                int selectedIndex = this.jListCuidadores.getSelectedIndex();
                this.listaCuidadores.get(selectedIndex).setEdad(Integer.valueOf(this.jTextFieldEdad_cuidador.getText()) );
                this.listaCuidadores.get(selectedIndex).setNombre(this.jTextFieldNombre_cuidador.getText());
                this.listaCuidadores.get(selectedIndex).setDni(this.jTextFieldDni_cuidador.getText());
                this.listaCuidadores.get(selectedIndex).setGrupoespecialista(this.getIdReferenceGrupoEspecialista_Cuidador(this.jComboBoxIdRefenciaGrupoEspecialista_Cuidador.getSelectedIndex()));
                if(this.jComboBoxJefe_cuidador.getSelectedIndex() == 1){
                    this.listaCuidadores.get(selectedIndex).setJefe(true);
                }else if (this.jComboBoxJefe_cuidador.getSelectedIndex() == 2 ){
                    this.listaCuidadores.get(selectedIndex).setJefe(false);
                }

                printListCuidadores();
                this.controladorDeCuidadores.persistenciaDeDatos(this.listaCuidadores, selectedIndex, 2);
            }

        }
        
        
        this.setJtextFieldsClean_cuidador();
        this.setfalseAdd_eliminar_modificar();

    }//GEN-LAST:event_jButtonGuardar_cuidadorActionPerformed

    private void jButtonCancelar_cuidadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelar_cuidadorActionPerformed

        this.editableJtextFields_cuidador(false);
        this.setJtextFieldsClean_cuidador();

        this.setfalseAdd_eliminar_modificar();
        
    }//GEN-LAST:event_jButtonCancelar_cuidadorActionPerformed

    private void jTextFieldEdad_cuidadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEdad_cuidadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEdad_cuidadorActionPerformed

    private void jListCuidadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListCuidadoresMouseClicked
        mostrarDatosCuidadorSeleccionado();
    }//GEN-LAST:event_jListCuidadoresMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        this.setfalseAdd_eliminar_modificar();
        
        this.setJtextFieldsClean_refugio();
        this.setJtextFieldsClean_grupoEspecialista();
        this.setJtextFieldsClean_cuidador();
        this.setJtextFieldsClean_tortuga();
        
        this.editableJtextFields_refugio(false);
        this.editableJtextFields_grupoEspecialista(false);
        this.editableJtextFields_cuidador(false);
        this.editableJtextFields_tortuga(false);

        recargarJComboBoxIdReferenciados();
        
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void recargarJComboBoxIdReferenciados(){
        
        this.jComboBoxIdRefenciaRefugio_grupoEspecialista.removeAllItems();
        this.jComboBoxIdRefenciaGrupoEspecialista_Cuidador.removeAllItems();
        this.jComboBoxIdRefenciaCuidador_Tortuga.removeAllItems();
        
        this.jComboBoxIdRefenciaRefugio_grupoEspecialista.addItem("Sin Refugio");
        for (Refugio refugio: this.listaRefugios){
            this.jComboBoxIdRefenciaRefugio_grupoEspecialista.addItem(refugio.getNombre());
        }
        
        this.jComboBoxIdRefenciaGrupoEspecialista_Cuidador.addItem("Sin Especialidad");
        for (Grupoespecialista grupoEspecialista: this.listaGruposEspecialistas){
            this.jComboBoxIdRefenciaGrupoEspecialista_Cuidador.addItem(grupoEspecialista.getEspecialidad());
        }
        
        this.jComboBoxIdRefenciaCuidador_Tortuga.addItem("Sin Cuidador");
        for (Cuidador cuidador: this.listaCuidadores){
            this.jComboBoxIdRefenciaCuidador_Tortuga.addItem(cuidador.getNombre());
        }
        
    }
    
    private void mostrarDatosRefugioSeleccionado(){
        
        int selectedIndex = this.jListRefugios.getSelectedIndex();
        if(this.listaRefugios.size() != 0){
            this.jTextFieldNombre_refugio.setText(String.valueOf(this.listaRefugios.get(selectedIndex).getNombre()));
            this.jTextFieldCiudad_refugio.setText(String.valueOf(this.listaRefugios.get(selectedIndex).getCiudad()));
            this.jTextFieldSucursales_refugio.setText(String.valueOf(this.listaRefugios.get(selectedIndex).getSucursales()));
            if(this.listaRefugios.get(selectedIndex).getAbierto()){
                this.jComboBoxAbierto_refugio.setSelectedIndex(1);
            }else{
                this.jComboBoxAbierto_refugio.setSelectedIndex(2);
            }
        }
    }
    
    private void mostrarDatosGrupoEspecialistaSeleccionado(){
        
        int selectedIndex = this.jListGrupoEspecialista.getSelectedIndex();
        if(this.listaGruposEspecialistas.size() != 0){
            this.jTextFieldEspecialidad_grupoEspecialista.setText(String.valueOf(this.listaGruposEspecialistas.get(selectedIndex).getEspecialidad()));
            this.jTextFieldInformes_grupoEspecialista.setText(String.valueOf(this.listaGruposEspecialistas.get(selectedIndex).getInformes()));
            this.jTextFieldCapacidad_grupoEspecialista.setText(String.valueOf(this.listaGruposEspecialistas.get(selectedIndex).getCapacidad()));
            this.jComboBoxIdRefenciaRefugio_grupoEspecialista.setSelectedIndex(this.getArrayListPositionWithIdReferenceRefugio_GrupoEspecialista(this.listaGruposEspecialistas.get(selectedIndex).getRefugio().getId()));
            if(this.listaGruposEspecialistas.get(selectedIndex).getExpedicion()){
                this.jComboBoxExpedicion_grupoEspecialista.setSelectedIndex(1);
            }else{
                this.jComboBoxExpedicion_grupoEspecialista.setSelectedIndex(2);
            }
        }
    }
    
    private void mostrarDatosCuidadorSeleccionado(){
        
        int selectedIndex = this.jListCuidadores.getSelectedIndex();
        if(this.listaCuidadores.size() != 0){
            this.jTextFieldNombre_cuidador.setText(String.valueOf(this.listaCuidadores.get(selectedIndex).getNombre()));
            this.jTextFieldDni_cuidador.setText(String.valueOf(this.listaCuidadores.get(selectedIndex).getDni()));
            this.jTextFieldEdad_cuidador.setText(String.valueOf(this.listaCuidadores.get(selectedIndex).getEdad()));
            this.jComboBoxIdRefenciaGrupoEspecialista_Cuidador.setSelectedIndex(this.getArrayListPositionWithIdReferenceGrupoEspecialista_Cuidador(this.listaCuidadores.get(selectedIndex).getGrupoespecialista().getId()));
            if(this.listaCuidadores.get(selectedIndex).getJefe()){
                this.jComboBoxJefe_cuidador.setSelectedIndex(1);
            }else{
                this.jComboBoxJefe_cuidador.setSelectedIndex(2);
            }
        }
    }
    
    private void mostrarDatosTortugaSeleccionada(){
        
        int selectedIndex = this.jListTortugas.getSelectedIndex();
        if(this.listaTortugas.size() != 0){
            this.jTextFieldEdad_tortuga.setText(String.valueOf(this.listaTortugas.get(selectedIndex).getEdad()));
            this.jTextFieldApodo_tortuga.setText(String.valueOf(this.listaTortugas.get(selectedIndex).getApodo()));
            this.jTextFieldPeso_tortuga.setText(String.valueOf(this.listaTortugas.get(selectedIndex).getPeso()));
            this.jComboBoxIdRefenciaCuidador_Tortuga.setSelectedIndex(this.getArrayListPositionWithIdReferenceCuidador_Tortuga(this.listaTortugas.get(selectedIndex).getCuidador().getId()));
            if(this.listaTortugas.get(selectedIndex).getHiberna()){
                this.jComboBoxHiberna_tortuga.setSelectedIndex(1);
            }else{
                this.jComboBoxHiberna_tortuga.setSelectedIndex(2);
            }   
        } 
    }
    
    
    private void setfalseAdd_eliminar_modificar(){
        
        this.add_refugio = false;
        this.eliminar_refugio = false;
        this.modificar_refugio = false;
        this.jButtonAdd_refugio.setBackground(new java.awt.Color(61, 65, 68));
        this.jButtonEliminar_refugio.setBackground(new java.awt.Color(61, 65, 68));
        this.jButtonModificar_refugio.setBackground(new java.awt.Color(61, 65, 68));       
        
        this.add_grupoEspecialista = false;
        this.eliminar_grupoEspecialista = false;
        this.modificar_grupoEspecialista = false;
        this.jButtonAdd_grupoEspecialista.setBackground(new java.awt.Color(61, 65, 68));
        this.jButtonEliminar_grupoEspecialista.setBackground(new java.awt.Color(61, 65, 68));
        this.jButtonModificar_grupoEspecialista.setBackground(new java.awt.Color(61, 65, 68));

        this.add_cuidador = false;
        this.eliminar_cuidador = false;
        this.modificar_cuidador = false;
        this.jButtonAdd_cuidador.setBackground(new java.awt.Color(61, 65, 68));
        this.jButtonEliminar_cuidador.setBackground(new java.awt.Color(61, 65, 68));
        this.jButtonModificar_cuidador.setBackground(new java.awt.Color(61, 65, 68));  
        
        this.add_tortuga = false;
        this.eliminar_tortuga = false;
        this.modificar_tortuga = false;
        this.jButtonAdd_tortuga.setBackground(new java.awt.Color(61, 65, 68));
        this.jButtonEliminar_tortuga.setBackground(new java.awt.Color(61, 65, 68));
        this.jButtonModificar_tortuga.setBackground(new java.awt.Color(61, 65, 68));                
    }
    
    private void setJtextFieldsClean_refugio(){
        
        this.jTextFieldNombre_refugio.setText("");
        this.jTextFieldCiudad_refugio.setText("");
        this.jTextFieldSucursales_refugio.setText("");
                        
    }
    
    private void setJtextFieldsClean_grupoEspecialista(){
        
        this.jTextFieldEspecialidad_grupoEspecialista.setText("");
        this.jTextFieldInformes_grupoEspecialista.setText("");
        this.jTextFieldCapacidad_grupoEspecialista.setText("");
                        
    }
        
    private void setJtextFieldsClean_cuidador(){
        
        this.jTextFieldNombre_cuidador.setText("");
        this.jTextFieldDni_cuidador.setText("");
        this.jTextFieldEdad_cuidador.setText("");
                        
    }
            
    private void setJtextFieldsClean_tortuga(){
        
        this.jTextFieldEdad_tortuga.setText("");
        this.jTextFieldApodo_tortuga.setText("");
        this.jTextFieldPeso_tortuga.setText("");
                        
    }
    
    private void editableJtextFields_refugio(boolean setEnabled){
      
        this.jTextFieldNombre_refugio.setEditable(setEnabled);
        this.jTextFieldCiudad_refugio.setEditable(setEnabled);
        this.jTextFieldSucursales_refugio.setEditable(setEnabled);
        this.jComboBoxAbierto_refugio.enable(setEnabled);
    }
    
    private void editableJtextFields_grupoEspecialista(boolean setEnabled){
      
        this.jTextFieldEspecialidad_grupoEspecialista.setEditable(setEnabled);
        this.jTextFieldInformes_grupoEspecialista.setEditable(setEnabled);
        this.jTextFieldCapacidad_grupoEspecialista.setEditable(setEnabled);
        this.jComboBoxExpedicion_grupoEspecialista.enable(setEnabled);
        this.jComboBoxIdRefenciaRefugio_grupoEspecialista.enable(setEnabled);
    }
            
    private void editableJtextFields_cuidador(boolean setEnabled){
      
        this.jTextFieldNombre_cuidador.setEditable(setEnabled);
        this.jTextFieldDni_cuidador.setEditable(setEnabled);
        this.jTextFieldEdad_cuidador.setEditable(setEnabled);
        this.jComboBoxJefe_cuidador.enable(setEnabled);
        this.jComboBoxIdRefenciaGrupoEspecialista_Cuidador.enable(setEnabled);
    }
    
    private void editableJtextFields_tortuga(boolean setEnabled){
      
        this.jTextFieldEdad_tortuga.setEditable(setEnabled);
        this.jTextFieldApodo_tortuga.setEditable(setEnabled);
        this.jTextFieldPeso_tortuga.setEditable(setEnabled);
        this.jComboBoxHiberna_tortuga.enable(setEnabled);
        this.jComboBoxIdRefenciaCuidador_Tortuga.enable(setEnabled);
    }
    
    private int getArrayListPositionWithIdReferenceRefugio_GrupoEspecialista(int idReferenceRefugio){
        
        int arrayListPosition = 0;
        if(idReferenceRefugio >= 1){
            int posicion = 0;
            int i = 0;
            for(Refugio refugio: this.listaRefugios){

                if(refugio.getId() == idReferenceRefugio){
                    posicion = i;
                }
                i++;
            }
            arrayListPosition = posicion + 1;
        }else{
            arrayListPosition = 0;
        }

        return arrayListPosition;        
    }
    
    private int getArrayListPositionWithIdReferenceGrupoEspecialista_Cuidador(int idReferenceGrupoEspecialista){
        
        int arrayListPosition = 0;
        if(idReferenceGrupoEspecialista >= 1){
            int posicion = 0;
            int i = 0;
            for(Grupoespecialista grupoEspecialista: this.listaGruposEspecialistas){

                if(grupoEspecialista.getId() == idReferenceGrupoEspecialista){
                    posicion = i;
                }
                i++;
            }
            arrayListPosition = posicion + 1;
        }else{
            arrayListPosition = 0;
        }
       
        return arrayListPosition;        
    }
    
    private int getArrayListPositionWithIdReferenceCuidador_Tortuga(int idReferenceCuidador){
        
        int arrayListPosition = 0;
        if(idReferenceCuidador >= 1){
            int posicion = 0;
            int i = 0;
            for(Cuidador cuidador: this.listaCuidadores){

                if(cuidador.getId() == idReferenceCuidador){
                    posicion = i;
                }
                i++;
            }
            arrayListPosition = posicion + 1;
        }else{
            arrayListPosition = 0;
        }

        return arrayListPosition;        
    }
    
    private Refugio getIdReferenceRefugio_GrupoEspecialista(int arrayListRefugioPosition){
        
        Refugio refugio;
        if(arrayListRefugioPosition >= 1){
            refugio = this.listaRefugios.get(arrayListRefugioPosition - 1);        
        }else{
            refugio = none_refugio;
        }
        return refugio;
    }
    
    private Grupoespecialista getIdReferenceGrupoEspecialista_Cuidador(int arrayListGrupoEspecialistaPosition){
        
        Grupoespecialista grupoespecialista;
        if(arrayListGrupoEspecialistaPosition >= 1){
            grupoespecialista = this.listaGruposEspecialistas.get(arrayListGrupoEspecialistaPosition - 1);
        }else{
            grupoespecialista = none_grupoespecialista;
        }
        return grupoespecialista;
    }
    
    private Cuidador getIdReferenceCuidador_Tortuga(int arrayListCuidadorPosition){
        
        Cuidador cuidador;
        if(arrayListCuidadorPosition >= 1){
            cuidador = this.listaCuidadores.get(arrayListCuidadorPosition - 1);
        }else{
            cuidador = none_cuidador;
        }
        return cuidador;        
    }
    
    private void eliminarReferenciasRefugio(int idReference){
        
        for(Grupoespecialista grupoEspecialista: this.listaGruposEspecialistas){
            
            if(grupoEspecialista.getRefugio().getId()== idReference){
                grupoEspecialista.setRefugio(none_refugio);
            }
        }
    }
    
    private void eliminarReferenciasGrupoEspecialista(int idReference){
        
        for(Cuidador cuidador: this.listaCuidadores){
            
            if(cuidador.getGrupoespecialista().getId()== idReference){
                cuidador.setGrupoespecialista(none_grupoespecialista);
            }
        }
    }
    
    private void eliminarReferenciasCuidador(int idReference){
        
        for(Tortuga tortuga: this.listaTortugas){
            
            if(tortuga.getCuidador().getId()== idReference){
                tortuga.setCuidador(none_cuidador);
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run(){
                new JFrameMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd_cuidador;
    private javax.swing.JButton jButtonAdd_grupoEspecialista;
    private javax.swing.JButton jButtonAdd_refugio;
    private javax.swing.JButton jButtonAdd_tortuga;
    private javax.swing.JButton jButtonCancelar_cuidador;
    private javax.swing.JButton jButtonCancelar_grupoEspecialista;
    private javax.swing.JButton jButtonCancelar_refugio;
    private javax.swing.JButton jButtonCancelar_tortuga;
    private javax.swing.JButton jButtonEliminar_cuidador;
    private javax.swing.JButton jButtonEliminar_grupoEspecialista;
    private javax.swing.JButton jButtonEliminar_refugio;
    private javax.swing.JButton jButtonEliminar_tortuga;
    private javax.swing.JButton jButtonGuardar_cuidador;
    private javax.swing.JButton jButtonGuardar_grupoEspecialista;
    private javax.swing.JButton jButtonGuardar_refugio;
    private javax.swing.JButton jButtonGuardar_tortuga;
    private javax.swing.JButton jButtonModificar_cuidador;
    private javax.swing.JButton jButtonModificar_grupoEspecialista;
    private javax.swing.JButton jButtonModificar_refugio;
    private javax.swing.JButton jButtonModificar_tortuga;
    private javax.swing.JComboBox<String> jComboBoxAbierto_refugio;
    private javax.swing.JComboBox<String> jComboBoxExpedicion_grupoEspecialista;
    private javax.swing.JComboBox<String> jComboBoxHiberna_tortuga;
    private javax.swing.JComboBox<String> jComboBoxIdRefenciaCuidador_Tortuga;
    private javax.swing.JComboBox<String> jComboBoxIdRefenciaGrupoEspecialista_Cuidador;
    private javax.swing.JComboBox<String> jComboBoxIdRefenciaRefugio_grupoEspecialista;
    private javax.swing.JComboBox<String> jComboBoxJefe_cuidador;
    private javax.swing.JLabel jLabelAbierto_refugio;
    private javax.swing.JLabel jLabelApodo_tortuga;
    private javax.swing.JLabel jLabelCapacidad_grupoEspecialista;
    private javax.swing.JLabel jLabelCiudad_refugio;
    private javax.swing.JLabel jLabelDni_cuidador;
    private javax.swing.JLabel jLabelEdad_cuidador;
    private javax.swing.JLabel jLabelEdad_tortuga;
    private javax.swing.JLabel jLabelEspecialidad_grupoEspecialista;
    private javax.swing.JLabel jLabelExpedicion_grupoEspecialista;
    private javax.swing.JLabel jLabelHiberna_tortuga;
    private javax.swing.JLabel jLabelIdRefenciaCuidador_Tortuga;
    private javax.swing.JLabel jLabelIdRefenciaGrupoEspecialista_Cuidador;
    private javax.swing.JLabel jLabelIdRefenciaRefugio_grupoEspecialista;
    private javax.swing.JLabel jLabelInformes_grupoEspecialista;
    private javax.swing.JLabel jLabelJefe_cuidador;
    private javax.swing.JLabel jLabelNombre_cuidador;
    private javax.swing.JLabel jLabelNombre_refugio;
    private javax.swing.JLabel jLabelPeso_tortuga;
    private javax.swing.JLabel jLabelSucursales_refugio;
    private javax.swing.JList<String> jListCuidadores;
    private javax.swing.JList<String> jListGrupoEspecialista;
    private javax.swing.JList<String> jListRefugios;
    private javax.swing.JList<String> jListTortugas;
    private javax.swing.JPanel jPanelCuidador;
    private javax.swing.JPanel jPanelGrupoEspecialistas;
    private javax.swing.JPanel jPanelRefugio;
    private javax.swing.JPanel jPanelTortugas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldApodo_tortuga;
    private javax.swing.JTextField jTextFieldCapacidad_grupoEspecialista;
    private javax.swing.JTextField jTextFieldCiudad_refugio;
    private javax.swing.JTextField jTextFieldDni_cuidador;
    private javax.swing.JTextField jTextFieldEdad_cuidador;
    private javax.swing.JTextField jTextFieldEdad_tortuga;
    private javax.swing.JTextField jTextFieldEspecialidad_grupoEspecialista;
    private javax.swing.JTextField jTextFieldInformes_grupoEspecialista;
    private javax.swing.JTextField jTextFieldNombre_cuidador;
    private javax.swing.JTextField jTextFieldNombre_refugio;
    private javax.swing.JTextField jTextFieldPeso_tortuga;
    private javax.swing.JTextField jTextFieldSucursales_refugio;
    // End of variables declaration//GEN-END:variables
}
