package services;

import java.util.List;
import java.util.Map;

import model.entities.Conteudo;
import util.JsonParser;

public class ExtratorConteudoIMDB implements ExtratorDeConteudo {
    public List<Conteudo> extraiConteudos(String json){
        // Filtrando os dados de interesse (Rank, Titulo, Ano, Avaliação Poster)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        return listaDeAtributos.stream().map(atributos -> new Conteudo(atributos.get("title"), atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg")))
        .toList();
    }
}
