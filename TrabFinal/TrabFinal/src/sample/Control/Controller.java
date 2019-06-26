package sample.Control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Model.Album;
import sample.Model.Autor;
import sample.Model.Item;
import sample.Model.Musica;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class Controller implements Initializable {
    @FXML private TableView<Autor> tvAutor;
    @FXML private TableColumn<Autor, String> tcAutorNome;
    @FXML private TableColumn<Autor, String> tcAutorCity;
    @FXML private TableColumn<Autor, DatePicker> tcAutorAno;

    @FXML private TableView<Musica> tvMusica;
    @FXML private TableColumn<Musica, String> tcMusicaNome;
    @FXML private TableColumn<Musica, String> tcMusicaComp;
    @FXML private TableColumn<Musica, String> tcMusicaDuracao;

    @FXML private TableView<Album> tvAlbum;
    @FXML private TableColumn<Album, String> tcTitulo;
    @FXML private TableColumn<Album, String> tcMusicas;
    @FXML private TableColumn<Album, String> tcAutor;

    @FXML private TableView<Item> tvItem;
    @FXML private TableColumn<Item, String> tcTipo;
    @FXML private TableColumn<Item, String> tcAlbum;
    @FXML private TableColumn<Item, String> tcPosicao;

    @FXML private TextField tfNome;
    @FXML private TextField tfCity;
    @FXML private DatePicker datePicker;

    @FXML private TextField tfMusicaNome;
    @FXML private TextField tfMusicaComp;
    @FXML private TextField tfMusicaDuracao;

    @FXML private TextField tfTitulo;
    @FXML private TextArea taMusicas;
    @FXML private TextField tfAutor;

    @FXML private RadioButton cd;
    @FXML private RadioButton k7;
    @FXML private RadioButton vinil;


    private ArrayList<Autor> autores = new ArrayList<>();
    private ArrayList<Musica> musicas = new ArrayList<>();
    private ArrayList<Album> albuns = new ArrayList<>();
    private ArrayList<Item> itens = new ArrayList<>();

    private ObservableList<Musica> observableListMusicas;
    private ObservableList<Album> observableListAlbuns;
    private ObservableList<Autor> observableListAutores;
    private ObservableList<Item> observableListItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readFile();
        gerarTableAutor();
        gerarTableMusica();
        gerarTableAlbum();
        gerarTableItem();
        tvAutor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionaAutor(newValue));
        tvMusica.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionaMusica(newValue));
        tvAlbum.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionaAlbum(newValue));
        tvItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionaItem(newValue));

    }

    public void gerarTableAutor(){

        tcAutorNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tcAutorCity.setCellValueFactory(new PropertyValueFactory<>("Cidade"));
        tcAutorAno.setCellValueFactory(new PropertyValueFactory<>("Nascimento"));
       observableListAutores = FXCollections.observableArrayList(autores);
        tvAutor.setItems(observableListAutores);
    }
    public void gerarTableMusica(){

        tcMusicaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tcMusicaComp.setCellValueFactory(new PropertyValueFactory<>("Compositor"));
        tcMusicaDuracao.setCellValueFactory(new PropertyValueFactory<>("Duracao"));
        observableListMusicas = FXCollections.observableArrayList(musicas);
        tvMusica.setItems(observableListMusicas);
    }
    public void gerarTableAlbum(){

        tcTitulo.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tcAutor.setCellValueFactory(new PropertyValueFactory<>("Autor"));
        tcMusicas.setCellValueFactory(new PropertyValueFactory<>("Musicas"));
        observableListAlbuns = FXCollections.observableArrayList(albuns);
        tvAlbum.setItems(observableListAlbuns);
    }
    public void gerarTableItem(){

        tcTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        tcAlbum.setCellValueFactory(new PropertyValueFactory<>("Album"));
        tcPosicao.setCellValueFactory(new PropertyValueFactory<>("Posicao"));
        observableListItem = FXCollections.observableArrayList(itens);
        tvItem.setItems(observableListItem);
    }

    public void addAutor(){
        Autor autor = new Autor(tfNome.getText(), tfCity.getText(), datePicker.getValue());
        tvAutor.getItems().add(autor);
        File f = new File("autores.bin");

        try(FileOutputStream fos = new FileOutputStream(f);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos)
        ){

            oos.writeObject(autor);

        }catch (IOException e){
            System.out.println("Erro ao escrever o arquivo...");
        }


    }
    public void addAlbum(){
        String check = " ";

        Album album = new Album(tfTitulo.getText(), taMusicas.getText(), tfAutor.getText());

        if(cd.isSelected()){
            check = "CD";
        }else if (k7.isSelected()){
            check = "K7";
        }else if (vinil.isSelected()){
            check = "Vinil";
        }


        tvAlbum.getItems().add(album);
        Item item = new Item(check, tfTitulo.getText(), 0);
        tvItem.getItems().add(item);
    }

    public void addMusica(){
        Musica musica = new Musica(tfMusicaNome.getText(), tfMusicaComp.getText(), tfMusicaDuracao.getText());
        tvMusica.getItems().add(musica);
    }

    public void selecionaAutor(Autor autor){
        if(autor != null) {
            System.out.println(autor.getNome() + " selecionado com sucesso.");
        }
    }
    public void selecionaMusica(Musica musica){
        if(musica != null) {
            System.out.println(musica.getNome() + " selecionado com sucesso.");
        }
    }

    public void selecionaAlbum(Album album){
        if(album != null) {
            System.out.println(album.getNome() + " selecionado com sucesso.");
        }
    }
    public void selecionaItem(Item item){
        if(item != null) {
            System.out.println(item.getTipo() + " selecionado com sucesso.");
        }
    }


    @FXML
    public void removeAutor(){
        Autor autor = tvAutor.getSelectionModel().getSelectedItem();
        if(autor != null) {
            tvAutor.getItems().remove(autor);
        }
    }
    @FXML
    public void removeMusica(){
        Musica musica = tvMusica.getSelectionModel().getSelectedItem();
        if( musica != null) {
            tvMusica.getItems().remove(musica);
        }
    }
    @FXML
    public void removeAlbum(){
        Album album = tvAlbum.getSelectionModel().getSelectedItem();
        if( album != null) {
            tvAlbum.getItems().remove(album);
        }
    }
    @FXML
    public void removeItem(){
        Item item = tvItem.getSelectionModel().getSelectedItem();
        if( item != null) {
            tvItem.getItems().remove(item);
        }
    }
    public void readFile(){
        File f = new File("autores.bin");

        Autor p=null;
        ArrayList<Autor> ps=null;

        try(FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
        ){

            p = (Autor) ois.readObject();
            System.out.println(p);


        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getException());
        }

    }





}
