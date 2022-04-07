package br.com.etecia.atividadecep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarCep;
    EditText txtCep;
    TextView respostalogradouro;
    TextView respostacomplemento;
    TextView respostabairro;
    TextView respostacidade;
    TextView respostauf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCep = findViewById(R.id.txtCep);
        respostalogradouro = findViewById(R.id.respostalogradouro);
        respostacomplemento = findViewById(R.id.respostacomplemento);
        respostabairro = findViewById(R.id.respostabairro);
        respostacidade = findViewById(R.id.respostacidade);
        respostauf = findViewById(R.id.respostauf);
        btnBuscarCep = findViewById(R.id.btnBuscaCep);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String endereco = txtCep.getText().toString().trim();

                try {
                    //preencher o cep no lblResposta do layout
                    CEP retorno = new HttpService(txtCep.getText().toString().trim()).execute().get();
                    respostalogradouro.setText("Logradouro: "+retorno.getLogradouro());
                    respostacomplemento.setText("Complemento: "+retorno.getComplemento());
                    respostabairro.setText("Bairro: "+retorno.getBairro());
                    respostacidade.setText("Cidade: "+retorno.getLocalidade());
                    respostauf.setText("uf: "+retorno.getUf());

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}