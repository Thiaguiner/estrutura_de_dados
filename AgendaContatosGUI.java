import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class AgendaContatosGUI extends Application {
    private Map<String, Contato> contatos = new HashMap<>();
    private TextField nomeField, telefoneField, nascimentoField, emailField, celularField;
    private TextArea listaContatosTextArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Agenda de Contatos");

        //revisar antes de enviar
        BorderPane layout = new BorderPane();

        
        VBox entradaDados = new VBox(10);
        entradaDados.setPadding(new Insets(10, 10, 10, 10));

        Label nomeLabel = new Label("Nome:");
        nomeField = new TextField();
        Label telefoneLabel = new Label("Telefone:");
        telefoneField = new TextField();
        Label nascimentoLabel = new Label("Nascimento:");
        nascimentoField = new TextField();
        Label emailLabel = new Label("Email:");
        emailField = new TextField();
        Label celularLabel = new Label("Celular:");
        celularField = new TextField();

        Button adicionarButton = new Button("Adicionar Contato");
        adicionarButton.setOnAction(e -> adicionarContato());

        entradaDados.getChildren().addAll(
                nomeLabel, nomeField, telefoneLabel, telefoneField,
                nascimentoLabel, nascimentoField, emailLabel, emailField,
                celularLabel, celularField, adicionarButton);

        // lista de contato para interface
        listaContatosTextArea = new TextArea();
        listaContatosTextArea.setEditable(false);
        listaContatosTextArea.setWrapText(true);
        listaContatosTextArea.setPrefHeight(300);

        // botão para listar contatos
        Button listarContatosButton = new Button("Listar Contatos");
        listarContatosButton.setOnAction(e -> listarContatos());

        // Painel de botões
        VBox botoes = new VBox(10);
        botoes.setPadding(new Insets(10, 10, 10, 10));
        botoes.getChildren().addAll(listarContatosButton);

        // Adiciona os componentes ao layout principall
        layout.setLeft(entradaDados);
        layout.setCenter(listaContatosTextArea);
        layout.setRight(botoes);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void adicionarContato() {
        String nome = nomeField.getText();
        String telefone = telefoneField.getText();
        String nascimento = nascimentoField.getText();
        String email = emailField.getText();
        String celular = celularField.getText();

        Contato novoContato = new Contato(nome, telefone, nascimento, email, celular);
        contatos.put(nome, novoContato);

        nomeField.clear();
        telefoneField.clear();
        nascimentoField.clear();
        emailField.clear();
        celularField.clear();
    }

    private void listarContatos() {
        StringBuilder lista = new StringBuilder();
        for (Contato contato : contatos.values()) {
            lista.append(contato.toString()).append("\n");
        }
        listaContatosTextArea.setText(lista.toString());
    }

    public static class Contato {
        private String nome;
        private String telefone;
        private String nascimento;
        private String email;
        private String celular;

        public Contato(String nome, String telefone, String nascimento, String email, String celular) {
            this.nome = nome;
            this.telefone = telefone;
            this.nascimento = nascimento;
            this.email = email;
            this.celular = celular;
        }

        public String toString() {
            return "Nome: " + nome + "\nTelefone: " + telefone + "\nNascimento: " + nascimento +
                    "\nEmail: " + email + "\nCelular: " + celular;
        }
    }
}
