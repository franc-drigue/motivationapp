package com.example.motivation.data

import com.example.motivation.infra.Consts
import kotlin.random.Random

data class Phrase(val description: String, val category: Int);

class Mock{
    private val all = Consts.CATEGORY.ALL
    private val happy = Consts.CATEGORY.HAPPY
    private val sum = Consts.CATEGORY.SUM

    private val listPhrases: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", happy),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", happy),
        Phrase("Quando está mais escuro, vemos mais estrelas!", happy),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", happy),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", happy),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", happy),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sum),
        Phrase("Você perde todas as chances que você não aproveita.", sum),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", sum),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", sum),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", sum),
        Phrase("Se você acredita, faz toda a diferença.", sum),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", sum)
    );

    fun getPhrase(value: Int): String {
        val filtered = listPhrases.filter { (it.category == value || value == all) }

        val rand = Random.nextInt(filtered.size);

        return filtered[rand].description
    }
}