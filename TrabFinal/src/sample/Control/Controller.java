package sample.Control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.util.function.Predicate;


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

    @FXML private TextField searchAutor;
    @FXML private TextField searchAlbum;
    @FXML private TextField searchMusic;


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
        gerarTableAutor();
        gerarTableMusica();
        gerarTableAlbum();
        gerarTableItem();


    }
    public void pesquisaAutor(){
        FilteredList<Autor> filteredListAutor = new FilteredList<>(observableListAutores, e -> true);


        searchAutor.setOnKeyReleased(event -> {
            searchAutor.textProperty().addListener((observable, oldValue, newValue) ->{

                filteredListAutor.setPredicate((Predicate<? super Autor>) a->{
                    if(newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCase = newValue.toLowerCase();
                   if (a.getNome().toLowerCase().contains(lowerCase)){
                       return true;
                   }
                    return false;

                });
                SortedList<Autor> sortedAutor = new SortedList<>(filteredListAutor);
                sortedAutor.comparatorProperty().bind(tvAutor.comparatorProperty());
                tvAutor.setItems(sortedAutor);
            });

        });



    }
    public void pesquisaAlbum(){
        FilteredList<Album> filteredListAlbum = new FilteredList<>(observableListAlbuns, e -> true);


        searchAlbum.setOnKeyReleased(event -> {
            searchAlbum.textProperty().addListener((observable, oldValue, newValue) ->{

                filteredListAlbum.setPredicate((Predicate<? super Album>) album->{
                    if(newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCase = newValue.toLowerCase();
                    if (album.getNome().toLowerCase().contains(lowerCase)){
                        return true;
                    }
                    else if (album.getAutor().toLowerCase().contains(lowerCase)){
                        return true;
                    }
                    return false;

                });
                SortedList<Album> albumSortedList = new SortedList<>(filteredListAlbum);
                albumSortedList.comparatorProperty().bind(tvAlbum.comparatorProperty());
                tvAlbum.setItems(albumSortedList);
            });

        });
    }
    public void pesquisaMusicas(){
        FilteredList<Musica> musicaFilteredList = new FilteredList<>(observableListMusicas, e -> true);


        searchMusic.setOnKeyReleased(event -> {
            searchMusic.textProperty().addListener((observable, oldValue, newValue) ->{

                musicaFilteredList.setPredicate((Predicate<? super Musica>) musica->{
                    if(newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCase = newValue.toLowerCase();
                    if (musica.getNome().toLowerCase().contains(lowerCase)){
                        return true;
                    }
                    else if (musica.getCompositor().toLowerCase().contains(lowerCase)){
                        return true;
                    }
                    return false;

                });
                SortedList<Musica> musicaSortedList = new SortedList<>(musicaFilteredList);
                musicaSortedList.comparatorProperty().bind(tvMusica.comparatorProperty());
                tvMusica.setItems(musicaSortedList);
            });

        });
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
        observableListAutores.add(autor);
        File f = new File("autores.bin");


        try(FileOutputStream fos = new FileOutputStream(f);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos)
        ){
            for(Autor a: observableListAutores) {
                oos.writeObject(a);
            }

        }catch (IOException e){
            System.out.println("Erro ao escrever o arquivo...");
            e.printStackTrace();
        }
        tfNome.clear();
        tfCity.clear();

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
        observableListAlbuns.add(album);
        Item item = new Item(check, tfTitulo.getText(), 0);
        observableListItem.add(item);

        File f = new File("albuns.bin");

        try(FileOutputStream fos = new FileOutputStream(f);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos)
        ){
            for(Album a: observableListAlbuns) {
                oos.writeObject(a);
            }

        }catch (IOException e){
            System.out.println("Erro ao escrever o arquivo...");
            e.printStackTrace();
        }


        File file = new File("itens.bin");

        try(FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos)
        ){
            for(Item i: observableListItem) {
                oos.writeObject(i);
            }

        }catch (IOException e){
            System.out.println("Erro ao escrever o arquivo...");
            e.printStackTrace();
        }
        tfTitulo.clear();
        taMusicas.clear();
        tfAutor.clear();
    }

    public void addMusica(){
        Musica musica = new Musica(tfMusicaNome.getText(), tfMusicaComp.getText(), tfMusicaDuracao.getText());
        observableListMusicas.add(musica);
        File f = new File("musicas.bin");


        try(FileOutputStream fos = new FileOutputStream(f);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos)
        ){
            for(Musica a: observableListMusicas) {
                oos.writeObject(a);
            }

        }catch (IOException e){
            System.out.println("Erro ao escrever o arquivo...");
            e.printStackTrace();
        }
        tfMusicaNome.clear();
        tfMusicaComp.clear();
        tfMusicaDuracao.clear();
    }



    @FXML
    public void removeAutor(){
        Autor autor = tvAutor.getSelectionModel().getSelectedItem();
        if(autor != null) {
            observableListAutores.remove(autor);


        }
    }
    @FXML
    public void removeMusica(){
        Musica musica = tvMusica.getSelectionModel().getSelectedItem();
        if( musica != null) {
            observableListMusicas.remove(musica);
        }
    }
    @FXML
    public void removeAlbum(){
        Album album = tvAlbum.getSelectionModel().getSelectedItem();
        if( album != null) {
            observableListAlbuns.remove(album);
        }
    }
    @FXML
    public void removeItem(){
        Item item = tvItem.getSelectionModel().getSelectedItem();
        if( item != null) {
            observableListItem.remove(item);
        }
    }




    public void loadAutor(){
        File f = new File("autores.bin");
        Autor a;

        try(FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
        ){

            while(true) {
                a = (Autor) ois.readObject();
                if(a != null) { observableListAutores.add(a); }
                else {
                    break; }
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getException());
        }


    }
    public void loadAlbum(){
        File f = new File("albuns.bin");
        Album a;

        try(FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
        ){

            while(true) {
                a = (Album) ois.readObject();
                if(a != null) { observableListAlbuns.add(a); }
                else {
                    break; }
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getException());
        }
    }
    public void loadMusic(){
        File f = new File("musicas.bin");
        Musica a;

        try(FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
        ){

            while(true) {
                a = (Musica) ois.readObject();
                if(a != null) { observableListMusicas.add(a); }
                else {
                    break; }
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getException());
        }
    }
    public void loadItens(){
        File f = new File("itens.bin");
        Item a;

        try(FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
        ){
            while(true) {
                a = (Item) ois.readObject();
                if(a != null) { observableListItem.add(a); }
                else {
                    break; }
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getException());
        }
    }






}
