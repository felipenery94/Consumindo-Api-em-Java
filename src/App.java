
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
    

    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";


    var http = new ClienteHttp();
    String json =  http.buscaDados(url);



        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new jsonParser();
        List<Map<String, String>> listaDeConteudos = parser.parse(json);

        // exibir e manipular os dados 

        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        var geradora = new criadorDeFigurinhas();
        for (Map <String , String> conteudo : listaDeConteudos){

            String urlImagem = conteudo.get("image");

            String titulo = conteudo.get("title");
            double classificacao = Double.parseDouble(conteudo.get("imDbRating"));

            String textoFigurinha;

            InputStream imagemFilme ;

            if (classificacao >= 8.0){
                textoFigurinha = "Toppzera";
                imagemFilme = new FileInputStream(new File("sobreposicao/positivo.jpg"));
            }
            else{
                textoFigurinha =  "HMMMM";
                imagemFilme = new FileInputStream(new File("sobreposicao/negativo.jpg"));
                };
                


            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "figurinhas/" + titulo + ".png";

            geradora.cria(inputStream , nomeArquivo , textoFigurinha , imagemFilme);

            System.out.println(titulo);
            //System.out.println("\u001b[31;4mURL da imagem :\u001b[m" +filme.get("image"));
            //System.out.println(filme.get("imDbRating"));
            //double classificacao = Double.parseDouble(filme.get("imDbRating"));
            //int numeroEstrelas = (int) classificacao;
            //for(int n = 1 ; n <= numeroEstrelas; n++){
                //System.out.print("*");

            //}
            System.out.println("\n");
    
        }
    }
} 

