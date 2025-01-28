package com.example.motivation.data

import com.example.motivation.infra.Consts
import kotlin.random.Random

data class Phrase(val description: String, val category: Int, val lang: String);

class Mock{
    private val all = Consts.CATEGORY.ALL;
    private val happy = Consts.CATEGORY.HAPPY;
    private val sum = Consts.CATEGORY.SUM;

    private val langBr = Consts.LANGUAGE.PORTUGUESE;
    private val langEn = Consts.LANGUAGE.ENGLISH;

    private val listPhrases: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", happy, langBr),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", happy, langBr),
        Phrase("Quando está mais escuro, vemos mais estrelas!", happy , langBr),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", happy, langBr),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", happy, langBr),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", happy, langBr),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sum, langBr),
        Phrase("Você perde todas as chances que você não aproveita.", sum, langBr),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", sum, langBr),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", sum, langBr),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", sum, langBr),
        Phrase("Se você acredita, faz toda a diferença.", sum, langBr),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", sum, langBr),

        Phrase("Not knowing it was impossible, he went there and did it.", happy, langEn),
        Phrase("You are not defeated when you lose, you are defeated when you give up!", happy, langEn),
        Phrase("When it is darkest, we see more stars!", happy , langEn),
        Phrase("Insanity is doing the same thing over and over again and expecting a different result.", happy, langEn),
        Phrase("Don't stop when you're tired, stop when you're done.", happy, langEn),
        Phrase("What can you do now that has the greatest impact on your success?", happy, langEn),
        Phrase("The best way to predict the future is to invent it.", sum, langEn),
        Phrase("You lose all the chances you don’t take.", sum, langEn),
        Phrase("Failure is the spice that gives flavor to success.", sum, langEn),
        Phrase("Until we are committed, there will be hesitation!", sum, langEn),
        Phrase("If you don’t know where you’re going, any road will take you there.", sum, langEn),
        Phrase("If you believe, it makes all the difference.", sum, langEn),
        Phrase("Risks should be taken, because the greatest danger is not taking any!", sum, langEn)

    );

    fun getPhrase(value: Int, lang: String): String {
        var languageFilter = lang.lowercase()

        if(lang !in listOf(langBr, langEn)) {
            languageFilter = langBr
        }

        val filtered = listPhrases.filter {
            (it.category == value || value == all) && it.lang == languageFilter
        };

        val rand = Random.nextInt(filtered.size);

        return filtered[rand].description;
    }
}