package com.example.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.infra.Consts
import com.example.motivation.infra.SecurityPreferences

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding;
    private var categoryId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        handleUserName();
        handleFilter(R.id.all);
        handleFilteredCategory()

        binding.buttonNewPhrase.setOnClickListener(this);
        binding.all.setOnClickListener(this);
        binding.happy.setOnClickListener(this);
        binding.sum.setOnClickListener(this);
    }


    override fun onClick(view: View) {
       if (view.id == R.id.button_new_phrase){
           handleFilteredCategory()
       }else if (view.id in listOf(R.id.all, R.id.happy, R.id.sum)) {
           handleFilter(view.id)
       }
    }

    private fun handleFilteredCategory() {
        binding.phrases.text = Mock().getPhrase(categoryId);
    }

    private fun handleFilter(id:Int){
        binding.all.setColorFilter(ContextCompat.getColor(this, R.color.darkMagenta));
        binding.sum.setColorFilter(ContextCompat.getColor(this, R.color.darkMagenta));
        binding.happy.setColorFilter(ContextCompat.getColor(this, R.color.darkMagenta));

        when (id) {
            R.id.all -> {
                binding.all.setColorFilter(ContextCompat.getColor(this, R.color.white));
                categoryId = Consts.CATEGORY.ALL
            }
            R.id.happy -> {
                binding.happy.setColorFilter(ContextCompat.getColor(this, R.color.white));
                categoryId = Consts.CATEGORY.HAPPY
            }
            R.id.sum -> {
                binding.sum.setColorFilter(ContextCompat.getColor(this, R.color.white));
                categoryId = Consts.CATEGORY.SUM
            }
        }

    }

    private fun handleUserName(){
        val name = SecurityPreferences(applicationContext).getStoreString(Consts.KEY.KEY_NAME);
        binding.name.text = "Olá, $name";
    }

}