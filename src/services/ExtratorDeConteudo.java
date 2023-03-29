package services;

import java.util.List;

import model.entities.Conteudo;

public interface ExtratorDeConteudo {
    List<Conteudo> extraiConteudos(String json);
}
