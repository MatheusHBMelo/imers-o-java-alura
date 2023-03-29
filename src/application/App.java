package application;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import model.entities.Conteudo;
import model.entities.CriaSticker;
import model.enums.Api;
import model.factory.ClienteHttp;
import services.ExtratorDeConteudo;

public class App {
    public static void main(String[] args) throws Exception {
        Api api = Api.NASA;
        String url = api.getUrl();

        ExtratorDeConteudo extrator = api.getExtratorDeConteudo();
        
        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        CriaSticker geradora = new CriaSticker();

        for (int i = 0; i < conteudos.size(); i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "out/" + conteudo.titulo() + ".png";

            geradora.createImage(inputStream, conteudo.titulo(), nomeArquivo);

            System.out.println(conteudo.titulo());
            System.out.println("");
        }
    }
}
