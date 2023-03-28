package application;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import model.entities.CriaSticker;
import util.JsonParser;

public class App {
    public static void main(String[] args) throws Exception {
        // Criando uma conexão HTTP e buscando o top 10 dos filmes;
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Filtrando os dados de interesse (Rank, Titulo, Ano, Avaliação Poster)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibindo os dados filtrados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[1mRank:\u001b[m #" + filme.get("rank"));
            System.out.println("\u001b[1mTitulo do filme:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[1mAno do filme:\u001b[m " + filme.get("year"));
            System.out.println("\u001b[38;2;255;255;255m\u001b[48;2;10;159;236mClassificação: " + filme.get("imDbRating") + "\u001b[m");
            double rate = Double.parseDouble(filme.get("imDbRating"));
            int classifi = (int) rate;
            for (int i = 1; i <= classifi; i++) {
                System.out.print("\u2B50");
            }
            System.out.println("\n\u001b[1mPoster do filme(link):\u001b[m " + filme.get("image"));
            System.out.println("");
            
            
            CriaSticker sticker = new CriaSticker();
            sticker.createImage(new URL(filme.get("image")).openStream(), filme.get("title"), filme.get("title") + ".png");
        }
    }
}
