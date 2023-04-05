import java.io.File;
import java.io.InputStream;

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
import javax.imageio.ImageIO;

public class criadorDeFigurinhas {


    public void cria ( InputStream inputStream, String nomeArquivo ,String texto , InputStream inputStreamimagemFilme) throws Exception{

        //leitura da imagem
        //URL inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg");


      BufferedImage imagemOriginal = ImageIO.read(inputStream);
      

        // criar nova imagem em memória com transparência e novo tamanho

        int largura  = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra nova imagem (em memória)

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        BufferedImage imagemFilme =   ImageIO.read(inputStreamimagemFilme);
        int sobreposicaoy = novaAltura - imagemFilme.getHeight();
        graphics.drawImage(imagemFilme, 0, sobreposicaoy, null);


        //configurar fonte

       var fonte =  new Font("impact", Font.BOLD, 120);
       graphics.setColor(Color.red);
       graphics.setFont(fonte);


        // escreve uma frase na nova imagem

        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int)retangulo.getWidth();
        int posicaoTextox = (largura - larguraTexto)/2;
        int  posicaoTextoy = novaAltura - 100;
        graphics.drawString(texto, posicaoTextox, posicaoTextoy);



        FontRenderContext FontRenderContext = graphics.getFontRenderContext();
         var TextLayout = new TextLayout(texto, fonte, FontRenderContext);


        Shape outline =  TextLayout.getOutline(null);

        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextox, posicaoTextoy);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);
        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);








        graphics.drawString(texto, posicaoTextox, novaAltura - 100);


        // escreve nova imagem no araquivo

        ImageIO.write(novaImagem, "png",  new File(nomeArquivo));
    }

}
