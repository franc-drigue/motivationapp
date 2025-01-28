package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.infra.Consts
import com.example.motivation.infra.SecurityPreferences
import java.util.Locale

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding;
    // -> Variável criada para controlar a categoria escolhida
    private var categoryId = Consts.CATEGORY.ALL;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        // -> Chamada da função que verifica qual categoria foi selecionada
        handleFilter(R.id.all); // -> De inicio é passado a primeira categoria

        // -> Chamada da função que busca as frases de acordo com a categoria passada para a classe Mock(),
        /** A função também é chamada aqui na inicialização da Activity para não ser
         renderizado o valor estático que está definido no layout**/
        handleFilteredCategory();

        binding.buttonNewPhrase.setOnClickListener(this);
        binding.all.setOnClickListener(this);
        binding.happy.setOnClickListener(this);
        binding.sum.setOnClickListener(this);
        binding.name.setOnClickListener(this);
    }



    override fun onClick(view: View) {
       if (view.id == R.id.button_new_phrase){
           /** Ao clicar no botão para gerar uma nova frase,
            função handleFilterCategory é chamada, trazendo uma frase aleatória
            a cada clique, de acordo com categoria passda na função getPhrase da
            Classe Mock() **/
           handleFilteredCategory();
       }else if (view.id in listOf(R.id.all, R.id.happy, R.id.sum)) {
           /** Ao clicar em umas dos ícones é passado par a função
            handleFilter o id específico clicado **/
           handleFilter(view.id);
       }else if(view.id == R.id.name) {
           startActivity(Intent(this, UserActivity:: class.java));
       }
    }


    /** O onResume() é umas das funções que faz parte do cliclo de vida da
     Activity, assim como o onCreate()
     após a execução desse método a UI já está renderizada para o usuário
     e pronta para interação
     **/
    override fun onResume() {
        super.onResume();
        // -> Chamada da função que busca e verifica se já existe tem um valor no SharedPreferences
        handleUserName();
    }


    // função que vai ser chamada quando o botão "nova frase" for clicado
    private fun handleFilteredCategory() {
         /**
           elemento TextView que recebe as frases alatória com base na categoria passada
           para a Classe Mock()
         **/

        binding.phrases.text = Mock().getPhrase(categoryId, Locale.getDefault().language);
    }

    /** Função que recebe por parâmetro o id das opções de categoria **/
    private fun handleFilter(id:Int){
        binding.all.setColorFilter(ContextCompat.getColor(this, R.color.darkMagenta));
        binding.sum.setColorFilter(ContextCompat.getColor(this, R.color.darkMagenta));
        binding.happy.setColorFilter(ContextCompat.getColor(this, R.color.darkMagenta));

        when (id) {
            R.id.all -> {
                binding.all.setColorFilter(ContextCompat.getColor(this, R.color.white));
                categoryId = Consts.CATEGORY.ALL;
            }
            R.id.happy -> {
                binding.happy.setColorFilter(ContextCompat.getColor(this, R.color.white));
                categoryId = Consts.CATEGORY.HAPPY;
            }
            R.id.sum -> {
                binding.sum.setColorFilter(ContextCompat.getColor(this, R.color.white));
                categoryId = Consts.CATEGORY.SUM;
            }
        }

    }

    // função que pega o valor salvo no SharedPreferences e passa para o TextView(name)
    private fun handleUserName(){
        val name = SecurityPreferences(applicationContext).getStoreString(Consts.KEY.KEY_NAME);
        val hello = getString(R.string.hello)
        binding.name.text = "$hello, $name!";
    }

}