package model.enums;

import services.*;

public enum Api {
    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ExtratorConteudoIMDB()),
    IMDB_TOP_SERIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json", new ExtratorConteudoIMDB()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14", new ExtratorDeConteudoDaNasa());

    private String url;
    private ExtratorDeConteudo extratorDeConteudo;

    Api(String url, ExtratorDeConteudo extratorDeConteudo){
        this.url = url;
        this.extratorDeConteudo = extratorDeConteudo;
    }

    public String getUrl() {
        return url;
    }

    public ExtratorDeConteudo getExtratorDeConteudo() {
        return extratorDeConteudo;
    }
}
