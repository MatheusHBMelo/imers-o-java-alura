package model.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class CriaSticker {

    public void createImage(InputStream inputStream, String textoImage, String nomeDoArquivo) throws IOException{

    // Leitura da imagem - Serve tanto para link, quanto para diretorio local.
    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    // Cria uma nova imagem com transparência e redimencionada
    int larguraOriginal = imagemOriginal.getWidth();
    int alturaOriginal = imagemOriginal.getHeight();
    int novaAltura = alturaOriginal + 200;
    BufferedImage novaImagem = new BufferedImage(larguraOriginal, novaAltura, BufferedImage.TRANSLUCENT);

    // Faz um copia da imagem original como molde para a nova imagem (em memória)
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    // Configura aspectos de font, cor e tamanho
    Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(fonte);

    // Adiciona texto sobre a nova imagem
    graphics.drawString(textoImage, 235, novaAltura - 70);

    // Escreve a nova imagem em um arquivo
    ImageIO.write(novaImagem, "png", new File("out/" + nomeDoArquivo));
    }
    
    /* 
    public static void main(String[] args) throws IOException {
        // Cria figurinha manuais (fora da lista recebida da API)
        CriaSticker sticker = new CriaSticker();
        InputStream inputStream = new URL("https://cinema10.com.br/upload/featuredImage.php?url=https%3A%2F%2Fcinema10.com.br%2Fupload%2Fseries%2Fseries_3652_the-night-agent.jpg").openStream();
        sticker.createImage(inputStream, "O agente noturno", "Agente.png");
    }
    */
}
