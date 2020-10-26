package com.View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.DAO.CarroDAO;
import com.Entity.Carro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerCarro extends Application implements Initializable {
	CarroDAO dao = new CarroDAO();
	
	List<Carro> listaCarros = new ArrayList<Carro>();

	@FXML
	private TextField textFieldModelo;

	@FXML
	private TextField textFieldValor;

	@FXML
	private TextField textFieldMarca;

	@FXML
	private TextField textFieldAno;

	@FXML
	private TextArea textAreaCarros;

	@FXML
	private TextField textField_ID;
	@FXML
	private Label labelTextId;

	@FXML
	private Label labelTextIdInserted;
	@FXML
	private Label qtd;

	@FXML
	private Label labelQtd;
	

	@FXML
	void ExcluirCarro(ActionEvent event) {
    	String busca= textField_ID.getText();
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Deletar Produto");
    	alert.setHeaderText("Deletar");
    	alert.setContentText("Tem certeza que deseja deletar?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
            dao.Deletar(busca, listaCarros);
        	limpaCampos();
        	listarCarros();
    	}
    	
	}

	@FXML
	void PesquisarCarroID(ActionEvent event) {
		String buscar= textField_ID.getText();
    	Carro carro = null;
    	if (!buscar.equals("")) {
			try {
			carro = new CarroDAO().findByModelo(buscar, listaCarros);
			} catch (Exception e) {
			}
			if (carro != null) { 
				labelTextId.setVisible(true);
				labelTextIdInserted.setVisible(true);
				labelTextIdInserted.setText(carro.getModelo());
				textFieldMarca.setText(carro.getMarca());
				textFieldModelo.setText(carro.getModelo());
				textFieldAno.setText(carro.getAno() + "");
				textFieldValor.setText(carro.getValor() + "");
			}
		}

	}

	@FXML
	void alterarCarros(ActionEvent event) {
		Carro carro = pegaCarros();
    	String busca = textField_ID.getText();
    	textFieldMarca.setText(carro.getMarca());
		textFieldModelo.setText(carro.getModelo());
		textFieldAno.setText(carro.getAno() +"") ;
		textFieldValor.setText(carro.getValor() +"");
    	dao.Alterar(carro,busca, listaCarros );
    	listarCarros();
	}

	private void limpaCampos() {
		textFieldModelo.clear();
		textFieldMarca.clear();
		textFieldAno.clear();
		textFieldValor.clear();
		textFieldMarca.requestFocus();
		labelTextId.setVisible(false);
		labelTextIdInserted.setVisible(false);
		labelTextIdInserted.setText("");

	}

	private Carro pegaCarros() {
		return new Carro(textFieldMarca.getText(),
				textFieldModelo.getText(), 
				Integer.valueOf(textFieldAno.getText()),
				Float.valueOf(textFieldValor.getText()
						));
	}

	public void execute() {
		launch();
	}

	@FXML
	void inserirCarros(ActionEvent event) {
    	Carro carro = pegaCarros();
    	listaCarros.add(carro);
		limpaCampos();
		listarCarros();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listarCarros();
	}

	private void listarCarros() {

		textAreaCarros.clear();
		listaCarros.forEach(carro -> {
			textAreaCarros.appendText(carro.toString() + "\n");
		});

	}
	   @FXML
	    void Sair(ActionEvent event) {
		  	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Sair da Aplicação");
	    	alert.setContentText("Saindo da aplicação !!!");
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){           
	    		System.exit(0);
	    	}
	    }

	@Override
	public void start(Stage stage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Carro.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
