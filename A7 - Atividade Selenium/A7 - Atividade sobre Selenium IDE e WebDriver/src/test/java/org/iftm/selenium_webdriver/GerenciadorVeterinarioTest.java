package org.iftm.selenium_webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GerenciadorVeterinarioTest {

    private WebDriver driver;
    // Ajuste a URL base de acordo com a porta e rota do seu sistema base
    private final String BASE_URL = "http://localhost:8080/veterinarios"; 

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    public void testCadastrarVeterinario() {
        // CENÁRIO: Acessar a página de formulário de cadastro
        driver.get("http://localhost:8080/addVeterinarioForm"); 

        // AÇÃO: Preencher os textfields e acionar o botão de cadastro
        WebElement textoNome = driver.findElement(By.id("nome"));
        textoNome.sendKeys("Dr. João Silva");
        
        driver.findElement(By.id("especialidade")).sendKeys("Animais de Grande Porte");
        driver.findElement(By.id("salario")).sendKeys("5000");
        
        // Utilizando a dica de XPath para acessar o botão
        WebElement botaoCadastrar = driver.findElement(By.xpath("//button[contains(.,'Cadastrar')]"));
        botaoCadastrar.click();

        // VERIFICAÇÃO: Redirecionamento e uso de assert text para verificar a tabela
        WebElement tabela = driver.findElement(By.tagName("table"));
        assertTrue(tabela.getText().contains("Dr. João Silva"), "O veterinário cadastrado deve aparecer na tabela (assert text).");
    }

    @Test
    @Order(2)
    public void testListarVeterinario() {
        // CENÁRIO: Acessar a página principal/listagem de veterinários
        driver.get(BASE_URL);

        // AÇÃO: Localizar a tabela de listagem na página
        WebElement tabela = driver.findElement(By.tagName("table"));

        // VERIFICAÇÃO: Validar se a tabela contém texto indicando que está povoada (assert text)
        assertTrue(tabela.getText().length() > 0, "A tabela deve exibir os veterinários listados (assert text).");
    }

    @Test
    @Order(3)
    public void testPesquisarVeterinario() {
        // CENÁRIO: Acessar a página com o campo de pesquisa
        driver.get(BASE_URL);

        // AÇÃO: Inserir o nome no textfield e pesquisar
        WebElement textoPesquisa = driver.findElement(By.id("inputPesquisa"));
        textoPesquisa.sendKeys("João Silva");
        
        driver.findElement(By.xpath("//button[contains(.,'Pesquisar')]")).click();

        // VERIFICAÇÃO: Utilizando a dica de acessar elemento específico da tabela para assert text
        WebElement linha1Coluna1 = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
        assertTrue(linha1Coluna1.getText().contains("João Silva"), "A pesquisa deve retornar o veterinário esperado (assert text).");
    }

    @Test
    @Order(4)
    public void testAlterarVeterinario() {
        // CENÁRIO: Acessar a edição de um veterinário existente
        driver.get(BASE_URL);
        
        // AÇÃO: Acessar o botão de edição e alterar um valor de textfield
        driver.findElement(By.xpath("//a[contains(.,'Editar')]")).click(); 
        
        WebElement inputSalario = driver.findElement(By.id("salario"));
        inputSalario.clear(); 
        inputSalario.sendKeys("6000"); 

        // VERIFICAÇÃO: Utilizar assert value para verificar os valores presentes no formulário antes de salvar
        String valorNoFormulario = inputSalario.getAttribute("value");
        assertEquals("6000", valorNoFormulario, "O valor do formulário deve ter sido atualizado (assert value).");

        driver.findElement(By.xpath("//button[contains(.,'Salvar')]")).click();
    }

    @Test
    @Order(5)
    public void testExcluirVeterinario() {
        // CENÁRIO: Acessar a listagem que contém o veterinário a ser excluído
        driver.get(BASE_URL);

        // AÇÃO: Acessar o botão de exclusão
        driver.findElement(By.xpath("//a[contains(.,'Excluir')]")).click();

        // VERIFICAÇÃO: Utilizar assert text para confirmar a ausência do registro na tabela
        WebElement body = driver.findElement(By.tagName("body"));
        assertTrue(!body.getText().contains("Dr. João Silva"), "O veterinário excluído não deve constar na tabela (assert text).");
    }
}