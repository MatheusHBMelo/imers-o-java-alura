package model.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class CriaSticker {
    public void createImage(InputStream inputStream, String textoImage, String nomeDoArquivo) throws IOException{

    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    int larguraOriginal = imagemOriginal.getWidth();
    int alturaOriginal = imagemOriginal.getHeight();
    int novaAltura = alturaOriginal + 200;
    BufferedImage novaImagem = new BufferedImage(larguraOriginal, novaAltura, BufferedImage.TRANSLUCENT);

    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    Font fonte = new Font("Impact", Font.BOLD, 70);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(fonte);

    String texto = textoImage;
    FontMetrics fontMetrics = graphics.getFontMetrics();
    Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
    int larguraTexto = (int) retangulo.getWidth();
    int posicaoTextoX = (larguraOriginal - larguraTexto) / 2;
    int posicaoTextoY = novaAltura - 80;
    graphics.drawString(texto, posicaoTextoX, posicaoTextoY);

    FontRenderContext fontRenderContext = graphics.getFontRenderContext();
    TextLayout textLayout = new TextLayout(texto, fonte, fontRenderContext);
    Shape outline = textLayout.getOutline(null);
    AffineTransform transform = graphics.getTransform();
    transform.translate(posicaoTextoX, posicaoTextoY);
    graphics.setTransform(transform);

    BasicStroke outlineStroke = new BasicStroke(larguraOriginal * 0.004f);
    graphics.setStroke(outlineStroke);
    graphics.setColor(Color.BLACK);
    graphics.draw(outline);
    graphics.setClip(outline);

    File file = new File(nomeDoArquivo);
    this.checkDirectory(file);
    ImageIO.write(novaImagem, "png", new File(nomeDoArquivo));
    }
    
    private void checkDirectory (File file){
        if (!file.exists()){
            file.mkdirs();
        }
    }
}
