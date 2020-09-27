# Tutorial para iniciantes em Spring Boot
Nesse projeto construimos uma api REST utilizando o framework Spring Boot para pessoas que não tiveram contato com API's,
sendo assim, foi construido uma aplicação para cadastro, atualização, busca e deleção(CRUD) de um documento.

## Configurando o ambiente Java no Ubuntu
Abaixo estão as informações para configurar o ambiente Java.
### Instalando o Java
```
sudo apt install openjdk-11-jdk
sudo apt install openjdk-11-jre
```

### Verificando a versão do Java
```
java -version
```

### Verificando e alterando a versão do Java instalado
```
sudo update-alternatives --config java
```

### Adicionando a variável de ambiente JAVA_HOME
```
sudo nano /etc/environment
```
Ao abrir o editor Nano, adicione ao final do arquivo a seguinte linha:

```
JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64/bin/"
```

Reinicie o terminal e digite:
```
echo $JAVA_HOME
```
Caso a saída seja "/"usr/lib/jvm/java-11-openjdk-amd64/bin/" a instalação foi feita corretamente.

## Iniciando o projeto em Spring Boot
Entre em https://start.spring.io/ para criar um projeto com as seguintes configurações: 
<img src="https://raw.githubusercontent.com/matheuscosantos/tutorial-spring-boot-para-iniciantes/master/imagens/initializr.png" width="70%">

Abra o IntelliJ e adicione o plugin Lombok para redução de boilerplate
<img src="https://raw.githubusercontent.com/matheuscosantos/tutorial-spring-boot-para-iniciantes/master/imagens/instalando_plugin.png" width="70%">

<img src="https://raw.githubusercontent.com/matheuscosantos/tutorial-spring-boot-para-iniciantes/master/imagens/instalando_lombok.png" width="70%">


## Adicionando o banco de dados H2
Nesse projeto utilizamos o banco de dados em memória chamado H2, sempre que a aplicação reiniciar os dados serão apagados, lembrando que esse banco é utilizado para testes.
Caso queira adicionar outro banco de dados como o Postgres ou Mysql pesquise sobre as especificificações para adicionar no application.properties.
Em src/main/resources/application.properties adicione:
```
spring.datasource.url=jdbc:h2:mem:TEST;DB_CLOSE_DELAY=-1;
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.platform=h2
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

Para acessar o console do H2 acesse a URL:

http://localhost:8080/h2-console

Conforme os atríbutos atribuidos no application.properties adicione as seguintes informações:

<img src="https://raw.githubusercontent.com/matheuscosantos/tutorial-spring-boot-para-iniciantes/master/imagens/console-h2.png" width="60%"> 

## Camadas do projeto
### Model
É a camada para adição das entidades da aplicação.
### Repository
É a camada que implementa o JPA para persistência dos dados no banco.
### Resource
É a camada para exposições das URL's da API.
### Árvore de diretórios

<img src="https://raw.githubusercontent.com/matheuscosantos/tutorial-spring-boot-para-iniciantes/master/imagens/arvore.png" width="50%">

## Adicionando um Model
```java
@Entity
@Table(name="TB_PRODUTO")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Documento {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String titulo;
    private String autor;

    public Documento(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
}
```

## Adicionando um Repository
```java
@Repository
// Ao herdar o JpaRepository deve-se passar a classe do modelo(Documento) e o tipo do seu atríbuto id(Integer).
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
    Optional<Documento> findById(Integer id);
}
```

## Adicionando um Controller
```java
@RestController
@RequestMapping(value="/api")
class ProdutoResource {
    @Autowired
    DocumentoRepository documentoRepository;

    @PostMapping("/documento")
    public Documento salva(@RequestBody Documento documento) {
        return documentoRepository.save(documento);
    }

    @GetMapping("/documento/{id}")
    public Optional<Documento> buscaPorId(@PathVariable(value="id") Integer id){
        return documentoRepository.findById(id);
    }

    @DeleteMapping("/documento/{id}")
    public void apagaPorId(@PathVariable(value="id") Integer id){
        Optional<Documento> documento = documentoRepository.findById(id);
        if (documento.isPresent()){
            documentoRepository.delete(documento.get());
        }
    }

    @PutMapping("/documento/{id}")
    public Documento atualiza(@PathVariable(value="id") Integer id,
                              @RequestBody Documento documento){
        Optional<Documento> documentoAntigo = documentoRepository.findById(id);
        if (documentoAntigo.isPresent()){
            documentoAntigo.get().setTitulo(documento.getTitulo());
            documentoAntigo.get().setAutor(documento.getAutor());
            return documentoRepository.save(documentoAntigo.get());
        }
        return documentoAntigo.get();
    }

    @GetMapping("/documentos")
    public List<Documento> lista(){
        return documentoRepository.findAll();
    }
}
```

Tutorial em construção