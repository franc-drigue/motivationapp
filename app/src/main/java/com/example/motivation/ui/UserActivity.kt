package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.motivation.R
import com.example.motivation.databinding.ActivityUserBinding
import com.example.motivation.infra.Consts
import com.example.motivation.infra.SecurityPreferences

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.buttonName.setOnClickListener(this)
    }

    // Ao clicar no botão para salvar o nome a função handleSave() é chamada.
    override fun onClick(view: View) {
        if (view.id == R.id.button_name) {
            handleSave();
        }
    }

    // Função handleSave(), responsável em pegar o valor digitado pelo o usuário
    // Salvar no SharedPreferences, navegar para a próxima página e destruir a Activity em seguida
    // Senão, caso o usuário não informe nada no EditText é lançado uma notificação
    private fun handleSave() {
        val name = binding.editName.text.toString().trim();
        if (name != "") {
            SecurityPreferences(this).storeString(Consts.KEY.KEY_NAME, name);
            startActivity(Intent(this, MainActivity:: class.java));
            finish();
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_LONG).show();
        }
    }

    /** Função que verifica se existe algum valor no SharedPreferences,
    se existir não a necessidade de preencher novamente, então é feito a
    navegação para a MainActivity e então a UserActivity é destruída
     **/
    /**private fun verifyName() {
        val name = SecurityPreferences(applicationContext).getStoreString(Consts.KEY.KEY_NAME);
        if (name != "") {
            startActivity(Intent(this, MainActivity:: class.java));
            finish();
        }
    }**/

}