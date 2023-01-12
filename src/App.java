import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {      
       
        //fazer uma conexão HTTp e buscar os top 250 filmes
        String url = "https://imdb-api.com/en/API/MostPopularMovies/k_2xt9vcck";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);

        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser =  new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
   
        // exibir e os dosdos
        for (Map<String,String> filme : listaDeFilmes) {

            System.out.println("\u001b[1mTítulo: "+filme.get("title"));
            System.out.println("Poster: "+filme.get("image"));
            System.out.println("Classificação: "+filme.get("imDbRating"));   
            System.out.println();    
        }
    }
}
